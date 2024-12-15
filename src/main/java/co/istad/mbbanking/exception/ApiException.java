package co.istad.mbbanking.exception;


import co.istad.mbbanking.errorrespone.ErorrRespone;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ApiException {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    Map<?,?> handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<co.istad.mbbanking.errorrespone.FieldError> fieldErrors = new ArrayList<>();
        ex.getFieldErrors().forEach(e -> {
            fieldErrors.add(new co.istad.mbbanking.errorrespone.FieldError(e.getField(), e.getDefaultMessage()));
        });
        return Map.of("error", ErorrRespone.builder()
                        .code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                        .response(fieldErrors)
                .build());
    }
    @ExceptionHandler(ResponseStatusException.class)
    ResponseEntity<?> handlerRequestException(ResponseStatusException e) {
        System.out.println(e.getMessage());
        ErorrRespone<String> errorResponse = ErorrRespone.<String>builder()
                .code(String.valueOf(e.getStatusCode()))
                .response(e.getReason())
                .build();
        return ResponseEntity.status(e.getStatusCode()).body(Map.of("error", errorResponse));
    }
}
