package br.unitins.topicosii.controllers;

import br.unitins.topicosii.application.RepositoryException;
import br.unitins.topicosii.application.Util;
import br.unitins.topicosii.models.DefaultEntity;
import br.unitins.topicosii.respository.Repository;

public abstract class Controller<T extends DefaultEntity> {
	
	private Repository<T> repository;
	protected T entity;
	
	public Controller(Repository<T> repository) {
		super();
		this.repository=repository;
	}
	
	public void incluir() {
		try {
			getRepository().save(getEntity());
			Util.addInfoMessage("Inclusão realizada com sucesso");
			limpar();
		}catch(RepositoryException e){
			e.printStackTrace();
			Util.addErrorMessage(e.getMessage());
		}
	}
	
	public void editar() {
		try {
			getRepository().save(entity);
			Util.addInfoMessage("Edição realizada com sucesso");
			limpar();
		}catch(RepositoryException e) {
			e.printStackTrace();
			Util.addErrorMessage(e.getMessage());
		}
	}
	
	public void excluir() {
		try {
			getRepository().remove(entity);
			Util.addInfoMessage("Exclusão realizada com sucesso");
			limpar();
		}catch(RepositoryException e) {
			e.printStackTrace();
			Util.addErrorMessage(e.getMessage());
		}
	}
	
	public void limpar() {
		this.setEntity(null);
	}
	
	public Repository<T> getRepository() {
		return repository;
	}

	public void setRepository(Repository<T> repository) {
		this.repository = repository;
	}

	public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}
}
