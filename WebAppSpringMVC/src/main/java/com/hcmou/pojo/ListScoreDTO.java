/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hcmou.pojo;

import java.util.List;
import java.util.Map;

/**
 *
 * @author vhuunghia
 */
public class ListScoreDTO {

    
    private List<Map<String, Object>> listScore;
    private int selectedSchoolYearId;
    private int selectedSubjectTeacherId;
    
    
    
    
    public List<Map<String, Object>> getListScore() {
        return listScore;
    }

    /**
     * @param listScore the listScore to set
     */
    public void setListScore(List<Map<String, Object>> listScore) {
        this.listScore = listScore;
    }

    /**
     * @return the selectedSchoolYearId
     */
    public int getSelectedSchoolYearId() {
        return selectedSchoolYearId;
    }

    /**
     * @param selectedSchoolYearId the selectedSchoolYearId to set
     */
    public void setSelectedSchoolYearId(int selectedSchoolYearId) {
        this.selectedSchoolYearId = selectedSchoolYearId;
    }

    /**
     * @return the selectedSubjectTeacherId
     */
    public int getSelectedSubjectTeacherId() {
        return selectedSubjectTeacherId;
    }

    /**
     * @param selectedSubjectTeacherId the selectedSubjectTeacherId to set
     */
    public void setSelectedSubjectTeacherId(int selectedSubjectTeacherId) {
        this.selectedSubjectTeacherId = selectedSubjectTeacherId;
    }
}
