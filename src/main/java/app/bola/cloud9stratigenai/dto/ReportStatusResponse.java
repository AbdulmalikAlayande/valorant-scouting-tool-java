package app.bola.esportsscoutingtool.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ReportStatusResponse {
    private Long requestId;
    private String status;
    private String message;
    private LocalDateTime createdAt;
    private LocalDateTime completedAt;
    private String errorMessage;
}