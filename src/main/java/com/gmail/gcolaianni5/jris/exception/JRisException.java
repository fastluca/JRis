/*
 * JRisException.java
 * 
 * 22 apr 2017
 */
package com.gmail.gcolaianni5.jris.exception;

/**
 * A {@code JrisException} is thrown when an application or logical error occurs.
 *
 * @author Gianluca Colaianni -- g.colaianni5@gmail.com
 * @version 1.0
 * @since 22 apr 2017
 */
public class JRisException extends Throwable {


	/**
	 * @param message
	 */
	public JRisException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public JRisException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public JRisException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public JRisException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
