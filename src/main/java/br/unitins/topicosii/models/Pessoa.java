package br.unitins.topicosii.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
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

	private String imagem;
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(nullable = true)
	private List<Telefone> telefones;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(unique = true)
	private Endereco endereco;
	public String getImagem() {
		return imagem;
	}
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	public Endereco getEndereco() {
		if(endereco==null) {
			this.setEndereco(new Endereco());
		}
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public List<Telefone> getTelefones() {
		if(this.telefones==null) {
			this.setTelefones(new ArrayList<Telefone>());
			this.telefones.add(new Telefone());
			}
		return telefones;
	}
	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
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
