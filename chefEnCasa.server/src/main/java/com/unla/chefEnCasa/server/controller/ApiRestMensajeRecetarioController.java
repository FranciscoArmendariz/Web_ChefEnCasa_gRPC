package com.unla.chefEnCasa.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unla.chefEnCasa.server.Soap.SoapService.MensajeService;
import com.unla.chefEnCasa.server.Soap.SoapService.RecetarioService;
import com.unla.chefEnCasa.server.dto.CrearMensajeRequestDto;
import com.unla.chefEnCasa.server.dto.ResponderMensajeRequest;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class ApiRestMensajeRecetarioController {

    @Autowired
    private MensajeService mensajeService;

    @Autowired
    private RecetarioService recetarioService;

    @PostMapping("/mensaje/crear")
    public ResponseEntity<?> crearMensaje(@RequestBody CrearMensajeRequestDto request) {
        boolean borrado = mensajeService.CrearMensaje(request);
        return ResponseEntity.ok(borrado);
    }

    @PostMapping("/mensaje/responder")
    public ResponseEntity<?> responderMensaje(@RequestBody ResponderMensajeRequest request) {
        boolean creado = mensajeService.ResponderMensaje(request);
        return ResponseEntity.ok(creado);
    }

    @GetMapping("/mensaje/autor/{idautor}")
    public ResponseEntity<?> traerMensajeIdAutor(@PathVariable("idautor") long idAutor) {
        return ResponseEntity.ok(mensajeService.traerMensajesPorAutor(idAutor));
    }

    @GetMapping("/mensaje/receptor/{idreceptor}")
    public ResponseEntity<?> traerMensajeIdReceptor(@PathVariable("idreceptor") long idReceptor) {
        return ResponseEntity.ok(mensajeService.traerMensajesPorReceptor(idReceptor));
    }

}