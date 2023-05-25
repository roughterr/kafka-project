package org.kk.examples;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.ListTopicsResult;

import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * Connects to Kafka and gets a list of Kafka topics.
 */
public class AdminClientListTopicsExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties props = new Properties();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        AdminClient admin = AdminClient.create(props);

        // Do something useful with AdminClient
        ListTopicsResult topics = admin.listTopics();
        topics.names().get().forEach(System.out::println);

        admin.close(Duration.ofSeconds(30));
    }
}
