### Install Zookeeper, Kafka locally

### Start Zookeper
zkServer start

### start Kafka server:
kafka-server-start -daemon /opt/homebrew/etc/kafka/server.properties

### Create topic:
kafka-topics --create abba --bootstrap-server=localhost:9092

### See the list of Kafka topics:
kafka-topics --list --bootstrap-server localhost:9092

### Describe topic:
kafka-topics --bootstrap-server localhost:9092 --describe --topic abba

### Send messages to the topic:
kafka-console-producer --bootstrap-server localhost:9092 --topic abba

### Receive messages from the topic:
kafka-console-consumer --bootstrap-server localhost:9092 --topic abba --from-beginning

### In case you receive the following error:
Exception in thread "main" org.apache.kafka.common.config.ConfigException: Invalid value io.confluent.kafka.serializers.KafkaAvroSerializer for configuration key.serializer: Class io.confluent.kafka.serializers.KafkaAvroSerializer could not be found.
https://intellij-support.jetbrains.com/hc/en-us/community/posts/207108385-IntelliJ-not-able-to-resolve-classes-of-external-Libraries-for-Maven-projects
In IntelliJ IDEA: File -> Invalidate Caches

### Download Confluent Platform using only Confluent Community components https://docs.confluent.io/platform/current/installation/installing_cp/zip-tar.html#prod-kafka-cli-install
### Set $CONFLUENT_HOME variable to the path of a folder where you extracted the Confluent Platform
### Start Schema Registry. Run this command in its own terminal.
${CONFLUENT_HOME}/bin/schema-registry-start ${CONFLUENT_HOME}/etc/schema-registry/schema-registry.properties
