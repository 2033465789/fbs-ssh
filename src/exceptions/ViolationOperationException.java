package exceptions;

import base.BaseException;

public class ViolationOperationException extends BaseException {

	private static final long serialVersionUID = 1793593224156785844L;

	public ViolationOperationException(String errorInfo) {
		this.errorInfo = errorInfo;
	}



	public void printStackTrace() {
		System.err.println(errorInfo);
	}
}
