package com.unla.chefEnCasa.server;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
/*import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.KafkaException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
@Component
@Slf4j*/
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unla.chefEnCasa.server.entity.Comentario;
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
        } catch (IOException e) {
            System.out.println(e);
        }
    }

   /*  @KafkaListener(topics = "Popularidadusuario")
    public void consumePopularidadUsuario(String message) {
        System.out.println("Mensaje recibido: " + message);

    }

    @KafkaListener(topics = "PopularidadReceta")
    public void consumePopularidadReceta(String message) {
        System.out.println("Mensaje recibido: " + message);

        // Procesa el mensaje según tus necesidades
    }

    public void guardarPopularidadUsuario(String json){
        try {
        ObjectMapper objectMapper=new ObjectMapper();
       
            JsonNode jsonNode=objectMapper.readTree(json);

        } catch (JsonMappingException e) {

            e.printStackTrace();
       
        
    }*/

}
/*
 * private KafkaConsumer<String, String> kafkaConsumer;
 * 
 * private Consumer() {
 * try {
 * Properties conf = ConsumerProperties.createConsumerProperties();
 * this.kafkaConsumer = new KafkaConsumer(conf);
 * } catch (Exception ioe) {
 * log.error(ioe.getMessage());
 * }
 * }
 * 
 * public void start() {
 * int count = 0;
 * do {
 * try {
 * kafkaConsumer.subscribe(List.of(TOPIC));
 * ConsumerRecords<String, String> records =
 * kafkaConsumer.poll(Duration.ofSeconds(20));
 * records.forEach(r -> {
 * String msg = String.format("key %s, value %s", r.key(), r.value());
 * log.info(msg);
 * });
 * 
 * } catch (KafkaException e) {
 * log.error(e.getMessage());
 * this.close();
 * }
 * count ++;
 * } while (count <= 100);
 * }
 * 
 * public void close() {
 * this.kafkaConsumer.close();;
 * }
 * 
 * public static Consumer getInstance() {
 * return (Objects.nonNull(consumer)) ? consumer : new Consumer();
 * }
 * 
 * private static Consumer consumer;
 * private static final String TOPIC ="test";
 * private static final String TOPIC2="Comentarios";
 * private static final String TOPIC3="PopularidadReceta";
 * private static final Logger log = LogManager.getLogger(Consumer.class);
 */
