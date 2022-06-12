package br.unitins.topicosii.controllers;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.unitins.topicosii.listing.PessoaListing;
import br.unitins.topicosii.models.Paciente;
import br.unitins.topicosii.models.Pessoa;
import br.unitins.topicosii.respository.PacienteRepository;

@Named
@ViewScoped
public class GerenciarPaciente extends Controller<Paciente> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GerenciarPaciente() {
		super(new PacienteRepository());
		
	}

	@Override
	public Paciente getEntity() {
		if (entity == null)
			entity = new Paciente();
		return null;
	}
	
	public void abrirPessoaListing() {
		PessoaListing listing = new PessoaListing();
		listing.open();
	}

	public void obterPessoaListing(SelectEvent<Pessoa> event) {
		this.getEntity().setPessoa(event.getObject());
	}

}
