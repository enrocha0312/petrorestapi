package com.mindsim.petroapi.controllers.exceptions;

import com.mindsim.petroapi.services.exceptions.DataIntegrityViolationException;
import com.mindsim.petroapi.services.exceptions.DatabaseException;
import com.mindsim.petroapi.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;


@ControllerAdvice
public class ResourceExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest httpServletRequest){
        String error = "Resource not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError standardError = new StandardError(Instant.now(), status.value(), error, e.getMessage(), httpServletRequest.getRequestURI());
        return ResponseEntity.status(status).body(standardError);//devolve o erro no corpo da requisicao
    }
    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest httpServletRequest){
        String error = "Database Error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError standardError = new StandardError(Instant.now(), status.value(), error, e.getMessage(), httpServletRequest.getRequestURI());
        return ResponseEntity.status(status).body(standardError);
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> idWhichExists(DataIntegrityViolationException e, HttpServletRequest httpServletRequest){
        String error = "Object with Id already exists";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError standardError = new StandardError(Instant.now(), status.value(), error, e.getMessage(), httpServletRequest.getRequestURI());
        return ResponseEntity.status(status).body(standardError);
    }
}
