package com.librarymanagementsystem.librarymanagementsystem.controller;

import com.librarymanagementsystem.librarymanagementsystem.dto.UserLoginDetails;
import com.librarymanagementsystem.librarymanagementsystem.entity.User;
import com.librarymanagementsystem.librarymanagementsystem.exception.UserException;
import com.librarymanagementsystem.librarymanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public User addUser(@RequestBody User user) throws UserException {
        return userService.addUser(user);
  //    return ResponseEntity<>(addedUser, HttpStatus.CREATED);

    }

    @GetMapping("/userId/{id}")
    public User getUser(@PathVariable int id) throws UserException{
        return userService.getUser(id);

    }

    @GetMapping("/users")
    public List<User> getUsers() throws UserException {
        return userService.getUsers();
    }

    @PutMapping("/update")
    public User updateUser(@RequestBody User updatedUser) throws UserException {
        return userService.updateUser(updatedUser);
        }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable int id) throws UserException{
         userService.deleteUser(id);
    }

    @PostMapping("/login")
    public String  loginUser(@RequestBody UserLoginDetails userLoginDetails) throws UserException{
        User loginUser = userService.userLogin(userLoginDetails.getName(), userLoginDetails.getPassword());
        if(loginUser!=null) {
            return "login successful";
        }
        else {
            return "login unsuccessful";
        }
    }
}
