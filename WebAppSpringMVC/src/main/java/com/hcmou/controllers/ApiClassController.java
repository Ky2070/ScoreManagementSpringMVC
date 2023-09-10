/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hcmou.controllers;

import com.hcmou.pojo.Schoolyear;
import com.hcmou.pojo.Subject;
import com.hcmou.pojo.Teacher;
import com.hcmou.service.TeacherService;
import java.util.Calendar;
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
    
    @PostMapping("/listsubject")
    @CrossOrigin()
    public ResponseEntity<Integer> subject(@RequestBody Map<String, String> requestData) {
//        int selectedSchoolYearId;
//        if(requestData.get("selectedSchoolYearId") == null)
//            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//        else
//            selectedSchoolYearId = Integer.parseInt( requestData.get("selectedSchoolYearId"));
        String userUserName = requestData.get("userUserName");
        
        int idTeacher = this.teacherService.getIdTeacherByEmail(userUserName);
        
        
        return new ResponseEntity<>(idTeacher, HttpStatus.OK);
    }
}
