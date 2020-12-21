package ru.bellintegrator.practice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.bellintegrator.practice.dto.ApiResponse;
import ru.bellintegrator.practice.dto.ErrorResponse;

import java.util.UUID;

@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {
    private static Logger log = LoggerFactory.getLogger(ResponseExceptionHandler.class);
    @ExceptionHandler(value = Exception.class)
    private ResponseEntity<ApiResponse> handleError(Exception ex){
        if(ex instanceof ResponseStatusException){
            ResponseStatusException statusException = (ResponseStatusException) ex;
            return new ResponseEntity<>(new ErrorResponse(statusException.getReason()), statusException.getStatus());
        }
        else {
            String uuid = UUID.randomUUID().toString();
            log.error(uuid, ex);
            return new ResponseEntity<>(new ErrorResponse("что-то пошло не так, обратитесь в техподдержку. ID ошибки: " + uuid), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
