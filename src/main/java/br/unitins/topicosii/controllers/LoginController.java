package br.unitins.topicosii.controllers;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.topicosii.application.RepositoryException;
import br.unitins.topicosii.application.Session;
import br.unitins.topicosii.application.Util;
import br.unitins.topicosii.application.VersionException;
import br.unitins.topicosii.models.Paciente;
import br.unitins.topicosii.models.Pessoa;
import br.unitins.topicosii.models.Psicologo;
import br.unitins.topicosii.models.Telefone;
import br.unitins.topicosii.respository.PacienteRepository;
import br.unitins.topicosii.respository.PsicologoRepository;

@Named
@ViewScoped
public class LoginController implements Serializable {

	private static final long serialVersionUID = 1L;
	private Pessoa pessoa;

	private boolean logarComoPaciente=true;
	
	public void login() {
		this.pessoa.setSenha(Util.hash(pessoa));
		if(logarComoPaciente) {
			this.loginAsPaciente();
		}else {
			this.loginAsPsicologo();
		}
		this.getPessoa().setSenha("");
		
	}
	private void loginAsPaciente() {
		PacienteRepository pacienteRepository = new PacienteRepository();
		Paciente paciente = new Paciente();
		paciente.setPessoa(pessoa);
		paciente.getPessoa().setTelefones(new ArrayList<Telefone>());
		try {
			paciente = pacienteRepository.findByEmailESenha(paciente);
			if (paciente != null) {
				Session.getInstance().set("pacienteLogado", paciente);
				this.redirect("/agenda-paciente.xhtml");
			} else {
				Util.addWarnMessage("Informações incorretas");
			}

		} catch (

		RepositoryException e) {
			Util.addErrorMessage(e.getMessage());
		}
	}

	private void loginAsPsicologo() {
		PsicologoRepository repository = new PsicologoRepository();
		Psicologo psicologo = new Psicologo();
		psicologo.setPessoa(pessoa);
		psicologo.getPessoa().setTelefones(new ArrayList<Telefone>());
		try {
			psicologo=repository.findByEmailESenha(psicologo);
			if (psicologo != null) {
				System.out.println(psicologo.getPessoa().getNome());
				Session.getInstance().set("psicologoLogado", psicologo);
				this.redirect("/agenda-psicologo.xhtml");
			} else {
				Util.addWarnMessage("Informações incorretas");
			}

		} catch (

		RepositoryException e) {
			Util.addErrorMessage(e.getMessage());
		}
	}
	
	public void cadastrar() {
		PacienteRepository pacienteRepository = new PacienteRepository();
		Paciente paciente = new Paciente();
		this.pessoa.setSenha(Util.hash(pessoa));
		paciente.setPessoa(pessoa);
		paciente.getPessoa().setTelefones(new ArrayList<Telefone>());
		try {
			if (pacienteRepository.findByEmailESenha(paciente) == null) {
				pacienteRepository.save(paciente);
				this.pessoa=null;
				Util.addInfoMessage("Cadastro feito com sucesso");
			} else {
				Util.addErrorMessage("Já há um cadastro");
			}
		} catch (RepositoryException e) {
			Util.addErrorMessage(e.getMessage());
		}catch(VersionException e) {
			e.printStackTrace();
			Util.addErrorMessage(e.getMessage());
		}

	}


	public void redirect(String page) {
		Util.redirect(page);
	}

	public Pessoa getPessoa() {
		if(this.pessoa==null) {
			this.pessoa=new Pessoa();
		}
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public boolean islogarComoPaciente() {
		return logarComoPaciente;
	}
	public void setlogarComoPaciente(boolean logarComoPaciente) {
		this.logarComoPaciente = logarComoPaciente;
	}


}
