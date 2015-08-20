package br.com.softbox.ejb.local;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import br.com.softbox.ejb.entity.Answer;
import br.com.softbox.ejb.entity.Questionnaire;


@Local
public interface AnswerLocal extends BaseDaoLocal<Answer>{

	void persistList(List<Answer> list);

	List<Answer> findByUserAndQuestionnaire(int userId, Long questionnaireId);

	List<Long> findQuestionnaireIdsByUser(int userId);

	Map<Answer, List<String>> findMapByUserAndQuestionnaire(int userId, Long questionnaireId);

	List<Questionnaire> findQuestionnaireByUser(int userId);

}