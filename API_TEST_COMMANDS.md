# API Test Commands

This file contains curl commands to test all endpoints of the Scouting API.

**Base URL:** `http://localhost:8080`

**Note:** The server runs on port 8080 by default. Adjust the base URL if your server is running on a different port or host.

## Important: PowerShell vs Bash

- **Bash/Linux/Mac**: Use backslashes (`\`) for line continuation
- **PowerShell (Windows)**: Use backticks (`` ` ``) for line continuation, or use single-line commands
- **PowerShell Alternative**: Use `Invoke-RestMethod` or `Invoke-WebRequest` (see PowerShell section below)

## Quick Reference - PowerShell Single-Line Commands

For quick testing in PowerShell, here are single-line commands you can copy-paste:

**Health Check:**
```powershell
curl -X GET http://localhost:8080/api/health/check -H "Content-Type: application/json"
```

**Generate Report:**
```powershell
curl -X POST http://localhost:8080/api/reports/generate -H "Content-Type: application/json" -d '{"userPrompt": "Analyze Cloud9 Valorant team performance in the last tournament, focusing on map control and agent selection strategies."}'
```

**Get Report Status (replace YOUR_REQUEST_ID):**
```powershell
curl -X GET http://localhost:8080/api/reports/YOUR_REQUEST_ID/status -H "Content-Type: application/json"
```

**Get Completed Report (replace YOUR_REQUEST_ID):**
```powershell
curl -X GET http://localhost:8080/api/reports/YOUR_REQUEST_ID -H "Content-Type: application/json"
```

---

## 1. Health Check Endpoint

### GET /api/health/check
Check if the API is running and healthy.

**Bash/Linux/Mac:**
```bash
curl -X GET http://localhost:8080/api/health/check \
  -H "Content-Type: application/json"
```

**PowerShell (Windows):**
```powershell
curl -X GET http://localhost:8080/api/health/check `
  -H "Content-Type: application/json"
```

**PowerShell Single-Line:**
```powershell
curl -X GET http://localhost:8080/api/health/check -H "Content-Type: application/json"
```

**Expected Response:**
```json
{
  "status": "UP",
  "timestamp": 1234567890123
}
```

---

## 2. Report Endpoints

### 2.1. Generate Report Request

#### POST /api/reports/generate
Submit a new report generation request.

**Request Body:**
- `userPrompt` (string, required): The prompt for generating the report
  - Minimum length: 10 characters
  - Maximum length: 500 characters

**Bash/Linux/Mac:**
```bash
curl -X POST http://localhost:8080/api/reports/generate \
  -H "Content-Type: application/json" \
  -d '{
    "userPrompt": "Analyze Cloud9 Valorant team performance in the last tournament, focusing on map control and agent selection strategies."
  }'
```

**PowerShell (Windows) - Multi-line:**
```powershell
curl -X POST http://localhost:8080/api/reports/generate `
  -H "Content-Type: application/json" `
  -d '{"userPrompt": "Analyze Cloud9 Valorant team performance in the last tournament, focusing on map control and agent selection strategies."}'
```

**PowerShell (Windows) - Single-line:**
```powershell
curl -X POST http://localhost:8080/api/reports/generate -H "Content-Type: application/json" -d '{"userPrompt": "Analyze Cloud9 Valorant team performance in the last tournament, focusing on map control and agent selection strategies."}'
```

**Example with longer prompt (Bash):**
```bash
curl -X POST http://localhost:8080/api/reports/generate \
  -H "Content-Type: application/json" \
  -d '{
    "userPrompt": "Create a comprehensive scouting report for Cloud9 Valorant team. Include analysis of their recent matches, preferred agents, map strategies, and recommendations for improvement. Focus on their performance in Bind and Haven maps."
  }'
```

**Example with longer prompt (PowerShell - Single-line):**
```powershell
curl -X POST http://localhost:8080/api/reports/generate -H "Content-Type: application/json" -d '{"userPrompt": "Create a comprehensive scouting report for Cloud9 Valorant team. Include analysis of their recent matches, preferred agents, map strategies, and recommendations for improvement. Focus on their performance in Bind and Haven maps."}'
```

**Expected Response:**
```json
{
  "requestId": "uuid-here",
  "status": "PENDING",
  "progress": 0,
  "currentStep": "Queued for processing",
  "reportAvailable": false,
  "createdAt": "2026-02-03T18:00:00",
  "error": null,
  "message": null,
  "completedAt": null
}
```

**Error Cases:**

Invalid prompt (too short):

**Bash:**
```bash
curl -X POST http://localhost:8080/api/reports/generate \
  -H "Content-Type: application/json" \
  -d '{"userPrompt": "Short"}'
```

**PowerShell:**
```powershell
curl -X POST http://localhost:8080/api/reports/generate -H "Content-Type: application/json" -d '{"userPrompt": "Short"}'
```

Invalid prompt (empty):

