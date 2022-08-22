package com.github.yash777.User.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FileNotFoundExceptionCustom extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public FileNotFoundExceptionCustom(String message) {
		super(message);
	}
	
	public FileNotFoundExceptionCustom(String message, Throwable cause) {
		super(message, cause);
	}
	
	public static void throwException() throws FileNotFoundExceptionCustom {
		throw new FileNotFoundExceptionCustom("Custom File Exception");
	}
}
