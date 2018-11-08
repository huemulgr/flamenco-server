package com.ingenieriahuemul.flamencoserver.controllers;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ingenieriahuemul.flamencoserver.dao.ComportamientoHoraDao;
import com.ingenieriahuemul.flamencoserver.dao.ComportamientoUmbralDao;
import com.ingenieriahuemul.flamencoserver.dao.EmpresaDao;
import com.ingenieriahuemul.flamencoserver.dao.EstadoMasDao;
import com.ingenieriahuemul.flamencoserver.dao.LecturaDao;
import com.ingenieriahuemul.flamencoserver.dao.PerfilDao;
import com.ingenieriahuemul.flamencoserver.dao.PlantaDao;
import com.ingenieriahuemul.flamencoserver.dao.TipoSensorDao;
import com.ingenieriahuemul.flamencoserver.dao.UsuarioDao;
import com.ingenieriahuemul.flamencoserver.dao.UsuarioDao.AutenticacionResultado;
import com.ingenieriahuemul.flamencoserver.domain.Alarma;
import com.ingenieriahuemul.flamencoserver.domain.ComportamientoHora;
import com.ingenieriahuemul.flamencoserver.domain.ComportamientoUmbral;
import com.ingenieriahuemul.flamencoserver.domain.Empresa;
import com.ingenieriahuemul.flamencoserver.domain.Lectura;
import com.ingenieriahuemul.flamencoserver.domain.Perfil;
import com.ingenieriahuemul.flamencoserver.domain.Planta;
import com.ingenieriahuemul.flamencoserver.domain.PuntoDeSensado;
import com.ingenieriahuemul.flamencoserver.domain.Sensor;
import com.ingenieriahuemul.flamencoserver.domain.TipoSensor;
import com.ingenieriahuemul.flamencoserver.domain.Usuario;
import com.ingenieriahuemul.flamencoserver.services.AlarmaService;
import com.ingenieriahuemul.flamencoserver.services.EmpresaService;
import com.ingenieriahuemul.flamencoserver.services.MasService;
import com.ingenieriahuemul.flamencoserver.services.PuntoDeSensadoService;
import com.ingenieriahuemul.flamencoserver.services.SensorService;


@RestController
@RequestMapping("/test")
public class TestController extends BaseController{
	Logger logger = Logger.getLogger(TestController.class);
	
	@Autowired
	private MasService masService;
	
	@GetMapping(path = "/manual/on/")
	public boolean onManual(@RequestParam("idPs") Long idPs, @RequestParam("nroRele") int nroRele ) {
		return masService.activarReleManual(idPs, nroRele);
	}
	
	@GetMapping(path = "/manual/off/")
	public boolean offManual(@RequestParam("idPs") Long idPs, @RequestParam("nroRele") int nroRele ) {
		return masService.desactivarReleManual(idPs, nroRele);
	}
	
	@Autowired
	private AlarmaService alarmaService;
	
	@PostMapping(path = "/alarma")
    public void create(@RequestBody Alarma alarma){
    	alarmaService.create(alarma);
    }
	
	@GetMapping(path = "/alarma/{a}")
	public Alarma get1 (@PathVariable("a") Long a) {
		return alarmaService.findById(Long.valueOf(a));
	}
	
	@GetMapping(path = "/alarma")
	public List Listar1() {
		return alarmaService.findAll();
	}
	
	@GetMapping(path = "/alarma/sensor/{idSensor}")
	public List ListarPorSensor(@PathVariable("idSensor") Long idSensor) {
		return alarmaService.findByIdSensor(idSensor);
	}
	
	@DeleteMapping(path = "/alarma/{a}")
	public void test1 (@PathVariable("a") int a) {
		alarmaService.delete(Long.valueOf(a));
	}
	
	@PutMapping(path = "/alarma")
    public void update(@RequestBody Alarma alarma){
    	alarmaService.update(alarma);
    }
//-----------------------------------------------------------------------------------
//	chora
	@Autowired
	private ComportamientoHoraDao cHoraDao;
	
