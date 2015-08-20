package br.com.softbox.ejb.dao;
// Generated 17/08/2015 18:01:24 by Hibernate Tools 3.4.0.CR1

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.softbox.ejb.entity.UserAnswer;
import br.com.softbox.ejb.local.UserAnswerLocal;

/**
 * Home object for domain model class UserAnswer.
 * @see br.com.softbox.ejb.entity.UserAnswer
 * @author Hibernate Tools
 */
@Stateless
public class UserAnswerDao extends BaseDao<UserAnswer> implements UserAnswerLocal {
	@PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserAnswerDao() {
        super(UserAnswer.class);
    }
    
    public UserAnswer findByEmail(String email){
    	try {
			 Query q = getEntityManager().createQuery("SELECT u FROM UserAnswer u WHERE u.email = :email" );
			 q.setParameter("email", email);
			 @SuppressWarnings("unchecked")
			 List<UserAnswer> list = q.getResultList();
			 if(list!=null && list.size()>0){
				 return list.get(0);
			 }
		} catch (RuntimeException re) {
			throw re;
		}
		return null;
    }
}