package br.com.brb.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.brb.entity.Usuario;

public class UsuarioDAO extends AbstractDAO {

	@PersistenceContext(unitName = "AppBanco")
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<Usuario> consultarUsuarioPorFiltro(Usuario usuario) {
		
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT u FROM br.com.brb.entity.Usuario u WHERE u.nome=:nome and u.senha=:senha");
		
		Query query = entityManager.createQuery(hql.toString());
		
		query.setParameter("email", usuario.getEmail());
		query.setParameter("senha", usuario.getSenha());
		
		return (List<Usuario>) query.getResultList();
	}
}