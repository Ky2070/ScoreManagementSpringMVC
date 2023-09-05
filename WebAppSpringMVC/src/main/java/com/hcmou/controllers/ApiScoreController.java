/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hcmou.controllers;

import com.hcmou.pojo.Score;
import com.hcmou.service.ScoreService;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author nguye
 */
@RestController
@RequestMapping("/api")
public class ApiScoreController {

    @Autowired
    private ScoreService scoService;

    @GetMapping("/scores/")
    @CrossOrigin
    public ResponseEntity<List<Score>> list() {
        return new ResponseEntity<>(this.scoService.getScores(), HttpStatus.OK);
    }

    @GetMapping("/scores/{studentCode}")
    @CrossOrigin
    public ResponseEntity<List<Score>> getByStudentCode(@PathVariable String studentCode) {
        List<Score> scores = this.scoService.getScoreByStudentCode(studentCode);

        if (scores.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(scores, HttpStatus.OK);
    }

    @GetMapping("/scores/studentName")
    @CrossOrigin
    public ResponseEntity<List<Score>> getByStudentName(
            @RequestParam String firstName,
            @RequestParam String lastName
    ) {
        List<Score> scores = scoService.getScoreByStudentFullName(firstName, lastName);

        if (scores.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(scores, HttpStatus.OK);
    }

    @GetMapping("/scores/student/{studentCode}")
    @CrossOrigin
    public ResponseEntity<List<Score>> getSubjectScoresByStudentCode(@PathVariable String studentCode) {
        List<Score> scores = scoService.getSubjectScoresByStudentCode(studentCode);

        if (scores.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(scores, HttpStatus.OK);
    }

    @GetMapping("/scores/subjectSchoolyear")
    @CrossOrigin
    public ResponseEntity<List<Score>> getSubjectScoresByStudentCodeAndSchoolYear(
            @RequestParam String studentCode,
            @RequestParam int schoolYearId
    ) {
        List<Score> scores = scoService.getSubjectScoresByStudentCodeAndSchoolYear(studentCode, schoolYearId);

        if (scores.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(scores, HttpStatus.OK);
    }

    @GetMapping("/scores/subjects/{studentCode}/{schoolYearId}")
    @CrossOrigin
    public ResponseEntity<List<Score>> getSubjectScoresBySchoolYear(
            @PathVariable String studentCode,
            @PathVariable int schoolYearId
    ) {
        List<Score> scores = scoService.getSubjectScoresByStudentCodeAndSchoolYear(studentCode, schoolYearId);

        if (scores.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(scores, HttpStatus.OK);
    }

}
