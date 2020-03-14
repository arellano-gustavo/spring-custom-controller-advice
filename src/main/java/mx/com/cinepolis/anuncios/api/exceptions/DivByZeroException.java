package mx.com.cinepolis.anuncios.api.exceptions;

public class DivByZeroException extends BusinessException {
	private static final long serialVersionUID = -826524616111321246L;

	public DivByZeroException(String shortMsg, String longMsg, String code) {
		super(shortMsg, longMsg, code);
	}

}
