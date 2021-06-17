package com.assignment.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class Response implements Serializable {
	private String jwtToken;
}