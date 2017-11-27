package tn.codeinc.util;

import java.io.Serializable;

import tn.codeinc.persistance.AccessToken;

public class LoginResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4L;
	private int code;
	private AccessToken token;
	private String message;
	
	public LoginResponse() {
		// TODO Auto-generated constructor stub
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public AccessToken getToken() {
		return token;
	}

	public void setToken(AccessToken token) {
		this.token = token;
	}

	public LoginResponse(int code, AccessToken token) {
		super();
		this.code = code;
		this.token = token;
	}

	@Override
	public String toString() {
		return "ResponseMessage [code=" + code + ", message=" + token + "]";
	}

	public LoginResponse(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
	

}
