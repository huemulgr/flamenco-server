package com.ingenieriahuemul.flamencoserver.constants;


public final class MensajesMesh {
	
//Mensajes para el MESH. Todos los mensajes estan armados para ser concatenados a la mac. 
	//Por ahora uso string format, no es muy performante pero por ahora no deberia ser un problema. Dejo fijo tanto como sea posible para ahorrar procesamiento
	
	/** formato base <strong>|S|F</strong><br>F es timestamp formato DDMMAAAAHHmmSS. (año es completo o solo los ultimos 2 digitos??) 
	 * <br><strong>Ej: "{mac}|S|09102018145050"</strong>*/ 
	public static final String MESH_STATUS = "|S|%s\n";
	
	/** formato base <strong>|R|N|Tmin|Tmax|CE</strong> donde <br>N es salida 0/1, <br>T es temperatura, <br>C es condicion O/Y/N y <br>E es entrada 0/1/N. 
	 * <br><strong>Ej: "{mac}|R|1|-5.00|10.00|Y0\n"</strong> */
	public static final String MESH_C_UMBRAL	= "|R|%d|%.2f|%.2f|%s%s\n";
	
	/** formato base <strong>|R|N|H|Hi|Hf|CE</strong> <br>N es salida, <br>Hi, hf son horas formato HHmm, <br>C es condicion Y/O/N, <br>E es entrada 0/1/N. 
	 *  <br><strong>Ej: {mac}|R|H|1730|1731|O1</strong>*/
	public static final String MESH_C_HORA 	= "|R|%d|H|%s|%s|%s%s\n";
	
	/** formato bae <strong>|R|N|+</strong><br>N es salida 0/1 <br>Ej: {mac}|R|1|+*/		
	static final String MESH_C_MANUAL_ON	= "|R|%s|+\n";
	/** formato bae <strong>|R|N|-</strong><br>N es salida 0/1 <br>Ej: {mac}|R|1|-*/
	static final String MESH_C_MANUAL_OFF	= "|R|%s|-\n";
	
}
