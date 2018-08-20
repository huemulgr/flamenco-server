package com.ingenieriahuemul.flamencoserver.controllers;

import org.springframework.beans.factory.annotation.Value;

public abstract class BaseController {
//clase base de los controllers, aprovecho para cargar variables comunes a toda la aplicacion
	
	//esto me sirve para obtener la empresa de este servidor que desde archivo application.properties, 
	//en el futuro puede hacerse de otras formas (x defecto que este siempre en id 1, por ejemplo)
	@Value("${idEmpresa}")
	protected Long idEmpresa;
}
