package br.com.softbox.ejb.dao;
// Generated 17/08/2015 18:01:24 by Hibernate Tools 3.4.0.CR1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.softbox.ejb.entity.Option;
import br.com.softbox.ejb.local.OptionLocal;

/**
 * Home object for domain model class Option.
 * @see br.com.softbox.ejb.entity.Option
 * @author Hibernate Tools
 */
@Stateless
public class OptionDao extends BaseDao<Option> implements OptionLocal {
	@PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OptionDao() {
        super(Option.class);
    }
}