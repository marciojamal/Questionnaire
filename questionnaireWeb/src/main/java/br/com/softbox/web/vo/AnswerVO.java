package br.com.softbox.web.vo;

import java.util.ArrayList;
import java.util.List;
public class AnswerVO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7831376592839229877L;
	private Long id;
	private String openQuestion;
	private List<Long> optionIds = new ArrayList<Long>();
	private Long optionId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOpenQuestion() {
		return openQuestion;
	}
	public void setOpenQuestion(String openQuestion) {
		this.openQuestion = openQuestion;
	}
	public List<Long> getOptionIds() {
		return optionIds;
	}
	public void setOptionIds(List<Long> optionIds) {
		this.optionIds = optionIds;
	}
	@Override
	public String toString() {
		return "AnswerVO [id=" + id + ", openQuestion=" + openQuestion + ", optionIds=" + optionIds + ", optionId=" + optionId + "]";
	}
	public Long getOptionId() {
		return optionId;
	}
	public void setOptionId(Long optionId) {
		this.optionId = optionId;
	}


}
