package app.bola.cloud9stratigenai.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScoutingReportResponse {
	
	private String requestId;
	private String reportType;
	private String reportTitle;
	private String summary;
	private LocalDateTime createdAt;
	private List<ReportSection> sections;
	
	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class ReportSection {
		private String title;
		private Object content;
		private Integer order;
	}
}
