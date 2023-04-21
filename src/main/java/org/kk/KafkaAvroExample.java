package org.kk;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class KafkaAvroExample {
    public static final String SCHEMA_URL = "http://schema-registry.server.name:8081/";

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer",
                "io.confluent.kafka.serializers.KafkaAvroSerializer");
        props.put("value.serializer",
                "io.confluent.kafka.serializers.KafkaAvroSerializer");
        props.put("schema.registry.url", SCHEMA_URL);
        String topic = "customerContacts";
        Producer<String, Customer> producer = new KafkaProducer<>(props);
        // We keep producing new events until someone ctrl-c
        while (true) {
            Customer customer = CustomerGenerator.getNext();
            System.out.println("Generated customer " +
                    customer.toString());
            ProducerRecord<String, Customer> record =
                    new ProducerRecord<>(topic, customer.getName(), customer);
            producer.send(record);
        }
    }
}
