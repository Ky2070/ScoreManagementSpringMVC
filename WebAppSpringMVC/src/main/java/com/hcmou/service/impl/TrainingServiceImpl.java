/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hcmou.service.impl;

import com.hcmou.pojo.Trainingtype;
import com.hcmou.repository.TrainingTypeRepository;
import com.hcmou.service.TrainingTypeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nguye
 */
@Service
public class TrainingServiceImpl implements TrainingTypeService{
    
    @Autowired
    private TrainingTypeRepository trainRepo;
    
    @Override
    public List<Trainingtype> getTrainingType() {
        return this.trainRepo.getTrainingType();
    }
    
}
