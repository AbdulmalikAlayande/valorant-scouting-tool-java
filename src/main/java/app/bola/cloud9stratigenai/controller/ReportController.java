package app.bola.esportsscoutingtool.controller;

import app.bola.esportsscoutingtool.dto.ReportStatusResponse;
import app.bola.esportsscoutingtool.dto.ScoutingReportResponse;
import app.bola.esportsscoutingtool.service.ReportSubmissionService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.CompletableFuture;


@RestController
@AllArgsConstructor
@RequestMapping("api/reports")
public class ReportController {
	
	private static final Logger log = LoggerFactory.getLogger(ReportController.class);
	
	final ReportService reportService;
	
	@PostMapping("/submit")
	public ResponseEntity<@NonNull ReportStatusResponse> submitReport(@RequestBody Object reportRequest) {
		log.info("Received report submission: {}", reportRequest);
		return ResponseEntity.ok().body(reportService.generateReport(reportRequest));
	}
	
	@GetMapping("/{id}/status")
	public ResponseEntity<@NonNull ReportStatusResponse> getReportStatus(@PathVariable String id) {
		log.info("Received report status request for report id: {}", id);
		return ResponseEntity.ok().body(reportService.getReportStatus(id));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<@NonNull ScoutingReportResponse> getReport(@PathVariable String id) {
		log.info("Received report request for report id: {}", id);
		return ResponseEntity.ok().body(reportService.getReport(id));
	}
	
}
