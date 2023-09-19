package com.gof.microservice.exception;

public class ValidationError extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1308807470321795453L;
	
	public  ValidationError(String s) {
		super(s);
		
	}

}
