package br.com.brb.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.brb.entity.Usuario;

public class UsuarioDAO extends AbstractDAO {

	public Usuario consultarUsuarioPorFiltro(Usuario usuario) {
		try {
			StringBuilder hql = new StringBuilder();
			hql.append("SELECT u FROM Usuario u WHERE u.email=:email and u.senha=:senha");

			Query query = getEm().createQuery(hql.toString());

			query.setParameter("email", usuario.getEmail());
			query.setParameter("senha", usuario.getSenha());

			return (Usuario) query.getSingleResult();
		} catch (NoResultException nre) {
			nre.printStackTrace();
			return null;
		}
	}
	
	public Usuario getUsuarioById( String id ) {
		return getEm().find(Usuario.class, id);
	}
}