	@PostMapping(path = "/chora/configurar")
	public boolean configurarRele(@RequestBody ComportamientoHora cHora) {
		return masService.configurarReleXHora(cHora);
	}
	
	@PostMapping("/chora")
    public ComportamientoHora create(@RequestBody ComportamientoHora cHora){
    	logger.info("creando comp hora " + cHora + "...");
        return cHoraDao.save(cHora);
    }
	
	@GetMapping(path = "/chora/{a}")
	public ComportamientoHora test2 (@PathVariable("a") Long a) {
		logger.info("ok");
		
		return cHoraDao.findById(Long.valueOf(a));
	}
	
	@GetMapping(path = "/chora/sensor/{idSensor}")
	public List<ComportamientoHora> listarChoraPorSensor (@PathVariable("idSensor") Long idSensor) {
		return cHoraDao.findByIdSensor(Long.valueOf(idSensor));
	}
	
	@GetMapping(path = "/chora")
	public List listar2() {
		return cHoraDao.findAll();
	}
	
	@DeleteMapping(path = "/chora/{a}")
	public void test2 (@PathVariable("a") int a) {
		logger.info("ok1");

		cHoraDao.delete(Long.valueOf(a));
	}
	
	@PutMapping(path = "/chora")
    public ComportamientoHora update(@RequestBody ComportamientoHora chora){
    	logger.info("actualizando comp hora " + chora + "...");
        return cHoraDao.update(chora);
    }
//-----------------------------------------------------------------------------------	
//	cumbral
	@Autowired
	private ComportamientoUmbralDao cUmbralDao;
	
	@PostMapping(path = "/cumbral/configurar")
	public boolean configurarRele(@RequestBody ComportamientoUmbral cUmbral) throws Exception {
		return masService.configurarReleXUmbral(cUmbral);
	}
	
	@PostMapping("/cumbral")
    public ComportamientoUmbral create(@RequestBody ComportamientoUmbral cumbral){
    	logger.info("creando...");
        return cUmbralDao.save(cumbral);
    }
	
	@GetMapping(path = "/cumbral/{a}")
	public ComportamientoUmbral test3 (@PathVariable("a") Long a) {
		logger.info("ok");
		
		return cUmbralDao.findById(Long.valueOf(a));
	}
	
	@GetMapping(path = "/cumbral/sensor/{idSensor}")
	public List<ComportamientoUmbral> listarCumbralPorSensor (@PathVariable("idSensor") Long idSensor) {
		return cUmbralDao.findByIdSensor(Long.valueOf(idSensor));
	}
	
	@GetMapping(path = "/cumbral")
	public List Listar3() {
		return cUmbralDao.findAll();
	}
	
	@DeleteMapping(path = "/cumbral/{a}")
	public void test3 (@PathVariable("a") int a) {
		logger.info("ok1");

		cUmbralDao.delete(Long.valueOf(a));
	}
	
	@PutMapping(path = "/cumbral")
    public ComportamientoUmbral update(@RequestBody ComportamientoUmbral cumbral){
    	logger.info("actualizando...");
        return cUmbralDao.update(cumbral);
    }
//-----------------------------------------------------------------------------------	
//	empresa
	@Autowired
	private EmpresaDao empresaDao;
	
	@PostMapping("/empresa")
    public Empresa create(@RequestBody Empresa empresa){
    	logger.info("creando...");
        return empresaDao.save(empresa);
    }
	
	@GetMapping(path = "/empresa/{a}")
	public Empresa test4 (@PathVariable("a") Long a) {
		logger.info("ok");
		
		return empresaDao.findById(Long.valueOf(a));
	}
	
	@GetMapping(path = "/empresa")
	public List Listar4() {
		return empresaDao.findAll();
	}
	
	@DeleteMapping(path = "/empresa/{a}")
	public void test4 (@PathVariable("a") int a) {
		logger.info("ok1");

		empresaDao.delete(Long.valueOf(a));
	}
	
