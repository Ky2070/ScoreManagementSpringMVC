/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hcmou.service.impl;

import com.hcmou.pojo.ListScoreDTO;
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
public class ScoreServiceImpl implements ScoreService {

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

    @Override
    public List<Score> getScoreByStudentFullName(String firstName, String lastName) {
        return this.scoreRepo.getScoreByStudentFullName(firstName, lastName);
    }

    @Override
    public List<Score> getSubjectScoresByStudentCode(String studentCode) {
        return this.scoreRepo.getSubjectScoresByStudentCode(studentCode);
    }

    @Override
    public List<Score> getSubjectScoresByStudentCodeAndSchoolYear(String studentCode, int schoolYearId) {
        return this.scoreRepo.getSubjectScoresByStudentCodeAndSchoolYear(studentCode, schoolYearId);
    }

    @Override
    public List<Score> getListScoreBySubjectTeacherIdAndSchoolYearId(int subjectTeacherID, int schoolYearId) {
        return this.scoreRepo.getListScoreBySubjectTeacherIdAndSchoolYearId(subjectTeacherID, schoolYearId);
    }

    @Override
    public boolean saveListScoreByListScoreDTO(ListScoreDTO listScoreDTO) {
        return this.scoreRepo.saveListScoreByListScoreDTO(listScoreDTO);
    }
    
    
    @Override
    public List<Score> getListScoreBySubjectTeacherIdAndSchoolYearIdAndStudentId(int subjectTeacherID, int schoolYearId, int studentID) {
        return this.scoreRepo.getListScoreBySubjectTeacherIdAndSchoolYearIdAndStudentId(subjectTeacherID, schoolYearId, studentID);
    }
}
