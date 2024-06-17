package com.totvs.backend.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<StandardError> handleNegocioException(NegocioException ex,
                                                               HttpServletRequest request) {
        StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Negocio Exception", ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
    }

    @ExceptionHandler(ObjetoNaoEncontradoException.class)
    public ResponseEntity<StandardError> handleCustomException(ObjetoNaoEncontradoException ex,
                                                               HttpServletRequest request) {
        StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
                "ObjetoNaoEncontrado Exception", ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<StandardError> handleCustomException(CustomException ex,
                                                               HttpServletRequest request) {
        StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
                "Custom Exception", ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
