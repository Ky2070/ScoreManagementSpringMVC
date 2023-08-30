/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hcmou.service.impl;

import com.hcmou.pojo.Score;
import com.hcmou.repository.ScoreRepository;
import com.hcmou.service.ScoreService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Kiet
 */
//Tầng này để kết nối với controller để xử lý
//Lưu ý gắn annotation @Service
@Service
public class ScoreServiceImpl implements ScoreService{
    @Autowired
    private ScoreRepository scoreRepo;
    
    
    @Override
    @Transactional
    public List<Score> getScores() {
        return this.scoreRepo.getScores();
    }

    @Override
    public Score getScoreById(int id) {
        return this.scoreRepo.getScoreById(id);
    }

    @Override
    public List<Score> getScoreByStudentCode(String studentCode) {
        return this.scoreRepo.getScoreByStudentCode(studentCode);
    }
    
}
