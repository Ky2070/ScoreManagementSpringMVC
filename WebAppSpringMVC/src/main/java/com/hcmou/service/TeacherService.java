/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hcmou.service;

import com.hcmou.pojo.Teacher;

/**
 *
 * @author vhuunghia
 */
public interface TeacherService {

    int getIdTeacherByEmail(String email);

    int getidStudentByEmail(String email);
    
    boolean addOrUpdateTeacher(Teacher teacher);
    boolean deleteTeacher(int teacherId);
}
