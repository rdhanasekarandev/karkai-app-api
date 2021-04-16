package com.karkai.controller;

import com.karkai.modal.User;
import com.karkai.service.UserService;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(value = "user")
public class UserController {
    @Autowired
    private UserService userService;

    //  create new User
    @PostMapping("/add")
    public String addNewUser(@RequestBody User user){
        return userService.addUser(user);
    }

    //  create new User
    @GetMapping("/get")
    public List<User> getAllUser() throws ExecutionException, InterruptedException, IOException, ParseException {
        return userService.getAllUser();
    }

}