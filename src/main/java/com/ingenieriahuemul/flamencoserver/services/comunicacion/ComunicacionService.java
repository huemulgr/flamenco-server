package com.ingenieriahuemul.flamencoserver.services.comunicacion;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
 * JPF, en este paquete podes ir codificando todo lo que necesites para la comunicacion. Deje 2 metodos para uso de thread a modo de ejemplo, que creo nos van a venir bien.  
 * En cuanto al tratamiento de strings de mensajes no se me ocurrio nada muy novedoso. Para los enviados pensando en temas de eficiencia usaria StringBuilder o
 * el operador + en su defecto. Para los recibidos lo que haria es usar 
 * String.split con el | y separar el mensaje, despues con la letra inicial diferenciamos el mensaje y se traduce el contenido al tipo de dato que corresponda.
 * esto lo dejo solo a modo de introduccion, podes hacerlo otra forma si te parece mejor
 */

@Component
public class ComunicacionService {
	Logger logger = Logger.getLogger(ComunicacionService.class);
	
	@Autowired 
	private ComunicacionMetodoAsincronico threadComunicacion;
	
    
    //creacion de un thread ejecutando el metodo de threadComunicacion
    public String prueba2(String msj) {
    	String resultado = null;
    	try {
    		CompletableFuture<String> thread = null;
			//se hace un polling de 4 intentos por el resultado cada uno con timeout de 1 seg.
			for(int i=0; i<4; i++) {
				try {
					if(resultado == null) {
						thread = threadComunicacion.ejecutar("intento numero " + i);
						
						//get es bloqueante para el thread, tiene como parametro su timeout
						resultado = thread.get(1, TimeUnit.SECONDS);
					} else {
						logger.info("exito en intento " + (i-1));
						break;
					}						
				} catch (ExecutionException e) {
					// hubo error inesperado al obtener resultado del metodo asincronico
					e.printStackTrace();
				} catch (TimeoutException e) {	
					//paso timeout, se cancela el thread demorado
					logger.info("timeout nro " + i);
					thread.cancel(true);
				}	
			}			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	
    	logger.info("resultado: " + (resultado == null ? "|sin exito" : "|exito en " + resultado));
    	return resultado;
    }
}
