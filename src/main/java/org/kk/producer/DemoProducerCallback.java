package org.kk.producer;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

public class DemoProducerCallback implements Callback  {
    @Override
    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
        System.out.println("Callback onCompletion");
        if (e != null) {
            e.printStackTrace();
        }
    }
}
