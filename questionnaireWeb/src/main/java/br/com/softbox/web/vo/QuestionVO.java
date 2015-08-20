package br.com.softbox.web.vo;

import java.util.ArrayList;
import java.util.List;

import br.com.softbox.ejb.entity.Option;
import br.com.softbox.ejb.entity.QuestionType;

public class QuestionVO implements java.io.Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 3151309749614809030L;
	private Long id;
	private String question;
	private AnswerVO answer = new AnswerVO();
	private QuestionType questionType;
	private List<Option> options = new ArrayList<Option>();

	public QuestionVO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public AnswerVO getAnswer() {
		return answer;
	}

	public void setAnswer(AnswerVO answer) {
		this.answer = answer;
	}

	public List<Option> getOptions() {
		return options;
	}

	public void setOptions(List<Option> options) {
		this.options = options;
	}

	public QuestionType getQuestionType() {
		return questionType;
	}

	public void setQuestionType(QuestionType questionType) {
		this.questionType = questionType;
	}

	@Override
	public String toString() {
		return "QuestionVO [id=" + id + ", answer=" + answer + "]";
	}


}