package br.unitins.topicosii.controllers;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.unitins.topicosii.listing.CidadeListing;
import br.unitins.topicosii.listing.PessoaListing;
import br.unitins.topicosii.models.Cidade;
import br.unitins.topicosii.models.Pessoa;
import br.unitins.topicosii.respository.PessoaRepository;

@Named
@ViewScoped
public class GerenciarPessoa extends Controller<Pessoa> implements Serializable{

	
	private static final long serialVersionUID = 1L;

	public GerenciarPessoa() {
		super(new PessoaRepository());
		// TODO Auto-generated constructor stub
	}

	@Override
	public Pessoa getEntity() {
		if (entity == null)
			entity = new Pessoa();
		return null;
	}
	
	public void abrirPessoaListing() {
		PessoaListing listing = new PessoaListing();
		listing.open();
	}

	public void obterPessoaListing(SelectEvent<Pessoa> event) {
		this.setEntity(event.getObject());
	}
	
	public void abrirCidadeListing() {
		CidadeListing listing = new CidadeListing();
		listing.open();
	}

	public void obterCidadeListing(SelectEvent<Cidade> event) {
		this.getEntity().getEndereco().setCidade(event.getObject());
	}
	
	

}