**Bash:**
```bash
curl -X POST http://localhost:8080/api/reports/generate \
  -H "Content-Type: application/json" \
  -d '{"userPrompt": ""}'
```

**PowerShell:**
```powershell
curl -X POST http://localhost:8080/api/reports/generate -H "Content-Type: application/json" -d '{"userPrompt": ""}'
```

---

### 2.2. Get Report Status

#### GET /api/reports/{id}/status
Get the status of a report generation request.

**Path Parameter:**
- `id` (string, required): The request ID returned from the generate endpoint

**Bash/Linux/Mac:**
```bash
# Replace {id} with the actual request ID from the generate response
curl -X GET http://localhost:8080/api/reports/{id}/status \
  -H "Content-Type: application/json"
```

**PowerShell (Windows):**
```powershell
# Replace {id} with the actual request ID from the generate response
curl -X GET http://localhost:8080/api/reports/{id}/status `
  -H "Content-Type: application/json"
```

**Example (Bash):**
```bash
curl -X GET http://localhost:8080/api/reports/550e8400-e29b-41d4-a716-446655440000/status \
  -H "Content-Type: application/json"
```

**Example (PowerShell):**
```powershell
curl -X GET http://localhost:8080/api/reports/550e8400-e29b-41d4-a716-446655440000/status -H "Content-Type: application/json"
```

**Expected Response (PENDING):**
```json
{
  "requestId": "550e8400-e29b-41d4-a716-446655440000",
  "status": "PENDING",
  "progress": 0,
  "currentStep": "Queued for processing",
  "reportAvailable": false,
  "createdAt": "2026-02-03T18:00:00",
  "error": null,
  "message": null,
  "completedAt": null
}
```

**Expected Response (PROCESSING):**
```json
{
  "requestId": "550e8400-e29b-41d4-a716-446655440000",
  "status": "PROCESSING",
  "progress": 50,
  "currentStep": "Analyzing team data",
  "reportAvailable": false,
  "createdAt": "2026-02-03T18:00:00",
  "error": null,
  "message": null,
  "completedAt": null
}
```

**Expected Response (COMPLETED):**
```json
{
  "requestId": "550e8400-e29b-41d4-a716-446655440000",
  "status": "COMPLETED",
  "progress": 100,
  "currentStep": "Report ready",
  "reportAvailable": true,
  "createdAt": "2026-02-03T18:00:00",
  "error": null,
  "message": null,
  "completedAt": "2026-02-03T18:05:00"
}
```

**Expected Response (FAILED):**
```json
{
  "requestId": "550e8400-e29b-41d4-a716-446655440000",
  "status": "FAILED",
  "progress": -1,
  "currentStep": "Processing failed",
  "reportAvailable": false,
  "createdAt": "2026-02-03T18:00:00",
  "error": "Processing failed",
  "message": null,
  "completedAt": null
}
```

**Error Case (Report Not Found):**

**Bash:**
```bash
curl -X GET http://localhost:8080/api/reports/non-existent-id/status \
  -H "Content-Type: application/json"
```

**PowerShell:**
```powershell
curl -X GET http://localhost:8080/api/reports/non-existent-id/status -H "Content-Type: application/json"
```

**Expected Error Response:**
```json
{
  "status": 404,
  "message": "non-existent-id",
  "timestamp": "2026-02-03T18:00:00"
}
```

---

### 2.3. Get Completed Report

#### GET /api/reports/{id}
Get the full scouting report (only available when status is COMPLETED).

**Path Parameter:**
- `id` (string, required): The request ID returned from the generate endpoint

**Bash/Linux/Mac:**
```bash
# Replace {id} with the actual request ID from the generate response
curl -X GET http://localhost:8080/api/reports/{id} \
  -H "Content-Type: application/json"
```

**PowerShell (Windows):**
```powershell
# Replace {id} with the actual request ID from the generate response
curl -X GET http://localhost:8080/api/reports/{id} `
  -H "Content-Type: application/json"
```

**Example (Bash):**
```bash
curl -X GET http://localhost:8080/api/reports/550e8400-e29b-41d4-a716-446655440000 \
  -H "Content-Type: application/json"
```

**Example (PowerShell):**
```powershell
curl -X GET http://localhost:8080/api/reports/550e8400-e29b-41d4-a716-446655440000 -H "Content-Type: application/json"
```

**Expected Response:**
```json
{
  "requestId": "550e8400-e29b-41d4-a716-446655440000",
  "reportType": "VALORANT_PRO",
  "reportTitle": "Scouting Report - VALORANT PRO",
  "summary": "Full text summary of the report.",
  "createdAt": "2026-02-03T18:05:00",
  "sections": [
    {
      "title": "Executive summary",
      "content": "\"This is a great team.\"",
      "order": 1
    },
    {
      "title": "Tactical analysis",
      "content": "\"Use more smokes.\"",
      "order": 2
    }
  ]
}
```

**Error Cases:**

Report Not Ready (Status is PENDING or PROCESSING):

**Bash:**
```bash
curl -X GET http://localhost:8080/api/reports/{pending-id} \
  -H "Content-Type: application/json"
