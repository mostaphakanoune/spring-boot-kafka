package de.kanoune.spring.boot.kafka.kafka.producer.service.impl;

import de.kanoune.microservices.kafka.avro.model.TwitterAvroModel;
import de.kanoune.spring.boot.kafka.kafka.producer.service.KafkaProducer;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;


import javax.annotation.PreDestroy;
import java.util.concurrent.CompletableFuture;

@Service
public class TwitterKafkaProducer implements KafkaProducer<Long, TwitterAvroModel> {

    private static final Logger LOG = LoggerFactory.getLogger(TwitterKafkaProducer.class);

    private KafkaTemplate<Long, TwitterAvroModel> kafkaTemplate;

    public TwitterKafkaProducer(KafkaTemplate<Long, TwitterAvroModel> template) {
        this.kafkaTemplate = template;
    }

    @Override
    public void send(String topicName, Long key, TwitterAvroModel message) {

        LOG.info("Sending message='{}' to topic '{}'", message, topicName);
        CompletableFuture<SendResult<Long, TwitterAvroModel>> kafkaResultFuture =
                (CompletableFuture<SendResult<Long, TwitterAvroModel>>) kafkaTemplate.send(topicName, key, message);

        addCallback(topicName, message, kafkaResultFuture);

    }

    @PreDestroy
    public void close() {
        if (kafkaTemplate != null) {
            LOG.info("Closing kafka producer!");
            kafkaTemplate.destroy();
        }
    }

    private static void addCallback(String topicName, TwitterAvroModel message, CompletableFuture<SendResult<Long, TwitterAvroModel>> kafkaResultFuture) {
        // Chaining the asynchronous computations
        kafkaResultFuture.thenAccept(
                        result -> {
                            System.out.println("Message sent successfully: " + result);
                            final RecordMetadata metadata = result.getRecordMetadata();
                            LOG.debug("Received new metadata, Topic: {}, Partition {}; Offset {}; Timestamp {}, at time {}",
                                    metadata.topic(),
                                    metadata.partition(),
                                    metadata.offset(),
                                    metadata.timestamp(),
                                    System.nanoTime());
                        })
                .exceptionally(e -> {
                    LOG.debug("Error while sending message {} to topic {}", message.toString(), topicName, e);
                    return null;
                });
    }
}
