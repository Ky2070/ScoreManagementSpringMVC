/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hcmou.service.impl;

import com.hcmou.pojo.Subject;
import com.hcmou.repository.SubjectRepository;
import com.hcmou.service.SubjectService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nguye
 */
@Service
public class SubjectServiceImpl implements SubjectService{
    @Autowired
    private SubjectRepository subjRepo;

    @Override
    public List<Subject> getSubjects() {
        return this.subjRepo.getSubjects();
    }
    
    
}
