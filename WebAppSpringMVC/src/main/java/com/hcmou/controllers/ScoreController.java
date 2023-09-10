/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hcmou.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.reflect.TypeToken;
import com.hcmou.pojo.ListScoreDTO;
import com.hcmou.pojo.Score;
import com.hcmou.pojo.Student;
import com.hcmou.pojo.Studentsubjectteacher;
import com.hcmou.repository.ScoreRepository;
import com.hcmou.service.ScoreService;
import com.hcmou.service.SubjectService;
import com.hcmou.service.TeacherService;
import com.nimbusds.jose.shaded.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
    @Autowired
    private ScoreRepository scoRepo;
    @Autowired
    private TeacherService teacherSer;

    //Này chỉ để test
    @GetMapping("/scores")
    public String list(Model model) {
        model.addAttribute("score", new Score());
        return "scores";
    }

    @GetMapping("/scores/{id}")
    public String update(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("score", this.scoService.getScoreById(id));
        return "scores";
    }

    @PostMapping("/api/listscore")
    @CrossOrigin()
    public ResponseEntity<List<Score>> getListScore(@RequestBody Map<String, String> requestData) {
        int selectedSchoolYearId;
        if (requestData.get("selectedSchoolYearId") == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else {
            selectedSchoolYearId = Integer.parseInt(requestData.get("selectedSchoolYearId"));
        }

        int subjectteacherID = Integer.parseInt(requestData.get("subjectTeacherId"));

        List<Score> listScore = this.scoService.getListScoreBySubjectTeacherIdAndSchoolYearId(subjectteacherID, selectedSchoolYearId);

        return new ResponseEntity<>(listScore, HttpStatus.OK);

    }

    @PostMapping("/api/savelistscore")
    @CrossOrigin()
    public ResponseEntity< String> saveListScore(@RequestBody ListScoreDTO scoreData) {

//        List<Map<String, Object>> listScore = this.scoRepo.convertListScoreByListScoreDTO(scoreData);
//        List<Integer> studentId = new ArrayList<>();
//        for (Map<String, Object> scoreMap : listScore) {
//            if (scoreMap.containsKey("student")) {
//                Student student = (Student) scoreMap.get("student");
//                int studentID = student.getId();
//                studentId.add(studentID);
//                // Sử dụng giá trị studentID tại đây
//            }
//        }
//        return new ResponseEntity<>(studentId, HttpStatus.OK);
        boolean test = this.scoRepo.saveListScoreByListScoreDTO(scoreData);
        if (test) {
            return new ResponseEntity<>("Cập nhật thành công", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cập nhật không thành công", HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/api/listscoreofstudent")
    @CrossOrigin()
    public ResponseEntity<List<Score>> getListScoreOfStudent(@RequestBody Map<String, String> requestData) {
        int selectedSchoolYearId;
        if (requestData.get("selectedSchoolYearId") == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else {
            selectedSchoolYearId = Integer.parseInt(requestData.get("selectedSchoolYearId"));
        }

        int subjectteacherID = Integer.parseInt(requestData.get("subjectTeacherId"));
        int studentID = this.teacherSer.getidStudentByEmail(requestData.get("userUserName"));
        List<Score> listScore = this.scoService.getListScoreBySubjectTeacherIdAndSchoolYearIdAndStudentId(subjectteacherID, selectedSchoolYearId, studentID);

        return new ResponseEntity<>(listScore, HttpStatus.OK);

    }
}
