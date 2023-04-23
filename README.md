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
docker run -e KAFKA_ZOOKEEPER_CONNECT=host.docker.internal:2181 -e KAFKA_ADVERTISED_LISTENERS='PLAINTEXT://host.docker.internal:9092,PLAINTEXT_HOST://host.docker.internal:29092' -e KAFKA_LISTENER_SECURITY_PROTOCOL_MAP='PLAINTEXT:PLAINTEXT,PLAINTEXT_HOST:PLAINTEXT' -p 9101:9101 --name pan-kurchak -it confluentinc/cp-server:latest