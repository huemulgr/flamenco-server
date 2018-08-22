package com.ingenieriahuemul.flamencoserver;

import java.util.List;

import org.apache.tomcat.util.http.fileupload.ThresholdingOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ingenieriahuemul.flamencoserver.domain.Alarma;
import com.ingenieriahuemul.flamencoserver.domain.Mas;
import com.ingenieriahuemul.flamencoserver.services.MasService;
import com.ingenieriahuemul.flamencoserver.services.SensorService;

/* Servicio de monitoreo
 * 
 */
@Component
public class Monitor {
	private static final Logger logger = LoggerFactory.getLogger(Monitor.class);

    //uso esta variable de clase para indicar que el listado de mas se modifico y debe actualizarse en el proximo ciclo
    //cada vez que se altere esta lista (alarmas, comportamientos, punto sensado, etc.) setear en true
    public static boolean outdated = true;
    
	public Monitor() { }
	
	//monitorear es una tarea programada que cada x milisegundos se dispara
	//TODO: pasar el monitoreo a un 2do log para no inundar de mensajes la consola
	@Scheduled(fixedRateString = "${periodoMonitoreo}")
    public void monitorear() {
		long start = System.currentTimeMillis();
		
		//si hubo cambios actualiza listado, cada vez que se modifique hay que setear en true outdated
    	if(isOutdated()) {
    		//actualizar lista alarmas
    		outdated = false;
    	}
    	logger.info("duracion update: " + (System.currentTimeMillis() - start));
    	logger.info("monitorear..." + start);
 
    	
	}
	
	
	//TODO:crear scheduled para persistir estado actual, va a requerir una variable tiempo para ir comparando contra el ultimo muestreo
	
	public static boolean isOutdated() {
		return outdated;
	}
	public static void setOutdated(boolean outdated) {
		Monitor.outdated = outdated;
	}    	
    	
    	
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
//			//join de los thread creados, tener en cuenta que joinear bloquea al padre (no usar en monitor)
//			CompletableFuture.allOf(threads).join();
//
//			// resultado y tiempo total, el resultado solo tiene sentido si es una tarea que queria hacer en paralelo y
    		// recuperar resultado mas tarde, si no es el caso se supone es seguro abrir thread y no joinearlos
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

