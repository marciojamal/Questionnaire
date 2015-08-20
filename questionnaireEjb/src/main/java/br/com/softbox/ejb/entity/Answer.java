package br.com.softbox.ejb.entity;
// Generated 17/08/2015 18:01:24 by Hibernate Tools 3.4.0.CR1

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Answer generated by hbm2java
 */
@Entity
@Table(name = "answer", catalog = "questionnaire")
public class Answer implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6671783499675759741L;
	private Long id;
	private Questionnaire questionnaire;
	private UserAnswer userAnswer;
	private String openQuestion;
	private Date creationDate;
	private Question question;
	private Set<Option> options = new HashSet<Option>(0);

	public Answer() {
	}

	public Answer(Questionnaire questionnaire, UserAnswer userAnswer) {
		this.questionnaire = questionnaire;
		this.userAnswer = userAnswer;
	}

	public Answer(Questionnaire questionnaire, UserAnswer userAnswer, String openQuestion, Date creationDate, Set<Option> options) {
		this.questionnaire = questionnaire;
		this.userAnswer = userAnswer;
		this.openQuestion = openQuestion;
		this.creationDate = creationDate;
		this.options = options;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "questionnaire_id", nullable = false)
	public Questionnaire getQuestionnaire() {
		return this.questionnaire;
	}

	public void setQuestionnaire(Questionnaire questionnaire) {
		this.questionnaire = questionnaire;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_answer_id", nullable = false)
	public UserAnswer getUserAnswer() {
		return this.userAnswer;
	}

	public void setUserAnswer(UserAnswer userAnswer) {
		this.userAnswer = userAnswer;
	}

	@Column(name = "open_question", length = 65535)
	public String getOpenQuestion() {
		return this.openQuestion;
	}

	public void setOpenQuestion(String openQuestion) {
		this.openQuestion = openQuestion;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creation_date", length = 19)
	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "answer_has_option", catalog = "questionnaire", joinColumns = {
			@JoinColumn(name = "answer_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "option_id", nullable = false, updatable = false) })
	public Set<Option> getOptions() {
		return this.options;
	}

	public void setOptions(Set<Option> options) {
		this.options = options;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "question_id", nullable = false)
	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	@Override
	public String toString() {
		return "Answer [id=" + id + ", openQuestion=" + openQuestion + "]";
	}

}