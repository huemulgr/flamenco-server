package com.ingenieriahuemul.flamencoserver.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.validator.internal.util.privilegedactions.NewSchema;

import com.ingenieriahuemul.flamencoserver.Utilitarios;


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
	
	//parametro para comprobar si el mas deberia recuperar estados no persistidos o no
	private boolean enRecuperacion = false;
	
	//para conexion con mesh y comunicacion. 
	//TODO: estos parametros es posible que los queramos configurables en algun momento
	private static final int PORT=23;
	private static final int TIMEOUT_READ_MS = 3000;
	private static final int REINTENTOS = 3;
	Socket socket;
	InputStream inputStream;
	OutputStream outputStream;
	BufferedReader reader;
	PrintWriter writer;
    
	private static Map<String, Socket> coordinadores = new HashMap<String, Socket>();
	
	
	public Mas() {	}
	
	private void abrirSocketCoordinador(String ip) throws Exception {
		Socket socket = coordinadores.get(ip);
		if(socket != null && !socket.isClosed()) {
			this.socket = socket;
			return;
		}
		
		//no se pueden reutilizar socket, creo uno nuevo
		Socket nuevoSocket = null; 
		try {
			nuevoSocket = new Socket(ip, PORT);
			nuevoSocket.setSoTimeout(TIMEOUT_READ_MS);
		} catch (UnknownHostException ex) {
            logger.error("No se pudo llegar al coordinador de ip " + ip, ex);	
        } catch (Exception ex) {
            logger.error("No se pudo abrir conexion con el coordinador " + ip + ":" + this.PORT, ex);
        }
		coordinadores.put(ip, nuevoSocket);
		this.socket = nuevoSocket;
	}
	
	public Socket getSocket() {
		return this.coordinadores.get(this.sensor.getMacDelCoordinador());
	}
	
	//esto lo dejo por si en algun momento surge la necesidad, los socket los cierra el garbage collector o cuando se pisan con una nueva conexion
	private void cerrarSocketCoordinador(String ip) {
		Socket socket = coordinadores.get(ip);
		if(socket != null) {
			try {
				socket.close();
			} catch (Exception e) {
				logger.info("No se pudo cerrar el socket con el coordinador de ip " + ip + " continua proceso...");
			}
		}
		coordinadores.remove(ip);
	}
	
	public boolean abrirConexionConMesh() {
		try {
			abrirSocketCoordinador(this.sensor.getMacDelCoordinador());			
			
			inputStream = socket.getInputStream();
			outputStream = socket.getOutputStream();
			
			reader = new BufferedReader(new InputStreamReader(inputStream));
			writer = new PrintWriter(outputStream, true);
			
			//timeout de 2 segundos
        	socket.setSoTimeout(TIMEOUT_READ_MS);
			return true;
		} catch (UnknownHostException ex) {	 
            logger.error("No se pudo llegar al coordinador de ip " + this.sensor.getMacDelCoordinador(), ex);	 
            return false;
        } catch (IOException ex) {	 
            logger.error("I/O error al obtener streams de la conexion del MAS " + this.puntoDeSensado.getNombreCorto(), ex);	
            return false;
        } catch (Exception ex) {	 
            logger.error("No se pudo abrir conexion con el coordinador " + this.sensor.getMacDelCoordinador() + ":" + this.PORT, ex);	 
            return false;
        }
	}
	
	/** permite comunicarse con el mesh envia mensaje y retorna respuesta o null si no hubo.<br> NO usar directamente, usar a traves de
	 * MasService que incorpora semaforo para el mesh. Si en algun momento se implementa un mesh que admita mas de 1 msj en transito si se podria
	 * @param mensaje: 		El mensaje a enviar al mesh
	 * @return 			 	Un map con los datos obtenidos, el mismo estara vacio en caso de error y contendra como minimo los datos "estado" con el estado
	 * 						actual del mas y "respuesta" con la cadena respuesta recibida del mesh
	 *  */
	public Map<String, Object> comunicarseConMesh(String mensaje) {        
        String respuesta = null;
        Map<String, Object> datosObtenidos = new HashMap<>();
        for(int i=0; i<REINTENTOS; i++) {
        	try {
        		if(writer == null || reader == null) {
        			//si aun no se conecto manejo como si se hubiera perdido conexion, muestra error e intenta conectar
        			throw new SocketException();
        		}
        		
        		logger.info(msjLogMesh("enviando " + (null != mensaje ? mensaje.trim() : null)));
        		writer.print(mensaje);
        		writer.flush();
        		
        		char[] cBuffer = new char[100];
				reader.read(cBuffer);	      
				respuesta = new String(cBuffer);
								
				Map<String, Object> datosParseados = parseRespuestaMsj(respuesta);
				
				logger.info(msjLogMesh("recibido " + (null != respuesta ? respuesta.trim() : null)));
				if(datosParseados == null) {
					continue;
				}
				
				datosObtenidos.putAll(datosParseados);
				
				break;
			} catch (SocketTimeoutException e) {
				if(i==REINTENTOS-1)
					logger.info(msjLogMesh("fallaron reintentos se da por perdido el mensaje"));
				else
					logger.info(msjLogMesh("falla reintento nro. " + (i+1) + "..."));
			} catch (SocketException e) {
				//hay todo un problema con los timeout de los mensajes en caso que no se hay perdido realmente sino que se haya demorado, 
				//sobre todo si hay un solo mas porque comparo por mac
				//ver si esto tiene logica quiza el coordinador devuelve otro dato que no conozco que permita establecer que hubo caida de un mas
				
				//Se perdio conexion con el mas, se reintenta abrir la conexion
				logger.error(msjLogMesh("Se perdio la conexion con el coordinador " + sensor.getMacDelCoordinador()) + " intentando reabrir la conexion...", e);
				try {
					this.socket.close();
				} catch (IOException e1) {
					logger.info("No se pudo cerrar socket " + sensor.getMacDelCoordinador() + ":" + PORT + " continua proceso.");
				}
				this.abrirConexionConMesh();
				break;
			} catch (IOException e) {
				//registro el error pero descarto la interrupcion, este es un buen punto para reportar MAS caido
				logger.error(msjLogMesh("error inesperado."), e);
				break;
			} catch (Exception e) {
				logger.error(msjLogMesh("error generico al comunicarse con mesh, se descarta interrupcion:"), e);
				break;
			}
        }
        return datosObtenidos;
	}
	
	private static final String SEPARADOR="\\|";	//hace falta la doble barra para escapar regex
	/** parsea la respuesta obtenida de un mensaje. Devuelve un map con los datos obtenidos (a veces es mas de 1), si este map esta vacio o es null fallo */
	private Map<String, Object> parseRespuestaMsj(String respuesta) {
		Map<String, Object> datosObtenidos = new HashMap<String,Object>();
		
		String[] campos = respuesta.split(SEPARADOR);
		datosObtenidos.put("respuesta", respuesta);
		
//		if(campos.length < 3) {
//			//esto no deberia pasar nunca
//			return null;
//		}		
		if(campos[0].charAt(0) == 'N') {
			//si devolvio una N es que el mensaje estaba malformado, esto seria una falla de mi lado y nunca deberia ocurrir
			//logeo el mensaje para encontrar la falla, tambien seria buena idea levantar una excepcion para no perder el mensaje por accidente
			logger.error(this.msjLogMesh("El coordinador devolvio N, el mensaje enviado estaria mal formado"));
			return datosObtenidos;
		}
		//TODO: validar checksum
		
		String mac = campos[0];
		if(! this.sensor.getMac().equalsIgnoreCase(mac)) {
			logger.info(this.msjLogMesh("Se descarta mensaje con mac distinta a la del MAS sensado, "
					+ "muy posiblemente es un retry que quedo perdido o un mensaje que llego pasado el timeout"));
			return null;
		}
		
		//todas las respuestas devuelven el estado
		EstadoMas estadoMas = new EstadoMas();
		
		try {
			//recordatorio, a partir del 2 viene el payload
			//{mac}|{codigo}|payload\n
			char cod_respuesta = campos[1].charAt(0);
			switch (cod_respuesta) {
			case 'R':	{
				Double valor = Double.valueOf(campos[2]);
				boolean[] reles = parseEstadoReles(campos[3]);
				
				estadoMas.setValor(valor);
				estadoMas.setEstadoReles(reles);
			}	break;
			case 'P': {
				String estaConfigurado = campos[2];
				Double valor = Double.valueOf(campos[3]);
				
				estadoMas.setValor(valor);
				if(estaConfigurado.charAt(0)!='O') {
					logger.info(this.msjLogMesh("No esta configurado el rele S1"));
				} 
				if(estaConfigurado.charAt(1)!='O') {
					logger.info(this.msjLogMesh("No esta configurado el rele S2"));
				}			
			}	break;
			case 'B': {
				//el mas se configura en modo recuperacion, reemplazando los mensajes de status para obtener los status pendientes
				this.setEnRecuperacion(true);
				
				Double valor;
				try {
					valor = Double.valueOf(campos[2]);

					boolean[] reles = parseEstadoReles(campos[3]);
					
					estadoMas.setValor(valor);
					estadoMas.setEstadoReles(reles);
				} catch (NumberFormatException e) {
					//caso 2 del mensaje, cuando algun relé no esta configurado
					valor = Double.valueOf(campos[3]);
					estadoMas.setValor(valor);
					
					String estaConfigurado = campos[2];
					if(estaConfigurado.charAt(0)!='O') {
						logger.info(this.msjLogMesh("No esta configurado el rele S1"));
					} 
					if(estaConfigurado.charAt(1)!='O') {
						logger.info(this.msjLogMesh("No esta configurado el rele S2"));
					}
				}		
			}	break;
			case 'C': {
				Double valor = Double.valueOf(campos[2]);
				logger.info(this.msjLogMesh("Se configuró el relé correctamente"));			
				estadoMas.setValor(valor);			
			}	break;
			case 'E':	{
				char codRecuperacion = campos[2].charAt(0);
				
				if(codRecuperacion == 'E') {
					EstadoMas estadoRecuperado = new EstadoMas();
					
					Double valor = Double.valueOf(campos[3]);				
					Date fecha = Utilitarios.parseFechaStatus(campos[4]);
					boolean[] reles = parseEstadoReles(campos[5]);
					
					estadoRecuperado.setValor(valor);
					estadoRecuperado.setEstadoReles(reles);	
					
					datosObtenidos.put("estadoRecuperado", estadoRecuperado);
					
					valor = Double.valueOf(campos[6]);				
					boolean[] reles2 = parseEstadoReles(campos[7]);
					
					estadoMas.setValor(valor);
					estadoMas.setEstadoReles(reles2);	
				} else {					
					Double valor = Double.valueOf(campos[3]);
					boolean[] reles = parseEstadoReles(campos[4]);
					
					estadoMas.setValor(valor);
					estadoMas.setEstadoReles(reles);	
					
					this.setEnRecuperacion(false);
				}				
			}	break;
			default:
				break;
			}
		} catch (Exception e) {
			logger.error("Error parseando la respuesta del mesh: " + respuesta + "\nEsto no deberia ocurrir en ningun caso, es un error del servidor o del mesh a corregirse");
			return null;
		} 
		
		datosObtenidos.put("estado", estadoMas);
		
		return datosObtenidos;
	}
	
	private boolean[] parseEstadoReles(String input) {
		boolean[] estadoReles = new boolean[4];
		char[] reles = input.toCharArray();
		
		for(int i=0; i<4; i++) {
			if(reles[i]=='X') {
				logger.info(this.msjLogMesh("No esta configurado el rele " + (i<2?"salida":"entrada") + " nro " + (i%2+1)));
			}
			estadoReles[i] = reles[i]=='T' ? true : false;
		}
		
		return estadoReles;
	} 
	
	/** metodo para hacer homogeneos los logs del mesh */
	private String msjLogMesh(String msj) {
		//TODO: por ahora uso embebido el tag mesh. es una cagada tengo que reemplazarlo por un archivo de log aparte
		//tengo presente que ver esto era un CU asi que deberia agregar tambien el acceso desde la web a este archivo
		return "MESH: " + puntoDeSensado.getNombreCorto() + " - " + msj;
	}
	
	public void cerrarConexionConMesh() {
		try {
			this.writer.close();
			this.reader.close();
			this.socket.close();
		} catch (IOException e) {
			logger.warn("No se pudo cerrar socket, esto no seria problema ya que el GC de java lo deberia hacer solo.", e);
		}
	}
	
	public void evaluarAlarmas() {
		for(Alarma alarma : this.alarmas) {
			
			if(!alarma.getHabilitado() || !sensor.getHabilitado() || puntoDeSensado == null 
					|| (puntoDeSensado != null && !puntoDeSensado.getHabilitado()) 
					|| this.estadoMas == null) {	//en caso que recien levante el server y no hayan mediciones esto puede ocurrir
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
	public Long getIdSensor() {
		return sensor.getId();
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

	public boolean isEnRecuperacion() {
		return enRecuperacion;
	}

	public void setEnRecuperacion(boolean enRecuperacion) {
		this.enRecuperacion = enRecuperacion;
	}
	
}
