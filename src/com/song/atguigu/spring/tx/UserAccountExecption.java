package com.song.atguigu.spring.tx;

public class UserAccountExecption extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserAccountExecption() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserAccountExecption(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public UserAccountExecption(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public UserAccountExecption(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public UserAccountExecption(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
