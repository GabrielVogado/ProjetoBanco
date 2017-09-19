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
<<<<<<< HEAD
	public Usuario getUsuarioById (String usuarioId) {
=======
	public Usuario getUsuarioById(String usuarioId) {
>>>>>>> refs/remotes/origin/master
		
		return usuarioDAO.getUsuarioById(usuarioId);
	}
}
 