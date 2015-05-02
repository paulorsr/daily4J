package com.dailymotion.dailymotion4j.exceptions;

public final class DailymotionResponseProcessingException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DailymotionResponseProcessingException(Exception e) {
		super(e);
	}
	
	public DailymotionResponseProcessingException(final String message) {
		super(message);
	}

}
