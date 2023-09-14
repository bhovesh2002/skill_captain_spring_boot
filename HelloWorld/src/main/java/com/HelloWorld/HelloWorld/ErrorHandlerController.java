package com.HelloWorld.HelloWorld;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;


//I tried checking online how to throw exception when 404 not found occurs, and this is what it basically told me to do.
//create an error-handler controller class and create an exception method and also change application.properties because spring
// doesn't throw exception when 404 not found occurs unless we make it so. Yet for some reason this doesn't work.
//How can I change the code to make it throw this custom exception with the message.
@RestControllerAdvice
public class ErrorHandlerController {

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND )
    public String handleNotFoundError() {
        return "path does not exists";
    }
}
