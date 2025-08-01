package com.gabryel.login.exception;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	// Email don't exist
		@ExceptionHandler(DataIntegrityViolationException.class)
		public ResponseEntity<ErrorResponse> handleDataIntegrityViolation(DataIntegrityViolationException ex, HttpServletRequest request){
			ErrorResponse error = new ErrorResponse(
					LocalDateTime.now(),
					HttpStatus.BAD_REQUEST.value(),
					"Bad Request",
					"Email is already in use",
					request.getRequestURI()
					);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		}
		
	// User din't find
		@ExceptionHandler(UserDidntFind.class)
		public ResponseEntity<ErrorResponse> handleUserDidntFind(UserDidntFind ex, HttpServletRequest request){
			ErrorResponse error = new ErrorResponse(
					LocalDateTime.now(),
					HttpStatus.BAD_REQUEST.value(),
					"Bad Request",
					ex.getMessage(),
					request.getRequestURI()
					);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		}
		
	// Repositoy is empty
		@ExceptionHandler(RepositoryIsEmpty.class)
		public ResponseEntity<ErrorResponse> handleRepositoryIsEmpty(RepositoryIsEmpty ex, HttpServletRequest request){
			ErrorResponse error = new ErrorResponse(
					LocalDateTime.now(),
					HttpStatus.BAD_REQUEST.value(),
					"Bad Request", 
					ex.getMessage(),
					request.getRequestURI()
					);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		}
		
	// Validations error
		@ExceptionHandler(MethodArgumentNotValidException.class)
		public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex, HttpServletRequest request){
			String messageError = ex.getBindingResult()
					.getFieldErrors()
					.stream()
					.map(error -> error.getField() + ": " + error.getDefaultMessage())
					.collect(Collectors.joining(", "));
			
			ErrorResponse error = new ErrorResponse(
					LocalDateTime.now(),
					HttpStatus.BAD_REQUEST.value(),
					"Bad Request", 
					messageError, 
					request.getRequestURI()
					);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		}
		
	// generic errors
		@ExceptionHandler(RuntimeException.class)
		public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex, HttpServletRequest request){
			ErrorResponse error = new ErrorResponse(
					LocalDateTime.now(),
					HttpStatus.BAD_REQUEST.value(),
					"Bad Request",
					ex.getMessage(),
					request.getRequestURI()
					);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		}
}
