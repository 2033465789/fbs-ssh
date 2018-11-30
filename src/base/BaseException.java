package base;

public class BaseException extends Exception {
	private static final long serialVersionUID = 7828720615842954842L;

	protected String errorInfo;

	public BaseException(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	public BaseException() {

	}

	public String getErrorInfo() {
		return errorInfo;
	}
}
