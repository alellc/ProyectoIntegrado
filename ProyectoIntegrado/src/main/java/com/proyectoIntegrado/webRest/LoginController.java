package com.proyectoIntegrado.webRest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proyectoIntegrado.model.Alumno;
import com.proyectoIntegrado.model.Usuario;
import com.proyectoIntegrado.repository.AlumnoRepository;
import com.proyectoIntegrado.service.UsuariosService;

@RestController
public class LoginController extends AbstractResourceController {
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);
	private final UsuariosService usuarioService;
	private final AlumnoRepository alumnoRepository;

	@Autowired
	public LoginController(UsuariosService usuariosService,AlumnoRepository alumnoRepository) {
		this.usuarioService=usuariosService;
		this.alumnoRepository=alumnoRepository;
	}

//	@InitBinder
//	public void setAllowedFields(WebDataBinder dataBinder) {
//		dataBinder.setDisallowedFields("id");
//	}
//	
	@RequestMapping(value = "/login")
	public Usuario login(@RequestParam("usuario")String usuario,@RequestParam("contrasena")String contrasena){
		Usuario u = usuarioService.login(usuario, contrasena);
		return u;
	}
	@RequestMapping(value = "/alumno")
	public Alumno alumno(){
		Alumno a=alumnoRepository.findOne(1);
		return a;
	}
//	@RequestMapping(value = "/alumno")
//	public ResponseEntity<Alumno> alumno(){
//		Alumno a=alumnoRepository.findOne(1);
//		return ResponseEntity.ok(a);
//	}
	@RequestMapping(value = "/usuario")
	public Usuario usuario(@RequestBody Usuario user){
		Usuario u=new Usuario();
		u.setId(user.getId());
		u.setUsuario(user.getUsuario());
		u.setContrasena(user.getContrasena());
		u.setRol_profesor(user.getRol_profesor());
		usuarioService.save(u);
		return u;
	}
}
