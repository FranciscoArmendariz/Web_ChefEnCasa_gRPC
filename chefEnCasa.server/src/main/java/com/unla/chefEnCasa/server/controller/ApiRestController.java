package com.unla.chefEnCasa.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unla.chefEnCasa.server.dto.CrearBorradorDto;
import com.unla.chefEnCasa.server.dto.CrearMensajeRequestDto;
import com.unla.chefEnCasa.server.dto.CrearRecetasDto;
import com.unla.chefEnCasa.server.dto.DenunciaRequestDto;
import com.unla.chefEnCasa.server.dto.EditarBorradorDto;
import com.unla.chefEnCasa.server.dto.RecetaResponseDto;
import com.unla.chefEnCasa.server.dto.ResolverDenunciaDto;
import com.unla.chefEnCasa.server.service.BorradorService;
import com.unla.chefEnCasa.server.service.DenunciaService;
import com.unla.chefEnCasa.server.service.RecetaService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class ApiRestController {

    @Autowired
    private DenunciaService denunciaService;

    @Autowired
    private BorradorService borradorService;

    @Autowired
    private RecetaService recetaService;

    @PostMapping("/denuncia/crear")
    public ResponseEntity<?> crearDenuncia(@RequestBody DenunciaRequestDto request) {
        boolean denuncia = denunciaService.crearDenuncia(request.getIdReceta(), request.getMotivo());
        if (denuncia) {
            return new ResponseEntity<>("Denuncia creada", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Denuncia no creada", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/denuncia/denuncias")
    public ResponseEntity<?> traerDenuncias() {
        return ResponseEntity.ok(denunciaService.traerDenuncias());
    }

    @PostMapping("/denuncia/resolver")
    public ResponseEntity<?> resolverDenuncia(@RequestBody ResolverDenunciaDto request) {
        boolean resuelta = denunciaService.resolverDenuncia(request.getIdDenuncia(), request.isEliminar());
        if (resuelta) {
            return new ResponseEntity<>("Denuncia resuelta", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Denuncia no resuelta", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/borrador/crear")
    public ResponseEntity<?> crearBorrador(@RequestBody CrearBorradorDto request) {
        boolean creado = borradorService.crearBorrador(request.getBorradores(), request.getIdUsuario());
        if (creado) {
            return new ResponseEntity<>("Borrador creado", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Borrador no creado", HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/borrador/usuario/{idUsuario}")
    public ResponseEntity<?> traerBorradoresPorUsuario(@PathVariable("idUsuario") long idUsuario) {
        return ResponseEntity.ok(borradorService.traerBorradoresPorUsuario(idUsuario));
    }

    @GetMapping("/borrador/{idBorrador}")
    public ResponseEntity<?> traerBorrador(@PathVariable("idBorrador") long idBorrador) {
        return ResponseEntity.ok(borradorService.traerBorrador(idBorrador));
    }

    @PutMapping("/borrador/editar/{idBorrador}")
    public ResponseEntity<?> editarBorrador(@PathVariable("idBorrador") long idBorrador,
            @RequestBody EditarBorradorDto request) {
        return ResponseEntity.ok(borradorService.editarBorrador(idBorrador, request.getBorradores()));
    }

    @PostMapping("/borrador/crear-receta")
    public ResponseEntity<?> crearRecetas(@RequestBody CrearRecetasDto request) {
        boolean creado = borradorService.crearRecetas(request.getIdBorrador(), request.getBorradores());
        if (creado) {
            return new ResponseEntity<>("recetas creadas", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("recetas no creadas", HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/receta")
    public ResponseEntity<?> traerRecetas(){
        return ResponseEntity.ok(recetaService.traerTodasLasRecetas());
    }
    @GetMapping("/recetas")
    public ResponseEntity<List<RecetaResponseDto>> traerRecetas(
            @RequestParam(name = "titulo", required = false, defaultValue = "") String titulo,
            @RequestParam(name = "categoria", required = false, defaultValue = "") String categoria,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "orderBy", defaultValue = "asc") String orderBy,
            @RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
            @RequestParam(name = "tiempoAproxMin", defaultValue = "0") int tiempoAproxMin,
            @RequestParam(name = "tiempoAproxMax", defaultValue = "0") int tiempoAproxMax,
            @RequestParam(name = "nombreIngrediente", required = false, defaultValue = "") String nombreIngrediente) {

        List<RecetaResponseDto> response = recetaService.traerRecetas(
                titulo, categoria, page, size, orderBy, sortBy, tiempoAproxMin, tiempoAproxMax, nombreIngrediente);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }



}
