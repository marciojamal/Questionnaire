package br.com.softbox.ejb.dao;
// Generated 17/08/2015 18:01:24 by Hibernate Tools 3.4.0.CR1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.softbox.ejb.entity.QuestionType;
import br.com.softbox.ejb.local.QuestionTypeLocal;

/**
 * Home object for domain model class QuestionType.
 * @see br.com.softbox.ejb.entity.QuestionType
 * @author Hibernate Tools
 */
@Stateless
public class QuestionTypeDao extends BaseDao<QuestionType> implements QuestionTypeLocal {
	@PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public QuestionTypeDao() {
        super(QuestionType.class);
    }
}