	@PutMapping(path = "/empresa")
    public Empresa update(@RequestBody Empresa empresa){
    	logger.info("actualizando...");
        return empresaDao.update(empresa);
    }
	
	@GetMapping(path = "/empresalocal")
	public Empresa test4 () {
		logger.info("empresa de este servidor local, obtenida con su id");
		return empresaDao.findById(this.idEmpresa);
	}
	
	@Autowired
	private EmpresaService empresaService;
	@GetMapping(path = "/empresa/espacio-libre/{periodo}")
	public Map verEspacioLibre (@PathVariable("periodo") int periodo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mensaje", empresaService.obtenerEspacioLibre(periodo));
		return map;
	}
	
	@PostMapping(path = "/empresa/validarClave")
	public Map validarClave (@RequestBody Empresa empresa) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		Empresa aux = empresaDao.findById(1000L);
		
		//TODO: remover ingreso con 1234
		if(empresa.getPasswordConfiguracion().equals(aux.getPasswordConfiguracion())
			|| empresa.getPasswordConfiguracion().equals("1234")
			|| empresa.getPasswordConfiguracion().equals("flamenco")
			) {
			map.put("ok", true);
		} else {
			map.put("ok", false);
		}
		
		return map;
	}
	
//-----------------------------------------------------------------------------------
//	estadoactual
	@Autowired
	private EstadoMasDao estadoActualDao;
	
	@GetMapping(path = "/estadoactual")
	public List estadoActual() {
		return estadoActualDao.obtenerEstadoActual();
	}

//-----------------------------------------------------------------------------------	
//	lectura
	@Autowired
	private LecturaDao lecturaDao;
	
	@GetMapping("/lectura")
    public List<Lectura> create(@RequestParam Long idPuntoDeSensado, @RequestParam String fechaDesde, @RequestParam String fechaHasta) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date desde = null;
		Date hasta = null;
		try {
			desde = new Date(sdf.parse(fechaDesde).getTime());
			hasta = new Date(sdf.parse(fechaHasta).getTime());
		} catch (ParseException e) {
			logger.error("Error en el formato de las fechas ingresadas debe ser AAAA-MM-DD");
			throw e;
		}
		
		return lecturaDao.obtenerLecturas(idPuntoDeSensado, desde, hasta);
    }
	
	@GetMapping("/lectura/pivot")
    public List<Object> create(@RequestParam String fecha) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date fechaParseada = null;
		try {
			fechaParseada = new Date(sdf.parse(fecha).getTime());
		} catch (ParseException e) {
			logger.error("Error en el formato de las fechas ingresadas debe ser AAAA-MM-DD");
			throw e;
		}
		
		return lecturaDao.vistaPivot(fechaParseada);
    }
	
//-----------------------------------------------------------------------------------	
//	perfil
	@Autowired
	private PerfilDao perfilDao;
	
	@PostMapping("/perfil")
    public Perfil create(@RequestBody Perfil perfil){
    	logger.info("creando...");
        return perfilDao.save(perfil);
    }

	@GetMapping(path = "/perfil")
	public List listarPerfiles() {
		return perfilDao.findAll();
	}
	
	@GetMapping(path = "/perfil/sensores/{idPerfil}")
	public List listarSensoresAsignados(@PathVariable("idPerfil") Long idPerfil) {
		return perfilDao.findSensoresAsignados(idPerfil);
	}
	
	@GetMapping(path = "/perfil/{a}")
	public Perfil test5 (@PathVariable("a") Long a) {
		return perfilDao.findById(Long.valueOf(a));
	}
	
	@GetMapping(path = "/perfil/asignarUsuario")
	public void asignarUsuario (@RequestParam("idPerfil") Long idPerfil, @RequestParam("idUsuario") Long idUsuario) {
		perfilDao.asignarUsuario(idPerfil, idUsuario);
	}
	
	@GetMapping(path = "/perfil/asignarSensor")
	public void asignarSensor (@RequestParam("idPerfil") Long idPerfil, @RequestParam("idSensor") Long idSensor) {
		perfilDao.asignarSensor(idPerfil, idSensor);
	}
	
	
	@DeleteMapping(path = "/perfil/{a}")
	public void test5 (@PathVariable("a") int a) {
		perfilDao.delete(Long.valueOf(a));
	}
	
	@DeleteMapping(path = "/perfil/quitarUsuario")
	public void quitarUsuario (@RequestParam("idPerfil") Long idPerfil, @RequestParam("idUsuario") Long idUsuario) {
		perfilDao.quitarUsuario(idPerfil, idUsuario);
	}
	
	@DeleteMapping(path = "/perfil/quitarSensor")
	public void quitarSensor (@RequestParam("idPerfil") Long idPerfil, @RequestParam("idSensor") Long idSensor) {
		perfilDao.quitarSensor(idPerfil, idSensor);
	}
	
	@PutMapping(path = "/perfil")
    public Perfil update(@RequestBody Perfil perfil){
    	return perfilDao.update(perfil);
    }
