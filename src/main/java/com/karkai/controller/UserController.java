package com.karkai.controller;

import com.karkai.modal.Id;
import com.karkai.modal.Update;
import com.karkai.modal.UpdatePrime;
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

    //  get All User
    @GetMapping("/getAll")
    public List<User> getAllUser() throws ExecutionException, InterruptedException, IOException, ParseException {
        return userService.getAllUser();
    }

    //  get User
    @PostMapping("/get")
    public User getUser(@RequestBody Id id) throws ExecutionException, InterruptedException, IOException, ParseException {
        return userService.getUserById(id.getId());
    }

    //  create new User
    @PostMapping("/update")
    public String updateEmployeeDetails(@RequestBody Update update) throws ExecutionException, InterruptedException {
        return userService.updateUser(update.getId(),update.getField(),update.getValue());
    }
}