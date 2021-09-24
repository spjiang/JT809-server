package com.ctfo.engine.access.protocol.support.src.main.java.com.ctfo.protocol.support.exception;

/**
 * 自定义809协议处理异常类
 *
 * @author 凉意 2018年4月22日 下午1:28:51
 * @see
 * @version 1.0
 */
public class P809Exception extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * 错误码
	 */
	private String errorCode;

	public P809Exception(String message) {
		super(message);
	}

	public P809Exception(String errorCode, String message) {
		super(message);
		this.setErrorCode(errorCode);
	}

	public P809Exception(String errorCode, String message, Throwable cause) {
		super(message, cause);
		this.setErrorCode(errorCode);
	}

	public P809Exception(String message, Throwable cause) {
		super(message, cause);
	}


	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}