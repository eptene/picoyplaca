/**
 * 
 */
package com.placa.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <h1>Controlador FechaDAO</h1>
 * FechaDAO implementa la interfaz FechaDAOLocal
 * Controlador para administrar los dias que un vehiculo
 * tiene pico y placa dependiendo de su placa en Quito * 
 * @author Edwin Tene
 * @version 1.0
 * @since 2017-01-03
 */
public class FechaDAO implements FechaDAOLocal{
	
	@Override
	public Boolean isSemana(Date fecha) {
		// TODO Auto-generated method stub
		Calendar dia = Calendar.getInstance();
		dia.setTime(fecha);
		int semana = dia.get(Calendar.DAY_OF_WEEK);		
		return ((semana >= Calendar.MONDAY) && (semana <= Calendar.FRIDAY));
	}

	/**
	 * Este metodo retorna un boleano si el character ingresado
	 * es digito y la fecha corresponde al dia de circulacion
	 * @param digito Es el ultimo caracter de la placa
	 * @param fecha Es la fecha que se requiere conocer
	 * @return Retorna la condicion del caracter
	 */
	@Override
	public Boolean isPicoPlaca(String digito, Date fecha) {
		// TODO Auto-generated method stub
		if(isDigit(digito))
			return diaPicoPlaca(Integer.valueOf(digito), fecha);
		else
			return false;
	}
	
	
	/**
	 * Este metodo retorna verdadero si el digito corresponde al
	 * dia de circulacion de pico y placa
	 * @param digito Es el ultimo digito de la placa
	 * @param fecha Es la fecha que se requiere conocer
	 * @return Retorna la condicion del digito y su circulacion
	 */
	public Boolean diaPicoPlaca(int digito, Date fecha){
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		int dia = cal.get(Calendar.DAY_OF_WEEK);
		//System.out.println("hoy es -> " + dia);
		if(dia == Calendar.MONDAY && (digito == 0 || digito == 1))
			return true;
		else if(dia == Calendar.TUESDAY && (digito == 2 || digito == 3))
			return true;
		else if(dia == Calendar.WEDNESDAY && (digito == 4 || digito == 5))
			return true;
		else if(dia == Calendar.THURSDAY && (digito == 6 || digito == 7))
			return true;
		else if(dia == Calendar.FRIDAY && (digito == 8 || digito == 9))
			return true;
		return false;
	}
	
	/**
	 * Este metodo retorna un booleano para saber si el string ingresado
	 * es digito o no
	 * @param digito String que contiene un digito
	 * @return Retorna la condicion del caracter
	 */

	@Override
	public Boolean isDigit(String digito) {
		// TODO Auto-generated method stub
		Pattern pattern = Pattern.compile("[0-9]{1}");
		Matcher matcher = pattern.matcher(digito);
		return matcher.matches();
	}

	
}
