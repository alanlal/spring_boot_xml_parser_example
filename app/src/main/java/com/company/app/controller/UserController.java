package com.company.app.controller;

import com.company.app.models.User;
import com.company.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/users")
    public List<User> findAll(){
        return userRepository.findAll();
    }


    @GetMapping(path="/user")
    public List<User> findByUser(
            @RequestParam(name="search") String name
    ) {
        return userRepository.findByName(name);
    }


    @GetMapping(path="/phonenumber")
    public List<User> findByPhoneNumber(
            @RequestParam(name="search") String number
    ) {
        return userRepository.findByPhoneNumber(number);
    }

    @GetMapping(path="/address")
    public List<User> findByAddress(
            @RequestParam(name="search") String address
    ) {
        return userRepository.findByAddress(address);
    }

}
