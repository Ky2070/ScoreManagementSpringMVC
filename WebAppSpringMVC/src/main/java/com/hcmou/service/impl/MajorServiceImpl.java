/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hcmou.service.impl;

import com.hcmou.pojo.Major;
import com.hcmou.repository.MajorRepository;
import com.hcmou.service.MajorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nguye
 */
@Service
public class MajorServiceImpl implements MajorService {
    @Autowired
    private MajorRepository majorRepo;
    @Override
    public List<Major> getMajorsByDepartmentId(int departmentId) {
        return this.majorRepo.getMajorsByDepartmentId(departmentId);
    }

    @Override
    public List<Major> getMajorsByTrainingTypeId(int trainingtypeId) {
        return this.majorRepo.getMajorsByTrainingTypeId(trainingtypeId);
    }

}
