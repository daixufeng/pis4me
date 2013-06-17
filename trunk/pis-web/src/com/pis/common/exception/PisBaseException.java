/**
 * 
 * @author Xufeng Dai create 2013-06-14
 */
package com.pis.common.exception;

/**
 * @author Xufeng Dai
 * 
 */
public abstract class PisBaseException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -32288562111560989L;
	private String code;

    public PisBaseException(final String code) {
    	super(code);
        this.code = code;
    }

    public PisBaseException(final String code, final Throwable throwable) {
        super(code, throwable);
        this.code = code;
    }

    public final String getCode() {
        return this.code;
    }

    public final String toString() {
        return getCode();
    }
	
}
