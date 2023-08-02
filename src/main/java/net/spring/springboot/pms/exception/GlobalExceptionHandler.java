package net.spring.springboot.pms.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException exception) {
		List<String> details = new ArrayList<String>();
		details.add(exception.getMessage());

		ApiError error = new ApiError(LocalDateTime.now(), HttpStatus.BAD_REQUEST, "Resource Not Found", details);

		return ResponseEntityBuilder.build(error);
	}

	// will be triggered if the requestBody is invalid
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		List<String> details = new ArrayList<String>();
		details.add(ex.getMessage());

		ApiError apiError = new ApiError(LocalDateTime.now(), HttpStatus.BAD_REQUEST, "Malformed JSON request",
				details);

		return ResponseEntityBuilder.build(apiError);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		List<String> details = new ArrayList<String>();
		details = ex.getBindingResult().getFieldErrors().stream()
				.map(error -> "*" + error.getDefaultMessage()).collect(Collectors.toList());
		ApiError apiError = new ApiError(LocalDateTime.now(), HttpStatus.BAD_REQUEST, "Validation Error", details);

		return ResponseEntityBuilder.build(apiError);
	}

	// will be thrown when a method parameter has the wrong type
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
			WebRequest request) {

		List<String> details = new ArrayList<String>();
		details.add(ex.getMessage());

		ApiError err = new ApiError(LocalDateTime.now(), HttpStatus.BAD_REQUEST, "Type Mismatch", details);
		return ResponseEntityBuilder.build(err);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<?> handleConstraintViolationException(Exception ex, WebRequest request) {

		List<String> details = new ArrayList<String>();
		details.add(ex.getMessage());

		ApiError err = new ApiError(LocalDateTime.now(), HttpStatus.BAD_REQUEST, "Constraint Violations", details);

		return ResponseEntityBuilder.build(err);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<?> illegalArgumentHandler(IllegalArgumentException exception) {
		List<String> details = new ArrayList<String>();
		details.add(exception.getMessage());
		
		ApiError err = new ApiError(LocalDateTime.now(), HttpStatus.BAD_REQUEST, "Illegal Argument", details);

		return ResponseEntityBuilder.build(err);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<?> illegalArgumentHandler(DataIntegrityViolationException exception) {
		List<String> details = new ArrayList<String>();
		details.add(exception.getMessage());
		
		ApiError err = new ApiError(LocalDateTime.now(), HttpStatus.CONFLICT , "Constraint Error", details);

		return ResponseEntityBuilder.build(err);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		List<String> details = new ArrayList<String>();
		details.add(ex.getParameterName() + " parameter is missing");

		ApiError err = new ApiError(LocalDateTime.now(), HttpStatus.BAD_REQUEST, "Missing Parameters", details);

		return ResponseEntityBuilder.build(err);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> globalExceptionHandler(Exception exception) {
		List<String> details = new ArrayList<String>();
		details.add(exception.getMessage());
		ApiError detail = new ApiError(
				LocalDateTime.now(), 
				HttpStatus.INTERNAL_SERVER_ERROR, 
				"System error", 
				details);

		return new ResponseEntity<>(detail, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
