package com.elsevier.api;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.elsevier.model.ErrorResponse;

@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorResponse> handleBadRequests(HttpServletResponse response) throws IOException {
	    ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.BAD_REQUEST.value());
		error.setMessage("Please try again and with a non empty string as 'name'");
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(FileSearchException.class)
	public ResponseEntity<ErrorResponse> exceptionToDoHandler(Exception ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.NOT_FOUND.value());
		error.setMessage("Resource not found");
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setMessage("The request could not be understood by the server due to malformed syntax.");
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}
}
