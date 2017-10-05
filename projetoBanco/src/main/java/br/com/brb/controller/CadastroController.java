package br.com.brb.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.brb.entity.Cadastro;
import br.com.brb.service.ICadastroService;

@ManagedBean(name = "cadastroController")
@SessionScoped
public class CadastroController {

	@Inject
	private ICadastroService cadastroService;

	private Cadastro cadastro;
	
	public void init() {
		setCadastro(new Cadastro());
	}
	
	public void gravarUsuario() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		Boolean isCadastrado = cadastroService.gravarUsuario(cadastro);
		
		if (isCadastrado) {
			context.addMessage(null, new FacesMessage("Cliente gravado com sucesso"));
			init();
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"N�o foi possivel realizar seu cadastro.Tente novamente", null));
		}
	}

	public Cadastro getCadastro() {
		return cadastro;
	}

	public void setCadastro(Cadastro cadastro) {
		this.cadastro = cadastro;
	}

}
