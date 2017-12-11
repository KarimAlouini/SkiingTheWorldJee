package tn.codeinc.util;

import java.io.Serializable;

public class ResponseMessage implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4L;
	private int code;
	private String message;
	
	public ResponseMessage() {
		// TODO Auto-generated constructor stub
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ResponseMessage(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	@Override
	public String toString() {
		return "ResponseMessage [code=" + code + ", message=" + message + "]";
	}
	
	public ResponseMessage(int code){
		this.code = code;
	}
	

}
