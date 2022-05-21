package br.unitins.topicosii.controllers;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.topicosii.application.RepositoryException;
import br.unitins.topicosii.application.Util;
import br.unitins.topicosii.application.VersionException;
import br.unitins.topicosii.models.DefaultEntity;
import br.unitins.topicosii.respository.Repository;

@Named
@ViewScoped
public abstract class Controller<T extends DefaultEntity> implements Serializable {

	private static final long serialVersionUID = 1L;
	private Repository<T> repository;
	protected T entity;

	protected void limparRelacionamentosNaoObrigatorios() {

	}

	public void alterar() {
		try {
			limparRelacionamentosNaoObrigatorios();
			this.setEntity(getRepository().save(getEntity()));
			Util.addInfoMessage("Alteração realizada com sucesso.");
			limpar();
		} catch (RepositoryException e) {
			e.printStackTrace();
			Util.addErrorMessage(e.getMessage());
		} catch (VersionException e) {
			e.printStackTrace();
			Util.addErrorMessage(e.getMessage());
		}
	}

	public Controller(Repository<T> repository) {
		super();
		this.repository = repository;
	}

	public void salvar() {
		try {
			getRepository().save(getEntity());
			Util.addInfoMessage("Salvo com sucesso");
			limpar();
		} catch (RepositoryException e) {
			e.printStackTrace();
			Util.addErrorMessage(e.getMessage());
		} catch (VersionException e) {
			e.printStackTrace();
			Util.addErrorMessage(e.getMessage());
		}
	}

	public void editar() {
		try {
			getRepository().save(entity);
			Util.addInfoMessage("Edição realizada com sucesso");
			limpar();
		} catch (RepositoryException e) {
			e.printStackTrace();
			Util.addErrorMessage(e.getMessage());
		} catch (VersionException e) {
			e.printStackTrace();
			Util.addErrorMessage(e.getMessage());
		}
	}

	public void excluir() {
		try {
			getRepository().remove(entity);
			Util.addInfoMessage("Exclusão realizada com sucesso");
			limpar();
		} catch (RepositoryException e) {
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

	public abstract T getEntity();

	public void setEntity(T entity) {
		this.entity = entity;
	}
}
