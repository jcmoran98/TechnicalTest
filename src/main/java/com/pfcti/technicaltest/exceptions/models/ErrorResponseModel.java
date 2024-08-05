package com.pfcti.technicaltest.exceptions.models;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ErrorResponseModel {

	private int status;
    private String message;
    private String error;
    private LocalDateTime timestamp;
    private String path;
    
}
