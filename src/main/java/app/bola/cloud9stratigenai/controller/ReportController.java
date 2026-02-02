package app.bola.esportsscoutingtool.controller;

import app.bola.esportsscoutingtool.dto.ReportStatusResponse;
import app.bola.esportsscoutingtool.dto.ScoutingReportResponse;
import app.bola.esportsscoutingtool.service.ReportSubmissionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;


@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("api/reports")
public class ReportController {
	
	final ReportSubmissionService reportSubmissionService;
	
	@PostMapping("/submit")
	public ResponseEntity<@NonNull ReportStatusResponse> submitReport(@RequestBody Object reportRequest) {
		log.info("Received report submission: {}", reportRequest);
		log.info(reportSubmissionService.toString());
		return ResponseEntity.ok().body(ReportStatusResponse.builder().build());
	}
	
	@GetMapping("/{id}/status")
	public ResponseEntity<@NonNull ReportStatusResponse> getReportStatus(@PathVariable String id) {
		log.info("Received report status request for report id: {}", id);
		return ResponseEntity.ok().body(ReportStatusResponse.builder().build());
	}
	
	@GetMapping("{id}")
	public CompletableFuture<ResponseEntity<@NonNull ScoutingReportResponse>> getReport(@PathVariable String id) {
		log.info("Received report request for report id: {}", id);
		return CompletableFuture.supplyAsync(() -> ResponseEntity.ok().body(ScoutingReportResponse.builder().build()));
	}
	
}
