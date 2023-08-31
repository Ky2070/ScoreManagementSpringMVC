/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hcmou.controllers;

import com.hcmou.pojo.User;
import com.hcmou.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author vhuunghia
 */
@RestController
@RequestMapping("/api")
public class ApiUserController {
    @Autowired
    public UserService userService;
    @GetMapping("/user")
    public ResponseEntity<List<User>> listUser(){
        return new ResponseEntity<>(this.userService.getUsers(), HttpStatus.OK);
    }
}
