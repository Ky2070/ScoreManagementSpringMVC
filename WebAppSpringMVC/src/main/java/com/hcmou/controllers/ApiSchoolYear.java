/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hcmou.controllers;

import com.hcmou.pojo.Schoolyear;
import com.hcmou.service.SchoolYearService;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author vhuunghia
 */
@RestController
@RequestMapping("/api")
public class ApiSchoolYear {
    @Autowired
    private SchoolYearService schoolYearService;

    @GetMapping("/schoolyear")
    @CrossOrigin()
    public ResponseEntity<List<Schoolyear>> schoolYear() {
        String startYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR)); // Lấy năm hiện tại
        String nextYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR) + 1);
        String year = startYear + "-" + nextYear;
        List<Schoolyear> schoolYearList = schoolYearService.getListSchoolYear(year);
        return new ResponseEntity<>(schoolYearList, HttpStatus.OK);
    }
}
