package exceptions;

import base.BaseException;

public class PermissionDenyException extends BaseException {

	private static final long serialVersionUID = 7984091996206244265L;

	public PermissionDenyException(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	public PermissionDenyException() {
		this.errorInfo = "PermissionDenyException";
	}
}
