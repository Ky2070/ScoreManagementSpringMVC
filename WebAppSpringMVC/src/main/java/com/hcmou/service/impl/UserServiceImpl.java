/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hcmou.service.impl;

import com.hcmou.pojo.User;
import com.hcmou.repository.UserRepository;
import com.hcmou.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nguye
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepo;
    
    @Override
    public List<User> getUser() {
            
        return this.userRepo.getUser();
            
    }
    
}
