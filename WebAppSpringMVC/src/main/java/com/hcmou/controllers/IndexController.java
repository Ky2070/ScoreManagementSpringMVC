/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hcmou.controllers;

import com.hcmou.pojo.Department;
import com.hcmou.pojo.Trainingtype;
import com.hcmou.service.DepartmentService;
import com.hcmou.service.TrainingTypeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Kiet
 */
@Controller
public class IndexController {

    
     @Autowired
    private TrainingTypeService trainService;
    
    @Autowired
    private DepartmentService departService;
    @GetMapping("/")
    public String index(Model model){
        // Xác định nguồn dữ liệu và xử lý tương ứng
        List<Trainingtype> trainingTypes = trainService.getTrainingType();
        List<Department> departments = departService.getDepartments();
        
        // Gửi dữ liệu tới view
        model.addAttribute("trainingTypes", trainingTypes);
        model.addAttribute("departments", departments);
        return "baseLayout";

    }
}
