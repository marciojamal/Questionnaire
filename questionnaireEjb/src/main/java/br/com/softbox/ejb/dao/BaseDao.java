package br.com.softbox.ejb.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.softbox.ejb.local.BaseDaoLocal;


public abstract class BaseDao<T> implements BaseDaoLocal<T> {

	private static final Log log = LogFactory.getLog(BaseDao.class);
	
	private Class<T> entityClass;

    public BaseDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
    
    protected abstract EntityManager getEntityManager();


	/* (non-Javadoc)
	 * @see br.com.softbox.ejb.dao.BaseDaoLocal#persist(T)
	 */
	@Override
	public void persist(T transientInstance) {
		log.debug(entityClass.getClass()+"persisting T instance");
		try {
			getEntityManager().persist(transientInstance);
			log.debug(entityClass.getClass()+"persist successful");
		} catch (RuntimeException re) {
			log.debug(entityClass.getClass()+"persist failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see br.com.softbox.ejb.dao.BaseDaoLocal#remove(T)
	 */
	@Override
	public void remove(T persistentInstance) {
		log.debug(entityClass.getClass()+"removing T instance");
		try {
			getEntityManager().remove(persistentInstance);
			log.debug(entityClass.getClass()+"remove successful");
		} catch (RuntimeException re) {
			log.debug(entityClass.getClass()+"remove failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see br.com.softbox.ejb.dao.BaseDaoLocal#merge(T)
	 */
	@Override
	public T merge(T detachedInstance) {
		log.debug(entityClass.getClass()+"merging T instance");
		try {
			T result = getEntityManager().merge(detachedInstance);
			log.debug(entityClass.getClass()+"merge successful");
			return result;
		} catch (RuntimeException re) {
			log.debug(entityClass.getClass()+"merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see br.com.softbox.ejb.dao.BaseDaoLocal#findById(java.lang.Integer)
	 */
	@Override
	public T findById(Object id) {
		log.debug(entityClass.getClass()+"getting T instance with id: " + id);
		try {
			T instance = getEntityManager().find(entityClass, id);
			log.debug(entityClass.getClass()+"get successful");
			return instance;
		} catch (RuntimeException re) {
			log.debug(entityClass.getClass()+"get failed", re);
			throw re;
		}
	}
	
	/* (non-Javadoc)
	 * @see br.com.softbox.ejb.dao.BaseDaoLocal#findAll()
	 */
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

	/* (non-Javadoc)
	 * @see br.com.softbox.ejb.dao.BaseDaoLocal#findRange(int[])
	 */
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

	/* (non-Javadoc)
	 * @see br.com.softbox.ejb.dao.BaseDaoLocal#count()
	 */
	@Override
	public int count() {
        CriteriaQuery<Object> cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

}