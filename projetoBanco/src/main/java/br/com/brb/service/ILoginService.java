package br.com.brb.service;

import javax.ejb.Local;

import br.com.brb.entity.Usuario;

@Local
public interface ILoginService {

	public Usuario verificarUsuarioLogado(Usuario usuario);

}
