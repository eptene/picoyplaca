/**
 * 
 */
package com.placa.controller;

import java.util.Date;

/**
 * @author Edwin Tene
 *
 */
public interface HoraDAOLocal {

	public Boolean isHoraPicoPlaca(Date hora);
	public Boolean mananaPicoPlaca(Date hora);
	public Boolean tardePicoPlaca(Date hora);
}
