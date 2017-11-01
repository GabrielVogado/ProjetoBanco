package br.com.brb.exception;

public class ValorDepositoNaoPodeZeroException extends NegocioException {

	private static final long serialVersionUID = -4899364036092643825L;

	public ValorDepositoNaoPodeZeroException() {
		super();
	}

	public ValorDepositoNaoPodeZeroException(String menssage) {
		super(menssage);
	}

}
