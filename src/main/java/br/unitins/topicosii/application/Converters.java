package br.unitins.topicosii.application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Converters {
	public static String getDateFormatted(LocalDateTime date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM");
		return formatter.format(date);
	}
	public static String getTimeFormatted(LocalDateTime date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm");
		return formatter.format(date);
	}
}
