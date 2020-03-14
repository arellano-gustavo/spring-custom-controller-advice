package mx.com.cinepolis.anuncios.api.exceptions;

public class OutOfMoneyException extends BusinessException {
	private static final long serialVersionUID = 3292381745030259644L;

	public OutOfMoneyException(String shortMsg, String longMsg, String code) {
		super(shortMsg, longMsg, code);
	}

}
