package br.com.brb.dao;

import javax.ejb.Stateless;
import java.util.List;

import br.com.brb.entity.Extrato;

@Stateless
public class ExtratoDAO extends AbstractDAO {

	public boolean gravarDados(Extrato extrato) {
		try {

			extrato = getEm().merge(extrato);
			getEm().flush();

		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}

		return true;

	}
	
	public List<Extrato> getExtrato( long contaId ){
	
		String query = "SELECT e FROM Extrato e WHERE e.conta.id=:contaId";
		
		Query query = getEm().createQuery( query );
		query.setParameter("contaId", contaId );
		
		return query.getResultList();
	}

}