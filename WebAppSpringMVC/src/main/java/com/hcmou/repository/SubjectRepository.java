/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.hcmou.repository;

import com.hcmou.pojo.Studentsubjectteacher;
import com.hcmou.pojo.Subject;
import com.hcmou.pojo.Subjectteacher;
import java.util.List;

/**
 *
 * @author nguye
 */
public interface SubjectRepository {

    List<Subject> getSubjects();

    List<Subject> getListSubjectById(List<Integer> listSubjectID);

    List<Subjectteacher> getSubjectTeacherByTeacherID(int TeacherID);

    List<Studentsubjectteacher> getStudentsubjectteacherBySubjectTeacherID(List<Subjectteacher> listsubjectteacher, int schoolYearID);

    List<Integer> getSubjectTeacherId(List<Studentsubjectteacher> studentSubjectTeacher);

    List<Integer> getSubjectIdByListSubjectTeacherId(List<Integer> listSubjectTeacherId);

    List<Studentsubjectteacher> getListStudentsubjectteacher(int subjectteacherID, int selectedSchoolYearId);

    List<Studentsubjectteacher> getListStudentsubjectteacherByStudentID(int studentID, int schoolyearID);

    List<Subjectteacher> getSubjectTeacherByListSubjectTeacherId(List<Studentsubjectteacher> listStudentSubjectTeacher);
          
    boolean addOrUpdateSubject(Subject subject);
    
    boolean deleteSubject(int subjectId);
}
