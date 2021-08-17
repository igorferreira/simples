package com.github.igorferreira.simples;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.joda.time.LocalDate;

import static java.time.temporal.ChronoUnit.*;

public class App {
	
	private static final String LOG_INICIO = "----------------------------------------------------------------------------------INICIO";
	
	public static final String UTC_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
	public static final String BR_FORMAT = "dd-MM-yyy HH:mm:ss";

	public static void main(String[] args) throws ParseException {


		java.util.Date date= new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int month = cal.get(Calendar.MONTH);
		int ano = cal.get(Calendar.YEAR);

		System.out.println(day);
		System.out.println(month+1);
		System.out.println(ano);
		System.out.println("\n");

		LocalDateTime localDateTimeDefaultZon = LocalDateTime.now();
		System.out.println("LocalDateTime em Default Zone..............................: " + localDateTimeDefaultZon );

		LocalDateTime localDateTimeUTC = LocalDateTime.now(ZoneId.of("UTC"));
		System.out.println("LocalDateTime em UTC Zone..................................: " + localDateTimeUTC );

		Instant instant = localDateTimeUTC.atZone(ZoneId.of("UTC")).toInstant();
		Date dateUTC = Date.from(instant);
		System.out.println("Date em UTC Zone from instant from localDateTimeUTC........: " + dateUTC );

		System.out.println("\n");
		LocalDate theLocalDate = LocalDate.fromDateFields(dateUTC);
		System.out.println("Date em UTC.....: " + dateUTC );
		System.out.println("getMonthOfYear..: "+ theLocalDate.getMonthOfYear());
		System.out.println("getMonthOfYear..: "+ theLocalDate.getYear());

		System.out.println("\n\n\n\n\n\n\n");

		SimpleDateFormat sdfUTC = new SimpleDateFormat(UTC_FORMAT);
		sdfUTC.setTimeZone(TimeZone.getTimeZone("UTC"));

		String createDateStringUTC = "2021-08-11T21:50:30Z";

		System.out.println("\n");
		System.out.println("utcFormat....................................: " + UTC_FORMAT );
		System.out.println("Date String no formato ......................: " + createDateStringUTC);
		Date createDatUTC = sdfUTC.parse(createDateStringUTC);
		System.out.println("Date Object no formato BRT ..................: " + createDatUTC);
		System.out.println("Date Object no formato GMT ..................: " + createDatUTC.toGMTString());
		System.out.println("\n");


		System.out.println("TESTE 1 MES DE DIFERENÇA EXATO ");
		System.out.println(LOG_INICIO);
		String startDateStringUTC = "2021-08-11T21:50:30Z";
		String endDateStringUTC = "2021-09-11T21:50:30Z";
		testeData(startDateStringUTC, endDateStringUTC);

		System.out.println("TESTE DIFERENÇA: MENOS DE 1 MES EM FEVEREIRO ");
		System.out.println(LOG_INICIO);
		startDateStringUTC = "2022-02-11T21:50:30Z";
		endDateStringUTC = "2022-03-11T21:50:30Z";
		testeData(startDateStringUTC, endDateStringUTC);

		System.out.println("TESTE DIFERENÇA: MENOS DE 1 MES EM FEVEREIRO + 15 DIAS");
		System.out.println(LOG_INICIO);
		startDateStringUTC = "2022-02-11T21:50:30Z";
		endDateStringUTC = "2022-03-26T21:50:30Z";
		testeData(startDateStringUTC, endDateStringUTC);

		/*
		 *
		 * System.out.println("TESTE DIFERENÇA: MENOS DE 1 MES dias ");
		 * System.out.println(
		 * "----------------------------------------------------------------------------------INICIO"
		 * ); startDateStringUTC = "2021-08-11T21:50:30Z"; endDateStringUTC =
		 * "2021-08-12T21:50:30Z"; testeData(startDateStringUTC, endDateStringUTC);
		 *
		 * System.out.println("TESTE DIFERENÇA: MENOS DE 1 MES DE dias e horas");
		 * System.out.println(
		 * "----------------------------------------------------------------------------------INICIO"
		 * ); startDateStringUTC = "2021-08-11T21:50:30Z"; endDateStringUTC =
		 * "2021-08-15T23:50:30Z"; testeData(startDateStringUTC, endDateStringUTC);
		 *
		 * System.out.println("TESTE DIFERENÇA: MAIS DE 1 MES E MENOS DE 2 MESES ");
		 * System.out.println(
		 * "----------------------------------------------------------------------------------INICIO"
		 * ); startDateStringUTC = "2021-08-11T21:50:30Z"; endDateStringUTC =
		 * "2021-09-20T21:50:30Z"; testeData(startDateStringUTC, endDateStringUTC);
		 *
		 * System.out.println("TESTE DIFERENÇA: MAIS DE 2 MESES "); System.out.println(
		 * "----------------------------------------------------------------------------------INICIO"
		 * ); startDateStringUTC = "2021-08-11T21:50:30Z"; endDateStringUTC =
		 * "2021-10-11T21:50:30Z"; testeData(startDateStringUTC, endDateStringUTC);
		 *
		 * System.out.println("TESTE DIFERENÇA: 2 horas"); System.out.println(
		 * "----------------------------------------------------------------------------------INICIO"
		 * ); startDateStringUTC = "2021-08-11T21:50:30Z"; endDateStringUTC =
		 * "2021-08-11T23:50:30Z"; testeData(startDateStringUTC, endDateStringUTC);
		 *
		 * System.out.println("TESTE DIFERENÇA: 2 dias"); System.out.println(
		 * "----------------------------------------------------------------------------------INICIO"
		 * ); startDateStringUTC = "2021-08-11T21:50:30Z"; endDateStringUTC =
		 * "2021-08-12T21:50:30Z"; testeData(startDateStringUTC, endDateStringUTC);
		 */
	}

