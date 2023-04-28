package org.kk.examples;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.kk.Customer;
import org.kk.CustomerGenerator;
import org.kk.KafkaProducerUtils;

public class KafkaProduceGeneratedObjects {
    public static void main(String[] args) {
        KafkaProducer kafkaProducer = KafkaProducerUtils.getProducer();
        String topic = "abba";

        // We keep producing new events until someone ctrl-c
        while (true) {
            Customer customer = CustomerGenerator.getNext();
            System.out.println("Generated customer " +
                    customer.toString());
            ProducerRecord<String, Customer> record =
                    new ProducerRecord<>(topic, customer.getName(), customer);
            kafkaProducer.send(record);
        }
    }
}
