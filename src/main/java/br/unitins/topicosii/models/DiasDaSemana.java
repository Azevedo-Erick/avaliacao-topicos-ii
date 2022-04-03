package br.unitins.topicosii.models;

public enum DiasDaSemana {
	
	DOMINGO(1,"Domingo"),SEGUNDA(2,"Segunda-Feira"),TERCA(3,"Terça-Feira"),QUARTA(4,"Quarta-Feira"),QUINTA(5,"Quinta-Feira"),SEXTA(6,"Sexta-Feira"),SABADO(7,"Sabado");
	
	private int id;
	private String label;
	DiasDaSemana(int id, String label){
		id = id;
		label=label;
	}
	public int getId() {
		return id;
	}
	public String getLabel() {
		return label;
	}
	
	
}
