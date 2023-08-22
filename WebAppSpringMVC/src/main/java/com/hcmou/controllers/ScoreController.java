/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hcmou.controllers;

import com.hcmou.pojo.Score;
import com.hcmou.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author Kiet
 */
//Mỗi khi tạo Controller cần autowired với service tương ứng (nếu có)
//Gắn annotation @Controller
@Controller
public class ScoreController {
    @Autowired
    private ScoreService scoService;
    //Này chỉ để test
    @GetMapping("/scores")
        public String list(Model model){
            model.addAttribute("score", new Score());
            return "scores";
        }
        
    @GetMapping("/scores/{id}")
    public String update(Model model, @PathVariable(value = "id") int id){
        model.addAttribute("score", this.scoService.getScoreById(id));
        return "scores";
    }
    
}
