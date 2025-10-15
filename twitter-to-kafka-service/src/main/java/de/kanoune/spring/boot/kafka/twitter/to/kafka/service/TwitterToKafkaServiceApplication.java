package de.kanoune.spring.boot.kafka.twitter.to.kafka.service;

import de.kanoune.spring.boot.kafka.app.config.data.config.TwitterToKafkaServiceConfigData;
import de.kanoune.spring.boot.kafka.twitter.to.kafka.service.init.StreamInitializer;
import de.kanoune.spring.boot.kafka.twitter.to.kafka.service.runner.StreamRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;

@SpringBootApplication
@ComponentScan(basePackages = "de.kanoune.spring.boot.kafka")
public class TwitterToKafkaServiceApplication  implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(TwitterToKafkaServiceApplication.class);

    private final StreamRunner streamRunner;

    private final StreamInitializer streamInitializer;

    public TwitterToKafkaServiceApplication(StreamRunner streamRunner, StreamInitializer streamInitializer) {
        this.streamRunner = streamRunner;
        this.streamInitializer = streamInitializer;
    }

    public static void main(String[] args) {
        SpringApplication.run(TwitterToKafkaServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        LOG.info("TwitterToKafkaApplication starts...");
        streamInitializer.init();
        streamRunner.run();


    }
}
