package br.com.brb.service;

import java.util.List;

import javax.ejb.Local;

import br.com.brb.entity.Extrato;

@Local
public interface IExtratoService {
	
	public boolean gravarDados(Extrato extrato);
	
	
	public List<Extrato> getExtrato(long id);

}
