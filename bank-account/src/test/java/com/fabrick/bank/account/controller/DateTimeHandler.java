package com.fabrick.bank.account.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



public class DateTimeHandler {

	private static final String formatDate = "yyyy-MM-dd";

	public static String getDateFormated(LocalDateTime dataTime) {

		return DateTimeFormatter.ofPattern(formatDate).format(dataTime);
	}

	public static LocalDateTime getDateParsed(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatDate);
		return LocalDateTime.parse(date.trim(), formatter);

	}

}