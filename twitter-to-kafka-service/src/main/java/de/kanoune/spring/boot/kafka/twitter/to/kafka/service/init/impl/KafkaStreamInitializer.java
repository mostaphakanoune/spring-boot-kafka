package de.kanoune.spring.boot.kafka.twitter.to.kafka.service.init.impl;


import de.kanoune.spring.boot.kafka.app.config.data.config.KafkaConfigData;
import de.kanoune.spring.boot.kafka.twitter.to.kafka.service.init.StreamInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class KafkaStreamInitializer  implements StreamInitializer {

    private static final Logger LOG = LoggerFactory.getLogger(KafkaStreamInitializer.class);

    private final KafkaConfigData kafkaConfigData;


    public KafkaStreamInitializer(KafkaConfigData kafkaConfigData) {
        this.kafkaConfigData = kafkaConfigData;
    }

    @Override
    public void init() {
        String topicName = getTopicName();
        LOG.info("Topics with name {} is ready for operations!", topicName);

    }

    private String getTopicName() {
        return kafkaConfigData.getTopicName();
    }
}
