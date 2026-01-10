package app.bola.esportsscoutingtool.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ReportStatusResponse {
    private Long requestId;
    private String status;
    private String message;
    private LocalDateTime createdAt;
    private LocalDateTime completedAt;
    private String errorMessage;
}