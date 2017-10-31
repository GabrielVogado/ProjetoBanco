package br.com.brb.exception;

public class ValorSaqueMaiorQueSaldoException extends NegocioException {

	private static final long serialVersionUID = 523116124950854721L;

	public ValorSaqueMaiorQueSaldoException() {

		super();
	}

	public ValorSaqueMaiorQueSaldoException(String menssage) {

		super(menssage);
	}
}
