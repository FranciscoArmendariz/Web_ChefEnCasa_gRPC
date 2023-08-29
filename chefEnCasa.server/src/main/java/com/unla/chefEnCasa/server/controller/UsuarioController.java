package com.unla.chefEnCasa.server.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unla.chefEnCasa.server.dto.Login;
import com.unla.chefEnCasa.server.dto.UsuarioRequest;
import com.unla.chefEnCasa.server.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping("/crear")
	public ResponseEntity<?> crearUsuario(@Valid @RequestBody UsuarioRequest request){
		return new ResponseEntity<>(usuarioService.crearUsuario(request), HttpStatus.CREATED);			
	}
	@GetMapping
	public ResponseEntity<?> traerUsuarios(){
		return ResponseEntity.ok(usuarioService.traerUsuarios());
	}
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody Login request){
		return new ResponseEntity<>(usuarioService.login(request), HttpStatus.OK);
	}
	
	@GetMapping("/recetas/usuario/{id}")
	public ResponseEntity<?> traerRecetasPorId(@PathVariable("id") long id){
		return new ResponseEntity<>(usuarioService.traerRecetasPorId(id), HttpStatus.OK);
	}

}
