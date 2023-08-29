package com.unla.chefEnCasa.server.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unla.chefEnCasa.server.dto.RecetaRequest;
import com.unla.chefEnCasa.server.service.RecetaService;

@RestController
@RequestMapping("/receta")
public class RecetaController {

	@Autowired
	private RecetaService recetaService;

	// Se manda por path el id del usuario ya que al no tener relacion
	// bidireccional, necesitamos saber en que usuario guardamos la receta
	@PostMapping("/crear/{id}")
	public ResponseEntity<?> crearReceta(@Valid @RequestBody RecetaRequest request, @PathVariable("id") long id) {
		return new ResponseEntity<>(recetaService.crearReceta(request, id), HttpStatus.CREATED);
	}

	// Se manda por path el id de la receta a editar
	@PutMapping("/editar/{id}")
	public ResponseEntity<?> editarReceta(@Valid @RequestBody RecetaRequest request, @PathVariable("id") long id) {
		return new ResponseEntity<>(recetaService.editarReceta(request, id), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<?> traerRecetas(@RequestParam(value = "titulo", defaultValue = "") String titulo,
			@RequestParam(value = "categoria", defaultValue = "") String categoria,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "999999") int size,
			@RequestParam(value = "orderBy", defaultValue = "asc") String orderBy,
			@RequestParam(value = "sortBy", defaultValue = "id") String soryBy) {
		return new ResponseEntity<>(recetaService.traerRecetas(titulo, categoria, page, size, orderBy, soryBy),
				HttpStatus.OK);
	}

}
