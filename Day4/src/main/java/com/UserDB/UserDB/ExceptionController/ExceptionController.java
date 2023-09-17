package com.UserDB.UserDB.ExceptionController;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.boot.context.properties.bind.validation.ValidationErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class ExceptionController {

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolation(ConstraintViolationException cve){
        return new ResponseEntity<>("Not valid due to validation error: " +cve.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);

//        return buildValidationErrors(cve.getConstraintViolations());
    }

//    private List<ValidationError> buildValidationErrors(Set<ConstraintViolation<?>> violations){
//        return violations.
//                stream().
//                map(violation ->
//                        ValidationError.builder().
//                                field(
//                                        StreamSupport.stream(
//                                                        violation.getPropertyPath().spliterator(), false).
//                                                reduce((first, second) -> second).
//                                                orElse(null).
//                                                toString()
//                                ).
//                                error(violation.getMessage()).
//                                build()).
//                collect(toList());
//    }
}
