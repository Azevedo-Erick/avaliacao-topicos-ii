package br.unitins.topicosii.controllers;

import java.io.Serializable;
import java.util.List;

import org.primefaces.event.SelectEvent;

import br.unitins.topicosii.application.RepositoryException;
import br.unitins.topicosii.application.Util;
import br.unitins.topicosii.listing.CidadeListing;
import br.unitins.topicosii.listing.ConsultorioListing;
import br.unitins.topicosii.models.Cidade;
import br.unitins.topicosii.models.Consultorio;
import br.unitins.topicosii.respository.CidadeRepository;
import br.unitins.topicosii.respository.ConsultorioRepository;

public class GerenciarConsultorio extends Controller<Consultorio> implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Cidade> cidades;

	public GerenciarConsultorio() {
		super(new ConsultorioRepository());
	}

	@Override
	public Consultorio getEntity() {
		if (entity == null)
			entity = new Consultorio();
		return entity;
	}

	public void abrirConsultorioListing() {
		ConsultorioListing listing = new ConsultorioListing();
		listing.open();
	}

	public void obterConsultorioListing(SelectEvent<Consultorio> event) {
		this.setEntity(event.getObject());

	}

	public void abrirCidadeListing() {
		CidadeListing listing = new CidadeListing();
		listing.open();
	}

	public void obterCidadeListing(SelectEvent<Cidade> event) {
		this.getEntity().getEndereco().setCidade(event.getObject());

	}

	public List<Cidade> getCidades() {
		if (this.cidades == null) {
			CidadeRepository repo = new CidadeRepository();
			try {
				this.setCidades(repo.findAll());
			} catch (RepositoryException e) {
				Util.addErrorMessage("Não foi possível buscar os estados");
			}
		}
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

}
