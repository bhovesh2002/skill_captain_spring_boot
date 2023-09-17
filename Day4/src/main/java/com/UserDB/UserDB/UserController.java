package com.UserDB.UserDB;

import com.UserDB.UserDB.Repository.UserRepository;
import com.UserDB.UserDB.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//I tried inculcating as many changes as possible, which were specified in the previous code review, but I got really confused
//with the last point. I have added bean validation to make sure the name and email are not blank but how to create a separate
//class for validation is still something I couldn't understand.

@RestController
public class UserController {

    UserRepository userRepository;
    ObjectMapper objectMapper;

    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
        objectMapper = new ObjectMapper();
    }

    @GetMapping("/")
    public String helloWorld(){
        return "Hello World!";
    }

    //add/post new user
    @PostMapping("/users")
    public User addUser(@RequestBody String userString) throws JsonProcessingException {
//        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(userString, User.class);
        userRepository.save(user);
        return user;
    }

    //print all saved users
    @GetMapping("/users")
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    //get a specific user and email based on specified id
    @GetMapping("/users/{id}")
    public String getUserById(@PathVariable String id){
        int correctId = Integer.parseInt(id);
        if(userRepository.findById(correctId).isPresent()){
            User user = userRepository.findById(correctId).get();
            return user.getName() + " " + user.getEmail();
        }else{
            return "No User found";
        }
    }

    //change email of the user
    @PutMapping("/users/{id}/email")
    public User updateEmail(@PathVariable String id, @RequestBody String email){
        int correctId = Integer.parseInt(id);
        if(userRepository.findById(correctId).isPresent()){
            userRepository.findById(correctId).get().setEmail(email);
            return userRepository.findById(correctId).get();
        }else {
            return null;
        }
    }

    //delete a particular user based on id
    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable String id){
        int correctId = Integer.parseInt(id);
        if(userRepository.findById(correctId).isPresent()){
            userRepository.delete(userRepository.findById(correctId).get());
            return "Success";
        }else {
            return "User doesn't exists";
        }
    }

}
