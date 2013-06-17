/**
 * 
 * @author Xufeng Dai create 2013-06-14
 */
package com.pis.common.exception;

/**
 * @author Xufeng Dai
 * 
 */
public class UnLoginException extends PisBaseException {

	private static final long serialVersionUID = -2045724314336478607L;

	public UnLoginException(String message) {
		super(message);
	}

	public UnLoginException(String message, Throwable cause) {
		super(message, cause);
	}

}
