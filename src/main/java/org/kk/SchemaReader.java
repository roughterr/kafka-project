package org.kk;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Reads the schema from file.
 */
public class SchemaReader {
    public static final String CUSTOMER_SCHEMA_FILEPATH = "avro/Customer.avsc";

    public static String readCustomerSchemaFromFile() throws IOException {
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
}
