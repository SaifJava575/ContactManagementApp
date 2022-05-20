package com.nit.saif.handler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.nit.saif.exception.ContactNotFoundException;
import com.nit.saif.payload.response.ErrorMessage;

@RestControllerAdvice
public class CustomeContactExceptionHandler {
	
	@ExceptionHandler(ContactNotFoundException.class)
	public ResponseEntity<ErrorMessage> contactCustomHandler(ContactNotFoundException cnfe){
		return ResponseEntity.internalServerError().body(
				new ErrorMessage(
				new Date().toString(),
				cnfe.getMessage(),
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				HttpStatus.INTERNAL_SERVER_ERROR.name())
				);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	 @ExceptionHandler(MethodArgumentNotValidException.class)
	 public Map<String,String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
	  Map<String,String> errors = new HashMap<>();
	  ex.getBindingResult().getFieldErrors()
	   .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
	  return errors;
	 }

}
