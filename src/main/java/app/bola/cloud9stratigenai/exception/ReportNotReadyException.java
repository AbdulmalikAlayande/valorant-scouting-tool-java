package app.bola.cloud9stratigenai.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ReportNotReadyException extends RuntimeException {
    
    public ReportNotReadyException(String publicId) {
        super("Report is not ready yet: " + publicId);
    }
}
