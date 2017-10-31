package br.com.brb.exception;

public class NegocioException extends Exception {

	private static final long serialVersionUID = 5666623926041520043L;

	public NegocioException() {
		super();
	}
	
	public NegocioException(String mensagem) {
		super(mensagem);
	}
}
