package br.com.softbox.ejb.entity;
// Generated 17/08/2015 18:01:24 by Hibernate Tools 3.4.0.CR1

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Question generated by hbm2java
 */
@Entity
@Table(name = "question", catalog = "questionnaire")
public class Question implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1582477759064130650L;

	private Long id;
	private Questionnaire questionnaire;
	private QuestionType questionType;
	private String question;
	private Date creationDate;
	private Set<Answer> answers = new HashSet<Answer>(0);
	private List<Option> options = new ArrayList<Option>();

	public Question() {
	}

	public Question(Questionnaire questionnaire, QuestionType questionType, String question) {
		this.questionnaire = questionnaire;
		this.questionType = questionType;
		this.question = question;
	}

	public Question(Questionnaire questionnaire, QuestionType questionType, String question, Date creationDate, List<Option> options) {
		this.questionnaire = questionnaire;
		this.questionType = questionType;
		this.question = question;
		this.creationDate = creationDate;
		this.options = options;
	}

	public Question(Long id) {
		this.id = id;
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
	@JoinColumn(name = "question_type_id", nullable = false)
	public QuestionType getQuestionType() {
		return this.questionType;
	}

	public void setQuestionType(QuestionType questionType) {
		this.questionType = questionType;
	}

	@Column(name = "question", nullable = false, length = 65535)
	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creation_date", length = 19)
	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "question", orphanRemoval = true)
	@OrderColumn(name="option_order")
	public List<Option> getOptions() {
		return this.options;
	}

	public void setOptions(List<Option> options) {
		this.options = options;
	}
	
	@Override
	public String toString() {
		return "Question [id=" + id + ", question=" + question + ", options=" + options + "]";
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "question")
	public Set<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(Set<Answer> answers) {
		this.answers = answers;
	}

}
