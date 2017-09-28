package br.com.brb.service;

import javax.ejb.Local;

import br.com.brb.entity.Usuario;

@Local
public interface IUsuarioService {

	// Usuario getUsuarioById(Usuario usuarioDestino);

	Usuario getUsuarioById(long usuarioId);
}
