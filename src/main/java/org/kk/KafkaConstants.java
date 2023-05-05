package org.kk;

public class KafkaConstants {
    public static final String KAFKA_SERVER = "localhost:9092";
    public static final String SCHEMA_URL = "http://localhost:8081";
    public static final String KAFKA_AVRO_SERIALIZER = "io.confluent.kafka.serializers.KafkaAvroSerializer";
    public static final String KAFKA_AVRO_DESERIALIZER = "io.confluent.kafka.serializers.KafkaAvroDeserializer";
    public static final String CUSTOMER_TOPIC_NAME = "customers4";
}
