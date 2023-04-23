### Install Zookeeper, Kafka locally

### start Kafka server:
kafka-server-start -daemon /opt/homebrew/etc/kafka/server.properties

### create topic:
kafka-topics --create abba --bootstrap-server=localhost:9092

### see the list of Kafka topics:
kafka-topics --list --bootstrap-server localhost:9092

### describe topics:
kafka-topics --bootstrap-server localhost:9092 --describe --topic abba

### send messages to the topics:
kafka-console-producer --bootstrap-server localhost:9092 --topic abba

### receive messages from the topic:
kafka-console-consumer --bootstrap-server localhost:9092 --topic abba --from-beginning

### Deploy Confluent Platform:
docker run -e KAFKA_ZOOKEEPER_CONNECT=host.docker.internal:2181 -e KAFKA_ADVERTISED_LISTENERS='PLAINTEXT://host.docker.internal:9092,PLAINTEXT_HOST://host.docker.internal:29092' -e KAFKA_LISTENER_SECURITY_PROTOCOL_MAP='PLAINTEXT:PLAINTEXT,PLAINTEXT_HOST:PLAINTEXT' -p 8081:8081 --name pan-kurchak -it confluentinc/cp-server:latest

### In case you receive the following error:
Exception in thread "main" org.apache.kafka.common.config.ConfigException: Invalid value io.confluent.kafka.serializers.KafkaAvroSerializer for configuration key.serializer: Class io.confluent.kafka.serializers.KafkaAvroSerializer could not be found.
https://intellij-support.jetbrains.com/hc/en-us/community/posts/207108385-IntelliJ-not-able-to-resolve-classes-of-external-Libraries-for-Maven-projects
In IntelliJ IDEA: File -> Invalidate Caches
