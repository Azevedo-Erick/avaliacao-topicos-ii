package br.unitins.topicosii.controllers;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.topicosii.application.RepositoryException;
import br.unitins.topicosii.application.Util;
import br.unitins.topicosii.models.Paciente;
import br.unitins.topicosii.models.Pessoa;
import br.unitins.topicosii.respository.PacienteRepository;
import br.unitins.topicosii.respository.PessoaRepository;

@Named
@ViewScoped
public class LoginPacienteController implements Serializable{

	private static final long serialVersionUID = 1L;
	private Paciente paciente;

	public void login() {
		PessoaRepository repository = new PessoaRepository();
		PacienteRepository pacienteRepository = new PacienteRepository();
		Pessoa pessoaLogin = null;
		try {
			pessoaLogin = repository.findByEmailESenha(paciente);
		}catch (RepositoryException e) {
			
		}
		try {
			System.out.println(pessoaLogin.getEmail());
			System.out.println(pessoaLogin.getSenha());
			if(pessoaLogin!=null) {
				
				if(pacienteRepository.findPessoaPaciente(paciente)!=null) {
					Util.addInfoMessage("Sucesso!!");
				}else {
					Util.addWarnMessage("Usuário não é um paciente");
				}
			}
		}catch (RepositoryException e) {
			Util.addErrorMessage(e.getMessage());
		}
	}
	public void cadastro(){
		PessoaRepository repository = new PessoaRepository();
		PacienteRepository pacienteRepository = new PacienteRepository();
		Pessoa pessoaLogin = null;
		try {
			pessoaLogin = repository.findByEmailESenha(paciente);
		}catch (RepositoryException e) {
			
		}
		try {
			if(pessoaLogin==null) {
				
				pacienteRepository.save(paciente);
				Util.addInfoMessage("Cadastrado");
			}else {
				Util.addWarnMessage("Essa pessoa já está cadastrada");
			}
		}catch (RepositoryException e) {
			Util.addErrorMessage(e.getMessage());
		}
	}
	public void redirect(String page) {
		Util.redirect(page);
	}
	public Paciente getPaciente() {
		if(this.paciente==null)
			this.paciente=new Paciente();
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
	
}
