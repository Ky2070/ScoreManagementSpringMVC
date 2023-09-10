package com.hcmou.repository;

import com.hcmou.pojo.Schoolyear;
import com.hcmou.pojo.Subjectteacher;
import com.hcmou.pojo.Typescore;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author vhuunghia
 */
public interface SchoolYearRepository {
    List<Schoolyear> getListSchoolYear(String currentYear);
    Schoolyear getSchoolYearById(int SchoolYearId);
    Subjectteacher getSubJectTeacherById(int subJectTeacherId);
    Typescore getScoreTypeByName(String typeScore);
}
