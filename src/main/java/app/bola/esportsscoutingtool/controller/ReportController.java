package app.bola.esportsscoutingtool.controller;

import app.bola.esportsscoutingtool.service.ReportSubmissionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("api/reports")
public class ReportController {
	
	final ReportSubmissionService reportSubmissionService;
	
	@PostMapping("/submit")
	public ResponseEntity<Object> submitReport(@RequestBody Object reportRequest) {
		log.info("Received report submission: {}", reportRequest);
		log.info(reportSubmissionService.toString());
		return ResponseEntity.ok().body("Report submitted successfully");
	}
}