//-----------------------------------------------------------------------------------	
//	planta
	@Autowired
	private PlantaDao plantaDao;
	
	@PostMapping("/planta")
    public Planta create(@RequestBody Planta planta){
    	logger.info("creando...");
        return plantaDao.save(planta);
    }
	
	@GetMapping(path = "/planta/{a}")
	public Planta test6 (@PathVariable("a") Long a) {
		return plantaDao.findById(Long.valueOf(a));
	}
	
	@GetMapping(path = "/planta/a{a}")
	public Planta test61 (@PathVariable("a") Long a) {
		return plantaDao.findByIdEmpresa(Long.valueOf(a));
	}
	
	@GetMapping(path = "/planta")
	public List Listar6() {
		return plantaDao.findAll();
	}
	
	@DeleteMapping(path = "/planta/{a}")
	public void test6 (@PathVariable("a") int a) {
		plantaDao.delete(Long.valueOf(a));
	}
	
	@PutMapping(path = "/planta")
    public Planta update(@RequestBody Planta planta){
    	logger.info("actualizando...");
        return plantaDao.update(planta);
    }
//-----------------------------------------------------------------------------------	
//	puntodesensado
	@Autowired
	private PuntoDeSensadoService puntoDeSensadoService;
	
	//TODO: reemplazar por manejo mas apropiado de las alarmas
	@GetMapping(path = "/puntodesensado/alarmasOn")
	public List getAlarmasOn() {
		return masService.getPuntosSensadoConAlarma();
	}
	
	@PostMapping("/puntodesensado")
    public void createPuntoSensado(@RequestBody PuntoDeSensado puntodesensado){
        puntoDeSensadoService.create(puntodesensado);
    }
	
	@GetMapping(path = "/puntodesensado/{a}")
	public PuntoDeSensado findPuntoDeSensado (@PathVariable("a") Long a) {
		return puntoDeSensadoService.findById(Long.valueOf(a));
	}
	
	@GetMapping(path = "/puntodesensado")
	public List findAllPuntosDeSensado() {
		return puntoDeSensadoService.findAll();
	}
	
	@DeleteMapping(path = "/puntodesensado/{a}")
	public void deletePuntoDeSensado (@PathVariable("a") int a) {
		puntoDeSensadoService.delete(Long.valueOf(a));
	}
	
	@PutMapping(path = "/puntodesensado")
    public void updatePuntoDeSensado(@RequestBody PuntoDeSensado puntodesensado){
    	puntoDeSensadoService.update(puntodesensado);
    }
//-----------------------------------------------------------------------------------	
//	sensor
	@Autowired
	private SensorService sensorService;
	
	@PostMapping("/sensor")
    public void createSensor(@RequestBody Sensor sensor){
    	sensorService.create(sensor);
    }
	
	@GetMapping(path = "/sensor/{a}")
	public Sensor findSensorById (@PathVariable("a") Long a) {
		return sensorService.findById(Long.valueOf(a));
	}
	
	@GetMapping(path = "/sensor")
	public List findAllSensores() {
		return sensorService.findAll();
	}
	
	@DeleteMapping(path = "/sensor/{a}")
	public void deleteSensor (@PathVariable("a") int a) {
		sensorService.delete(Long.valueOf(a));
	}
	
	@PutMapping(path = "/sensor")
    public void updateSensor(@RequestBody Sensor sensor){
    	sensorService.update(sensor);
    }
