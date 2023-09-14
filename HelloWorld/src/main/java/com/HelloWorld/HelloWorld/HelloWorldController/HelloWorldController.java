package com.HelloWorld.HelloWorld.HelloWorldController;

import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class HelloWorldController {

    @GetMapping(value = { "/greet", "/greet/{name}", "/greet/{name}/{language}"})
    public String helloWorld(@PathVariable Optional<String> name, @PathVariable Optional<String> language){
        if(name.isPresent() && language.isPresent()){
            return "Hello, "+name.get()+" in "+language.get();
        } else if (name.isPresent() && !language.isPresent()) {
            return "Hello, "+name.get()+"!";
        } else {
            return "Hello World";
        }
    }
//I also tried implementing error handling(unsuccessfully), so please do check that too!
}
