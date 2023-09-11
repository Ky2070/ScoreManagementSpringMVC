/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hcmou.service;

import com.hcmou.pojo.User;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author vhuunghia
 */
public interface UserService extends UserDetailsService{
    User getUserByUn(String username);
    boolean authUser(String username, String password);
    User addUser(Map<String, String> params);
    boolean isEmailExists(String email);
    User addTeacherUser(Map<String, String> params);
    boolean isTeacherEmailExists(String email);
    boolean authAdminUser(String username, String password);
}
