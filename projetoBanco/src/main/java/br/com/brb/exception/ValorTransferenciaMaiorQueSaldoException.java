package br.com.brb.exception;

public class ValorTransferenciaMaiorQueSaldoException extends NegocioException {
	
	private static final long serialVersionUID = -1775004639854950746L;
	
	public ValorTransferenciaMaiorQueSaldoException() {
		super();
	}
	
	public ValorTransferenciaMaiorQueSaldoException(String mensagem) {
		super(mensagem);
	}

}
