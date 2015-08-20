package br.com.softbox.web.bean;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.softbox.ejb.entity.UserAnswer;
import br.com.softbox.ejb.local.UserAnswerLocal;

@SessionScoped
@ManagedBean
public class UserBean extends BaseBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1424872651791632659L;

	public static final String MANAGED_BEAN_NAME = "userBean";
	
	@EJB
	private UserAnswerLocal userAnswerLocal;
	
	private String email;
	private String message;

	private UserAnswer userAnswer;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void login(){
		if(isEmailValid(email)){
			userAnswer = userAnswerLocal.findByEmail(email);
			if(userAnswer == null){
				userAnswer = new UserAnswer();
				userAnswer.setEmail(email);
				userAnswer.setCreationDate(new Date());
				userAnswerLocal.persist(userAnswer);
			}
			message=null;
		}else{
			message = "Email inválido";
			email=null;
			userAnswer=null;
		}
	}
	
	public void logout(){
		email = null;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	

	public static boolean isEmailValid(String email) {
        if ((email == null) || (email.trim().length() == 0))
            return false;

        String emailPattern = "\\b(^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@([A-Za-z0-9-])+(\\.[A-Za-z0-9-]+)*((\\.[A-Za-z0-9]{2,})|(\\.[A-Za-z0-9]{2,}\\.[A-Za-z0-9]{2,}))$)\\b";
        Pattern pattern = Pattern.compile(emailPattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
	public UserAnswer getUserAnswer() {
		return userAnswer;
	}
}
