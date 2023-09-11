/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hcmou.controllers;

import com.hcmou.pojo.User;
import com.hcmou.service.UserService;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author vhuunghia
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        return "register"; // Trả về trang đăng ký
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // Trả về trang đăng nhập
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam("username") String username,
            @RequestParam("password") String password,
            Model model, HttpSession session) {
        boolean isAuthenticated = userService.authAdminUser(username, password);

        if (isAuthenticated) {
            // Lưu thông tin người dùng vào session
            session.setAttribute("user", username);
            model.addAttribute("user", username);
            return "redirect:/register"; // Đăng nhập thành công và có quyền Admin, chuyển hướng đến trang đăng ký người dùng
        } else {
            model.addAttribute("error", "Tên người dùng hoặc mật khẩu không chính xác hoặc bạn không có quyền đăng nhập.");
            return "login"; // Đăng nhập không thành công hoặc không có quyền, hiển thị thông báo lỗi trên trang đăng nhập
        }
    }

    // Đăng xuất
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        // Xóa thông tin người dùng khỏi session để đăng xuất
        session.removeAttribute("user");
        return "redirect:/login";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam Map<String, String> params) {
        String email = params.get("email");

        // Kiểm tra xem email có nằm trong bảng Teacher
        if (!userService.isTeacherEmailExists(email)) {
            return "redirect:/register?error"; // Redirect đến trang đăng ký với thông báo lỗi
        }

        // Kiểm tra xem email có đúng đuôi "@dh.edu.vn"
        if (!email.endsWith("@dh.edu.vn")) {
            return "redirect:/register?error"; // Redirect đến trang đăng ký với thông báo lỗi
        }

        // Thực hiện đăng ký người dùng giáo viên
        User user = userService.addTeacherUser(params);
        if (user != null) {
            return "redirect:/register?successs"; // Đăng ký thành công, redirect đến trang đăng nhập
        } else {
            return "redirect:/register?error"; // Đăng ký không thành công, redirect đến trang đăng ký với thông báo lỗi
        }
    }

}
