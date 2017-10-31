package br.com.brb.service;

import java.util.List;

import javax.ejb.Local;

import br.com.brb.entity.Conta;
import br.com.brb.entity.Usuario;
import br.com.brb.exception.NegocioException;

@Local
public interface IContaService {

	public Conta deposita(Conta valor) throws NegocioException;

	public void realizarSaque(Usuario usuario ,double vlrSaque) throws NegocioException;

	public void realizarTransferencia(Usuario origem, Long destino, double valor) throws NegocioException;
	
	public List<Conta> getList() throws NegocioException;
	
	public Conta consultarContaPorId(Conta conta) throws NegocioException;

}
