package br.unitins.topicosii.application;

import java.time.LocalDate;
import java.util.Date;

public class Evento {
	private String titulo;
	private String texto;
	private LocalDate data;
	private String color;
	private String icon;
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Evento(String titulo, String texto, LocalDate data, String color, String Icon){
		this.titulo=titulo;
		this.texto = texto;
		this.setData(data);
		this.setColor(color);
		this.setIcon(Icon);
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	
}
