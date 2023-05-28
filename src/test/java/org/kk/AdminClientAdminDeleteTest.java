package org.kk;

import org.apache.kafka.clients.admin.*;
import org.junit.Assert;
import org.junit.Test;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.kk.KafkaConstants.CUSTOMER_TOPIC_NAME;

/**
 * Integration test for topic deleting functionality.
 */
public class AdminClientAdminDeleteTest {
    private static List<String> TOPIC_LIST = List.of(CUSTOMER_TOPIC_NAME);

    private AdminClientFactory adminClientFactory = new AdminClientFactory();

    @Test
    public void deleteTopicTest() throws ExecutionException, InterruptedException {
        AdminClient admin = adminClientFactory.newAdminClient();
        try {
            //create if doesn't exist
            CreateTopicsResult newTopic = admin.createTopics(Collections.singletonList(
                    new NewTopic(CUSTOMER_TOPIC_NAME, 1, (short) 1)));
            // print the list of topics
            ListTopicsResult topics = admin.listTopics();
            topics.names().get().forEach(System.out::println);

            //delete topic
            // Check that it is gone. Note that due to the async nature of deletes,
            // it is possible that at this point the topic still exists
            admin.deleteTopics(TOPIC_LIST).all().get();
            try {
                DescribeTopicsResult customerTopicResult = admin.describeTopics(TOPIC_LIST);
                TopicDescription customerTopicDescription = customerTopicResult.values().get(CUSTOMER_TOPIC_NAME).get();
                Assert.fail("Topic " + CUSTOMER_TOPIC_NAME + " is still around");
            } catch (ExecutionException e) {
                System.out.println("Topic " + CUSTOMER_TOPIC_NAME + " is gone");
            }
        } finally {
            admin.close(Duration.ofSeconds(30));
        }
    }
}
