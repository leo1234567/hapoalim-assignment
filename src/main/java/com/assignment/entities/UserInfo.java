package com.assignment.entities;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 5926468583005150707L;

    @ApiModelProperty(value = "username", position = 1)
    private String username;
    @ApiModelProperty(value = "address", position = 2)
    private String address;

}