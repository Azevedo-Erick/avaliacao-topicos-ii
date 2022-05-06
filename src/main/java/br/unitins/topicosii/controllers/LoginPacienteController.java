package br.unitins.topicosii.controllers;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.topicosii.application.RepositoryException;
import br.unitins.topicosii.application.Session;
import br.unitins.topicosii.application.Util;
import br.unitins.topicosii.models.Paciente;
import br.unitins.topicosii.models.Pessoa;
import br.unitins.topicosii.models.Psicologo;
import br.unitins.topicosii.models.Telefone;
import br.unitins.topicosii.respository.PacienteRepository;
import br.unitins.topicosii.respository.PsicologoRepository;

@Named
@ViewScoped
public class LoginPacienteController implements Serializable {

	private static final long serialVersionUID = 1L;
	private Pessoa pessoa;

	private boolean logarComoPaciente=true;
	
	public void login() {
		if(logarComoPaciente) {
			this.loginAsPaciente();
		}else {
			this.loginAsPsicologo();
		}
		
	}
	private void loginAsPaciente() {
		PacienteRepository pacienteRepository = new PacienteRepository();
		Paciente paciente = new Paciente();
		paciente.setPessoa(pessoa);
		paciente.getPessoa().setTelefones(new ArrayList<Telefone>());
		try {
			if (pacienteRepository.findByEmailESenha(paciente) != null) {
				Session.getInstance().set("pacientelogado", paciente);
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
			if (repository.findByEmailESenha(psicologo) != null) {
				Session.getInstance().set("psicologologado", psicologo);
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
		paciente.setPessoa(pessoa);
		paciente.getPessoa().setTelefones(new ArrayList<Telefone>());
		try {
			if (pacienteRepository.findByEmailESenha(paciente) == null) {
				pacienteRepository.save(paciente);
			} else {
				Util.addErrorMessage("Já há um cadastro");
			}
		} catch (RepositoryException e) {
			Util.addErrorMessage(e.getMessage());
		}

	}

	public void cadastro() {
		/*
		 * PessoaRepository repository = new PessoaRepository(); PacienteRepository
		 * pacienteRepository = new PacienteRepository(); Pessoa pessoaLogin = null; try
		 * { pessoaLogin = repository.findByEmailESenha(paciente); } catch
		 * (RepositoryException e) {
		 * 
		 * } try { if (pessoaLogin == null) {
		 * 
		 * pacienteRepository.save(paciente); Util.addInfoMessage("Cadastrado"); } else
		 * { Util.addWarnMessage("Essa pessoa já está cadastrada"); } } catch
		 * (RepositoryException e) { Util.addErrorMessage(e.getMessage()); }
		 */
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
