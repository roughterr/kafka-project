package org.kk.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.metrics.stats.CumulativeCount;
import org.kk.Customer;
import org.kk.KafkaConstants;

import java.time.Duration;
import java.util.Collections;

/**
 * Example of subscribing to a Kafka topic.
 */
public class SimpleSubscribeExample {
    private static final Duration TIMEOUT = Duration.ofMillis(100);

    public static void main(String[] args) {
        KafkaConsumerFactory consumerFactory = new KafkaConsumerFactory();
        KafkaConsumer consumer = consumerFactory.getConsumer();
        consumer.subscribe(Collections.singletonList(KafkaConstants.CUSTOMER_TOPIC_NAME));
        Duration timeout = Duration.ofMillis(100);
        // poll for new data
        while(true){
            ConsumerRecords<String, Customer> records =
                    consumer.poll(TIMEOUT);

            for (ConsumerRecord<String, Customer> record : records){
                System.out.println("Key: " + record.key() + ", Value: " + record.value());
                System.out.println("Partition: " + record.partition() + ", Offset:" + record.offset());
            }
        }
    }
}
