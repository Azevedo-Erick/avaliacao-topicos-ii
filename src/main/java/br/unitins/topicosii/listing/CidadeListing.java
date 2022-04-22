package br.unitins.topicosii.listing;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.topicosii.application.RepositoryException;
import br.unitins.topicosii.application.Util;
import br.unitins.topicosii.models.Cidade;
import br.unitins.topicosii.models.Paciente;
import br.unitins.topicosii.respository.CidadeRepository;
import br.unitins.topicosii.respository.PacienteRepository;
import br.unitins.topicosii.respository.PessoaRepository;

@Named
@ViewScoped
public class CidadeListing extends Listing<Cidade>{


	private static final long serialVersionUID = 1L;
	private String filtro;
	public CidadeListing() {
		super("cidadelisting", new CidadeRepository());
	}
	@Override
	public void pesquisar() {
		CidadeRepository repo = new CidadeRepository();
		try {
			this.setList(repo.findByNome(filtro));
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
