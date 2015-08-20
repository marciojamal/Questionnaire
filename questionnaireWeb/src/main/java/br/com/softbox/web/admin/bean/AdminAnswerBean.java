package br.com.softbox.web.admin.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.softbox.ejb.entity.Answer;
import br.com.softbox.ejb.entity.Questionnaire;
import br.com.softbox.ejb.local.AnswerLocal;
import br.com.softbox.ejb.local.QuestionnaireLocal;

@ViewScoped
@ManagedBean
public class AdminAnswerBean extends AdminBaseBean{
	


	/**
	 * 
	 */
	private static final long serialVersionUID = 3353428760065786668L;

	@EJB
	private QuestionnaireLocal	questionnaireLocal;

	@EJB
	private AnswerLocal	answerLocal;

	
	private Questionnaire questionnaire;

	private List<Answer> openAnswers;

	private Map<Answer, List<String>> mapAnswers;
	


	public Questionnaire getQuestionnaire() {
		if(questionnaire==null ){
			questionnaire = questionnaireLocal.findById(getQuestionnaireId());
		}
		return questionnaire;
	}
	
	public List<Answer> getOpenAnswers() {
		if(openAnswers==null ){
			if(getUserId()!=null ){
				openAnswers = answerLocal.findByUserAndQuestionnaire(getUserId(), getQuestionnaireId());
			}
		}
		return openAnswers; 
	}
	
	public Map<Answer, List<String>> getMapAnswers() {
		if(mapAnswers==null ){
			if(getUserId()!=null ){
				mapAnswers = answerLocal.findMapByUserAndQuestionnaire(getUserId(), getQuestionnaireId());
			}
		}
		return mapAnswers; 
	}
	public List<String> getAnswerByQUestionId(Long questionId, Long questionTypeId) {
		
		List<String> answersById =  new ArrayList<String>();
		if(getOpenAnswers()!=null && questionTypeId.equals(1L)){
			for (Answer answer : getOpenAnswers()) {
				if(answer.getQuestion().getId().equals(questionId)){
					answersById.add(answer.getOpenQuestion());
					return answersById;
				}
			}
		}else if(getMapAnswers()!=null){
			for (Answer answer : getMapAnswers().keySet()) {
				if(answer.getQuestion().getId().equals(questionId)){
					for (String optionName : getMapAnswers().get(answer)) {
						answersById.add(optionName);
					}
				}
			}
		}
		return answersById;
	}


	public Long getQuestionnaireId() {
		String questionnaireId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("questionnaireId");
		return Long.parseLong(questionnaireId);
	}
	public Integer getUserId() {
		String questionId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("userId");
		if(questionId!=null){
			return Integer.parseInt(questionId);
		}else{
			return null;
		}
	}


}
