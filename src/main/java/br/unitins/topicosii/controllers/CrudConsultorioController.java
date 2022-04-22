package br.unitins.topicosii.controllers;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.unitins.topicosii.application.RepositoryException;
import br.unitins.topicosii.application.Util;
import br.unitins.topicosii.listing.ConsultorioListing;
import br.unitins.topicosii.listing.PessoaListing;
import br.unitins.topicosii.models.Cidade;
import br.unitins.topicosii.models.Consultorio;
import br.unitins.topicosii.models.Pessoa;
import br.unitins.topicosii.respository.CidadeRepository;
import br.unitins.topicosii.respository.ConsultorioRepository;

@Named
@ViewScoped
public class CrudConsultorioController implements Serializable{

	private Consultorio entity;
	private List<Cidade> cidades;
	
	

	private static final long serialVersionUID = 1L;
	
	public void abrirConsultorioListing() {
		ConsultorioListing listing = new ConsultorioListing();
		listing.open();
	}
	
	
	public void obterConsultorioListing(SelectEvent<Consultorio> event) {
		this.setEntity(event.getObject());
	}


	public Consultorio getEntity() {
		if(this.entity==null)
			this.entity=new Consultorio();
		return entity;
	}


	public void setEntity(Consultorio entity) {
		this.entity = entity;
	}
	
	public void salvar() {
		ConsultorioRepository repo = new ConsultorioRepository();
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
		ConsultorioRepository repo = new ConsultorioRepository();
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
		ConsultorioRepository repo = new ConsultorioRepository();
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


	public List<Cidade> getCidades() {
		if(this.cidades==null) {
			try {
				CidadeRepository repo = new CidadeRepository();
				this.setCidades(repo.findAll());
			} catch (RepositoryException e) {
				e.printStackTrace();
				Util.addErrorMessage("Erro ao buscar as cidades");
			}
		}
		return cidades;
	}


	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}




}
