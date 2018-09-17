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
    private EstadoMasService estadoMasService;
    
    private List<Mas> listaMas;
    private List<EstadoMas> estadoActual;
    
    @Autowired
    private ComunicacionService comunicacionService;
    @Autowired MonitorService monitorService;
    
	public Monitor() { }
	
	//monitorear es una tarea programada que cada x milisegundos se dispara
	//TODO: pasar el monitoreo a un 2do log para no inundar de mensajes la consola
	@Scheduled(fixedRateString = "${periodoMonitoreo}")
    public void monitorear() {
		long start = System.currentTimeMillis();
		
		//si hubo cambios actualiza listado, cada vez que se modifique hay que setear en true outdated
    	if(isOutdated()) {
    		listaMas = masService.refrescarListaMAS();
    		outdated = false;
    	}
//    	logger.info("duracion update: " + (System.currentTimeMillis() - start));
    	    	
    	refrescarEstadoActual();
    	
    	for(Mas mas : listaMas) {
    		//TODO: este bloque tambien deberia hacerse dentro de un thread, para no bloquearse al enviar mensajes.
    		mas.evaluarAlarmas();
    		
    		//metodo de prueba para comunicacion con coordinador
    		comunicacionService.prueba2("msj");
    			
    	}
    	
    	logger.info("fin monitoreo, duracion: " + (System.currentTimeMillis() - start)); 
	}
	
	private void refrescarEstadoActual() {
		estadoActual = estadoMasService.obtenerEstadoActual();
		
		for(EstadoMas estadoMas : estadoActual) {
			for(Mas mas : listaMas) {
				if(mas.getPuntoDeSensado() != null && 
						estadoMas.getIdPuntoSensado().equals(mas.getPuntoDeSensado().getId())) {
					mas.actualizarEstado(estadoMas);
				}
			}
		}
	}
	
	
	public static boolean isOutdated() {
		return outdated;
	}
	public static void setOutdated(boolean outdated) {
		Monitor.outdated = outdated;
	}    	
   
}

