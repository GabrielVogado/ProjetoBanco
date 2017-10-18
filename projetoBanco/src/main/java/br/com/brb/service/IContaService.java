package br.com.brb.service;

import java.util.List;

import javax.ejb.Local;

import br.com.brb.entity.Conta;

@Local
public interface IContaService {

	public Conta deposita(Conta valor);

	public Conta saca(Conta valor);

	public Conta transfere(Conta valor);
	
	public List<Conta> getList();

}
