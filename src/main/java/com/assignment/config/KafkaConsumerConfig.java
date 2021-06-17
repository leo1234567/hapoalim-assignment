package com.assignment.config;

import com.assignment.properties.AssignmentProperties;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

import java.util.Arrays;
import java.util.Properties;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Autowired
    private AssignmentProperties assignmentProperties;

    @Bean
    public Consumer<String, String> consumer(){
        Properties props = new Properties();
        props.put("bootstrap.servers", assignmentProperties.getBootstrapAddress());
        props.put("group.id", assignmentProperties.getGroupId());
        props.put("enable.auto.commit", assignmentProperties.getEnableAutoCommit());
        props.put("auto.commit.interval.ms", assignmentProperties.getAutoCommitIntervalMs());
        props.put("enable.partition.eof", assignmentProperties.getEnablePartitionEof());
        props.put("key.deserializer", assignmentProperties.getKeyDeserializer());
        props.put("value.deserializer", assignmentProperties.getValueDeserializer());
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(assignmentProperties.getTopicName()));
        return consumer;
    }


}
