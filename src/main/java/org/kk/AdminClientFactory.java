package org.kk;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;

import java.util.Properties;

import static org.kk.KafkaConstants.KAFKA_SERVER;

/**
 * AdminClient generator. Don't forget to close an AdminClient object.
 */
public class AdminClientFactory {
    public AdminClient newAdminClient() {
        Properties props = new Properties();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_SERVER);
        return AdminClient.create(props);
    }
}
