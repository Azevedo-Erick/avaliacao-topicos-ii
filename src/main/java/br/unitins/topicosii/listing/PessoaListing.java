package br.unitins.topicosii.listing;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.topicosii.application.RepositoryException;
import br.unitins.topicosii.application.Util;
import br.unitins.topicosii.models.Pessoa;
import br.unitins.topicosii.respository.PessoaRepository;

@Named
@ViewScoped
public class PessoaListing extends Listing<Pessoa>{


	private static final long serialVersionUID = 1L;
	private String filtro;
	public PessoaListing() {
		super("/root/listings/listing-pessoa", new PessoaRepository());
	}
	
	@Override
	public void pesquisar() {
		PessoaRepository repo = new PessoaRepository();
		try {
			setList(repo.findByNome(filtro));
		} catch (RepositoryException e) {
			e.printStackTrace();
			Util.addErrorMessage("Problema ao realizar a consulta.");
		}
	}
	public String getFiltro() {
		return filtro;
	}
	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

}
