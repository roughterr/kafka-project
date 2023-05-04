package org.kk.examples;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.kk.producer.DemoProducerCallback;

public class KafkaSimpleExample {
    public static void main(String[] args) {
        Properties kafkaProps = new Properties();
        kafkaProps.put("bootstrap.servers", "localhost:9092");
        kafkaProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer producer = new KafkaProducer<String, String>(kafkaProps);
        ProducerRecord<String, String> producerRecord =
                new ProducerRecord<>("abba", "Precision Products", "France");
        try {
            producer.send(producerRecord, new DemoProducerCallback()).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
