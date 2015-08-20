package br.com.softbox.ejb.dao;
// Generated 17/08/2015 18:01:24 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.softbox.ejb.entity.Option;
import br.com.softbox.ejb.entity.Question;
import br.com.softbox.ejb.local.QuestionLocal;

/**
 * Home object for domain model class Question.
 * @see br.com.softbox.ejb.entity.Question
 * @author Hibernate Tools
 */
@Stateless
public class QuestionDao extends BaseDao<Question> implements QuestionLocal {
	@PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public QuestionDao() {
        super(Question.class);
    }
    
    @Override
    public void persistWithOptions(Question newQuestion, List<Option> newOptions) {
		
		newQuestion.setCreationDate(new Date());
		persist(newQuestion);
		newQuestion.setOptions(newOptions);
		if( newOptions != null){
			for (int i = 0; i < newOptions.size(); i++) {
				Option option = newOptions.get(i);
				option.setOptionOrder(i);
				option.setQuestion(newQuestion);
				em.persist(option);
			}
		}
	}
    @Override
    public Question mergeWithOptions(Question question, List<Option> options) {
		question.setCreationDate(findById(question.getId()).getCreationDate());
		question.setOptions(options);
		if( options != null){
			for (int i = 0; i < options.size(); i++) {
				Option option = options.get(i);
				option.setOptionOrder(i);
				option.setQuestion(question);
				if(option.getId()==null){
					em.persist(option);
				}else{
					em.merge(option);
				}
			}
		}
		question = merge(question);
		return question;
	}
    
    @Override
    public void remove(Question persistentInstance) {
    	persistentInstance = merge(persistentInstance);
    	super.remove(persistentInstance);
    	em.flush();
    }
}