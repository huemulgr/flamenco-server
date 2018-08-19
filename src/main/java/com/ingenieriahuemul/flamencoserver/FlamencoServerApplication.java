package com.ingenieriahuemul.flamencoserver;

import java.util.concurrent.Executor;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class FlamencoServerApplication extends SpringBootServletInitializer {

	private static final Logger logger = Logger.getLogger(FlamencoServerApplication.class);
		
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(FlamencoServerApplication.class);
    }

    public static void main(String[] args) {
    	logger.info("prueba log");
        SpringApplication.run(FlamencoServerApplication.class, args);
    }
    
    @Bean
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(15);
        executor.setMaxPoolSize(100);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("Monitoreo-");
        executor.initialize();
        return executor;
    }
}