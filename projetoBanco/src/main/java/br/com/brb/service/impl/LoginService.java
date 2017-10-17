package br.com.brb.service.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.brb.dao.UsuarioDAO;
import br.com.brb.entity.Usuario;
import br.com.brb.service.ILoginService;

@Stateless
public class LoginService implements ILoginService {

	@Inject
	private UsuarioDAO usuarioDAO;

	public Usuario verificarUsuarioLogado(Usuario usuario) {

		Usuario user = usuarioDAO.consultarUsuarioPorFiltro(usuario);

		return user;
	}

	@Override
	public Usuario verificaAdmLogado(Usuario usuario) {
		Usuario user = usuarioDAO.consultarAdmPorFiltro(usuario);
		return user;
	}
}