package br.com.softbox.web.admin.bean;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.softbox.ejb.entity.Questionnaire;
import br.com.softbox.ejb.entity.UserAnswer;
import br.com.softbox.ejb.local.AnswerLocal;
import br.com.softbox.ejb.local.UserAnswerLocal;

@ViewScoped
@ManagedBean
public class AdminUserBean extends AdminBaseBean{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6964373088515266520L;

	@EJB
	private UserAnswerLocal	userAnswerLocal;
	@EJB
	private AnswerLocal	answerLocal;

	private List<UserAnswer> userAnswers;
	private List<Questionnaire> questionnaires;
	
	public List<UserAnswer> getUserAnswers(){
		if(userAnswers==null){
			userAnswers = userAnswerLocal.findAll();
		}
		return userAnswers;
	}
	
	public List<Questionnaire> getQuestionnaires(){
		if(questionnaires==null){
			questionnaires = answerLocal.findQuestionnaireByUser(getUserAnswerId());
		}
		return questionnaires;
	}
	

//	public UserAnswer getBeanUserAnswer() {
//		if(beanUserAnswer==null){
//			if(getUserAnswerId()!=null && !getUserAnswerId().equals(0L)){
//				beanUserAnswer = userAnswerLocal.findById(getUserAnswerId());
//			}else{
//				beanUserAnswer = new UserAnswer();
//			}
//		}
//		return beanUserAnswer;
//	}

	public Integer getUserAnswerId() {
		String userAnswerId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("userId");
		if(userAnswerId!=null){
			try {
				return Integer.parseInt(userAnswerId);
			} catch (Exception e) {
				return null;
			}
		}else{
			return null;
		}
	}

}
