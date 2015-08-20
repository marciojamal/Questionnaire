package br.com.softbox.web.bean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.softbox.ejb.entity.Questionnaire;
import br.com.softbox.ejb.local.AnswerLocal;
import br.com.softbox.ejb.local.QuestionnaireLocal;
import br.com.softbox.web.vo.QuestionnaireHomeVO;

@RequestScoped
@ManagedBean
public class HomeBean extends BaseBean{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9029625279906198340L;

	@EJB
	private QuestionnaireLocal	questionnaireLocal;
	@EJB
	private AnswerLocal	answerLocal;

	private List<QuestionnaireHomeVO> questionnaireHomeVOs;
	
	private List<Questionnaire> getQuestionnaires(){
		List<Questionnaire> questionnaires = questionnaireLocal.findPublished();
		return questionnaires;
	}
	public List<QuestionnaireHomeVO> getQuestionnairesHome(){
		List<Questionnaire> questionnaires;
		if(questionnaireHomeVOs==null){
			questionnaires = getQuestionnaires();
			questionnaireHomeVOs =  new ArrayList<QuestionnaireHomeVO>();
			List<Long> ids=null;
			if(getUserBean().getUserAnswer()!=null ){
				ids = answerLocal.findQuestionnaireIdsByUser(getUserBean().getUserAnswer().getId());
			}
			for (Questionnaire questionnaire : questionnaires) {
				QuestionnaireHomeVO vo = new QuestionnaireHomeVO();
				if(getUserBean().getUserAnswer()!=null){
					if(ids != null && ids.contains(questionnaire.getId())){//já participou
						vo.setLink("respostas/"+questionnaire.getId());
						vo.setLogoName("logo.png");
						vo.setName(questionnaire.getName());
					}else{
						vo.setLink("questionario/"+questionnaire.getId());
						vo.setLogoName("logoVermelha.png");
						vo.setName(questionnaire.getName());
					}
					
				}else{
					vo.setLink("#");
					vo.setLogoName("logoAzul.png");
					vo.setName(questionnaire.getName());
				}
				questionnaireHomeVOs.add(vo);
			}
			
		}
		return questionnaireHomeVOs;
	}

}
