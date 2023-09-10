/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hcmou.controllers;

import com.hcmou.pojo.Subjectteacher;
import com.hcmou.service.SubjectTeacherService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author nguye
 */
@Controller
public class SubjectTeacherController {
    @Autowired
    private SubjectTeacherService subjTeachService;
    @GetMapping("/subjTeach")
    public String listST(Model model){
        List<Subjectteacher> subjteachs = subjTeachService.getSubjectTeachers();
        
        model.addAttribute("subjteachs", subjteachs);
        
        return "subjTeach";
    }
}
