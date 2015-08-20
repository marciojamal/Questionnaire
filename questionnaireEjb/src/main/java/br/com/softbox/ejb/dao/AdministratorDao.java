package br.com.softbox.ejb.dao;
// Generated 17/08/2015 18:01:24 by Hibernate Tools 3.4.0.CR1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.softbox.ejb.entity.Administrator;
import br.com.softbox.ejb.local.AdministratorLocal;

/**
 * Home object for domain model class Administrator.
 * @see br.com.softbox.ejb.entity.Administrator
 * @author Hibernate Tools
 */
@Stateless
public class AdministratorDao extends BaseDao<Administrator> implements AdministratorLocal {
	@PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdministratorDao() {
        super(Administrator.class);
    }
}
