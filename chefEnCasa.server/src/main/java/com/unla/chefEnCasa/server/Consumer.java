/*package com.unla.chefEnCasa.server;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unla.chefEnCasa.server.entity.Comentario;
import com.unla.chefEnCasa.server.entity.Receta;
import com.unla.chefEnCasa.server.entity.Usuario;
import com.unla.chefEnCasa.server.repository.ComentarioRepository;
import com.unla.chefEnCasa.server.repository.RecetaRepository;
import com.unla.chefEnCasa.server.repository.UsuarioRepository;

@Service
public class Consumer {
    @Autowired
    private ComentarioRepository comentarioRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RecetaRepository recetaRepository;

    @KafkaListener(topics = "Comentarios")
    @Scheduled(fixedDelay = 10000)
    public void consumeMensajes(String message) {
        System.out.println("Mensaje recibido: " + message);
        this.guardarComentarios(message);

    }

    public void guardarComentarios(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(json);
            Comentario comentario = new Comentario();
            comentario.setUsuario(usuarioRepository.getReferenceById(jsonNode.get("idRedactor").asLong()));
            comentario.setReceta(recetaRepository.getReferenceById(jsonNode.get("idReceta").asLong()));
            comentario.setComentario(jsonNode.get("comentario").asText());
            comentarioRepository.save(comentario);
        } catch (JsonProcessingException e) {
            System.out.println(e);
        }
    }

    @KafkaListener(topics = "Popularidadusuario")
    public void consumePopularidadUsuario(String message) {
        System.out.println("Mensaje recibido: " + message);
        this.guardarPopularidadUsuario(message);

    }

    public void guardarPopularidadUsuario(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(json);
            Optional<Usuario> user = usuarioRepository.findById(jsonNode.get("idUsuarioSeguido").asLong());
            int puntaje = user.get().getPuntajeUsuario() + jsonNode.get("puntaje").asInt();
            user.get().setPuntajeUsuario(puntaje);
            usuarioRepository.save(user.get());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @KafkaListener(topics = "PopularidadReceta")
    public void consumePopularidadReceta(String message) {
        System.out.println("Mensaje recibido: " + message);
        this.guardarPopularidadReceta(message);
    }

    public void guardarPopularidadReceta(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(json);
            Optional<Receta> recipe = recetaRepository.findById(jsonNode.get("idReceta").asLong());
            boolean esCalificacion = jsonNode.get("calificacion").asBoolean();
            int puntaje = 0;
            int calificacion = 0;

            puntaje = recipe.get().getPuntaje() + jsonNode.get("puntaje").asInt();
            if (esCalificacion) {
                calificacion = recipe.get().getCantidadCalificaciones() + 1;
            }

            recipe.get().setPuntaje(puntaje);
            recipe.get().setCantidadCalificaciones(calificacion);
            if (calificacion != 0) {
                recipe.get().setPromedio(puntaje / calificacion);
            } else {
                recipe.get().setPromedio(puntaje / 1);
            }
            recetaRepository.save(recipe.get());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
*/