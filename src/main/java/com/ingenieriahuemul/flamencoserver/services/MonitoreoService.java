package com.ingenieriahuemul.flamencoserver.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

import javax.security.auth.x500.X500Principal;

@Service
public class MonitoreoService {

    private static final Logger logger = LoggerFactory.getLogger(MonitoreoService.class);

    
    public MonitoreoService() { }
    
    //este metodo se ejecuta asincronicamente en un thread, puede que no sea necesario si la comunicacion con el 
    //coordinador no es bloqueante pero x las dudas esto lo resolveria si usamos un thread para cada mensaje enviado
    @Async
    public CompletableFuture<String> prueba(String mensaje) throws InterruptedException {
        
        String resultado = "prueba monitoreo";
        
        Random random = new Random();
        long rand = random.nextLong() % 5000L; 
        if(rand < 0) 
        	rand = -rand;
        logger.info("Ejecutando servicio en thread" + mensaje + " durmiendo " + rand + "s...");
        // delay artificial aleatorio a modo de prueba de hasta 5 segundos, eliminar
        Thread.sleep(rand);
        
        return CompletableFuture.completedFuture(resultado);
    }
    
}
