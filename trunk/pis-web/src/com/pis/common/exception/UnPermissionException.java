/**
 * 
 * @author Xufeng Dai create 2013-06-14
 */
package com.pis.common.exception;

/**
 * @author Xufeng Dai
 * 
 */
public class UnPermissionException extends PisBaseException {

	private static final long serialVersionUID = 1L;

	public UnPermissionException(String message) {
		super(message);
	}

	public UnPermissionException(String message, Throwable cause) {
		super(message, cause);
	}

}
