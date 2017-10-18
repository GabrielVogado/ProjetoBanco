
package br.com.brb.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import br.com.brb.entity.Conta;
import br.com.brb.entity.Usuario;
import br.com.brb.service.ILoginService;

@ManagedBean(name = "loginController")
@ViewScoped
public class LoginController {

	@Inject
	private ILoginService loginService;

	@Inject
	private ContaController contaControler;

	private String Login;
	private String NumConta = "0";
	private String Senha ; 

	public String realizaLoginAdm() {
		Usuario usuario = new Usuario();
		usuario.setEmail(this.Login);
		usuario.setSenha(this.Senha);

		Usuario isAdmLogado = loginService.verificaAdmLogado(usuario);

		if (isAdmLogado != null) {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioLogado", isAdmLogado);
			return "/admin/painelDoAdm.xhtml";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conta Invalida ou não existe", null));
		}

		contaControler.init();

		return "/admin/loginAdm.xhtml";

	}

	public String realizarLogin() {

		Usuario usuario = new Usuario();
		
		usuario.setConta(new Conta() );
		usuario.getConta().setNum_conta( Long.parseLong(NumConta) );
		
		usuario.setSenha(this.Senha);

		Usuario isUsuarioLogado = loginService.verificarUsuarioLogado(usuario);

		if (isUsuarioLogado != null) {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioLogado",
					isUsuarioLogado);
			return "/caixa.xhtml";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conta Invalida ou não existe", null));
		}

		contaControler.init();

		return "/index.xhtml";
	}

	public String logout() {
		HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		sessao.invalidate();
		return "index.xhtml";
	}

	public String getNumConta() {
		return NumConta;
	}

	public void setNumConta(String NumConta) {
		this.NumConta = NumConta;
	}

	public String getSenha() {
		return Senha;
	}

	public void setSenha(String senha) {
		Senha = senha;
	}

	public String getLogin() {
		return Login;
	}

	public void setLogin(String login) {
		Login = login;
	}
}
