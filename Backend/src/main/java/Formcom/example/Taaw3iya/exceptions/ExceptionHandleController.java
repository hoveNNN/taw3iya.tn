package Formcom.example.Taaw3iya.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;

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

     @ExceptionHandler(AuthorizationDeniedException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleAuthorizationDeniedException(AuthorizationDeniedException e, WebRequest request) {
        final ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(Instant.now())
                .error("Forbidden")
                .status(HttpStatus.FORBIDDEN.value())
                .message(e.getMessage())
                .path(request.getDescription(false))
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }
    @ExceptionHandler(DuplicateUserException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ErrorResponse> HandleDuplicateUserException(DuplicateUserException e, WebRequest request) {
        final ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(Instant.now())
                .error("Duplicate User")
                .status(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .path(request.getDescription(false))
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
