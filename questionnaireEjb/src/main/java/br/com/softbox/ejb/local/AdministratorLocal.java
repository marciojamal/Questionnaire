package br.com.softbox.ejb.local;

import javax.ejb.Local;

import br.com.softbox.ejb.entity.Administrator;


@Local
public interface AdministratorLocal extends BaseDaoLocal<Administrator>{

}