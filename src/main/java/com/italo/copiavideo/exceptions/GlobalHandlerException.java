package com.italo.copiavideo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseException> handlerResourceNotFoundException(ResourceNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseException(HttpStatus.NOT_FOUND, exception.getMessage()));
    }

    @ExceptionHandler(UserAlreadyRegisterException.class)
    public ResponseEntity<ResponseException> handlerUserAlreadyRegister(UserAlreadyRegisterException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseException(HttpStatus.CONFLICT, exception.getMessage()));
    }

    @ExceptionHandler(ResourceAlreadyExitsException.class)
    public ResponseEntity<ResponseException> handlerResourceAlreadyException(ResourceAlreadyExitsException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseException(HttpStatus.CONFLICT, exception.getMessage()));
    }

    @ExceptionHandler(FailedCreateTokenException.class)
    public ResponseEntity<ResponseException> handlerFailedCreateToken(FailedCreateTokenException exception){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage()));
    }
}
