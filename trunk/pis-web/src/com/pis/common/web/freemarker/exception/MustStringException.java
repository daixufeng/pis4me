/**
 * 
 */
package com.pis.common.web.freemarker.exception;

/**
 * @author Qiuxj
 * 
 */
public class MustStringException extends Exception {

	private static final long serialVersionUID = -5079350179260017865L;

	public MustStringException(String paramName) {
		super("The \"" + paramName + "\" parameter must be a string.");
	}

}
