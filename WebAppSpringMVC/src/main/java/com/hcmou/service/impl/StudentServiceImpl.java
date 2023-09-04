/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hcmou.service.impl;

import com.hcmou.pojo.Student;
import com.hcmou.repository.StudentRepository;
import com.hcmou.service.StudentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nguye
 */
@Service
public class StudentServiceImpl implements StudentService {
    
     @Autowired
    private StudentRepository studRepo;
    
    /**
     *
     * @return
     */
    @Override
    public List<Student> getStudents() {
        return this.studRepo.getStudents();
    }
    
}