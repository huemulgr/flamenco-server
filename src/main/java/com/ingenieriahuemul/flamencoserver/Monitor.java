package com.ingenieriahuemul.flamencoserver;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ingenieriahuemul.flamencoserver.domain.EstadoMas;
import com.ingenieriahuemul.flamencoserver.domain.Mas;
import com.ingenieriahuemul.flamencoserver.services.EstadoMasService;
import com.ingenieriahuemul.flamencoserver.services.MasService;

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
    		mas.evaluarAlarmas();
    	}
    	
//    	logger.info("monitorear..." + start); 
	}
	
	private void refrescarEstadoActual() {
		estadoActual = estadoMasService.obtenerEstadoActual();
		
		for(EstadoMas estadoMas : estadoActual) {
			for(Mas mas : listaMas) {
				if(mas.getPuntoDeSensado().getId().equals(estadoMas.getIdPuntoSensado())) {
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

