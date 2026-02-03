package app.bola.esportsscoutingtool.model;

import app.bola.esportsscoutingtool.common.model.BaseModel;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "report_requests")
public class ReportRequest extends BaseModel {
    
    @Column(name = "user_prompt", nullable = false, columnDefinition = "TEXT")
    private String userPrompt;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ReportStatus status = ReportStatus.PENDING;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
    
    @Column(name = "completed_at")
    private LocalDateTime completedAt;
    
    @Column(name = "error_message", columnDefinition = "TEXT")
    private String errorMessage;
    
    // Enums
    public enum ReportStatus {
        PENDING, PROCESSING, COMPLETED, FAILED
    }
}