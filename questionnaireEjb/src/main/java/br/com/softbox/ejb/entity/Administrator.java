package br.com.softbox.ejb.entity;
// Generated 17/08/2015 18:01:24 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Administrator generated by hbm2java
 */
@Entity
@Table(name = "administrator", catalog = "questionnaire")
public class Administrator implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8947848529400021957L;
	private Integer id;
	private String email;
	private String password;
	private String name;
	private Set<Questionnaire> questionnaires = new HashSet<Questionnaire>(0);

	public Administrator() {
	}

	public Administrator(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public Administrator(String email, String password, String name, Set<Questionnaire> questionnaires) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.questionnaires = questionnaires;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "email", nullable = false, length = 155)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "password", nullable = false)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "administrator")
	public Set<Questionnaire> getQuestionnaires() {
		return this.questionnaires;
	}

	public void setQuestionnaires(Set<Questionnaire> questionnaires) {
		this.questionnaires = questionnaires;
	}

}
