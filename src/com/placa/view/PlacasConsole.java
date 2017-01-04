package com.placa.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//String path = "test.txt";
		String path = "C:\\Users\\hp\\workspace-primefaces\\Placas\\test.txt";
		Date fecha = new Date();
		Date hora = new Date();			
		
		HorarioPicoPlaca manana = new HorarioPicoPlaca();
		HorarioPicoPlaca tarde = new HorarioPicoPlaca();
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
		
		PlacaDAO servicio = new PlacaDAO();
		//HoraDAO servicioHora = new HoraDAO(manana, tarde);
		HoraDAO servicioHora = new HoraDAO();
		FechaDAO servicioFecha = new FechaDAO();
		
		try{
			List<Placa> placas =  servicio.getAllPlacas(path);
			if(placas!=null){
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
			}
		}catch(Exception e){
			e.printStackTrace();
		}		
		
		/*SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
		String dat = "01-01-2017";
		try {
			Date date = sf.parse(dat);
			if(servicioFecha.isSemana(date))
				System.out.println("1 de enero es dia laborable " + date.toString());
			else
				System.out.println("1 de enero no es dia laborable " + date.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	
	}

}
