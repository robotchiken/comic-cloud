package com.takuba.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjuster;
import java.util.Calendar;
import java.util.Date;

public class FechasUtil {
	public static Date calcularFecha(Date input, int numeroSemanas) {
		LocalDate date = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		TemporalAdjuster temporalAdjuster = t -> t.plus(Period.ofWeeks(numeroSemanas));
		LocalDate result = date.with(temporalAdjuster);
		return asDate(result);
	}

	private static Date asDate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}

	private static Date asDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	private static LocalDate asLocalDate(Date date) {
		return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
	}

	private static LocalDateTime asLocalDateTime(Date date) {
		return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
	}
}
