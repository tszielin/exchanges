package tszielin.exchanges.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import tszielin.exchanges.domain.Identifiable;

/**
 * Base class for DAOs
 * 
 * @author Thomas Zielinski
 * @since 2015-11-29
 */
public abstract class AbstractDAO<T extends Identifiable<S>, S extends Serializable> {
    @PersistenceContext
	private EntityManager entityManager;
	
	private Class<T> cls;

	protected AbstractDAO(Class<T> cls) {
		this.cls = cls;
	}

	/**
	 * Get an object
	 * @param id identifier (primary key)
	 * @return the object
	 */
	@Transactional(readOnly=true)
	public T get(S id) {
		return entityManager.find(this.cls, id);
	}

	/**
	 * Save an object
	 * @param obj the object to be saved
	 * @return the object
	 */
	@Transactional
	public T save(T obj) {
		return entityManager.merge(obj);
	}

	/**
     * Remove an object
     * @param obj the object to be removed
     */
	@Transactional
	public void remove(T entity) {
		final T entityToRemove = entityManager.getReference(cls, entity.getId());
		entityManager.remove(entityToRemove);
	}

	/**
     * Remove an object
     * @param id identifier (primary key)
     */
	@Transactional
	public void remove(S key) {
		final T entity = entityManager.getReference(cls, key);
		if (entity != null) {
			entityManager.remove(entity);
		}
	}
	
	protected Class<T> getCls() {
		return cls;
	}

	protected void setCls(Class<T> clazz) {
		this.cls = clazz;
	}

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	protected void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
