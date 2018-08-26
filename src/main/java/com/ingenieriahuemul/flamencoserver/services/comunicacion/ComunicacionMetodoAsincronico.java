package com.ingenieriahuemul.flamencoserver.services.comunicacion;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ComunicacionMetodoAsincronico {
	Logger logger = Logger.getLogger(ComunicacionMetodoAsincronico.class);
	
	//este metodo se ejecuta en un thread aparte, podemos usar algo de este estilo para mandar los mensajes al coordinador
	//la idea era que este en la clase comunicacionService, pero x alguna razon no funciona si esta en la misma clase que va a usar el metodo (se puede comprobar con el log, esta el thread entre [])
    //TODO: en un futuro estaria bueno ponerle un nombre mas feliz pero por ahora no se me ocurre nada mejor
	@Async
    public CompletableFuture<String> ejecutar(String mensaje) throws InterruptedException {
            	
        String resultado = mensaje;
        
        Random random = new Random();
        long rand = random.nextLong() % 2000L; 
        if(rand < 0) 
        	rand = -rand;
        logger.info("Ejecutando metodo en thread, durmiendo " + rand + "ms...");
        // delay artificial aleatorio a modo de prueba de hasta 2 segundos, eliminar
        Thread.sleep(rand);
        
        return CompletableFuture.completedFuture(resultado);
    }
}
