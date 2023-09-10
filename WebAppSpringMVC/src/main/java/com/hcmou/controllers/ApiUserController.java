/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hcmou.controllers;

/**
 *
 * @author vhuunghia
 */
import com.hcmou.components.JwtService;
import com.hcmou.pojo.Role;
import com.hcmou.pojo.User;
import com.hcmou.service.UserService;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author huu-thanhduong
 */
@RestController
@RequestMapping("/api")
public class ApiUserController {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @CrossOrigin
    public ResponseEntity<String> login(@RequestBody Map<String, String> requestBody) {
        String username = requestBody.get("username");
        String password = requestBody.get("password");
        int roleID = Integer.parseInt(requestBody.get("roleID"));
        User authenticatedUser = userService.getUserByUn(username);
        if (this.userService.authUser(username, password) && roleID == authenticatedUser.getRoleID().getId()) {
            String token = this.jwtService.generateTokenLogin(username, authenticatedUser.getRoleID().getRoleName());
            return new ResponseEntity<>(token, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Tên người dùng, mật khẩu hoặc vai trò không đúng", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping(path = "/users/",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @CrossOrigin()
    public ResponseEntity<String> register(@RequestBody Map<String, String> requestData) {
        String email = requestData.get("email");
        byte[] imageBytes = Base64.decodeBase64(requestData.get("avatar"));
        String base64Image = Base64.encodeBase64String(imageBytes);
        requestData.put("avatar", base64Image);
        if (userService.isEmailExists(email) && userService.getUserByUn(email) == null) {
            User user = userService.addUser(requestData);
            if (user != null) {
                return new ResponseEntity<>("Đăng ký thành công", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Không có Email hoặc đã đăng ký", HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path = "/current-user", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<User> details(Principal user) {
        User u = this.userService.getUserByUn(user.getName());
        return new ResponseEntity<>(u, HttpStatus.OK);
    }
}
