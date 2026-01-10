package app.bola.esportsscoutingtool.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateReportRequest {
    
    @NotBlank(message = "User prompt cannot be empty")
    @Size(min = 10, max = 500, message = "Prompt must be between 10 and 500 characters")
    private String userPrompt;
    
}