package org.kk.examples;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.kk.KafkaProducerUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class SchemaStringExample {
    public static final String CUSTOMER_SCHEMA_FILEPATH = "avro/Customer.avsc";
    public static final int NUMBER_OF_CUSTOMERS = 10;

    private static String readCustomerSchemaFromFile() throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();

        StringBuilder textBuilder = new StringBuilder();
        try (InputStream is = classloader.getResourceAsStream(CUSTOMER_SCHEMA_FILEPATH);) {
            try (Reader reader = new BufferedReader(new InputStreamReader
                    (is, StandardCharsets.UTF_8))) {
                int c = 0;
                while ((c = reader.read()) != -1) {
                    textBuilder.append((char) c);
                }
            }
        }
        String schemaString = textBuilder.toString();
        System.out.println("schemaString = " + schemaString);
        return schemaString;
    }

    public static void main(String[] args) throws IOException {
        KafkaProducer kafkaProducer = KafkaProducerUtils.getProducer();
        Schema.Parser parser = new Schema.Parser();
        Schema schema = parser.parse(readCustomerSchemaFromFile());
        for (int nCustomers = 0; nCustomers < NUMBER_OF_CUSTOMERS; nCustomers++) {
            String name = "exampleCustomer" + nCustomers;
            String email = "example " + nCustomers + "@example.com";
            GenericRecord customer = new GenericData.Record(schema);
            customer.put("id", nCustomers);
            customer.put("name", name);
            customer.put("email", email);
            ProducerRecord<String, GenericRecord> data =
                    new ProducerRecord<>("customers", name, customer);
            System.out.println("sending customer: " + customer);
            kafkaProducer.send(data);
        }
    }
}
