/**
 * 
 * @author Xufeng Dai create 2013-06-14
 */
package com.pis.common.exception;

/**
 * @author Xufeng Dai
 * 
 */
public class UnKnowTplException extends PisBaseException {

	private static final long serialVersionUID = -2045724314336478607L;

	public UnKnowTplException(String message) {
		super(message);
	}

	public UnKnowTplException(String message, Throwable cause) {
		super(message, cause);
	}

}
