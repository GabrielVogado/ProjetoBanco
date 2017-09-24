package br.com.brb.service.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.brb.dao.UsuarioDAO;
import br.com.brb.entity.Usuario;
import br.com.brb.service.IUsuarioService;

@Stateless
public class UsuarioService implements IUsuarioService {

	@Inject
	private UsuarioDAO usuarioDAO;

	@Override

	public Usuario getUsuarioById(long usuarioId) {

		return usuarioDAO.getUsuarioById(usuarioId);
	}
}
