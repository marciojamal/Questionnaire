package br.com.softbox.web.admin.bean;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.softbox.ejb.entity.Questionnaire;
import br.com.softbox.ejb.local.QuestionnaireLocal;

@ViewScoped
@ManagedBean
public class AdminQuestionnaireBean extends AdminBaseBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5284139100641775747L;
	
	@EJB
	private QuestionnaireLocal	questionnaireLocal;

	private List<Questionnaire> questionnaires;
	private Questionnaire beanQuestionnaire;
	
	public List<Questionnaire> getQuestionnaires(){
		if(questionnaires==null){
			questionnaires = questionnaireLocal.findAll();
		}
		return questionnaires;
	}
	
	public String createQuestionnaire() {
		try {
			beanQuestionnaire.setAdministrator(getUserLogged());
			beanQuestionnaire.setCreationDate(new Date());
			questionnaireLocal.persist(beanQuestionnaire);
			return "/pages/restrict/questionList.jsf?questionnaireId="+beanQuestionnaire.getId()+"&faces-redirect=true";

		} catch (Exception e) {
			displayErrorMessage("Erro ao criar questionário.");
			return null;

		}finally {
			beanQuestionnaire = null;
		}
	}
	
	public String updateQuestionnaire() {
		try {
			beanQuestionnaire.setAdministrator(getUserLogged());
			beanQuestionnaire = questionnaireLocal.merge(beanQuestionnaire);
			return "/pages/restrict/questionList.jsf?questionnaireId="+beanQuestionnaire.getId()+"&faces-redirect=true";

		} catch (Exception e) {
			displayErrorMessage("Erro ao criar questionário.");
			return null;

		}finally {
			beanQuestionnaire = null;
		}
	}

	public Questionnaire getBeanQuestionnaire() {
		if(beanQuestionnaire==null){
			if(getQuestionnaireId()!=null && !getQuestionnaireId().equals(0L)){
				beanQuestionnaire = questionnaireLocal.findById(getQuestionnaireId());
			}else{
				beanQuestionnaire = new Questionnaire();
			}
		}
		return beanQuestionnaire;
	}

	public Long getQuestionnaireId() {
		String questionnaireId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("questionnaireId");
		if(questionnaireId!=null){
			try {
				return Long.parseLong(questionnaireId);
			} catch (Exception e) {
				return null;
			}
		}else{
			return null;
		}
	}

	public void setBeanQuestionnaire(Questionnaire beanQuestionnaire) {
		this.beanQuestionnaire = beanQuestionnaire;
	}
	
	public void removeQuestionnaire() {
		try {
			questionnaireLocal.remove(beanQuestionnaire);
			questionnaires.remove(beanQuestionnaire);
			beanQuestionnaire = null;
			displayInfoMessage("Removido com sucesso.");
		} catch (Exception e) {
			displayErrorMessage("Ocorreu um erro ao remover.");
		}
		
	}
}
