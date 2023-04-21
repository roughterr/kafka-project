package org.kk;

import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class KafkaSimpleExample {
    private static class DemoProducerCallback implements Callback {
        @Override
        public void onCompletion(RecordMetadata recordMetadata, Exception e) {
            System.out.println("Callback onCompletion");
            if (e != null) {
                e.printStackTrace();
            }
        }
    }

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
