package edu.miu.assignment.others;

import edu.miu.assignment.exceptions.ApiError;
import edu.miu.assignment.exceptions.HttpStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice(annotations = RestController.class, basePackages = "edu.miu.assignment.controllers")
public class HttpStatusHandler {
    @ExceptionHandler(HttpStatusException.class)
    public ResponseEntity<?> handleConflict(HttpStatusException ex) {
        return ResponseEntity.status(ex.getStatus()).body(new ApiError(ex.getStatus().value(), ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiError(500, ex.getMessage()));
    }
}
