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

	public String getUsuarioById(String usuarioDestino) {
		Usuario usuario = usuarioDAO.getUsuarioById(usuarioDestino);
		return usuarioDestino;

	}

	@Override
	public Usuario getUsuarioById(Usuario usuarioDestino) {
		// TODO Auto-generated method stub
		return null;
	}
}
