/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hcmou.service.impl;

import com.hcmou.pojo.Subjectteacher;
import com.hcmou.repository.SubjectTeacherRepository;
import com.hcmou.service.SubjectTeacherService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nguye
 */
@Service
public class SubjectTeacherServiceImpl implements SubjectTeacherService{
    @Autowired
    private SubjectTeacherRepository subjTeachRepo;
    @Override
    public List<Subjectteacher> getSubjectTeachers() {
        return this.subjTeachRepo.getSubjectTeachers();
    }
    
}
