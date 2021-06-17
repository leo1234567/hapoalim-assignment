package com.assignment.services;


import com.assignment.entities.UserInfo;
import com.assignment.properties.AssignmentProperties;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;


import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.time.Duration;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;

@Service
public class UserInfoService {
    private static final Logger logger = LoggerFactory.getLogger(UserInfoService.class);

    @Autowired
    private AssignmentProperties assignmentProperties;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private Consumer consumer;

    public void sendUserInfo(UserInfo userInfo) {
        logger.info(String.format("Send user info for user: %s", userInfo.getUsername()));
        ListenableFuture<SendResult<String, String>> future =
                kafkaTemplate.send(assignmentProperties.getTopicName(), userInfo.getUsername() + ":" + userInfo.getAddress());

        try {
            //blocking wait for completion
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            logger.error(e.getMessage());
        }
    }


    public String getUserInfoProcessResult() {
        ConsumerRecords<String, String> res = consumer.poll(Duration.ofSeconds(1));
        logger.info(String.format("Poll call returned with %d results", res.count()));
        //for our example we get only one message
        Iterator<ConsumerRecord<String, String>> iterator = res.iterator();
        if (iterator.hasNext()) {
            return iterator.next().value();
        }
        return null;
    }
}
