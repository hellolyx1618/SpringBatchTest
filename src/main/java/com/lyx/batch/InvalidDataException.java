package com.lyx.batch;

public class InvalidDataException extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidDataException() {
	}

	public InvalidDataException(String message) {
		super(message);
	}
}
