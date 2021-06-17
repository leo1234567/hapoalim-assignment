package com.assignment.controller;

import com.assignment.entities.UserInfo;
import com.assignment.services.UserInfoService;
import io.swagger.annotations.ApiImplicitParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserInfoController {
    private static final Logger logger = LoggerFactory.getLogger(UserInfoController.class);

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", example = "Access_token")
    public ResponseEntity<?> saveUserInfo(@RequestBody UserInfo userInfo) {
        //transfer user info to message broker
        userInfoService.sendUserInfo(userInfo);
        //try to get response message
        String result = userInfoService.getUserInfoProcessResult();
        if (result != null) {
            logger.info(String.format("Response from messaging service: %s", result));
            return ResponseEntity.status(HttpStatus.CREATED).body(String.format("Response from messaging service: %s", result));
        } else
            logger.info("Message not found");
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Message not found");
    }

}
