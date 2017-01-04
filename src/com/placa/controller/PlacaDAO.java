package com.placa.controller;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.placa.model.Placa;
import com.placa.util.LoadFile;

/**
 * <h1>Controlador PlacaDAO</h1>
 * Implementa la interfaz PlacaDAOLocal
 * Este controlador administra los objetos obtenidos 
 * del archivo de placas ingresadas
 * @author Edwin Tene
 * @version 1.0
 * @since 2017-01-03
 */
public class PlacaDAO implements PlacaDAOLocal {

	/**
	 * Servicio que permite obtener los datos del fichero
	 */
	private LoadFile servicio;

	/**
	 * Este metodo obtiene todas las cadenas del archivo de placas
	 * @param ruta String con la ruta del archivo
	 * @return Lista de Placas obtenidas del archivo
	 */
	@Override
	public List<Placa> getAllPlacas(String ruta) {
		// TODO Auto-generated method stub
		servicio = new LoadFile(ruta);
		if (servicio != null)
			return servicio.getPlacas();
		return null;
	}

	/**
	 * Este metodo verifica si la cadena ingresada corresponde
	 * a una placa en el formato ABC-1234
	 * @param placa String que contiene la cadena
	 * @return Retorn la condicion de la cadena
	 */
	@Override
	public Boolean isPlaca(String placa) {
		// TODO Auto-generated method stub
		Pattern pattern = Pattern.compile("[A-Z]{3}-[0-9]{4}");
		Matcher matcher = pattern.matcher(placa);
		return matcher.matches();
	}

}
