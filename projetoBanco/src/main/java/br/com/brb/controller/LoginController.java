
package br.com.brb.controller;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.brb.entity.Usuario;
import br.com.brb.service.ILoginService;

@ManagedBean
@ViewScoped
public class LoginController {

	@EJB
	private ILoginService loginService;

	private Usuario usuario = new Usuario();

	public String realizarLogin() {

		Boolean isUsuarioLogado = loginService.verificarUsuarioLogado(getUsuario());

		if (isUsuarioLogado) {
			return "home";
		}

		return "erroLogin";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
