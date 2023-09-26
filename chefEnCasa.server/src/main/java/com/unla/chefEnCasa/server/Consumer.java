package com.unla.chefEnCasa.server;
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
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    @KafkaListener(topics = "test")
    public void consume(String message) {
        System.out.println("Mensaje recibido: " + message);

        // Procesa el mensaje según tus necesidades
    }
}
    /*private KafkaConsumer<String, String> kafkaConsumer;

    private Consumer() {
        try {
            Properties conf = ConsumerProperties.createConsumerProperties();
            this.kafkaConsumer = new KafkaConsumer(conf);
        } catch (Exception ioe) {
            log.error(ioe.getMessage());
        }
    }

    public void start() {
         int count = 0;
        do {
            try {
                kafkaConsumer.subscribe(List.of(TOPIC));
                ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofSeconds(20));
                records.forEach(r -> {
                    String msg = String.format("key %s, value %s", r.key(), r.value());
                    log.info(msg);
                });

            } catch (KafkaException e) {
                log.error(e.getMessage());
                this.close();
            }
            count ++;
        } while (count <= 100);
    }

    public void close() {
        this.kafkaConsumer.close();;
    }

    public static Consumer getInstance() {
        return (Objects.nonNull(consumer)) ? consumer : new Consumer();
    }

    private static Consumer consumer;
    private static final String TOPIC ="test";
    private static final String TOPIC2="Comentarios";
    private static final String TOPIC3="PopularidadReceta";
    private static final Logger log = LogManager.getLogger(Consumer.class);*/
    
