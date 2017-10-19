package br.com.brb.service;

import javax.ejb.Local;

import br.com.brb.entity.Cadastro;

@Local
public interface ICadastroService {

	public boolean gravarUsuario(Cadastro cadastro, String tipoConta);
	
	public void remove(Long Id);

}
