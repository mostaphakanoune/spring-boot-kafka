package de.kanoune.spring.boot.kafka.kafka.admin.client;

import de.kanoune.spring.boot.kafka.app.config.data.config.KafkaConfigData;
import de.kanoune.spring.boot.kafka.app.config.data.config.RetryConfigData;
import org.apache.kafka.clients.admin.AdminClient;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;



@Component
public class KafkaAdminClient {

   //private static final Logger LOG = LoggerFactory.getLogger(KafkaAdminClient.class);

    private KafkaConfigData kafkaConfigData;

    private RetryConfigData retryConfigData;

    private AdminClient adminClient;

    private RetryTemplate retryTemplate;

    public KafkaAdminClient(KafkaConfigData config,
                            RetryConfigData retryConfigData,
                            AdminClient client,
                            RetryTemplate template) {
        this.kafkaConfigData = config;
        this.retryConfigData = retryConfigData;
        this.adminClient = client;
        this.retryTemplate = template;
    }
}
