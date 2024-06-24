package Formcom.example.Taaw3iya.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import jakarta.persistence.EntityNotFoundException;

@ControllerAdvice
public class ExceptionHandleController {


    @ExceptionHandler(DuplicatePostExecption.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ErrorResponse> HandleDuplicatePostException(DuplicatePostExecption e, WebRequest request){
        final ErrorResponse errorResponse=ErrorResponse.builder().timestamp(Instant.now())
        .error("duplicate Post")
        .status(HttpStatus.BAD_REQUEST.value())
        .message(e.getMessage())
        .path(request.getDescription(false))
        .build();
       return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST); 

    }

    
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity<ErrorResponse> HandleEntityNotFoundException(EntityNotFoundException e, WebRequest request){
        final ErrorResponse errorResponse=ErrorResponse.builder().timestamp(Instant.now())
        .error("Invalid ID")
        .status(HttpStatus.BAD_REQUEST.value())
        .message(e.getMessage())
        .path(request.getDescription(false))
        .build();
       return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND); 

    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity<ErrorResponse> HandleException(Exception e, WebRequest request){
        final ErrorResponse errorResponse=ErrorResponse.builder().timestamp(Instant.now())
        .error("Inernal Server Error")
        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .message(e.getMessage())
        .path(request.getDescription(false))
        .build();
       return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR); 

    }
}
