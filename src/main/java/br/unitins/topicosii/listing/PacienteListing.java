package br.unitins.topicosii.listing;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.topicosii.application.RepositoryException;
import br.unitins.topicosii.application.Util;
import br.unitins.topicosii.models.Paciente;
import br.unitins.topicosii.respository.PacienteRepository;

@Named
@ViewScoped
public class PacienteListing extends Listing<Paciente>{


	private static final long serialVersionUID = 1L;
	private String filtro;
	public PacienteListing() {
		super("pacientelisting", new PacienteRepository());
	}
	
	@Override
	public void pesquisar() {
		PacienteRepository repo = new PacienteRepository();
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
