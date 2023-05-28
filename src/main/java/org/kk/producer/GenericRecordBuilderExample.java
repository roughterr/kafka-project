package org.kk.producer;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.generic.GenericRecordBuilder;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.kk.KafkaConstants;
import org.kk.producer.DemoProducerCallback;
import org.kk.SchemaReader;
import org.kk.producer.KafkaProducerFactory;

import java.io.IOException;

public class GenericRecordBuilderExample {
    public static final String CUSTOMER_NAME = "joshua";

    public static void main(String[] args) throws IOException {
        KafkaProducerFactory kafkaProducerFactory = new KafkaProducerFactory();
        KafkaProducer producer = kafkaProducerFactory.getProducer();
        Schema.Parser parser = new Schema.Parser();
        Schema schema = parser.parse(SchemaReader.readCustomerSchemaFromFile());
        GenericRecordBuilder customerBuilder = new GenericRecordBuilder(schema);
        customerBuilder.set("id", 1);
        customerBuilder.set("name", CUSTOMER_NAME);
        customerBuilder.set("email", "joshua.fluke@ggggeeee.com");
        GenericData.Record myCustomer = customerBuilder.build();
        System.out.println(myCustomer);
        ProducerRecord<String, GenericRecord> data =
                new ProducerRecord<>(KafkaConstants.CUSTOMER_TOPIC_NAME, CUSTOMER_NAME, myCustomer);
        try {
            producer.send(data, new DemoProducerCallback()).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
