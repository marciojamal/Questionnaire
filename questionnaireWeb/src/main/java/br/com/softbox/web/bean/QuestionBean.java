package br.com.softbox.web.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.softbox.ejb.entity.Answer;
import br.com.softbox.ejb.entity.Option;
import br.com.softbox.ejb.entity.Question;
import br.com.softbox.ejb.entity.Questionnaire;
import br.com.softbox.ejb.local.AnswerLocal;
import br.com.softbox.ejb.local.QuestionnaireLocal;
import br.com.softbox.web.vo.QuestionVO;
import br.com.softbox.web.vo.QuestionnaireVO;

@ViewScoped
@ManagedBean
public class QuestionBean extends BaseBean{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -6916193485919474674L;

	@EJB
	private QuestionnaireLocal	questionnaireLocal;

	@EJB
	private AnswerLocal	answerLocal;

	private Long questionnaireId;
	
	private Questionnaire questionnaire;
	private QuestionnaireVO questionnaireVO;
	
	public QuestionnaireVO getQuestionnaireVO() {
		if(questionnaireVO==null ){
			questionnaireVO = buildQuestionnaireVO(getQuestionnaire());
		}
		return questionnaireVO;
	}
	
	
	private QuestionnaireVO buildQuestionnaireVO(Questionnaire qLocal) {
		QuestionnaireVO questionnaireVO = new QuestionnaireVO();
		questionnaireVO.setId(qLocal.getId());
		questionnaireVO.setName(qLocal.getName());
		questionnaireVO.setQuestions(new ArrayList<QuestionVO>());
		List<Question> questions = qLocal.getQuestions();
		for (Question question : questions) {
			questionnaireVO.getQuestions().add(buildQuestionVO(question));
		}
		return questionnaireVO;
	}
	private QuestionVO buildQuestionVO(Question qLocal) {
		QuestionVO questionVO = new QuestionVO();
		questionVO.setId(qLocal.getId());
		questionVO.setQuestion(qLocal.getQuestion());
		questionVO.setOptions(qLocal.getOptions());
		questionVO.setQuestionType(qLocal.getQuestionType());
		return questionVO;
	}


	public Questionnaire getQuestionnaire() {
		if(questionnaire==null ){
			questionnaire = questionnaireLocal.findById(getQuestionnaireId());
		}
		return questionnaire;
	}
	
	
	public String saveAnswers(){
		try {
			String result = executeSaveAnswers();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			displayErrorMessage("Occorreu um erro, tente novamente mais tarde.");
			return null;
		}
	}


	private String executeSaveAnswers() {
		List<Answer> userAnswers = null;
		if(getUserBean().getEmail()!=null){
			userAnswers = answerLocal.findByUserAndQuestionnaire(getUserBean().getUserAnswer().getId(), getQuestionnaireId());
		}
		if(getUserBean().getEmail()==null){
			displayErrorMessage("Favor preencha o email no cabeçalho.");
		}else if(userAnswers != null && userAnswers.size()>0){
			displayErrorMessage("Você já participou deste questionário");
		}else{
			boolean hasError = false;
			List<QuestionVO> questions = questionnaireVO.getQuestions();
			List<Answer> list = new ArrayList<Answer>();
			for (QuestionVO q : questions) { 
				Answer a = new Answer();
				a.setQuestionnaire(new Questionnaire(questionnaireVO.getId()));
				a.setUserAnswer(getUserBean().getUserAnswer());
				a.setQuestion(new Question(q.getId()));
				a.setCreationDate(new Date());
				if(q.getQuestionType().getId()==1){//Aberta
					if(q.getAnswer().getOpenQuestion()==null || q.getAnswer().getOpenQuestion().trim().equals("")){
						hasError =true;
					}else{
						a.setOpenQuestion(q.getAnswer().getOpenQuestion());
					}
					
					
				}else if(q.getQuestionType().getId()==2){// 1 opcao
					if(q.getAnswer().getOptionId()==null || q.getAnswer().getOptionId()==0){
						hasError =true;
					}else{
						Option option = new Option();
						option.setId(q.getAnswer().getOptionId());
						a.getOptions().add(option );
					}
					
					
					
				}else if(q.getQuestionType().getId()==3){ //N opcoes
					
					if(q.getAnswer().getOptionIds().size()<1){
						hasError =true;
					}else{
						for (Object optId : q.getAnswer().getOptionIds()) {
							Long opt;
							if(optId instanceof String){
								opt = Long.valueOf((String) optId);
							}else{
								opt = (Long) optId;
							}
							Option option = new Option();
							option.setId(opt);
							a.getOptions().add(option );
						}
					}
					
				}//Fim dos ifs
				list.add(a);
			}
			
			if(hasError){
				displayErrorMessage("Preencha todas as questões");
			}else{
				answerLocal.persistList(list);
				return "pretty:successAnswer";
			}
			
		}
		return null;
	}


	public Long getQuestionnaireId() {
		return questionnaireId;
	}


	public void setQuestionnaireId(Long questionnaireId) {
		this.questionnaireId = questionnaireId;
	}


}
