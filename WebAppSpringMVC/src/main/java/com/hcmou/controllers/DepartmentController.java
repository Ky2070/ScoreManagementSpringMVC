/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hcmou.controllers;

import com.hcmou.pojo.Department;
import com.hcmou.service.DepartmentService;
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
public class DepartmentController {
    @Autowired
    private DepartmentService departService;
    
    @GetMapping("/departments")
    public String list(Model model) {
        List<Department> departments = departService.getDepartments();
        model.addAttribute("departments", departments);
        return "department";
    }
}
