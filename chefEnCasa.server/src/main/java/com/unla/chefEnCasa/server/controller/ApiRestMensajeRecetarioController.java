package com.unla.chefEnCasa.server.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unla.chefEnCasa.server.Soap.SoapService.MensajeService;
import com.unla.chefEnCasa.server.Soap.SoapService.RecetarioService;
import com.unla.chefEnCasa.server.Soap.model.RecetarioModel;
import com.unla.chefEnCasa.server.Soap.repository.RecetarioRespository;
import com.unla.chefEnCasa.server.dto.AgregarRecetaRecetarioDto;
import com.unla.chefEnCasa.server.dto.CrearMensajeRequestDto;
import com.unla.chefEnCasa.server.dto.CrearRecetarioRequestDto;
import com.unla.chefEnCasa.server.dto.RemoverRecetaRecetario;
import com.unla.chefEnCasa.server.dto.ResponderMensajeRequest;
import com.unla.chefEnCasa.server.entity.Receta;
import com.unla.chefEnCasa.server.repository.RecetaRepository;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class ApiRestMensajeRecetarioController {

    @Autowired
    private MensajeService mensajeService;

    @Autowired
    private RecetarioService recetarioService;

    @Autowired
    private RecetarioRespository recetarioRespository;

    @Autowired
    private RecetaRepository recetaRepository;

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

    @PostMapping("/recetario/crear")
    public ResponseEntity<?> crearRecetario(@RequestBody CrearRecetarioRequestDto request) {
        RecetarioModel recetario = new RecetarioModel();
        boolean response = false;
        try {
            recetario.setIdUsuario(request.getIdUsuario());
            recetario.setNombre(request.getNombre());
            recetarioRespository.save(recetario);
            response = true;
        } catch (Exception e) {
            System.out.println(e);

        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/recetario/borrar/{idrecetario}")
    public ResponseEntity<?> borrarRecetario(@PathVariable("idrecetario") long idRecetario) {
        boolean response = false;
        try {
            recetarioRespository.deleteById(idRecetario);
            response = true;
        } catch (Exception e) {
            System.out.println(e);

        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/recetario/traerRecetarios/{idusuario}")
    public ResponseEntity<?> traerRecetariosPorUsuario(@PathVariable("idusuario") long idUsuario) {
        List<RecetarioModel> lista = recetarioRespository.findByIdUsuario(idUsuario);
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/recetario/traerRecetario/{idrecetario}")
    public ResponseEntity<?> traerRecetarioConRecetas(@PathVariable("idrecetario") long idRecetario) {
        Optional<RecetarioModel> lista = recetarioRespository.findById(idRecetario);
        return ResponseEntity.ok(lista);
    }

    @PostMapping("/recetario/agregarReceta")
    public ResponseEntity<?> agregarRecetaRecetario(@RequestBody AgregarRecetaRecetarioDto request) {
        boolean dev = true;
        RecetarioModel recetario = recetarioRespository.findById(request.getIdRecetario()).orElseThrow(null);
        Receta receta = recetaRepository.findById(request.getIdReceta()).orElseThrow(null);
        recetario.getRecetas().add(receta);
        recetarioRespository.save(recetario);
        return ResponseEntity.ok(dev);
    }

    @PostMapping("/recetario/borrarReceta")
    public ResponseEntity<?> removerRecetaRecetaRecetario(@RequestBody RemoverRecetaRecetario request) {
        boolean dev = true;
        RecetarioModel recetario = recetarioRespository.findById(request.getIdRecetario()).orElseThrow(null);
        Receta receta = recetaRepository.findById(request.getIdReceta()).orElseThrow(null);
        recetario.getRecetas().remove(receta);
        recetarioRespository.save(recetario);
        return ResponseEntity.ok(dev);
    }

}