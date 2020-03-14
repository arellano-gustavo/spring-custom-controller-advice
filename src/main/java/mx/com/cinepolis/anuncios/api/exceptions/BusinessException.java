package mx.com.cinepolis.anuncios.api.exceptions;

public class BusinessException extends Exception {
	private static final long serialVersionUID = -8976418699456672997L;
	
	private final String shortMsg;
	private final String longMsg;
	private final String code;

	public BusinessException(String shortMsg, String longMsg, String code) {
		super(longMsg);
		this.shortMsg = shortMsg;
		this.longMsg = longMsg;
		this.code = code;
	}
	
	public String getShortMsg() {
		return shortMsg;
	}
	public String getLongMsg() {
		return longMsg;
	}
	public String getCode() {
		return code;
	}

}
