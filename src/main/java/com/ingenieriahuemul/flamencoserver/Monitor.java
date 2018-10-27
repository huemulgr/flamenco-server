package com.ingenieriahuemul.flamencoserver;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ingenieriahuemul.flamencoserver.domain.EstadoMas;
import com.ingenieriahuemul.flamencoserver.domain.Mas;
import com.ingenieriahuemul.flamencoserver.services.EstadoMasService;
import com.ingenieriahuemul.flamencoserver.services.MasService;
import com.ingenieriahuemul.flamencoserver.services.MonitorService;
import com.ingenieriahuemul.flamencoserver.services.comunicacion.ComunicacionService;

/* Servicio de monitoreo
 * 
 */
@Component
public class Monitor {
	private static final Logger logger = LoggerFactory.getLogger(Monitor.class);
	
    //uso esta variables de clase para indicar que el listado de mas se modifico y debe actualizarse en el proximo ciclo
    //cada vez que se altere esta lista (alarmas, punto sensado, etc.) setear en true. Me parece una alternativa potable a usar memoria compartida y semaforos
    public static boolean outdated = true;
    
    @Autowired
    private MasService masService;
    @Autowired
    public static EstadoMasService estadoMasService;
    
    
    @Autowired
    private ComunicacionService comunicacionService;
    @Autowired MonitorService monitorService;
    
	public Monitor() { }
	
	@Scheduled(fixedRateString = "1000")
    public void monitorear() {
    	logger.info("iniciando monitoreo..."); 
		long start = System.currentTimeMillis();
		
		//si hubo cambios actualiza listado, cada vez que se modifique hay que setear en true outdated
		//esto quedaria para sensores y alarmas, ver si es necesario cambiar a un semaforo para dar prioridad
		//o si me ilumino esto se borraria por completo
    	if(isOutdated()) {
    		masService.refrescarListaMAS();
    		outdated = false;
    		logger.info("duracion update: " + (System.currentTimeMillis() - start));
    	}
    	    	
    	//actualizar el estado desde la base de datos en memoria
    	logger.info("refrescando estado de los mas...");
    	masService.refrescarEstadoActual();
       	
    	logger.info("fin monitoreo, duracion: " + (System.currentTimeMillis() - start) + "\n"); 
	}
	
//	private void refrescarEstadoActual() {
//		estadoActual = estadoMasService.obtenerEstadoActual();
//		
//		for(EstadoMas estadoMas : estadoActual) {
//			for(Mas mas : listaMas) {
//				if(mas.getPuntoDeSensado() != null && 
//						estadoMas.getIdPuntoSensado().equals(mas.getPuntoDeSensado().getId())) {
//					mas.actualizarEstado(estadoMas);
//				}
//			}
//		}
//	}	
	
	public static boolean isOutdated() {
		return outdated;
	}
	public static void setOutdated(boolean outdated) {
		Monitor.outdated = outdated;
	}    	
   
}

