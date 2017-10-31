package br.com.brb.exception;

public class ValorSaqueMenorQueZeroException extends NegocioException {

	private static final long serialVersionUID = 4740098884308757975L;

	public ValorSaqueMenorQueZeroException() {

		super();
	}

	public ValorSaqueMenorQueZeroException(String message) {

		super(message);
	}
}
