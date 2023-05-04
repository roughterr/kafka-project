package org.kk.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.kk.KafkaConstants;

import java.util.Properties;

public class KafkaProducerFactory {
    public KafkaProducer getProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstants.KAFKA_SERVER);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, KafkaConstants.KAFKA_AVRO_SERIALIZER);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaConstants.KAFKA_AVRO_SERIALIZER);
        props.put("schema.registry.url", KafkaConstants.SCHEMA_URL);
        return new KafkaProducer<>(props);
    }
}
