package br.com.brb.controller;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.brb.entity.Cadastro;
import br.com.brb.service.ICadastroService;

@ManagedBean
@SessionScoped
public class CadastroController {

	@EJB
	private ICadastroService cadastroService;

	private Cadastro cadastro = new Cadastro();

	public void gravarUsuario() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		Boolean isCadastrado = cadastroService.gravarUsuario(cadastro);
		
		if (isCadastrado) {
			context.addMessage(null, new FacesMessage("Cliente gravado com sucesso"));
			
		} else {
			context.addMessage(null, new FacesMessage("Não foi possivel realizar seu cadastro.Tente novamente"));
		}
	}

	public Cadastro getCadastro() {
		return cadastro;
	}

	public void setCadastro(Cadastro cadastro) {
		this.cadastro = cadastro;
	}

}
