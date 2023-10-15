package com.unla.chefEnCasa.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unla.chefEnCasa.server.dto.CrearBorradorDto;
import com.unla.chefEnCasa.server.dto.DenunciaRequestDto;
import com.unla.chefEnCasa.server.dto.ResolverDenunciaDto;
import com.unla.chefEnCasa.server.service.BorradorService;
import com.unla.chefEnCasa.server.service.DenunciaService;

@RestController
@RequestMapping("/api")
public class ApiRestController {

    @Autowired
    private DenunciaService denunciaService;

    @Autowired
    private BorradorService borradorService;

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
    public ResponseEntity<?> crearBorrador(@RequestBody CrearBorradorDto request){
        boolean creado = borradorService.crearBorrador(request.getBorradores(), request.getIdUsuario());
        if (creado) {
            return new ResponseEntity<>("Borrador creado", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Borrador no creado", HttpStatus.BAD_REQUEST);
        }
        
    }

    @GetMapping("/borrador/usuario/{idUsuario}")
    public ResponseEntity<?> traerBorradoresPorUsuario(@PathVariable("idUsuario") long idUsuario){
        return ResponseEntity.ok(borradorService.traerBorradoresPorUsuario(idUsuario));
    }

    @GetMapping("/borrador/{idBorrador}")
      public ResponseEntity<?> traerBorrador(@PathVariable("idBorrador") long idBorrador){
        return ResponseEntity.ok(borradorService.traerBorrador(idBorrador));
    }

}
