/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hcmou.service.impl;

import com.hcmou.pojo.Schoolyear;
import com.hcmou.repository.SchoolYearRepository;
import com.hcmou.repository.impl.SchoolYearRepositoryImp;
import com.hcmou.service.SchoolYearService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author vhuunghia
 */
@Service
public class SchoolYearServiceImp implements SchoolYearService{
    @Autowired
    private SchoolYearRepository schoolYearRepository;
    
    
    @Override
    public List<Schoolyear> getListSchoolYear(String currentYear){
        
        
        
        return this.schoolYearRepository.getListSchoolYear(currentYear);
    }
}
