package br.unitins.topicosii.controllers;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.unitins.topicosii.listing.PessoaListing;
import br.unitins.topicosii.models.Estado;
import br.unitins.topicosii.models.Pessoa;
import br.unitins.topicosii.models.Psicologo;
import br.unitins.topicosii.respository.PsicologoRepository;

@Named
@ViewScoped
public class GerenciarPsicologo extends Controller<Psicologo> implements Serializable{

	private static final long serialVersionUID = 1L;

	public GerenciarPsicologo() {
		super(new PsicologoRepository());
		// TODO Auto-generated constructor stub
	}

	@Override
	public Psicologo getEntity() {
		if (entity == null)
			entity = new Psicologo();
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
