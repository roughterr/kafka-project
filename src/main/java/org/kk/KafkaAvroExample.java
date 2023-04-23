package org.kk;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import static org.kk.KafkaProducerUtils.getProducer;

public class KafkaAvroExample {

    public static void main(String[] args) {
        Producer producer = getProducer();
        // We keep producing new events until someone ctrl-c
        while (true) {
            Customer customer = CustomerGenerator.getNext();
            System.out.println("Generated customer " +
                    customer.toString());
            ProducerRecord<String, Customer> record =
                    new ProducerRecord<>("abba", customer.getName(), customer);
            producer.send(record);
        }

//        new KafkaAvroSerializer();
    }
}
