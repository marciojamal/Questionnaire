package br.com.softbox.ejb.local;

import javax.ejb.Local;

import br.com.softbox.ejb.entity.UserAnswer;


@Local
public interface UserAnswerLocal extends BaseDaoLocal<UserAnswer>{

	UserAnswer findByEmail(String email);

}