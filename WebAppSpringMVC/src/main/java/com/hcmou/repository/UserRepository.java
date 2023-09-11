/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hcmou.repository;

import com.hcmou.pojo.Student;
import com.hcmou.pojo.Teacher;
import com.hcmou.pojo.User;
import java.util.List;

/**
 *
 * @author vhuunghia
 */
public interface UserRepository {

//    List<User> getUsers();

    User getUserByUsername(String username);

    boolean authUser(String username, String password);

    User addUser(User user);
    User addTeacherUser(User user);
    boolean findEmail(String email);
    List<Student> getStudentbyEmail(String email);
    List<Teacher> getTeacherByEmail(String email);
    boolean findTeacherEmail(String email);
    boolean authAdminUser(String username, String password);
}
