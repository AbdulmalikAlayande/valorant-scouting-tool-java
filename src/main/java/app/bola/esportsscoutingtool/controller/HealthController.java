package app.bola.esportsscoutingtool.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@AllArgsConstructor
@RequestMapping("api/health")
public class HealthController {
	
	@GetMapping("/check")
	public Map<String, Object> checkHealth() {
		return Map.of("status", "UP", "timestamp", System.currentTimeMillis());
	}
}
