package br.com.softbox.ejb.local;

import java.util.List;

import javax.ejb.Local;

import br.com.softbox.ejb.entity.Questionnaire;


@Local
public interface QuestionnaireLocal extends BaseDaoLocal<Questionnaire>{

	List<Questionnaire> findWithAnswer(int userId, Long questionnaireId);

	List<Questionnaire> findPublished();

}