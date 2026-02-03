package app.bola.esportsscoutingtool.common;

import app.bola.esportsscoutingtool.exception.ErrorResponse;
import app.bola.esportsscoutingtool.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex) {
        log.error("Resource not found: {}", ex.getMessage());
        
        ErrorResponse error = new ErrorResponse(
		        HttpStatus.NOT_FOUND.value(),
		        ex.getMessage(),
		        LocalDateTime.now()
        );
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
	
	@ExceptionHandler({ReportNotFoundException.class, ReportNotReadyException.class})
	public ResponseEntity<ErrorResponse> handleAnnotatedExceptions(RuntimeException ex) {
		HttpStatus status = getResponseStatus(ex);
		return ResponseEntity
				       .status(status)
				       .body(new ErrorResponse(status.value(), ex.getMessage(), LocalDateTime.now()));
	}

	private HttpStatus getResponseStatus(Exception ex) {
		ResponseStatus annotation = ex.getClass().getAnnotation(ResponseStatus.class);
		if (annotation != null) {
			return annotation.value();
		}
		return HttpStatus.INTERNAL_SERVER_ERROR;
	}
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationError(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldErrors().stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .collect(Collectors.joining(", "));
        
        ErrorResponse error = new ErrorResponse(
		        HttpStatus.BAD_REQUEST.value(), message,
		        LocalDateTime.now()
        );
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericError(Exception ex) {
        log.error("Unexpected error", ex);
        
        ErrorResponse error = new ErrorResponse(
		        HttpStatus.INTERNAL_SERVER_ERROR.value(),
		        "An unexpected error occurred",
		        LocalDateTime.now()
        );
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
