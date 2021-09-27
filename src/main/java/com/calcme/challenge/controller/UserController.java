package com.calcme.challenge.controller;

import com.calcme.challenge.model.User;
import com.calcme.challenge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public List<User> getUsers() {
        return service.findAll();
    }

    @PostMapping
    public ResponseEntity addUser(@RequestBody User user) {
		User savedUser = service.save(user);
        return new ResponseEntity(savedUser, HttpStatus.OK);
    }

}