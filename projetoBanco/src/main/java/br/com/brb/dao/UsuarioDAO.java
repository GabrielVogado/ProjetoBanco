package br.com.brb.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.brb.entity.Usuario;

public class UsuarioDAO extends AbstractDAO {

	@PersistenceContext(unitName = "AppBanco")
	private EntityManager entityManager;

	public Usuario consultarUsuarioPorFiltro(Usuario usuario) {
		
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT u FROM Usuario u WHERE u.email=:email and u.senha=:senha");
		
		Query query = entityManager.createQuery(hql.toString());
		
		query.setParameter("email", usuario.getEmail());
		query.setParameter("senha", usuario.getSenha());
		
		return (Usuario) query.getSingleResult();
	}
}