package com.demo.exceptionhandlers;

public class ProhibitedException extends RuntimeException {

	private static final long serialVersionUID = 8651763654483775791L;
	
	private final ErrorCode code;

	public ProhibitedException(ErrorCode code) {
        super();
        this.code = code;
    }

	public ProhibitedException(String message, ErrorCode code) {
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
