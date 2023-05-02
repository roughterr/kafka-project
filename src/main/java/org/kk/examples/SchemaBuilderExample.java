package org.kk.examples;

import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.kk.KafkaProducerUtils;

public class SchemaBuilderExample {

    public static void main(String[] args) {
        KafkaProducer kafkaProducer = KafkaProducerUtils.getProducer();

        Schema schema = SchemaBuilder.record("TestFile")
                .namespace("com.example.kafka.data.ingestion.model")
                .fields()
                .requiredLong("date")
                .requiredInt("counter")
                .requiredString("mc")
                .endRecord();

        GenericRecord entry1 = new GenericData.Record(schema);
        entry1.put("date", 1L);
        entry1.put("counter", 2);
        entry1.put("mc", "3");
        kafkaProducer.send(new ProducerRecord<>("customers1", entry1));

    }
}
