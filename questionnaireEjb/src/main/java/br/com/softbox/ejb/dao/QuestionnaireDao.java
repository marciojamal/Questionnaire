package br.com.softbox.ejb.dao;
// Generated 17/08/2015 18:01:24 by Hibernate Tools 3.4.0.CR1

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.softbox.ejb.entity.Questionnaire;
import br.com.softbox.ejb.local.QuestionnaireLocal;

/**
 * Home object for domain model class Questionnaire.
 * @see br.com.softbox.ejb.entity.Questionnaire
 * @author Hibernate Tools
 */
@Stateless
public class QuestionnaireDao extends BaseDao<Questionnaire> implements QuestionnaireLocal {
	@PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public QuestionnaireDao() {
        super(Questionnaire.class);
    }
    
    
    @Override
    public void remove(Questionnaire persistentInstance) {
    	persistentInstance = merge(persistentInstance);
    	super.remove(persistentInstance);
    	em.flush();
    }
    
    @Override
    public List<Questionnaire> findWithAnswer(int userId, Long questionnaireId){
    	try {
			 Query q = getEntityManager().createQuery("SELECT q FROM Questionnaire q  inner join q.answers a inner join a.options o  WHERE a.userAnswer.id = :userId AND q.id = :questionnaireId " );
			 q.setParameter("userId", userId);
			 q.setParameter("questionnaireId", questionnaireId);
			 @SuppressWarnings("unchecked")
			 List<Questionnaire> list = q.getResultList();
			 return list;
		} catch (RuntimeException re) {
			throw re;
		}
    }
    
    @Override
    public List<Questionnaire> findPublished(){
    	try {
			 Query q = getEntityManager().createQuery("SELECT q FROM Questionnaire q WHERE q.published = 1 " );
			 @SuppressWarnings("unchecked")
			 List<Questionnaire> list = q.getResultList();
			 return list;
		} catch (RuntimeException re) {
			throw re;
		}
    }
    
}