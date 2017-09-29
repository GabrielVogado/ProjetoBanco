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
	
	public List<Extrato> getExtrato( String usuarioId ){
	
		String query = "SELECT e FROM Extrato e WHERE e.usuario.id=:usuarioId";
		
		Query query = getEm().createQuery( query );
		query.setParameter("usuarioId", usuarioId );
		
		return query.getResultList();
	}

}