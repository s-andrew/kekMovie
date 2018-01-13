package com.kek.kekMovie.Controllers;

import com.kek.kekMovie.DTO.User;
import com.kek.kekMovie.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user_api")
@PreAuthorize("hasAuthority('ADMIN')")
public class Users {

    @Autowired
    UserService userService;

    @GetMapping
    public Iterable<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") long id){
        return userService.getUser(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") long id){
        userService.deleteUser(id);
        return new ResponseEntity("Para-pa-para", HttpStatus.OK);
    }


}
