package app.bola.cloud9stratigenai.service;

import app.bola.cloud9stratigenai.dto.GenerateReportRequest;
import app.bola.cloud9stratigenai.dto.ReportStatusResponse;
import app.bola.cloud9stratigenai.dto.ScoutingReportResponse;

public interface ReportService {
	
	ReportStatusResponse generateReport(GenerateReportRequest request);

	ReportStatusResponse getReportStatus(String requestId);

	ScoutingReportResponse getReport(String requestId);
}
