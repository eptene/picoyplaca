package com.placa.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.placa.model.Placa;

/**
 * <h1>Utilitario LoadFile</h1>
 * Esta clase utilitaria permite leer un conjunto
 * de placas desde un archivo cuya ruta ha sido enviada
 * @author Edwin Tene
 * @version 1.0
 * @since 2017-01-03
 */
public class LoadFile {

	private String path;
	/**
	 * Constructor que establece la ruta del archivo
	 * @param path
	 */
	public LoadFile(String path){
		this.path = path;
	}
	
	/**
	 * Obtiene todas las cadenas del archivo de placas
	 * @return Lista de cadenas con placas
	 * @throws Errores en el nombre del archivo y en lectura del archivo
	 */
	public List<Placa> getPlacas(){		
		ArrayList<Placa> placas = new ArrayList();
		InputStream fin = null;
		Scanner sc = null;
		StringBuilder sb = new StringBuilder();
		try{
			fin = new FileInputStream(path);
			sc = new Scanner(fin, "ISO-8859-1");
			String temp = "";
			while(sc.hasNextLine()){
				Placa placa = new Placa();
				placa.setPlaca(sc.nextLine());
				//sb.append(sc.nextLine());
				placas.add(placa);
			}
			
			if(sc.ioException() != null)
				throw sc.ioException();
			
			temp = sb.toString();
			return placas;
		}catch(FileNotFoundException e){
			System.out.println(e.getMessage());			
			return null;
		}catch(IOException e){
			System.out.println(e.getMessage());
			return null;
		}finally{
			if(fin != null){
				try{
					fin.close();
				}catch(IOException e){
					e.printStackTrace();
					return null;
				}
			}
			if(sc != null){
				sc.close();
			}
		}		
	
	}
}
