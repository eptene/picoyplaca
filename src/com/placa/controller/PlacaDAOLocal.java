/**
 * 
 */
package com.placa.controller;

import java.util.Date;
import java.util.List;

import com.placa.model.Placa;

/**
 * @author Edwin Tene
 *
 */
public interface PlacaDAOLocal {

	public List<Placa> getAllPlacas(String ruta);	
	public Boolean isPlaca(String placa);
}
