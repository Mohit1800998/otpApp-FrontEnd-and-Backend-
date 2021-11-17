package com.otp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidEmailException extends Exception{

	private static final long serialVersionUID = 1L;
	public InvalidEmailException(String message) {
		super(message);
	}
}
