package br.com.softbox.ejb.local;

import java.util.List;

public interface BaseDaoLocal<T> {

	void persist(T transientInstance);

	void remove(T persistentInstance);

	T merge(T detachedInstance);

	T findById(Object id);

	List<T> findAll();

	List<T> findRange(int[] range);

	int count();


}