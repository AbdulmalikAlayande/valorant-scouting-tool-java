package app.bola.cloud9stratigenai.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ReportNotFoundException extends RuntimeException {
    
    public ReportNotFoundException(String publicId) {
        super("Report request not found: " + publicId);
    }
}
