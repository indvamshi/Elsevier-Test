package com.elsevier.api;

/*
 * Custom exception to display a friendly message to user
 */
public class FileSearchException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String errorMessage;

	public FileSearchException() {
		super();
	}

	public FileSearchException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}
