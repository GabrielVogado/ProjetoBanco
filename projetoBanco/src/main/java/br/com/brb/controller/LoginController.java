
package br.com.brb.controller;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.brb.entity.Usuario;
import br.com.brb.service.ILoginService;

@ManagedBean
@ViewScoped
public class LoginController {

	@EJB
	private ILoginService loginService;

	private String Login="";
	private String Senha="";
	
	public String realizarLogin() {

		Usuario usuario = new Usuario();
		usuario.setEmail(this.Login);
		usuario.setSenha(this.Senha);
		
		Usuario isUsuarioLogado = loginService.verificarUsuarioLogado( usuario );

		if (isUsuarioLogado != null) {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioLogado", isUsuarioLogado);
			return "home";
		}

		return "erroLogin";
	}

	public String getLogin() {
		return Login;
	}

	public void setLogin(String login) {
		Login = login;
	}

	public String getSenha() {
		return Senha;
	}

	public void setSenha(String senha) {
		Senha = senha;
	}
}
