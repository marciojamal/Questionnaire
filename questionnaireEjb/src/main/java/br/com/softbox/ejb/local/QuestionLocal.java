package br.com.softbox.ejb.local;

import java.util.List;

import javax.ejb.Local;

import br.com.softbox.ejb.entity.Option;
import br.com.softbox.ejb.entity.Question;


@Local
public interface QuestionLocal extends BaseDaoLocal<Question>{

	void persistWithOptions(Question newQuestion, List<Option> newOptions);
	
	Question mergeWithOptions(Question newQuestion, List<Option> newOptions);

}