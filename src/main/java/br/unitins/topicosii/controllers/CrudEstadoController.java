package br.unitins.topicosii.controllers;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.unitins.topicosii.application.RepositoryException;
import br.unitins.topicosii.application.Util;
import br.unitins.topicosii.listing.EstadoListing;
import br.unitins.topicosii.models.Estado;
import br.unitins.topicosii.respository.EstadoRepository;

@Named
@ViewScoped
public class CrudEstadoController implements Serializable{
	
	
	private Estado entity;

	private static final long serialVersionUID = 1L;
	
	public void abrirEstadoListing() {
		EstadoListing listing = new EstadoListing();
		listing.open();
	}
	
	

	public Estado getEntity() {
		if (entity == null)
			entity = new Estado();
		return entity;
	}
	
	public void obterEstadoListing(SelectEvent<Estado> event) {
		System.out.println("get estado");
		this.setEntity(event.getObject());
	}



	public void setEntity(Estado entity) {
		this.entity = entity;
	}
	public void salvar() {
		EstadoRepository repo = new EstadoRepository();
		try {
			repo.save(getEntity());
			Util.addInfoMessage("Salvo com sucesso");
			limpar();
		}catch(RepositoryException e){
			e.printStackTrace();
			Util.addErrorMessage(e.getMessage());
		}
	}
	
	public void editar() {
		EstadoRepository repo = new EstadoRepository();
		try {
			repo.save(entity);
			Util.addInfoMessage("Edição realizada com sucesso");
			limpar();
		}catch(RepositoryException e) {
			e.printStackTrace();
			Util.addErrorMessage(e.getMessage());
		}
	}
	
	public void excluir() {
		EstadoRepository repo = new EstadoRepository();
		try {
			repo.remove(entity);
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

}
