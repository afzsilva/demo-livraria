package com.afzdev.demo.livraria.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;

@RestControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(DataBaseException.class)
    public ResponseEntity<ProblemDetail> SqlExceptionHelperDuplicateEntry (DataBaseException e, WebRequest request){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, e.getLocalizedMessage());
        problemDetail.setTitle("Database Exception");
        problemDetail.setProperty("Hora da ocorrencia", Instant.now());
        return ResponseEntity.ok(problemDetail);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ProblemDetail> entityNotFound(ResourceNotFoundException e, WebRequest request){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
        problemDetail.setTitle("Registro Inexistente");
        problemDetail.setProperty("Hora da ocorrencia", Instant.now());
        return ResponseEntity.ok(problemDetail);
    }
}
