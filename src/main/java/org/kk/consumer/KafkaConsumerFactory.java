package org.kk.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.kk.KafkaConstants;

import java.util.Properties;

public class KafkaConsumerFactory {
    public KafkaConsumer getConsumer() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstants.KAFKA_SERVER);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "CountryCounter");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, KafkaConstants.KAFKA_AVRO_DESERIALIZER);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaConstants.KAFKA_AVRO_DESERIALIZER);
        props.put("schema.registry.url", KafkaConstants.SCHEMA_URL);
        return new KafkaConsumer<>(props);
    }
}
