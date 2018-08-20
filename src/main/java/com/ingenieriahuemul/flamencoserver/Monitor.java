package com.ingenieriahuemul.flamencoserver;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ingenieriahuemul.flamencoserver.dominio.Sensor;
import com.ingenieriahuemul.flamencoserver.services.MonitorService;
import com.ingenieriahuemul.flamencoserver.services.SensorService;

/* Servicio de monitoreo
 * 
 */
@Component
public class Monitor {
	private static final Logger logger = LoggerFactory.getLogger(Monitor.class);

	/* Esta variable de clase se va a usar para indicar cuando el listado de sensores (y alarmas, reles, etc.)
     * fue modificado externamente y necesita ser recargado de la base de datos.
     * Es una alternativa medianamente limpia a usar semaforos y trabajar siempre sobre memoria. 
     */
    public static boolean outdated = true;
    
    @Autowired
    private SensorService sensorService;
    
    //esta lista va a contener tanto a los sensores como a sus alarmas, comportamientos de reles y puntos de sensado
    private List<Sensor> listaMAS;
	
    
	public Monitor() { }
	
	//monitorear es una tarea programada que  cada x milisegundos se dispara
	//TODO: pasar el monitoreo a un 2do log para no inundar de mensajes la consola
	@Scheduled(fixedRateString = "${periodoMonitoreo}")
    public void monitorear() {
		long start = System.currentTimeMillis();
		
		//si hubo cambios actualiza listado, cada vez que se modifique hay que setear en true outdated
    	if(isOutdated()) {
    		listaMAS = sensorService.getListaMAS();
    		outdated = false;
    	}
    	logger.info("duracion update: " + (System.currentTimeMillis() - start));
    	logger.info("monitorear..." + start);
 
//TODO: lo dejo comentado para tenerlo a mano si usamos threads, si no se usa borrarlo    	
//    	CompletableFuture[] threads = new CompletableFuture[15];
//  	
//	  	try {
//	  		//recorro para 1 thread x MAS suponiendo que el mensaje de status es bloqueante, 
//	  		//si no es asi no serian necesarios threads adicionales
//			for(int i=0; i<15; i++) {
//				//se inicia thread, lo unico que hace es esperar y escribir
//				CompletableFuture<String> thread = pruebaService.prueba(" " + i);
//			    threads[i] = thread;
//			}
//			          	
//			//join de los thread creados
//			CompletableFuture.allOf(threads).join();
//
//			// resultado y tiempo total
//			logger.info("Elapsed time: " + (System.currentTimeMillis() - start));
//			for(int i=0; i<15; i++) {
//			// 	logger.info("--> " + threads[i].get());
//			}
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	public static boolean isOutdated() {
		return outdated;
	}
	public static void setOutdated(boolean outdated) {
		Monitor.outdated = outdated;
	}
}

