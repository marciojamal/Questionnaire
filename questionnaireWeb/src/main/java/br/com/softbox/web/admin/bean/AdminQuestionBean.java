package br.com.softbox.web.admin.bean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import br.com.softbox.ejb.entity.Option;
import br.com.softbox.ejb.entity.Question;
import br.com.softbox.ejb.entity.QuestionType;
import br.com.softbox.ejb.entity.Questionnaire;
import br.com.softbox.ejb.local.OptionLocal;
import br.com.softbox.ejb.local.QuestionLocal;
import br.com.softbox.ejb.local.QuestionTypeLocal;
import br.com.softbox.ejb.local.QuestionnaireLocal;

@ViewScoped
@ManagedBean
public class AdminQuestionBean extends AdminBaseBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5284139100641775747L;
	
	@EJB
	private QuestionLocal	questionLocal;
	
	@EJB
	private QuestionnaireLocal	questionnaireLocal;

	@EJB
	private QuestionTypeLocal	questionTypeLocal;
	
	@EJB
	private OptionLocal	optionLocal;
	
	private List<QuestionType> questionTypes;

	private List<Question> questions;

	private Questionnaire questionnaire;
	
	private List<Option> newOptions;
	private Question beanQuestion;
	
	
	
	public List<Question> getQuestions(){
		if(questions==null){
			questions = new ArrayList<Question>(getQuestionnaire().getQuestions());
		}
		return questions;
	}
	public String updateQuestion() {
		try {
			
			
			if(beanQuestion.getQuestion()==null || beanQuestion.getQuestion().trim().equals("")){
				displayErrorMessage("Favor adicionar titulo.");
				return null;
			}else if(beanQuestion.getQuestionType()==null || beanQuestion.getQuestionType().getId()==null || beanQuestion.getQuestionType().getId()==0){
				displayErrorMessage("Selecione o tipo de questão");
				return null;
			}else if(beanQuestion.getQuestionType().getId()==1){//Questao aberta
				newOptions=null;
				beanQuestion.setOptions(newOptions);
			}else{
				for (int i = 0; i < newOptions.size(); i++) {//remove opções vazias
					if(newOptions.get(i).getDescription()==null || newOptions.get(i).getDescription().trim().equals("")){
						newOptions.remove(i);
						i--;
					}
				}
				if(newOptions.size()<2){//valida, numero minimo de 2 opções
					displayErrorMessage("Necessário pelo menos 2 opções.");
					
					return null;
				}
			}
			Long id = getQuestionnaire().getId();
			
			beanQuestion.setQuestionnaire(getQuestionnaire());
			beanQuestion = questionLocal.mergeWithOptions(beanQuestion, newOptions);
			for (int i = 0; i < getQuestionnaire().getQuestions().size(); i++) {
				if(getQuestionnaire().getQuestions().get(i).getId().equals(beanQuestion.getId())){
					getQuestionnaire().getQuestions().set(i, beanQuestion);
					i=getQuestionnaire().getQuestions().size();
				}
				
			}
			questionnaireLocal.merge(getQuestionnaire());// para atualizar o cache
			beanQuestion.setQuestionType(questionTypeLocal.findById(beanQuestion.getQuestionType().getId()));//atualiza dados do tipo
			
			
			resetQuestion();
			questions=null;
			RequestContext.getCurrentInstance().execute("PF('dlgQuestion').hide();");
			displayInfoMessage("Atualizado com sucesso");
			return "/pages/restrict/questionList.jsf?questionnaireId="+id+"&faces-redirect=true";

		} catch (Exception e) {
			 RequestContext.getCurrentInstance().execute("PF('dlgQuestion').hide();");
			displayErrorMessage("Erro ao atualizar questão.");
			return null;

		}finally {
			beanQuestion = null;
		}
	}
	
	public String createQuestion() {
		try {
			
			
			if(beanQuestion.getQuestion()==null || beanQuestion.getQuestion().trim().equals("")){
				displayErrorMessage("Favor adicionar titulo.");
				return "";
			}else if(beanQuestion.getQuestionType()==null || beanQuestion.getQuestionType().getId()==null || beanQuestion.getQuestionType().getId()==0){
				displayErrorMessage("Selecione o tipo de questão");
				return null;
			}else if(beanQuestion.getQuestionType().getId()==1){//Questao aberta
				newOptions=null;
			}else{
				for (int i = 0; i < newOptions.size(); i++) {//remove opções vazias
					if(newOptions.get(i).getDescription()==null || newOptions.get(i).getDescription().trim().equals("")){
						newOptions.remove(i);
						i--;
					}
				}
				if(newOptions.size()<2){//valida, numero minimo de 2 opções
					displayErrorMessage("Necessário pelo menos 2 opções.");
					
					return null;
				}
			}
			Long id = getQuestionnaire().getId();

			beanQuestion.setQuestionnaire(getQuestionnaire());
			questionLocal.persistWithOptions(beanQuestion, newOptions);
			getQuestionnaire().getQuestions().add(beanQuestion);
			questionnaireLocal.merge(getQuestionnaire());// para atualizar o cache
			beanQuestion.setQuestionType(questionTypeLocal.findById(beanQuestion.getQuestionType().getId()));//atualiza dados do tipo

			resetQuestion();
			questions=null;
			RequestContext.getCurrentInstance().execute("PF('dlgQuestion').hide();");
			displayInfoMessage("Adicionado com sucesso");
			return "/pages/restrict/questionList.jsf?questionnaireId="+id+"&faces-redirect=true";

		} catch (Exception e) {
			 RequestContext.getCurrentInstance().execute("PF('dlgQuestion').hide();");
			displayErrorMessage("Erro ao criar questão.");
			return null;

		}finally {
			beanQuestion = null;
		}
	}

	
	public void resetQuestion() {
		beanQuestion = null;
		newOptions = null;
		
	}
	public void removeQuestion() {
		try {
			questionLocal.remove(beanQuestion);
			questionnaire.getQuestions().remove(beanQuestion);
			beanQuestion = null;
			newOptions = null;
			questions = null;
			displayInfoMessage("Removido com sucesso.");
		} catch (Exception e) {
			displayErrorMessage("Ocorreu um erro ao remover.");
		}
		
	}

	public Question getBeanQuestion() {
		if(beanQuestion==null || (beanQuestion.getId()!=null && !beanQuestion.getId().equals(getQuestionId()))){//TODO
			
			if(getQuestionId()!=null && !getQuestionId().equals(0L) ){
				beanQuestion = questionLocal.findById(getQuestionId());
				newOptions = beanQuestion.getOptions();
			}else{
				beanQuestion =  new Question();
				beanQuestion.setQuestionType(new QuestionType());
			}
		}
		return beanQuestion;
	}

	public Questionnaire getQuestionnaire() {
		if(questionnaire==null ){
			questionnaire = questionnaireLocal.findById(getQuestionnaireId());
		}
		return questionnaire;
	}
	public Long getQuestionnaireId() {
		String questionnaireId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("questionnaireId");
		return Long.parseLong(questionnaireId);
	}
	public Long getQuestionId() {
		String questionId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("questionId");
		if(questionId!=null){
			return Long.parseLong(questionId);
		}else{
			return null;
		}
	}

	public List<QuestionType> getQuestionTypes() {
		if (questionTypes == null) {
			questionTypes = questionTypeLocal.findAll();
		}
		return questionTypes;
	}


	public void addOption() {
		getNewOptions().add(new Option());
    }

	public List<Option> getNewOptions() {
		if (newOptions == null) {
			newOptions = new ArrayList<Option>();
			newOptions.add(new Option());
		}
		return newOptions;
	}

	public void setNewOptions(List<Option> newOptions) {
		this.newOptions = newOptions;
	}

	public void setBeanQuestion(Question beanQuestion) {
		this.beanQuestion = beanQuestion;
	}
	
	public void publish() {
		try {
			getQuestionnaire().setPublished(true);
			questionnaireLocal.merge(getQuestionnaire());
			super.displayInfoMessage("Publicação efetuada com sucesso");
		} catch (Exception e) {
			super.displayErrorMessage("Ocorreu um erro");
			e.printStackTrace();
		}
	}
	public void unpublish() {
		try {
			getQuestionnaire().setPublished(false);
			questionnaireLocal.merge(getQuestionnaire());
			super.displayInfoMessage("Despublicação efetuada com sucesso");
		} catch (Exception e) {
			super.displayErrorMessage("Ocorreu um erro");
			e.printStackTrace();
		}
	}

}
