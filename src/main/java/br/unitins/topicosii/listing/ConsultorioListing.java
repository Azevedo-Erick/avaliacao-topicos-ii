package br.unitins.topicosii.listing;


import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.topicosii.application.RepositoryException;
import br.unitins.topicosii.application.Util;
import br.unitins.topicosii.models.Consultorio;
import br.unitins.topicosii.respository.ConsultorioRepository;

@Named
@ViewScoped
public class ConsultorioListing extends Listing<Consultorio>{


	private static final long serialVersionUID = 1L;
	private String filtro;
	public ConsultorioListing() {
		super("/root/listings/listing-consultorio", new ConsultorioRepository());
	}
	@Override
	public void pesquisar() {
		ConsultorioRepository repo = new ConsultorioRepository();
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