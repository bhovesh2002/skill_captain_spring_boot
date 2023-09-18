package com.ThymeleafTry.CRUDUsingThymeleaf.UserController;

import com.ThymeleafTry.CRUDUsingThymeleaf.model.User;
import com.ThymeleafTry.CRUDUsingThymeleaf.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

//the whole program felt wierd because of thymeleaf. Not being able to use putmapping and only being limited to get and post was really
//confusing before I figured it out, even then there are many things I'm not clear about.
//I couldn't add exception handling because its already confusing.

@Controller
public class UserController {
    private final UserRepository userRepository;
    private int editedUserId;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
        editedUserId = Integer.MIN_VALUE;
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("user", new User());
        return "users";
    }

    @PostMapping("/users")
    public String createUser(User user) {
        userRepository.save(user);
        return "redirect:/users";
    }

    @GetMapping("/users/edit")
    public String editUserPage(Model model){
        model.addAttribute("message", "User doesn't exist");
        return "edit";
    }


    @PostMapping("/users/edit")
    public String editUser(@RequestParam int id, Model model){
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()){
//            userRepository.deleteById(user.getId());
//            userRepository.save(user);
            editedUserId = id;
            return "edit-user";
        }else {
            model.addAttribute("message", "User doesn't exist");
            return "redirect:/edit";
        }
    }


    //I tried to change the name and email through setters, but for some reason it doesn't work
    @PostMapping("/users/edit-user")
    public String editUser(Model model, @RequestParam String NameOfEditedUser, @RequestParam String EmailOfEditedUser){
//        userRepository.findById(editedUserId).get().setName( NameOfEditedUser);
//        userRepository.findById(editedUserId).get().setEmail(EmailOfEditedUser);
        userRepository.deleteById(editedUserId);
        User editedUser = new User(editedUserId, NameOfEditedUser, EmailOfEditedUser);
        editedUserId = Integer.MIN_VALUE;
        userRepository.save(editedUser);
        return "redirect:/users";
    }




//    @GetMapping("/{id}/edit")
//    public String editUser(@PathVariable int id, Model model){
//        Optional<User> optionalUser = userRepository.findById(id);
//        if(optionalUser.isPresent()){
//
//        }
//    }

    @GetMapping("/{id}/delete")
    public String deleteUser(@PathVariable int id){
        userRepository.deleteById(id);
        return "redirect:/users";
    }

}
