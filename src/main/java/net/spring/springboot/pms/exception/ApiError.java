package net.spring.springboot.pms.exception;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

public class ApiError {
	private LocalDateTime timestamp;
	private HttpStatus status;
	private String message;
	private List<String> errors;
	
	public ApiError(LocalDateTime timestamp, HttpStatus status, String message, List<String> errors) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.message = message;
		this.errors = errors;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	@Override
	public String toString() {
		return "ErrorDetail [timestamp=" + timestamp + ", status=" + status + ", message=" + message + ", errors="
				+ errors + "]";
	}
	
	
}
