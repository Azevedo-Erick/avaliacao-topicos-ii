package br.unitins.topicosii.respository;

import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;

import br.unitins.topicosii.application.JPAUtil;
import br.unitins.topicosii.application.RepositoryException;
import br.unitins.topicosii.application.Util;
import br.unitins.topicosii.models.DefaultEntity;

public class Repository<T extends DefaultEntity> {
	
	private EntityManager  entityManager;
	
	
	public Repository() {
		super();
		setEntityManager(JPAUtil.getEntityManager());
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void save(T entity) throws RepositoryException{
		try {
			getEntityManager().getTransaction().begin();
			getEntityManager().merge(entity);
			getEntityManager().getTransaction().commit();
		}catch (Exception e) {
			System.out.println("Erro ao salvar");
			e.printStackTrace();
			try {
				getEntityManager().getTransaction().rollback();
			}catch (Exception e1) {
				e1.printStackTrace();
			}
			throw new RepositoryException("Ocorreu um erro ao salvar");
		}

	}
	
	public void save(T... entities) throws RepositoryException{
		try {
			
			getEntityManager().getTransaction().begin();
			for(T entity:entities) {
				getEntityManager().merge(entity);				
			}
			getEntityManager().getTransaction().commit();
		}catch (Exception e) {
			System.out.println("Erro ao salvar");
			try {
				getEntityManager().getTransaction().rollback();
				
			}catch (Exception e1) {
				e.printStackTrace();
			
			}
			throw new RepositoryException("Erro ao salvar");
		}
	}
	

	
	public void remove(T entity) throws RepositoryException{
		try {
			
		getEntityManager().getTransaction().begin();
		getEntityManager().remove(getEntityManager().merge(entity));
		getEntityManager().getTransaction().commit();
		}catch (Exception e) {
			System.out.println("Houveram problemas ao excluir");
			try {
				getEntityManager().getTransaction().rollback();
			}catch (Exception e1) {
				e1.printStackTrace();
			}
			throw  new RepositoryException("Problema ao remover ");
		}
	}
	
	public T findById(int id) throws RepositoryException{
		try {
			final ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
			Class<T> tClass = (Class<T>) (type).getActualTypeArguments()[0];
			T t = (T) getEntityManager().find(tClass, id);
			return t;
		}catch (Exception e) {
			System.out.println("Erro ao buscar o id");
			e.printStackTrace();
			throw new RepositoryException("Erro ao buscar dados");
		}
	}
	
	
	
	
	
}
