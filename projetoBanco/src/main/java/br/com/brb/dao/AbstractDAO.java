package br.com.brb.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public abstract class AbstractDAO {

	@PersistenceContext(name = "AppBanco")
	private static EntityManager em;
	
	public static EntityManager getEm() {
		return em;
	}
	public static void setEm(EntityManager em) {
		AbstractDAO.em = em;
	}

}
