package br.unitins.topicosii.application;

public class Avaliacao {
	private String nome;
	private int avaliacao;
	private String titulo;
	private String descricao;
	
	public Avaliacao(String nome,int avaliacao,String titulo,String descricao){
		this.setNome(nome);
		this.setAvaliacao(avaliacao);
		this.setTitulo(titulo);
		this.setDescricao(descricao);
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getAvaliacao() {
		return avaliacao;
	}
	public void setAvaliacao(int avaliacao) {
		this.avaliacao = avaliacao;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
