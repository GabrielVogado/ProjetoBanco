package br.com.brb.exception;

public class ValorDepositoMenorQueZeroException extends NegocioException {

	private static final long serialVersionUID = -5342643178292064383L;
	
	public ValorDepositoMenorQueZeroException() {
		super();
	}
	public ValorDepositoMenorQueZeroException(String menssage) {
		super(menssage);
	}
}
