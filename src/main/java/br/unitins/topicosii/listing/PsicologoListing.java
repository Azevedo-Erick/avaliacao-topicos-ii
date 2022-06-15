package br.unitins.topicosii.listing;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.topicosii.application.RepositoryException;
import br.unitins.topicosii.application.Util;
import br.unitins.topicosii.models.Psicologo;
import br.unitins.topicosii.respository.PsicologoRepository;

@Named
@ViewScoped
public class PsicologoListing extends Listing<Psicologo>{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String filtro;
	public PsicologoListing() {
		super("/root/listings/listing-psicologo", new PsicologoRepository());
	}
	
	@Override
	public void pesquisar() {
		PsicologoRepository repo = new PsicologoRepository();
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
