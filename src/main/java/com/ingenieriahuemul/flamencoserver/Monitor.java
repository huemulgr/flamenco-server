package com.ingenieriahuemul.flamencoserver;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ingenieriahuemul.flamencoserver.services.MonitoreoService;

@Component
public class Monitor {
	private static final Logger logger = LoggerFactory.getLogger(Monitor.class);

	private MonitoreoService pruebaService;
	
	public Monitor(MonitoreoService pruebaService) {
    	this.pruebaService = pruebaService;
    }
	
	//monitorear es una tarea programada cada x milisegundos se dispara
	//TODO: dejar rate como parametro configurable
	@Scheduled(fixedRate=5000)
    public void monitorear() {
    	logger.info("monitorear...");
    	CompletableFuture[] threads = new CompletableFuture[15];
    	// referencia
    	long start = System.currentTimeMillis();
  	
	  	try {
	  		//recorro para 1 thread x MAS suponiendo que el mensaje de status es bloqueante, 
	  		//si no es asi no serian necesarios threads adicionales
			for(int i=0; i<15; i++) {
				//se inicia thread, lo unico que hace es esperar y escribir
				CompletableFuture<String> thread = pruebaService.prueba(" " + i);
			    threads[i] = thread;
			}
			          	
			//join de los thread creados
			CompletableFuture.allOf(threads).join();

			// resultado y tiempo total
			logger.info("Elapsed time: " + (System.currentTimeMillis() - start));
			for(int i=0; i<15; i++) {
			 	logger.info("--> " + threads[i].get());
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

