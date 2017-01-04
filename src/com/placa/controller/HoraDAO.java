/**
 * 
 */
package com.placa.controller;

import java.util.Calendar;
import java.util.Date;

import com.placa.model.HorarioPicoPlaca;

/**
 * <h1>Controlador HoraDAO</h1>
 * Implementa la interfaz HoraDAOLocal 
 * Este controlador administra los horarios de circulacion 
 * para el pico y placa en Quito
 * @author Edwin Tene
 * @version 1.0
 * @since 2017-01-03
 */
public class HoraDAO implements HoraDAOLocal {	
	
	/**
	 * hora inicial restriccion manana 
	 */
	private int fromm;
	/**
	 * hora final restriccion manana 
	 */
	private int tom;
	/**
	 * hora inicial restriccion tarde 
	 */
	private int fromt;
	/**
	 * hora final restriccion tarde 
	 */
	private int tot;
	
	/**
	 * Crea un controlador con los horarios predefinidos
	 * para la ciudad de Quito
	 */
	public HoraDAO() {
		// TODO Auto-generated constructor stub
		this.fromm = 730;
		this.tom = 900;
		this.fromt = 1630;
		this.tot = 1930;
	}
	
	/**
	 * Crea un controlador con los horarios de restriccion
	 * parametrizables 
	 * @param horaManana Tipo Hora, Horario Restriccion Manana
	 * @param horaTarde Tipo Hora, Horario Restriccion Tarde
	 */
	public HoraDAO(HorarioPicoPlaca horaManana, HorarioPicoPlaca horaTarde){
		this.fromm = horaManana.getHoraInicio().getHours() * 100 + horaManana.getHoraInicio().getMinutes();
		this.tom = horaManana.getHoraFinal().getHours() * 100 + horaManana.getHoraFinal().getMinutes();
		this.fromt = horaTarde.getHoraInicio().getHours() * 100 + horaTarde.getHoraInicio().getMinutes();
		this.tot = horaTarde.getHoraFinal().getHours() * 100 + horaTarde.getHoraFinal().getMinutes();
	}

	/**
	 * Este metodo verifica si la hora ingresada esta dentro de la 
	 * restriccion de circulacion de la manana
	 * @param hora Date con la hora a verificar
	 */
	public Boolean mananaPicoPlaca(Date hora){
		int from = fromm;
		int to = tom;
		Calendar c = Calendar.getInstance();
		c.setTime(hora);
		int t = c.get(Calendar.HOUR_OF_DAY)*100 + c.get(Calendar.MINUTE);
		return (t > from && t >= from && t <= to || to < from && (t >= from || t <= to));
	}
	
	/**
	 * Este metodo verifica si la hora ingresada esta dentro de la 
	 * restriccion de circulacion de la tarde
	 * @param hora Date con la hora a verificar
	 */
	public Boolean tardePicoPlaca(Date hora){
		int from = fromt;
		int to = tot;
		Calendar c = Calendar.getInstance();
		c.setTime(hora);
		int t = c.get(Calendar.HOUR_OF_DAY)*100 + c.get(Calendar.MINUTE);
		return (t > from && t >= from && t <= to || to < from && (t >= from || t <= to));
	}
	
	/**
	 * Este metodo verifica si la hora ingresada esta dentro de la 
	 * restriccion de circulacion del dia
	 * @param hora Date con la hora a verificar
	 */
	@Override
	public Boolean isHoraPicoPlaca(Date hora) {
		// TODO Auto-generated method stub		
		return mananaPicoPlaca(hora) || tardePicoPlaca(hora);
	}

}