```

**PowerShell:**
```powershell
curl -X GET http://localhost:8080/api/reports/{pending-id} -H "Content-Type: application/json"
```

**Expected Error Response:**
```json
{
  "status": 400,
  "message": "Report is not ready yet",
  "timestamp": "2026-02-03T18:00:00"
}
```

Report Not Found:

**Bash:**
```bash
curl -X GET http://localhost:8080/api/reports/non-existent-id \
  -H "Content-Type: application/json"
```

**PowerShell:**
```powershell
curl -X GET http://localhost:8080/api/reports/non-existent-id -H "Content-Type: application/json"
```

**Expected Error Response:**
```json
{
  "status": 404,
  "message": "non-existent-id",
  "timestamp": "2026-02-03T18:00:00"
}
```

---

## Complete Workflow Example

Here's a complete workflow to test the entire report generation process:

### Step 1: Generate a Report Request

**Bash:**
```bash
RESPONSE=$(curl -s -X POST http://localhost:8080/api/reports/generate \
  -H "Content-Type: application/json" \
  -d '{"userPrompt": "Analyze Cloud9 Valorant team performance in the last tournament, focusing on map control and agent selection strategies."}')

# Extract request ID (requires jq or manual extraction)
REQUEST_ID=$(echo $RESPONSE | jq -r '.requestId')
echo "Request ID: $REQUEST_ID"
```

**PowerShell:**
```powershell
$response = curl -s -X POST http://localhost:8080/api/reports/generate -H "Content-Type: application/json" -d '{"userPrompt": "Analyze Cloud9 Valorant team performance in the last tournament, focusing on map control and agent selection strategies."}'
$jsonResponse = $response | ConvertFrom-Json
$requestId = $jsonResponse.requestId
Write-Host "Request ID: $requestId"
```

### Step 2: Check Report Status

**Bash:**
```bash
# Poll the status endpoint until the report is completed
curl -X GET http://localhost:8080/api/reports/$REQUEST_ID/status \
  -H "Content-Type: application/json"
```

**PowerShell:**
```powershell
# Poll the status endpoint until the report is completed
curl -X GET http://localhost:8080/api/reports/$requestId/status -H "Content-Type: application/json"
```

### Step 3: Get the Completed Report

**Bash:**
```bash
# Once status is COMPLETED, fetch the full report
curl -X GET http://localhost:8080/api/reports/$REQUEST_ID \
  -H "Content-Type: application/json"
```

**PowerShell:**
```powershell
# Once status is COMPLETED, fetch the full report
curl -X GET http://localhost:8080/api/reports/$requestId -H "Content-Type: application/json"
```

---

## Pretty Print Responses

To format JSON responses for better readability, pipe the output to `jq`:

```bash
curl -X GET http://localhost:8080/api/reports/{id}/status \
  -H "Content-Type: application/json" | jq .
```

Or use Python:
```bash
curl -X GET http://localhost:8080/api/reports/{id}/status \
  -H "Content-Type: application/json" | python -m json.tool
```

---

## Windows PowerShell Alternative

If you're using Windows PowerShell, here are equivalent commands:

### Generate Report
```powershell
$body = @{
    userPrompt = "Analyze Cloud9 Valorant team performance in the last tournament, focusing on map control and agent selection strategies."
} | ConvertTo-Json

Invoke-RestMethod -Uri "http://localhost:8080/api/reports/generate" `
  -Method Post `
  -ContentType "application/json" `
  -Body $body
```

### Get Report Status
```powershell
$requestId = "your-request-id-here"
Invoke-RestMethod -Uri "http://localhost:8080/api/reports/$requestId/status" `
  -Method Get `
  -ContentType "application/json"
```

### Get Completed Report
```powershell
$requestId = "your-request-id-here"
Invoke-RestMethod -Uri "http://localhost:8080/api/reports/$requestId" `
  -Method Get `
  -ContentType "application/json"
```

---

## Notes

1. **Request ID Format**: The request ID is a UUID returned in the `requestId` field of the generate endpoint response.

2. **Status Values**: 
   - `PENDING`: Report request is queued
   - `PROCESSING`: Report is being generated
   - `COMPLETED`: Report is ready
   - `FAILED`: Report generation failed

3. **Validation**: 
   - User prompt must be between 10 and 500 characters
   - Empty prompts are rejected

4. **Error Handling**: All endpoints return appropriate HTTP status codes:
   - `200 OK`: Success
   - `400 Bad Request`: Validation errors or report not ready
   - `404 Not Found`: Report or request not found
   - `500 Internal Server Error`: Server errors

5. **Security**: The `/api/reports/**` endpoints are publicly accessible (no authentication required). The health check endpoint may require authentication depending on your security configuration.

