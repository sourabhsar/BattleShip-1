package com.demo.exceptionhandlers;

public class InputException extends Exception {

	private static final long serialVersionUID = -1688659226572012620L;

	private final ErrorCode code;

	public InputException(ErrorCode code) {
        super();
        this.code = code;
    }

	public InputException(String message, ErrorCode code) {
        super(message);
        this.code = code;
    }

	public ErrorCode getCode() {
		return this.code;
	}

	@Override
	public String toString() {
		return "[code=" + code + "]";
	}

	public String getMessage(){
		return super.getMessage()+" "+this.toString();
	}
}
