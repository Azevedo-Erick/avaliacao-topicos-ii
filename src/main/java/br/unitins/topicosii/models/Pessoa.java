package br.unitins.topicosii.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class Pessoa extends DefaultEntity{
	private String nome;
	private String sobrenome;
	private String email;
	private String cpf;
	private LocalDate dataNascimento;
	private String senha;
	@OneToMany(cascade=CascadeType.ALL)
	private List<Telefone> telefone;
	@OneToOne(cascade=CascadeType.ALL)
	private Endereco endereco;
	public Endereco getEndereco() {
		if(endereco==null) {
			this.setEndereco(new Endereco());
		}
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public List<Telefone> getTelefone() {
		if(this.telefone==null) {
			this.setTelefone(new ArrayList<Telefone>());
			this.telefone.add(new Telefone());
			}
		return telefone;
	}
	public void setTelefone(List<Telefone> telefone) {
		this.telefone = telefone;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	 
}
