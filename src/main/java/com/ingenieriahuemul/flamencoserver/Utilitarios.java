package com.ingenieriahuemul.flamencoserver;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public abstract class Utilitarios {
	
	/*
	 * metodo para convertir de un campo time (viene con fecha 0) a campo date con fecha de hoy y misma hora
	 * esto facilita hacer comparaciones contra fecha y hora actual
	 */
	public static Date sumarFechaHoy(Time time) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, time.getHours());
		calendar.set(Calendar.MINUTE, time.getMinutes());
		calendar.set(Calendar.SECOND, time.getSeconds());
		
		return calendar.getTime();
	}
		
	//metodo para sumar un dia a una fecha, seria necesario en el caso que un intervalo pase durante la medianoche hacia el dia siguiente
	public static Date sumarUnDia(Date date) {
		Calendar calendar = Calendar.getInstance(); 
		calendar.setTime(date); 
		calendar.add(Calendar.DATE, 1);
		
		return calendar.getTime();
	}
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyHHmmss");
	public static String formatofechaStatus(Date fecha) {
		return sdf.format(fecha);
	}
}