//-----------------------------------------------------------------------------------	
//	tiposensor
	@Autowired
	private TipoSensorDao tipoSensorDao;
	
	@PostMapping("/tiposensor")
    public TipoSensor create(@RequestBody TipoSensor tipoSensor){		
    	logger.info("creando...");
        return tipoSensorDao.save(tipoSensor);
    }
	
	@GetMapping(path = "/tiposensor/{a}")
	public TipoSensor test9 (@PathVariable("a") Long a) {
		masService.desactivarReleManual(1L, 0);
		return tipoSensorDao.findById(Long.valueOf(a));
	}
	
	@GetMapping(path = "/tiposensor")
	public List Listar9() {
		masService.activarReleManual(1L, 0);
		return tipoSensorDao.findAll();
	}
	
	@DeleteMapping(path = "/tiposensor/{a}")
	public void test9 (@PathVariable("a") int a) {
		tipoSensorDao.delete(Long.valueOf(a));
	}
	
	@PutMapping(path = "/tiposensor")
    public TipoSensor update(@RequestBody TipoSensor tipoSensor){
    	logger.info("actualizando...");
        return tipoSensorDao.update(tipoSensor);
    }
//-----------------------------------------------------------------------------------	
//	usuario
	@Autowired
	private UsuarioDao usuarioDao;
	
	@PostMapping("/usuario")
    public Usuario create(@RequestBody Usuario usuario){
    	logger.info("creando...");
        return usuarioDao.save(usuario);
	}	
	
	@GetMapping(path = "/usuario/{a}")
	public Usuario test10 (@PathVariable("a") Long a) {
		return usuarioDao.findById(Long.valueOf(a));
	}
	
	@GetMapping(path = "/usuario")
	public List Listar10() {
		return usuarioDao.findAll();
	}
	
	@DeleteMapping(path = "/usuario/{a}")
	public void test10 (@PathVariable("a") int a) {
		usuarioDao.delete(Long.valueOf(a));
	}
	
	@PutMapping(path = "/usuario")
    public Usuario update(@RequestBody Usuario usuario){
    	logger.info("actualizando...");
        return usuarioDao.update(usuario);
    }	
	
	@PostMapping("/usuario/login")
    public Usuario autenticar(@RequestBody Usuario usuario){
        AutenticacionResultado ar = usuarioDao.autenticar(usuario.getNombreUsuario(), usuario.getPassword());
        Long idUsuario = ar.getUsuario();
        Usuario usuarioLogeado;
        if(ar.getAutorizacion() == 1) {
        	//login ok devuelvo usuario
        	usuarioLogeado = usuarioDao.findById(idUsuario);
        } else {
        	//login no ok devuelvo objeto usuario vacio, con id negativo para discriminar el error
        	usuarioLogeado = new Usuario();
        	if(ar.getAutorizacion() == 2) {
        		usuarioLogeado.setIdusuario(-2L);
        	} else {
        		usuarioLogeado.setIdusuario(-1L);
        	}
        }
        
        return usuarioLogeado;
	}
	
	@GetMapping(path = "/usuario/sensores/{idUsuario}")
	public List obtenerSensoresUsuario(@PathVariable("idUsuario") Long idUsuario) {
		return usuarioDao.obtenerSensoresUsuario(idUsuario);
	}
	
	@GetMapping(path = "/usuario/perfil/{idUsuario}/{idPerfil}")
	public Boolean validarPerfil(@PathVariable("idUsuario") Long idUsuario, @PathVariable("idPerfil") Long idPerfil) {
		return usuarioDao.validarPerfil(idUsuario, idPerfil);
	}
}
