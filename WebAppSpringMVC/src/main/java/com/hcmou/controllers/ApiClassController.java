/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hcmou.controllers;

import com.hcmou.pojo.Schoolyear;
import com.hcmou.pojo.Studentsubjectteacher;
import com.hcmou.pojo.Subject;
import com.hcmou.pojo.Subjectteacher;
import com.hcmou.pojo.Teacher;
import com.hcmou.service.SubjectService;
import com.hcmou.service.TeacherService;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author vhuunghia
 */
@RestController
@RequestMapping("/api")
public class ApiClassController {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private SubjectService subjectService;

    @PostMapping("/listsubject")
    @CrossOrigin()
    public ResponseEntity<Map<String, Object>> subject(@RequestBody Map<String, String> requestData) {
        int selectedSchoolYearId;
        if (requestData.get("selectedSchoolYearId") == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else {
            selectedSchoolYearId = Integer.parseInt(requestData.get("selectedSchoolYearId"));
        }
        String userUserName = requestData.get("userUserName");
        // lấy được idTeacher
        int idTeacher = this.teacherService.getIdTeacherByEmail(userUserName);

        // lấy được listsubject mà teacher phụ trách
        List<Subjectteacher> subjectteacher = this.subjectService.getSubjectTeacherByTeacherID(idTeacher);
        // lấy được ListStudentsubjectteacher (giáo viên phụ trách môn học nào trong học kì nào)
        List<Studentsubjectteacher> ListStudentsubjectteacher = this.subjectService.getStudentsubjectteacherBySubjectTeacherID(subjectteacher, selectedSchoolYearId);
        // Lấy được những uniqueSubjectTeacherId
        List<Integer> uniqueSubjectTeacherIdList = this.subjectService.getSubjectTeacherId(ListStudentsubjectteacher);

        // Lấy được subjectid mà giáo viên phục trách trong học ki đó
        List<Integer> listSubjectID = this.subjectService.getSubjectIdByListSubjectTeacherId(uniqueSubjectTeacherIdList);

        List<Subject> listSubject = this.subjectService.getListSubjectById(listSubjectID);

        Map<String, Object> responseData = new HashMap<>();
        responseData.put("uniqueSubjectTeacherIdList", uniqueSubjectTeacherIdList);
        responseData.put("listSubject", listSubject);

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PostMapping("/listsubject/liststudents")
    @CrossOrigin()
    public ResponseEntity<List<Studentsubjectteacher>> subject2(@RequestBody Map<String, String> requestData) {
        int selectedSchoolYearId;
        if(requestData.get("selectedSchoolYearId") == null)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        else
            selectedSchoolYearId = Integer.parseInt( requestData.get("selectedSchoolYearId"));
        
        int subjectteacherID = Integer.parseInt(requestData.get("subjectTeacherId"));
       // Lấy được list học sinh học môn đó ở học kì đó do giáo viên nào phụ trách
        List<Studentsubjectteacher> listStudentsubjectteacher = this.subjectService.getListStudentsubjectteacher(subjectteacherID, selectedSchoolYearId);
        
        
        return new ResponseEntity<>(listStudentsubjectteacher, HttpStatus.OK);
    }
    
    @PostMapping("/listoldclass")
    @CrossOrigin()
    public ResponseEntity<List<Subjectteacher> > getOldClass(@RequestBody Map<String, String> requestData) {
        int selectedSchoolYearId;
        if(requestData.get("selectedSchoolYearId") == null)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        else
            selectedSchoolYearId = Integer.parseInt( requestData.get("selectedSchoolYearId"));
        
        
        int studentID = this.teacherService.getidStudentByEmail(requestData.get("userUserName"));
        
//        int subjectteacherID = Integer.parseInt(requestData.get("subjectTeacherId"));
//       // Lấy được list học sinh học môn đó ở học kì đó do giáo viên nào phụ trách
//        List<Studentsubjectteacher> listStudentsubjectteacher = this.subjectService.getListStudentsubjectteacher(subjectteacherID, selectedSchoolYearId);
        List<Studentsubjectteacher> listStudentsubjectteacher = this.subjectService.getListStudentsubjectteacherByStudentID(studentID, selectedSchoolYearId);
        List<Subjectteacher> ListSubjectTeacher = this.subjectService.getSubjectTeacherByListSubjectTeacherId(listStudentsubjectteacher);
        return new ResponseEntity<>(ListSubjectTeacher, HttpStatus.OK);
    }
    
}
