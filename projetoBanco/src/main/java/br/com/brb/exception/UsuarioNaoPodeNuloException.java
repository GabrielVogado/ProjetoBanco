package br.com.brb.exception;

public class UsuarioNaoPodeNuloException extends NegocioException {

	private static final long serialVersionUID = 2097259016409790723L;
	
	public UsuarioNaoPodeNuloException() {
		super();
	}
	
	public UsuarioNaoPodeNuloException(String mensagem) {
		super(mensagem);
	}

}
