package br.com.softbox.web.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.softbox.ejb.entity.Answer;
import br.com.softbox.ejb.entity.Questionnaire;
import br.com.softbox.ejb.local.AnswerLocal;
import br.com.softbox.ejb.local.QuestionnaireLocal;

@ViewScoped
@ManagedBean
public class AnswerBean extends BaseBean{
	


	/**
	 * 
	 */
	private static final long serialVersionUID = 3353428760065786668L;

	@EJB
	private QuestionnaireLocal	questionnaireLocal;

	@EJB
	private AnswerLocal	answerLocal;

	private Long questionnaireId;
	
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
			if(getUserBean().getUserAnswer()!=null ){
				openAnswers = answerLocal.findByUserAndQuestionnaire(getUserBean().getUserAnswer().getId(), getQuestionnaireId());
			}
		}
		return openAnswers; 
	}
	
	public Map<Answer, List<String>> getMapAnswers() {
		if(mapAnswers==null ){
			if(getUserBean().getUserAnswer()!=null ){
				mapAnswers = answerLocal.findMapByUserAndQuestionnaire(getUserBean().getUserAnswer().getId(), getQuestionnaireId());
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
		return questionnaireId;
	}


	public void setQuestionnaireId(Long questionnaireId) {
		this.questionnaireId = questionnaireId;
	}


}
