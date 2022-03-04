package com.videotips.app.exceptions.controller;

import javax.mail.internet.AddressException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.videotips.app.exceptions.email.EmailException;

@RestControllerAdvice
public class EmailControlleExceptionHandler {

	@ExceptionHandler({Exception.class, AddressException.class})
	public ResponseEntity<?> handledEmailExceptions(Exception ex) {
		ResponseEntity<?> response = null;
		if (ex instanceof EmailException) {
			response = new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return response;
	}
}
