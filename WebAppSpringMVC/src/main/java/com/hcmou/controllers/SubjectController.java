/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hcmou.controllers;

import com.hcmou.pojo.Subject;
import com.hcmou.service.SubjectService;
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
public class SubjectController {
    @Autowired
    private SubjectService subjService;
    
    @GetMapping("/subjects")
    public String list(Model model) {
        List<Subject> subjects = subjService.getSubjects();
        model.addAttribute("subjects", subjects);
        return "subject";
    }
}
