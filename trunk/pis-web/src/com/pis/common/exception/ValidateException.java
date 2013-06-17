/**
 * 
 * @author Xufeng Dai create 2013-06-14
 */
package com.pis.common.exception;

/**
 * @author Xufeng Dai
 * 校验未通过异常
 */
public class ValidateException extends PisBaseException {

	private static final long serialVersionUID = -2045724314336478607L;

	public ValidateException(String message) {
		super(message);
	}

	public ValidateException(String message, Throwable cause) {
		super(message, cause);
	}

}
