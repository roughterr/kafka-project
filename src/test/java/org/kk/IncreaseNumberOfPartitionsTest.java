package org.kk;


import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.DescribeTopicsResult;
import org.apache.kafka.clients.admin.NewPartitions;
import org.apache.kafka.clients.admin.TopicDescription;
import org.apache.kafka.common.KafkaFuture;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static junit.framework.TestCase.assertEquals;
import static org.kk.KafkaConstants.CUSTOMER_TOPIC_NAME;

/**
 * Test increasing the number of partitions in a topic.
 */
public class IncreaseNumberOfPartitionsTest {
    private static List<String> TOPIC_LIST = List.of(CUSTOMER_TOPIC_NAME);
    private AdminClientFactory adminClientFactory = new AdminClientFactory();

    /**
     * Returns the number of existing partitions of the topic.
     *
     * @param admin
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private int getNumberOfPartitions(AdminClient admin) throws ExecutionException, InterruptedException {
        DescribeTopicsResult result = admin.describeTopics(TOPIC_LIST);
        Map<String, KafkaFuture<TopicDescription>> values = result.values();
        KafkaFuture<TopicDescription> topicDescription = values.get(CUSTOMER_TOPIC_NAME);
        //number of existing partitions
        return topicDescription.get().partitions().size();
    }

    @Test
    public void addTwoPartitions() throws ExecutionException, InterruptedException {
        try (AdminClient admin = adminClientFactory.newAdminClient()) {
            int partitions = getNumberOfPartitions(admin);
            System.out.println("number of partitions is " + partitions);
            Map<String, NewPartitions> newPartitions = new HashMap<>();
            int newNumberOfPartitions = partitions + 2;
            newPartitions.put(CUSTOMER_TOPIC_NAME, NewPartitions.increaseTo(newNumberOfPartitions));
            admin.createPartitions(newPartitions).all().get();
            assertEquals(newNumberOfPartitions, getNumberOfPartitions(admin));
        }
    }
}
