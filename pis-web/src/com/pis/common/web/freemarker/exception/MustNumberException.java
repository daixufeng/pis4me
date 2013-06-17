/**
 * 
 */
package com.pis.common.web.freemarker.exception;

import freemarker.template.TemplateModelException;

/**
 * @author Qiuxj
 * 
 */
public class MustNumberException extends TemplateModelException {

	private static final long serialVersionUID = -5079350179260017865L;

	public MustNumberException(String paramName) {
		super("The \"" + paramName + "\" parameter must be a string.");
	}

}
