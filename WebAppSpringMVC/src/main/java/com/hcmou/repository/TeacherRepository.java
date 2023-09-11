/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hcmou.repository;

import com.hcmou.pojo.Teacher;
import java.util.List;

/**
 *
 * @author vhuunghia
 */
public interface TeacherRepository {
    int getidTeacherByEmail(String email);
    int getidStudentByEmail(String email);
    boolean addOrUpdateTeacher(Teacher teacher);
    boolean deleteTeacher(int teacherId);
}
