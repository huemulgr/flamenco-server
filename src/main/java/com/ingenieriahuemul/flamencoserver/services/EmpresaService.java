
package com.ingenieriahuemul.flamencoserver.services;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingenieriahuemul.flamencoserver.dao.EmpresaDao;
import com.ingenieriahuemul.flamencoserver.dao.EmpresaDao.EspacioLibreRetorno;

@Service
public class EmpresaService {
	private final Logger logger = Logger.getLogger(EmpresaService.class);
	
	@Autowired
	private EmpresaDao empresaDao;
	
	public String obtenerEspacioLibre(Integer periodoImpresion) {
		Integer espacio = null;
		Path filePath = Paths.get("/opt/bitnami/apache-tomcat/webapps/espacio");
		Scanner scanner = null;
		try {
			scanner = new Scanner(filePath);
			espacio = scanner.nextInt();
		} catch (Exception e1) {
			logger.error("Error leyendo archivo de espacio libre", e1);
			return "<No se pudo comprobar el espacio libre>";
		} finally {
			scanner.close();
		}
		
		if(espacio == null) {
			logger.error("Error leyendo archivo de espacio libre, no se encontro el dato espacio libre");
			return "<No se pudo comprobar el espacio libre>";
		}
		EspacioLibreRetorno e = empresaDao.espacioLibre(espacio, periodoImpresion);
		
		return e.getMesesAutonomia() > 12 ? 
				(e.getAniosAutonomia() + " año" + (e.getAniosAutonomia() > 1 ? "s." : ".") )
				: e.getMesesAutonomia() + " meses."; 
	}
}
