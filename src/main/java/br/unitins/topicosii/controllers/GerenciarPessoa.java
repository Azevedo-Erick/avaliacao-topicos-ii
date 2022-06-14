package br.unitins.topicosii.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.unitins.topicosii.application.RepositoryException;
import br.unitins.topicosii.listing.CidadeListing;
import br.unitins.topicosii.listing.PessoaListing;
import br.unitins.topicosii.models.Cidade;
import br.unitins.topicosii.models.Pessoa;
import br.unitins.topicosii.models.Telefone;
import br.unitins.topicosii.respository.CidadeRepository;
import br.unitins.topicosii.respository.PessoaRepository;

@Named
@ViewScoped
public class GerenciarPessoa extends Controller<Pessoa> implements Serializable {

	private static final long serialVersionUID = 1L;
	private Telefone telefone;

	public GerenciarPessoa() {
		super(new PessoaRepository());
	}

	@Override
	public Pessoa getEntity() {
		if (entity == null) {
			this.setEntity(new Pessoa());
		}
		return entity;
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

	public Telefone getTelefone() {
		if (this.telefone == null) {
			this.telefone = new Telefone();
		}
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public void adicionarTelefone() {
		this.getEntity().getTelefones().add(telefone);
	}

	public void removerTelefone(Telefone tel) {
		this.getEntity().getTelefones().remove(tel);
	}

	public void editarTelefone() {
		this.setTelefone(telefone);
	}

	public List<Cidade> completeCidade(String filtro) {
		CidadeRepository repo = new CidadeRepository();
		try {
			return repo.findByNome(filtro, 4);
		} catch (RepositoryException e) {
			e.printStackTrace();
			return new ArrayList<Cidade>();
		}
	}

}
