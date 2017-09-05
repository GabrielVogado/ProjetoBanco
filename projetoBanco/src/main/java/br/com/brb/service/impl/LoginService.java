package br.com.brb.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.brb.dao.UsuarioDAO;
import br.com.brb.entity.Usuario;
import br.com.brb.service.ILoginService;

@Stateless
public class LoginService implements ILoginService {

	
	@Inject
	private UsuarioDAO usuarioDAO;

	@Override
	public Boolean verificarUsuarioLogado(Usuario usuario) {

		List<Usuario> usuarios = usuarioDAO.consultarUsuarioPorFiltro(usuario);

		if (usuarios != null && !usuarios.isEmpty()) {
			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}

}
