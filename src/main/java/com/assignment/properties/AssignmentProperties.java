package com.assignment.properties;

import lombok.Getter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties
@Getter
public class AssignmentProperties {

    @Value(value = "${assignment.enable.auto.commit}")
    private String enableAutoCommit;
    @Value(value = "${assignment.auto.commit.interval.ms}")
    private String autoCommitIntervalMs;
    @Value(value = "${assignment.enable.partition.eof}")
    private String enablePartitionEof;
    @Value(value = "${assignment.key.deserializer}")
    private String keyDeserializer;
    @Value(value = "${assignment.value.deserializer}")
    private String valueDeserializer;

    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;
    @Value(value = "${kafka.topicName}")
    private String topicName;
    @Value(value = "${kafka.groupId}")
    private String groupId;

    @Value(value = "${jwt.secret}")
    private String secret;

}
