package br.com.lucas.vrsoftwareApi.resource.exception;

import br.com.lucas.vrsoftwareApi.service.exception.GenericException;
import br.com.lucas.vrsoftwareApi.service.exception.ObjectNotFoundException;
import br.com.lucas.vrsoftwareApi.service.exception.UniqueFieldException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ResourceExceptionHandler {


    @ExceptionHandler(UniqueFieldException.class)
    public ResponseEntity<GenericException> uniqueField(UniqueFieldException uniqueFieldException, HttpServletRequest httpServletRequest){
        var err = GenericException
                .builder()
                .message(uniqueFieldException.getMessage())
                .error("uniqueField")
                .status(HttpStatus.BAD_REQUEST.value())
                .path( httpServletRequest.getContextPath()+httpServletRequest.getServletPath())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(err);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<GenericException> notFaund(ObjectNotFoundException objectNotFoundException, HttpServletRequest httpServletRequest){
        var err = GenericException
                .builder()
                .message(objectNotFoundException.getMessage())
                .error("NotFaund")
                .status(HttpStatus.NOT_FOUND.value())
                .path( httpServletRequest.getContextPath()+httpServletRequest.getServletPath())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(err);
    }



}
