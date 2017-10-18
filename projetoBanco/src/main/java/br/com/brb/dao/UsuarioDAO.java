package br.com.brb.dao;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.brb.entity.Usuario;

@Stateless
public class UsuarioDAO extends AbstractDAO {

	public Usuario consultarUsuarioPorFiltro(Usuario usuario) {
		try {
			StringBuilder hql = new StringBuilder();
			hql.append("SELECT u FROM Usuario u "
					+ "WHERE u.senha=:senha AND u.conta.num_conta =:idconta");

			Query query = getEm().createQuery(hql.toString());

			query.setParameter("idconta", usuario.getConta().getNum_conta());
			query.setParameter("senha", usuario.getSenha());

			return (Usuario) query.getSingleResult();
		} catch (NoResultException nre) {
			//nre.printStackTrace();
			return null;
		}
	}

	public Usuario getUsuarioById(long id) {
		return getEm().find(Usuario.class, id);
	}

	public Usuario consultarAdmPorFiltro(Usuario usuario) {

		try {

			StringBuilder hql = new StringBuilder();
			hql.append("SELECT u FROM Usuario u WHERE u.email=:email and u.senha=:senha ");

			Query query = getEm().createQuery(hql.toString());

			query.setParameter("email", usuario.getEmail());
			query.setParameter("senha", usuario.getSenha());
		

			return (Usuario) query.getSingleResult();
		} catch (NoResultException nre) {
			nre.printStackTrace();
			return null;
		}
	}

}