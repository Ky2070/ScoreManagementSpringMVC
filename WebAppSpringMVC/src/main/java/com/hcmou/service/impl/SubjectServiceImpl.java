/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hcmou.service.impl;

import com.hcmou.pojo.Studentsubjectteacher;
import com.hcmou.pojo.Subject;
import com.hcmou.pojo.Subjectteacher;
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
    @Override
    public List<Subject> getListSubjectById(List<Integer> listSubjectID ) {
        return this.subjRepo.getListSubjectById(listSubjectID);
    }
    @Override
    public List<Subjectteacher> getSubjectTeacherByTeacherID(int TeacherID){
        return this.subjRepo.getSubjectTeacherByTeacherID(TeacherID);
    }
    @Override
    public List<Studentsubjectteacher> getStudentsubjectteacherBySubjectTeacherID(List<Subjectteacher> listsubjectteacher,
            int schoolYearID) {
        
        return this.subjRepo.getStudentsubjectteacherBySubjectTeacherID(listsubjectteacher, schoolYearID);
    }
    
    @Override
    public List<Integer> getSubjectTeacherId(List<Studentsubjectteacher> studentSubjectTeacher) {
 
        return this.subjRepo.getSubjectTeacherId(studentSubjectTeacher);
    }
    @Override
    public List<Integer> getSubjectIdByListSubjectTeacherId(List<Integer> listSubjectTeacherId) {
        
        return this.subjRepo.getSubjectIdByListSubjectTeacherId(listSubjectTeacherId);
    }
    
    @Override
    public List<Studentsubjectteacher> getListStudentsubjectteacher(int subjectteacherID, int selectedSchoolYearId) {
        return this.subjRepo.getListStudentsubjectteacher(subjectteacherID, selectedSchoolYearId);
    }
    
    @Override
    public List<Studentsubjectteacher> getListStudentsubjectteacherByStudentID(int studentID, int schoolyearID) {
        return this.subjRepo.getListStudentsubjectteacherByStudentID(studentID, schoolyearID);
    }
    
    @Override
    public List<Subjectteacher> getSubjectTeacherByListSubjectTeacherId(List<Studentsubjectteacher> listStudentSubjectTeacher ) {
        return this.subjRepo.getSubjectTeacherByListSubjectTeacherId(listStudentSubjectTeacher);
    }

    @Override
    public boolean addOrUpdateSubject(Subject subject) {
        return this.subjRepo.addOrUpdateSubject(subject);
    }

    @Override
    public boolean deleteSubject(int subjectId) {
        return this.deleteSubject(subjectId);
    }
}
