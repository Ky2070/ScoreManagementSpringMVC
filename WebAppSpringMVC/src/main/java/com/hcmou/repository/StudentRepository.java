/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.hcmou.repository;


import com.hcmou.pojo.Student;
import com.hcmou.pojo.User;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Kiet
 */

public interface StudentRepository {
    List<Student> getStudents();
//    Score getScoreById(int id);
}
