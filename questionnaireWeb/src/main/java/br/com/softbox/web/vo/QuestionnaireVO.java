package br.com.softbox.web.vo;

import java.util.ArrayList;
import java.util.List;

public class QuestionnaireVO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1103862779306141572L;
	private long id;
	private String name;
	private List<QuestionVO> questions = new ArrayList<QuestionVO>(0);
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<QuestionVO> getQuestions() {
		return questions;
	}
	public void setQuestions(List<QuestionVO> questions) {
		this.questions = questions;
	}
	@Override
	public String toString() {
		return "QuestionnaireVO [id=" + id + ", name=" + name + ", questions=" + questions + "]";
	}

}
