package br.com.brb.service.impl;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

import br.com.brb.constante.ValorConstante;
import br.com.brb.dao.ContaDao;
import br.com.brb.entity.Conta;
import br.com.brb.entity.Extrato;
import br.com.brb.entity.Usuario;
import br.com.brb.enums.TipoTransacao;
import br.com.brb.exception.NegocioException;
import br.com.brb.exception.UsuarioNaoPodeNuloException;
import br.com.brb.exception.ValorDepositoNaoPodeZeroException;
import br.com.brb.exception.ValorSaqueMaiorQueSaldoException;
import br.com.brb.exception.ValorSaqueMenorQueZeroException;
import br.com.brb.exception.ValorTransferenciaMaiorQueSaldoException;
import br.com.brb.service.IContaService;
import br.com.brb.service.IExtratoService;
import br.com.brb.service.IUsuarioService;

@Stateless
public class ContaService implements IContaService {

	@Inject
	private IUsuarioService usuarioService;

	@Inject
	private IExtratoService extractService;

	@EJB
	ContaDao contaDAO;

	public Conta realizarDeposito(Usuario usuario,  double vlrDeposito) throws NegocioException {
		
		Conta conta = usuario.getConta();
		
		validarValorDeposito(vlrDeposito);
		
		conta.setSaldo(conta.getSaldo() + vlrDeposito);
		conta.setUsuario(usuario);
		contaDAO.alterarSaldoConta(conta);
		gravarExtratoConta(conta, vlrDeposito, TipoTransacao.CREDITO);
		
		return conta;
		
	}
	private void validarValorDeposito(double vlrDeposito) throws ValorDepositoNaoPodeZeroException {

		if (vlrDeposito <= ValorConstante.ZERO) {
			
			throw new ValorDepositoNaoPodeZeroException("Valor de deposito não é valido");
		}
	}

	public Conta realizarSaque(Usuario usuario, double vlrSaque) throws NegocioException {

		Conta conta = usuario.getConta();

		validarValorSaque(conta, vlrSaque);
		
		conta.setSaldo(conta.getSaldo() - vlrSaque);
		conta.setUsuario(usuario);
		contaDAO.alterarSaldoConta(conta);
		gravarExtratoConta(conta, vlrSaque * (-1), TipoTransacao.DEBITO);
		
		return conta;

	}
	private void validarValorSaque(Conta conta, double vlrSaque) throws ValorSaqueMaiorQueSaldoException, ValorSaqueMenorQueZeroException {
		
		if (conta.getSaldo() < vlrSaque) {
			
			throw new ValorSaqueMaiorQueSaldoException("Seu saldo é insuficiente para realizar esta operação!");
		}
		if ( vlrSaque <= ValorConstante.ZERO) {
			
			throw new ValorSaqueMenorQueZeroException("Valor inserido deve ser maior que zero");
		}
	}

	public Conta realizarTransferencia(Usuario usuarioOrigem, Long idUsuarioDestino, double valorTransferencia)
			throws NegocioException {

		Conta contaOrigem = usuarioOrigem.getConta();

		validarValorTranseferencia(contaOrigem, valorTransferencia);

		Usuario usuarioDestino = getUsuarioExistente(idUsuarioDestino);
		Conta contaDestino = usuarioDestino.getConta();

		// Atualizando o saldo da conta de origem
		contaOrigem.setSaldo(contaOrigem.getSaldo() - valorTransferencia);
		contaDAO.alterarSaldoConta(contaOrigem);
		gravarExtratoConta(contaOrigem, valorTransferencia * (-1), TipoTransacao.DEBITO);

		// Atualizando o saldo da conta de destino
		contaDestino.setSaldo(contaDestino.getSaldo() + valorTransferencia);
		contaDAO.alterarSaldoConta(contaDestino);
		gravarExtratoConta(contaDestino, valorTransferencia, TipoTransacao.CREDITO);
		
		return contaOrigem;
	}


	private Usuario getUsuarioExistente(Long idUsuario) throws UsuarioNaoPodeNuloException {

		Usuario usuario = usuarioService.getUsuarioById((idUsuario));

		if (usuario != null) {
			return usuario;
		}

		throw new UsuarioNaoPodeNuloException("Usuário da conta de destino não encontrado.");
	}

	private void validarValorTranseferencia(Conta contaOrigem, double valorTransferencia) throws NegocioException {

		double saldoConta = contaOrigem.getSaldo();

		if (valorTransferencia <= ValorConstante.ZERO || saldoConta < valorTransferencia) {
			throw new ValorTransferenciaMaiorQueSaldoException("Valor da transferência é inválido.");
		}
	}

	private void gravarExtratoConta(Conta conta, Double valor, TipoTransacao acao) {

		Extrato extrato = new Extrato();
		extrato.setAcao(acao.getDescricao());
		extrato.setData(new Date());
		extrato.setValor(valor);
		extrato.setContaId(conta.getId());

		extractService.gravarDados(extrato);
	}

	@Override
	public List<Conta> getList() throws NegocioException {
		return contaDAO.finAll();
	}

	@Override
	public Conta consultarContaPorId(Conta conta) throws NegocioException {

		try {
			return contaDAO.consultarContaPorId(conta);

		} catch (NoResultException nre) {
			throw new NegocioException("Conta informada para transferencia não existe.");
		}
	}
}
