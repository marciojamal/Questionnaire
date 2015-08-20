package br.com.softbox.ejb.dao;
// Generated 17/08/2015 18:01:24 by Hibernate Tools 3.4.0.CR1

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.softbox.ejb.entity.Answer;
import br.com.softbox.ejb.entity.Questionnaire;
import br.com.softbox.ejb.local.AnswerLocal;

/**
 * Home object for domain model class Answer.
 * @see br.com.softbox.ejb.entity.Answer
 * @author Hibernate Tools
 */
@Stateless
public class AnswerDao extends BaseDao<Answer> implements AnswerLocal {
	@PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AnswerDao() {
        super(Answer.class);
    }
    
    @Override
    public void persistList(List<Answer> list){
    	for (Answer answer : list) {
			persist(answer);
		}
    }
    
    @Override
    public List<Answer> findByUserAndQuestionnaire(int userId, Long questionnaireId){
    	try {
			 Query q = getEntityManager().createQuery("SELECT a FROM Answer a WHERE a.userAnswer.id = :userId AND a.questionnaire.id = :questionnaireId " );
			 q.setParameter("userId", userId);
			 q.setParameter("questionnaireId", questionnaireId);
			 @SuppressWarnings("unchecked")
			 List<Answer> list = q.getResultList();
			 return list;
		} catch (RuntimeException re) {
			throw re;
		}
    }
    
    @Override
    public Map<Answer, List<String>> findMapByUserAndQuestionnaire(int userId, Long questionnaireId){
    	try {
			 Query q = getEntityManager().createQuery("SELECT a , optionAlias.description FROM Answer a inner join a.options optionAlias WHERE a.userAnswer.id = :userId AND a.questionnaire.id = :questionnaireId " );
			 q.setParameter("userId", userId);
			 q.setParameter("questionnaireId", questionnaireId);
			 @SuppressWarnings("unchecked")
			 List<Object[]> list = q.getResultList();
			 
			 
			 Map<Answer, List<String>> results = new HashMap<Answer, List<String>>();
			 for (Object[] object : list) {
				 if(!results.containsKey(object[0])){
					 results.put((Answer)object[0], new ArrayList<String>());
				 }
				 results.get(object[0]).add((String)object[1]);
			}
			 return results;
		} catch (RuntimeException re) {
			throw re;
		}
    }
    
    @Override
    public List<Long> findQuestionnaireIdsByUser(int userId){
    	try {
			 Query q = getEntityManager().createQuery("SELECT a.questionnaire.id FROM Answer a WHERE a.userAnswer.id = :userId group by a.questionnaire.id" );
			 q.setParameter("userId", userId);
			 @SuppressWarnings("unchecked")
			 List<Long> list = q.getResultList();
			 return list;
		} catch (RuntimeException re) {
			throw re;
		}
    }
    
    @Override
    public List<Questionnaire> findQuestionnaireByUser(int userId){
    	try {
			 Query q = getEntityManager().createQuery("SELECT a.questionnaire FROM Answer a WHERE a.userAnswer.id = :userId group by a.questionnaire.id" );
			 q.setParameter("userId", userId);
			 @SuppressWarnings("unchecked")
			 List<Questionnaire> list = q.getResultList();
			 return list;
		} catch (RuntimeException re) {
			throw re;
		}
    }
}