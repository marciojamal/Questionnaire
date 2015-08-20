package br.com.softbox.web.vo;

public class QuestionnaireHomeVO implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6660153315069925567L;
	private String name;
	private String logoName;
	private String link;
	
	
	public QuestionnaireHomeVO() {
	}
	public QuestionnaireHomeVO(String name, String logoName, String link) {
		super();
		this.name = name;
		this.logoName = logoName;
		this.link = link;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLogoName() {
		return logoName;
	}
	public void setLogoName(String logoName) {
		this.logoName = logoName;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	@Override
	public String toString() {
		return "QuestionnaireHomeVO [name=" + name + ", logoName=" + logoName + ", link=" + link + "]";
	}

}
