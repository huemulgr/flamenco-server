package com.ingenieriahuemul.flamencoserver;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
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
}