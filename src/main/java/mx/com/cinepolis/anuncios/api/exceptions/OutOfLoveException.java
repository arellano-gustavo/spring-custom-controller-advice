package mx.com.cinepolis.anuncios.api.exceptions;

public class OutOfLoveException extends BusinessException {
	private static final long serialVersionUID = -826524616111321246L;

	public OutOfLoveException(String shortMsg, String longMsg, String code) {
		super(shortMsg, longMsg, code);
	}

}
