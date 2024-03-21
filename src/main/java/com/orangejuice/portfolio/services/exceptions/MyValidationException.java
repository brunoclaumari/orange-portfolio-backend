package com.orangejuice.portfolio.services.exceptions;



public class MyValidationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public MyValidationException(String msg) {
		super(msg);
	}

}
