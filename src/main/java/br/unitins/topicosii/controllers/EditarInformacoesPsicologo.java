package br.unitins.topicosii.controllers;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.topicosii.application.RepositoryException;
import br.unitins.topicosii.application.Util;
import br.unitins.topicosii.models.Psicologo;
import br.unitins.topicosii.respository.PacienteRepository;
import br.unitins.topicosii.respository.PsicologoRepository;

@Named
@ViewScoped
public class EditarInformacoesPsicologo implements Serializable{

	private static final long serialVersionUID = 1L;
	private Psicologo psicologo;
	public Psicologo getPsicologo() {
		return psicologo;
	}
	public void setPsicologo(Psicologo psicologo) {
		this.psicologo = psicologo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public void salvar() {
		PsicologoRepository repository = new PsicologoRepository();
		try {
			repository.save(this.getPsicologo());
			Util.addInfoMessage("Dados atualizados com sucesso");
		}catch(RepositoryException e){
			e.printStackTrace();
			Util.addErrorMessage(e.getMessage());
		}
	}
	
	public void redirect(String page) {
		Util.redirect(page);
	}

}
