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
//    @PostMapping("/login")
//    @CrossOrigin
//    public ResponseEntity<String> login(@RequestBody User user) {
//
//        // Xác thực người dùng
//        if (this.userService.authUser(user.getUsername(), user.getPassword())) {
//            User authenticatedUser = userService.getUserByUn(user.getUsername());
//
//            // Kiểm tra vai trò của người dùng
//            if (authenticatedUser.getRoleID() != null && authenticatedUser.getRoleID().getRoleName().equals(user.getRoleID())) {
//                // Nếu vai trò khớp, tạo token và gửi lại
//                String token = this.jwtService.generateTokenLogin(user.getUsername(), authenticatedUser.getRoleID().getRoleName());
//                return new ResponseEntity<>(token, HttpStatus.OK);
//            } else {
//                // Nếu vai trò không khớp hoặc roleID không tồn tại, trả về thông báo lỗi
//                return new ResponseEntity<>("Không đúng role", HttpStatus.UNAUTHORIZED);
//            }
//        } else {
//            return new ResponseEntity<>("Mật khẩu không đúng", HttpStatus.UNAUTHORIZED);
//        }
//    }

//    @PostMapping("/test")
//public ResponseEntity<String> test(HttpServletRequest request) {
//    // Lấy tiêu đề "Authorization" từ yêu cầu
//    String authorizationHeader = request.getHeader("Authorization");
//
//    // Kiểm tra xem tiêu đề Authorization có tồn tại và có chứa mã token không
//    if (authorizationHeader != null) {
//
//            return new ResponseEntity<>("SUCCESSFUL", HttpStatus.OK);
//
//    }
//// Thay thế bằng phương thức lấy tên người dùng từ mã token của bạn
//    // Nếu không có hoặc không hợp lệ, trả về lỗi truy cập bị từ chối
//    return new ResponseEntity<>("Như cc", HttpStatus.FORBIDDEN);
//}
//    @PostMapping(path = "/users/",
//            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
//            produces = {MediaType.APPLICATION_JSON_VALUE})
//    @CrossOrigin()
//    public ResponseEntity<?> addUser(@RequestParam Map<String, String> params, @RequestPart MultipartFile avatar) {
//        // Trích xuất email từ params
//        String email = params.get("email");
//        // Kiểm tra xem email đã tồn tại trong cơ sở dữ liệu hay chưa
//        if (!userService.isEmailExists(email)) {
//            // Nếu email không tồn tại, trả về mã lỗi 404 (Not Found) và thông báo lỗi
//            return new ResponseEntity<>(email, HttpStatus.NOT_FOUND);
//        }
//
//        // Tiếp tục xử lý đăng ký nếu email hợp lệ
//        // ...
//        return new ResponseEntity<>("Có email", HttpStatus.OK);
//    }
//    
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
