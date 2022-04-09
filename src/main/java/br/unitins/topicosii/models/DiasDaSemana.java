package br.unitins.topicosii.models;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public enum DiasDaSemana {
	
	DOMINGO(1,"Domingo","DOM"),SEGUNDA(2,"Segunda-Feira","SEG"),TERCA(3,"Terça-Feira","TER"),QUARTA(4,"Quarta-Feira","QUA"),QUINTA(5,"Quinta-Feira","QUI"),SEXTA(6,"Sexta-Feira","SEX"),SABADO(7,"Sabado","SAB");
	
	private int id;
	private String label;
	private String shortLabel;
	DiasDaSemana(int id, String label,String shortLabel){
		this.id = id;
		this.label=label;
		this.shortLabel=shortLabel;
	}
	public int getId() {
		return id;
	}
	public String getLabel() {
		return label;
	}
	
	public static DiasDaSemana valueOf(int id) {
		for (DiasDaSemana DiaDaSemana : DiasDaSemana.values()) {
			if (DiaDaSemana.getId() == id)
				return DiaDaSemana;
		}
		return null;
	}
	public String getShortLabel() {
		return shortLabel;
	}
	
	public LocalDate getNextWeekDay(LocalDate data) {
		switch (this) {
		case DOMINGO:
			return data.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
		case SEGUNDA:
			return data.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
		case TERCA:
			return data.with(TemporalAdjusters.next(DayOfWeek.TUESDAY));
		case QUARTA:
			return data.with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY));
		case QUINTA:
			return data.with(TemporalAdjusters.next(DayOfWeek.THURSDAY));
		case SEXTA:
			return data.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
		case SABADO:
			return data.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
		default:
			return null;
		}
	}
}
