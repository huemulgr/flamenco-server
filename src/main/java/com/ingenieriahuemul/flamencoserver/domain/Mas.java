package com.ingenieriahuemul.flamencoserver.domain;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;


/*
 * El monitor va a tener un listado de MAS en memoria actualizado entre monitoreos 
 */
public class Mas {
	private final Logger logger = Logger.getLogger(Mas.class);
	
	private Sensor sensor;
	private PuntoDeSensado puntoDeSensado;
	private List<Alarma> alarmas = new ArrayList<>();
	private Double ultimaTempMuestreo = -999.0;
	private long ultimaHoraMuestreo = -1;
	private EstadoMas estadoMas;
	
	public Mas() {	}
	
	
	public void actualizarEstado(EstadoMas estadoMas) {
		this.setEstadoMas(estadoMas);
	}
	
	public void evaluarAlarmas() {
		for(Alarma alarma : this.alarmas) {
			
			if(!alarma.getHabilitado() || !sensor.getHabilitado() || !puntoDeSensado.getHabilitado()) {
				alarma.setAlarmaOn(false);
				continue;
			}
			
			int resultadoUmbral = estaDentroUmbral(this.estadoMas.getValor(), alarma.getUmbralInferior(), alarma.getUmbralSuperior());
			if(resultadoUmbral != 0) {
				logger.warn("Alarma: " + alarma.getNombre() + " ON, Inf: " + alarma.getUmbralInferior() 
					+ ", Sup: " + alarma.getUmbralSuperior() + ", resultado: " + resultadoUmbral);
				
				alarma.setAlarmaOn(true);
				
//TODO: notificar nube
//TODO: notificar interfaz web
				
			} else {				
				logger.info("Alarma: " + alarma.getNombre() + " OFF, Inf: " + alarma.getUmbralInferior() 
					+ ", Sup: " + alarma.getUmbralSuperior() + ", resultado: " + resultadoUmbral);
				
				alarma.setAlarmaOn(false);
			}
		}
	}
	
	/*
	 * metodo para comprobar si un valor esta por debajo, por encima o dentro de un umbral
	 */
	private int estaDentroUmbral(Double valor, Double valorUmbralInferior, Double valorUmbralSuperior) {
		//por base ya vienen acomodados, pero por las dudas se valida de vuelta por si pifia algo en el medio
		Double inferior = Math.min(valorUmbralInferior, valorUmbralSuperior);
		Double superior = Math.max(valorUmbralInferior, valorUmbralSuperior);
				
		if(valor < inferior)
			return -1;
		if(valor > superior)
			return 1;
		return 0;
	}
	
	public EstadoMas getEstadoMas() {
		return estadoMas;
	}
	
	public void setEstadoMas(EstadoMas estadoMas) {
		this.estadoMas = estadoMas;
	}
	public Sensor getSensor() {
		return sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

	public PuntoDeSensado getPuntoDeSensado() {
		return puntoDeSensado;
	}

	public void setPuntoDeSensado(PuntoDeSensado puntoDeSensado) {
		this.puntoDeSensado = puntoDeSensado;
	}

	public List<Alarma> getAlarmas() {
		return alarmas;
	}

	public void setAlarmas(List<Alarma> alarmas) {
		this.alarmas = alarmas;
	}

	public Double getUltimaTempMuestreo() {
		return ultimaTempMuestreo;
	}


	public void setUltimaTempMuestreo(Double ultimaTempMuestreo) {
		this.ultimaTempMuestreo = ultimaTempMuestreo;
	}


	public long getUltimaHoraMuestreo() {
		return ultimaHoraMuestreo;
	}


	public void setUltimaHoraMuestreo(long ultimaHoraMuestreo) {
		this.ultimaHoraMuestreo = ultimaHoraMuestreo;
	}
	
}