	public static void testeData(String startDateStringUTC,String endDateStringUTC) throws ParseException {

		SimpleDateFormat sdfUTC = new SimpleDateFormat(UTC_FORMAT);
		sdfUTC.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date startDateUTC;
		Date endDateUTC;
		startDateUTC = sdfUTC.parse(startDateStringUTC);
		endDateUTC = sdfUTC.parse(endDateStringUTC);
		//.toInstant().atOffset(ZoneOffset.UTC)
		long hours = HOURS.between(startDateUTC.toInstant().atOffset(ZoneOffset.UTC),endDateUTC.toInstant().atOffset(ZoneOffset.UTC));
		long days = DAYS.between(startDateUTC.toInstant().atOffset(ZoneOffset.UTC),endDateUTC.toInstant().atOffset(ZoneOffset.UTC));
		long months = MONTHS.between(startDateUTC.toInstant().atOffset(ZoneOffset.UTC),endDateUTC.toInstant().atOffset(ZoneOffset.UTC));
		long weeks = WEEKS.between(startDateUTC.toInstant().atOffset(ZoneOffset.UTC),endDateUTC.toInstant().atOffset(ZoneOffset.UTC));
		System.out.println(String.format("Diferença entre datas UTC:%n%s%n%s%n",new String[] {startDateStringUTC, endDateStringUTC}));
		System.out.println("Em Meses..........: " + months);
		System.out.println("Em Semanas........: " + weeks);
		System.out.println("Em Dias...........: " + days);
		System.out.println("Em Horas..........: " + hours);
		System.out.println("\nFORMATA DATAS PARA TIMEZONE BRT e pattern: " + BR_FORMAT +"\n");
		SimpleDateFormat sdfBRT = new SimpleDateFormat("dd-MM-yyy HH:mm:ss");
		sdfUTC.setTimeZone(TimeZone.getTimeZone("BRT"));
		String startDateStringBRT = sdfBRT.format(startDateUTC);
		String endDateStringBRT = sdfBRT.format(endDateUTC);
		System.out.println(String.format("datas convertidas para BRT:%n%s%n%s%n",new Object[] {startDateStringBRT, endDateStringBRT}));
		System.out.println("----------------------------------------------------------------------------------FIM \n\n");
	}

	public static boolean isMoreOneYear(String startDateStringUTC, String endDateStringUTC) throws ParseException {

		String brFormat = "dd-MM-yyy HH:mm:ss";
		SimpleDateFormat sdfUTC = new SimpleDateFormat(UTC_FORMAT);
		sdfUTC.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date startDateUTC;
		Date endDateUTC;
		startDateUTC = sdfUTC.parse(startDateStringUTC);
		endDateUTC = sdfUTC.parse(endDateStringUTC);
		//.toInstant().atOffset(ZoneOffset.UTC)
		long hours = HOURS.between(startDateUTC.toInstant().atOffset(ZoneOffset.UTC),endDateUTC.toInstant().atOffset(ZoneOffset.UTC));
		long days = DAYS.between(startDateUTC.toInstant().atOffset(ZoneOffset.UTC),endDateUTC.toInstant().atOffset(ZoneOffset.UTC));
		long months = MONTHS.between(startDateUTC.toInstant().atOffset(ZoneOffset.UTC),endDateUTC.toInstant().atOffset(ZoneOffset.UTC));
		long weeks = WEEKS.between(startDateUTC.toInstant().atOffset(ZoneOffset.UTC),endDateUTC.toInstant().atOffset(ZoneOffset.UTC));
		System.out.println(String.format("Diferença entre datas UTC:%n%s%n%s%n",new Object[] {startDateStringUTC, endDateStringUTC}));
		System.out.println("Em Meses..........: " + months);
		System.out.println("Em Semanas........: " + weeks);
		System.out.println("Em Dias...........: " + days);
		System.out.println("Em Horas..........: " + hours);
		System.out.println("\nFORMATA DATAS PARA TIMEZONE BRT e pattern: " + brFormat +"\n");
		SimpleDateFormat sdfBRT = new SimpleDateFormat("dd-MM-yyy HH:mm:ss");
		sdfUTC.setTimeZone(TimeZone.getTimeZone("BRT"));
		String startDateStringBRT = sdfBRT.format(startDateUTC);
		String endDateStringBRT = sdfBRT.format(endDateUTC);
		System.out.println(String.format("datas convertidas para BRT:%n%s%n%s%n",new Object[] {startDateStringBRT, endDateStringBRT}));
		System.out.println("----------------------------------------------------------------------------------FIM \n\n");

		return days > 365;

	}
}
