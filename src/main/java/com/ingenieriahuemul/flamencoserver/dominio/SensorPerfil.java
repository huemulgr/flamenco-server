package com.ingenieriahuemul.flamencoserver.dominio;

//lo agrego a la clase sensor por comodidad, aunque en el futuro podria hacer falta hacer 
//baja o modificar SP para que elimine registros anteriores asi se pueda reasignar
public class SensorPerfil {
	
	private Long idsensor;
	private Long idperfil;
	
	
	public Long getIdsensor() {
		return idsensor;
	}
	public void setIdsensor(Long idsensor) {
		this.idsensor = idsensor;
	}
	public Long getIdperfil() {
		return idperfil;
	}
	public void setIdperfil(Long idperfil) {
		this.idperfil = idperfil;
	}
	
}
