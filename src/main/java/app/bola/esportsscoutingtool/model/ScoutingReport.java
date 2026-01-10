package app.bola.esportsscoutingtool.model;

import jakarta.persistence.Entity;

import app.bola.esportsscoutingtool.common.model.BaseModel;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ScoutingReport extends BaseModel {
	
	@OneToOne
	@JoinColumn(name = "report_request_id", unique = true)
	private ReportRequest reportRequest;
	
	@Column(name = "team_id", nullable = false)
	private String teamId;
	
	@Column(name = "team_name")
	private String teamName;
	
	@Type(value = JsonBinaryType.class)
	@Column(name = "report_data", columnDefinition = "jsonb")
	private String reportData;  // Full report JSON
	
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt = LocalDateTime.now();
	
}
