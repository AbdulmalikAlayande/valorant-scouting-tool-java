package app.bola.esportsscoutingtool.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ScoutingReportResponse {
	
	String reportData;
	Map<String, Object> reportType;
	String generatedReport;
	String publicId;
	LocalDateTime createdAt;
	LocalDateTime lastModifiedAt;
	
}
