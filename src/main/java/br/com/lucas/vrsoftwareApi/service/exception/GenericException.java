package br.com.lucas.vrsoftwareApi.service.exception;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Builder
@Data
public class GenericException implements Serializable {
    private static final long serialVersionUID = 1L;

    private static long timestamp = new Date().getTime();
    private int status;
    private String error ;
    private String message;
    private String path;



}

