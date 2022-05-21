package br.unitins.topicosii.controllers;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.unitins.topicosii.application.RepositoryException;
import br.unitins.topicosii.application.Session;
import br.unitins.topicosii.application.Util;
import br.unitins.topicosii.application.VersionException;
import br.unitins.topicosii.listing.CidadeListing;
import br.unitins.topicosii.models.Cidade;
import br.unitins.topicosii.models.Psicologo;
import br.unitins.topicosii.respository.PacienteRepository;
import br.unitins.topicosii.respository.PsicologoRepository;

@Named
@ViewScoped
public class EditarInformacoesPsicologo implements Serializable{

	private static final long serialVersionUID = 1L;
	private Psicologo psicologo;
	private String senha;
	public String autenticar() {
		
		PsicologoRepository repository = new PsicologoRepository();
		try {
			Psicologo psico = repository.findByEmailESenha(this.psicologo.getPessoa().getEmail(), Util.hash(psicologo.getPessoa()));
			if(psico!=null) {
				return psico.getPessoa().getSenha();
			}else {
				return "";
			}
			
		}catch (RepositoryException e) {
			Util.addErrorMessage("Problemas na autenticação");
			return "";
		}
	}
	
	
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Psicologo getPsicologo() {
		if(this.psicologo==null) {
			this.psicologo= (Psicologo) Session.getInstance().get("psicologoLogado");
		}
		return psicologo;
	}
	public void setPsicologo(Psicologo psicologo) {
		this.psicologo = psicologo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public void abrirCidadeListing() {
		CidadeListing listing = new CidadeListing();
		listing.open();
	}
	
	
	public void obterCidadeListing(SelectEvent<Cidade> event) {
		this.getPsicologo().getPessoa().getEndereco().setCidade(event.getObject());
	}

	
	public void salvar() {
		System.out.println("Atualizando");
		PsicologoRepository repository = new PsicologoRepository();
		if(this.autenticar().equals(Util.hash(this.psicologo.getPessoa()))) {
			try {
				repository.save(this.getPsicologo());
				Util.addInfoMessage("Dados atualizados com sucesso");
			}catch(RepositoryException e){
				e.printStackTrace();
				Util.addErrorMessage(e.getMessage());
			}catch(VersionException e) {
				e.printStackTrace();
				Util.addErrorMessage(e.getMessage());
			}
		}else {
			Util.addErrorMessage("Dados não salvos");
		}
		this.setSenha("");
	}
	
	public void redirect(String page) {
		Util.redirect(page);
	}

}
