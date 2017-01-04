/**
 * 
 */
package com.placa.controller;

import java.util.Date;

/**
 * @author Edwin Tene
 *
 */
public interface FechaDAOLocal {

	public Boolean isSemana(Date fecha);
	public Boolean isPicoPlaca(String digito, Date Fecha);
	public Boolean isDigit(String digito);
}
