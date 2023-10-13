package com.unla.chefEnCasa.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unla.chefEnCasa.server.dto.DenunciaRequestDto;
import com.unla.chefEnCasa.server.dto.ResolverDenunciaDto;
import com.unla.chefEnCasa.server.service.DenunciaService;

@RestController
@RequestMapping("/api/denuncia")
public class ApiRestController {

    @Autowired
    private DenunciaService denunciaService;

    @PostMapping("/crear")
    public ResponseEntity<?> crearDenuncia(@RequestBody DenunciaRequestDto request) {
        boolean denuncia = denunciaService.crearDenuncia(request.getIdReceta(), request.getMotivo());
        if (denuncia) {
            return new ResponseEntity<>("Denuncia creada", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Denuncia no creada", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/denuncias")
    public ResponseEntity<?> traerDenuncias() {
        return ResponseEntity.ok(denunciaService.traerDenuncias());
    }

    @PostMapping("/resolver")
    public ResponseEntity<?> resolverDenuncia(@RequestBody ResolverDenunciaDto request) {
        boolean resuelta = denunciaService.resolverDenuncia(request.getIdDenuncia(), request.isEliminar());
        if (resuelta) {
            return new ResponseEntity<>("Denuncia resuelta", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Denuncia no resuelta", HttpStatus.BAD_REQUEST);
        }
    }

}
