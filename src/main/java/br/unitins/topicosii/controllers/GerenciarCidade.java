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
public class GerenciarCidade extends Controller<Cidade> implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private List<Estado> estados;
	
	
	public GerenciarCidade() {
		super(new CidadeRepository());
	}

	@Override
	public Cidade getEntity() {
		if (entity == null)
			entity = new Cidade();
		return entity;
	}
	
	public void abrirCidadeListing() {
		CidadeListing listing = new CidadeListing();
		listing.open();
	}

	public void obterCidadeListing(SelectEvent<Cidade> event) {
		this.setEntity(event.getObject());
	}

	public List<Estado> getEstados() {
		if(this.estados==null) {
			EstadoRepository repo = new EstadoRepository();
			try {
				this.setEstados(repo.findAll());
			}catch (RepositoryException e) {
				Util.addErrorMessage("Não foi possível buscar os estados");
			}
		}
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

}
