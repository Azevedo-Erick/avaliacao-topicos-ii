package br.unitins.topicosii.controllers;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.unitins.topicosii.listing.CidadeListing;
import br.unitins.topicosii.models.Estado;
import br.unitins.topicosii.respository.EstadoRepository;

@Named
@ViewScoped
public class GerenciarEstado extends Controller<Estado> implements Serializable{


	private static final long serialVersionUID = 1L;

	public GerenciarEstado() {
		super(new EstadoRepository());
	}

	@Override
	public Estado getEntity() {
		if (entity == null)
			entity = new Estado();
		return entity;
	}
	
	public void abrirEstadoListing() {
		CidadeListing listing = new CidadeListing();
		listing.open();
	}

	public void obterEstadoListing(SelectEvent<Estado> event) {
		this.setEntity(event.getObject());
	}
	
}
