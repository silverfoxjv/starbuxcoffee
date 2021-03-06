package com.bestseller.starbuxcoffee.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class StarbuxRuntimeException extends RuntimeException {

	private static final long serialVersionUID = -3418591843597747899L;

	public StarbuxRuntimeException() {
		super();
	}

	public StarbuxRuntimeException(final String message) {
		super(message);
	}

	public StarbuxRuntimeException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public StarbuxRuntimeException(final Throwable cause) {
		super(cause);
	}

}
