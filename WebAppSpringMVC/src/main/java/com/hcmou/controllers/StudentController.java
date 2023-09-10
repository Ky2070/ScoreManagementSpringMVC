/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hcmou.controllers;

import com.hcmou.pojo.Student;
import com.hcmou.service.StudentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author nguye
 */
@Controller
public class StudentController {

    @Autowired
    private StudentService studService;

    @GetMapping("/students")
    public String list(@RequestParam("classId") int classId, Model model) {
        List<Student> students = studService.getStudentByClassId(classId);
        model.addAttribute("students", students);
        return "student"; // Trả về trang web để hiển thị danh sách sinh viên
    }
}