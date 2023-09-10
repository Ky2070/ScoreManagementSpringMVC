/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hcmou.service.impl;

import com.hcmou.pojo.Studentsubjectteacher;
import com.hcmou.repository.StudentSubjectTeacherRepository;
import com.hcmou.service.StudentSubjectTeacherService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nguye
 */
@Service
public class StudentSubjectTeacherServiceImpl implements StudentSubjectTeacherService {
    
    @Autowired
    private StudentSubjectTeacherRepository studsubjteachRepo;
    @Override
    public List<Studentsubjectteacher> getStudsubjteachs() {
        return this.studsubjteachRepo.getStudsubjteachs();
    }
    
}
