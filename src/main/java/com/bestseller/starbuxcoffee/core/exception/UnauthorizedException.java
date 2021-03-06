package com.bestseller.starbuxcoffee.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends StarbuxRuntimeException {

	private static final long serialVersionUID = -7664775691367892301L;

	public UnauthorizedException() {
		super();
	}

	public UnauthorizedException(final String message) {
		super(message);
	}

	public UnauthorizedException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public UnauthorizedException(final Throwable cause) {
		super("Unauthorized", cause);
	}
}
