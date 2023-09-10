/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.hcmou.service;

import com.hcmou.pojo.ListScoreDTO;
import com.hcmou.pojo.Score;

import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author Kiet
 */
public interface ScoreService {

    List<Score> getScores();

    Score getScoreById(int id);

    List<Score> getScoreByStudentCode(String studentCode);

    List<Score> getScoreByStudentFullName(String firstName, String lastName);

    List<Score> getSubjectScoresByStudentCode(String studentCode);

    List<Score> getSubjectScoresByStudentCodeAndSchoolYear(String studentCode, int schoolYearId);

    List<Score> getListScoreBySubjectTeacherIdAndSchoolYearIdAndStudentId(int subjectTeacherID, int schoolYearId, int studentID);

    List<Score> getListScoreBySubjectTeacherIdAndSchoolYearId(int subjectTeacherID, int schoolYearId);

    boolean saveListScoreByListScoreDTO(ListScoreDTO listScoreDTO);
}
