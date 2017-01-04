package com.placa.view;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.placa.controller.FechaDAO;
import com.placa.controller.HoraDAO;
import com.placa.controller.PlacaDAO;
import com.placa.model.HorarioPicoPlaca;
import com.placa.model.Placa;

/**
 * <h1>Aplicacion de consola para placas</h1>
 * Programa principal donde se ejecuta el programa para
 * verificar las placas obtenidas desde el archivo de placas
 * @author Edwin Tene
 * @version 1.0
 * @since 2017-01-03
 */
public class PlacasConsole {
	
	private static SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
	private static HorarioPicoPlaca manana = new HorarioPicoPlaca();
	private static HorarioPicoPlaca tarde = new HorarioPicoPlaca();
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		// TODO Auto-generated method stub		
		
		String path;
		if(args != null && args.length > 0){
			//path = "C:\\Users\\hp\\workspace-primefaces\\Placas\\test.txt";
			path = args[0];
		}
		else{
			System.out.println("Ingrese ruta y nombre del archivo, formato C:\\ruta\\al\\directorio\\archivo.txt");
			System.out.print("(Enter para terminar): ");
			StringBuffer string  = new StringBuffer();
			char c;
			try{
				Reader entrada = new InputStreamReader(System.in);
				while((c=(char)entrada.read()) != '\n'){					
					string.append(c);
				}	
				path = string.toString().substring(0, string.length()-1);
			}catch(IOException e){
				System.out.println("Error al leer la ruta y el fichero, debe estar en el formato");
				path = "";
			}
		}
			
		
		Date fecha;
		if(args != null && args.length > 1){
			try {
				fecha = sf.parse(args[1]);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				fecha = null;
				System.out.println("Error en el formato de la fecha, ej: 31-12-2017");				
			}
		}
		else{				
			System.out.println("Ingrese fecha para verificar pico y placa, formato dd-mm-yyyy");
			System.out.print("(Enter para terminar): ");
			StringBuffer string  = new StringBuffer();
			char c;
			try{
				Reader entrada = new InputStreamReader(System.in);
				while((c=(char)entrada.read()) != '\n'){					
					string.append(c);
				}								
			}catch(IOException e){
				System.out.println("Error al leer la fecha, debe estar en el formato");
				//string = "".;
			}
			try {
				fecha = sf.parse(string.toString().substring(0, string.length()-1));
			} catch (ParseException e) {
				// TODO Auto-generated catch block				
				fecha = null;
				System.out.println("Error en el formato de la fecha, ej: 31-12-2017");
				
			}
		}
		Date hora;
		if(args != null && args.length > 2){			
			if(isHora(args[2]))
				hora = toHora(args[2]);
			else{
				hora = new Date();
				System.out.println("Error en el formato de hora, ej: 07:59, se usara la hora actual");
			}
		}
		else{			
			System.out.println("Ingrese hora para verificar pico y placa, formato hh:mm ");
			System.out.print("(Enter para terminar): ");
			StringBuffer string  = new StringBuffer();
			char c;
			try{
				Reader entrada = new InputStreamReader(System.in);
				while((c=(char)entrada.read()) != '\n'){
					if(c!='\n')
						string.append(c);
				}				
				if(isHora(string.toString().substring(0, string.length()-1)))
					hora = toHora(string.toString().substring(0, string.length()-1));
				else
					hora = null;
			}catch(IOException e){
				System.out.println("Error al leer la hora, debe estar en el formato");
				hora = null;
			}
		}			
		
		initHorarioCirculacion();
		PlacaDAO servicio = new PlacaDAO();
		//HoraDAO servicioHora = new HoraDAO(manana, tarde);
		HoraDAO servicioHora = new HoraDAO();
		FechaDAO servicioFecha = new FechaDAO();
		
		try{			
			List<Placa> placas =  servicio.getAllPlacas(path);
			if(placas!=null && fecha != null && hora != null){
				int i = 1;
				for(Placa item: placas){
					if(servicio.isPlaca(item.getPlaca())){
						if(servicioHora.isHoraPicoPlaca(hora)){
							String digito = item.getPlaca().substring(7, 8);
							if(servicioFecha.isPicoPlaca(digito, fecha))			
								System.out.println("placa no circula #" + i + "->" + item.getPlaca() + ", digito-> " + digito);
								
							else
								System.out.println("placa circula#" + i + "->" + item.getPlaca() + ", digito-> " + digito);
						}else
							System.out.println("horario libre placa circula #" + i + "->" + item.getPlaca() );						
					}
					else
						System.out.println("no es placa #" + i + "->" + item.getPlaca());
					i++;
				}					
			}else{
				System.out.println("Parametros no ingresados");
			}
		}catch(Exception e){
			e.printStackTrace();
		}				
		
	}
	
	@SuppressWarnings("deprecation")
	public static void initHorarioCirculacion(){
		Date horaTemp1 = new Date();
		Date horaTemp2 = new Date();
		Date horaTemp3 = new Date();
		Date horaTemp4 = new Date();
		horaTemp1.setHours(7);
		horaTemp1.setMinutes(30);
		manana.setHoraInicio(horaTemp1);
		horaTemp2.setHours(9);
		horaTemp2.setMinutes(0);
		manana.setHoraFinal(horaTemp2);
		horaTemp3.setHours(16);
		horaTemp3.setMinutes(0);
		tarde.setHoraInicio(horaTemp3);
		horaTemp4.setHours(19);
		horaTemp4.setMinutes(30);
		tarde.setHoraFinal(horaTemp4);
	}
	
	public static Boolean isHora(String arg){		
		Pattern pattern = Pattern.compile("([01]?[0-9]|2[0-3]):[0-5][0-9]");
		Matcher matcher = pattern.matcher(arg);		
		return matcher.matches();		
	}
	
	@SuppressWarnings("deprecation")
	public static Date toHora(String arg){
		Date hora = new Date();
		String[] tokens = arg.split(":");
		int hour;
		int minutes;
		hour = Integer.valueOf(tokens[0]);
		minutes = Integer.valueOf(tokens[1]);
		hora.setHours(hour);
		hora.setMinutes(minutes);
		return hora;
	}

}
