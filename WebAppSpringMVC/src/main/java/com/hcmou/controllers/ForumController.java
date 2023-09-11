/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hcmou.controllers;

import com.hcmou.pojo.Forum;
import com.hcmou.pojo.Studentsubjectteacher;
import com.hcmou.pojo.Subjectteacher;
import com.hcmou.service.ForumService;
import com.hcmou.service.StudentSubjectTeacherService;
import com.hcmou.service.SubjectTeacherService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author nguye
 */
@Controller
public class ForumController {

    @Autowired
    private SubjectTeacherService subjTeachService;
    @Autowired
    private ForumService forumService;

    @GetMapping("/forum")
    public String showForumPage(Model model) {
        List<Subjectteacher> subjteachs = subjTeachService.getSubjectTeachers();
        List<Forum> forums = forumService.getForums();

        //    if (subjectTeacherId > 0) {
        //        forums = forumService.getForumBySubjectTeacher(subjectTeacherId);
        //    }
        model.addAttribute("subjteachs", subjteachs);
        model.addAttribute("forums", forums);
        return "forum";
    }

    @GetMapping("/forumBySubjectTeacher")
    public String showForumBySubjTeach(Model model, @RequestParam("subjectTeacherId") int subjectTeacherId) {
        List<Subjectteacher> subjteachs = subjTeachService.getSubjectTeachers();
        List<Forum> forums = forumService.getForumBySubjectTeacher(subjectTeacherId);

        //    if (subjectTeacherId > 0) {
        //        forums = forumService.getForumBySubjectTeacher(subjectTeacherId);
        //    }
        model.addAttribute("subjteachs", subjteachs);
        model.addAttribute("forums", forums);
        return "forum";

    }

    @GetMapping("/addForumPage")
    public String showAddForumPage(Model model) {
        List<Subjectteacher> subjteachs = subjTeachService.getSubjectTeachers();
        model.addAttribute("subjteachs", subjteachs);
        model.addAttribute("forum", new Forum()); // Tạo một đối tượng Forum trống để binding dữ liệu
        return "addForum"; // Đưa ra view thêm bài đăng
    }

    @PostMapping("/addForum")
    public String addForum(@ModelAttribute("forum") Forum forum, @RequestParam("subjectTeacherId.id") Integer subjectTeacherId, Model model) {
        // Kiểm tra thông tin biểu mẫu và thêm bài đăng vào cơ sở dữ liệu
        if (forum.getTitle() != null && forum.getDescription() != null && forum.getContent() != null) {
            if (forumService.addForum(forum)) {

                // Tạo một đối tượng Subjectteacher với id đã chọn từ biểu mẫu
                Subjectteacher selectedSubjectTeacher = new Subjectteacher();
                selectedSubjectTeacher.setId(subjectTeacherId);

                // Gán đối tượng Subjectteacher vào đối tượng Forum
                forum.setSubjectTeacherId(selectedSubjectTeacher);
                model.addAttribute("successMessage", "Bài đăng đã được thêm thành công.");
            } else {

                model.addAttribute("errorMessage", "Đã xảy ra lỗi khi thêm bài đăng.");
            }
        } else {

            model.addAttribute("errorMessage", "Vui lòng điền đầy đủ thông tin.");
        }

        return "redirect:/forum";
    }

    @PostMapping("/deleteForum")
    public String deleteForum(@RequestParam("forumId") int forumId, Model model) {
        if (forumService.deleteForum(forumId)) {
            model.addAttribute("successMessage", "Bài đăng đã được xóa thành công.");
        } else {
            model.addAttribute("errorMessage", "Đã xảy ra lỗi khi xóa bài đăng.");
        }

        return "redirect:/forum";
    }
}
