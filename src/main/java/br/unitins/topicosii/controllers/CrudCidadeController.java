package br.unitins.topicosii.controllers;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.unitins.topicosii.application.RepositoryException;
import br.unitins.topicosii.application.Util;
import br.unitins.topicosii.listing.CidadeListing;
import br.unitins.topicosii.models.Cidade;
import br.unitins.topicosii.models.Estado;
import br.unitins.topicosii.respository.CidadeRepository;
import br.unitins.topicosii.respository.EstadoRepository;

@Named
@ViewScoped
public class CrudCidadeController  implements Serializable{

	
	private Cidade entity;
	private List<Estado> estados;



	public List<Estado> getEstados() {
		if(this.estados==null) {
			try {
				
			EstadoRepository repo = new EstadoRepository();
			this.setEstados(repo.findAll());
			}catch (RepositoryException e) {
				e.printStackTrace();
				Util.addErrorMessage("Problema ao buscar os estados");
			}
		}
		return estados;
	}


	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}


	private static final long serialVersionUID = 1L;

	
	public void abrirCidadeListing() {
		CidadeListing listing = new CidadeListing();
		listing.open();
	}
	
	
	public void obterCidadeListing(SelectEvent<Cidade> event) {
		this.setEntity(event.getObject());
	}
	
	public void salvar() {
		CidadeRepository repo = new CidadeRepository();
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
		CidadeRepository repo = new CidadeRepository();
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
		CidadeRepository repo = new CidadeRepository();
		try {
			repo.remove(entity);
			Util.addInfoMessage("Exclusão realizada com sucesso");
			limpar();
		}catch(RepositoryException e) {
			e.printStackTrace();
			Util.addErrorMessage(e.getMessage());
		}
	}
	
	public Cidade getEntity() {
		if(this.entity==null)
			this.entity=new Cidade();
		return entity;
	}


	public void setEntity(Cidade entity) {
		this.entity = entity;
	}


	public void limpar() {
		this.setEntity(null);
	}

}
