package org.kk;

import org.apache.kafka.clients.producer.KafkaProducer;

import java.util.Properties;

/**
 * Utility class for working with KafkaProducer.
 */
public class KafkaProducerUtils {
    private static final String KAFKA_SERVER = "localhost:9092";

    private static final String SCHEMA_URL = "http://localhost:8081";

    private static final String KAFKA_AVRO_SERIALIZER = "io.confluent.kafka.serializers.KafkaAvroSerializer";

    public static KafkaProducer getProducer() {
        Properties props = new Properties();
        props.put("bootstrap.servers", KAFKA_SERVER);
        props.put("key.serializer", KAFKA_AVRO_SERIALIZER);
        props.put("value.serializer", KAFKA_AVRO_SERIALIZER);
        props.put("schema.registry.url", SCHEMA_URL);
        return new KafkaProducer<>(props);
    }
}
