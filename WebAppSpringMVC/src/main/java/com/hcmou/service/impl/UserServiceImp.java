/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hcmou.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.hcmou.controllers.ApiUserController;
import com.hcmou.pojo.Role;
import com.hcmou.pojo.Student;
import com.hcmou.pojo.Teacher;
import com.hcmou.pojo.User;
import com.hcmou.repository.UserRepository;
import com.hcmou.service.UserService;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author vhuunghia
 */
@Service("UserDetailsService")
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private Cloudinary cloudinary;

//    @Autowired
//    private Cloudinary cloudinary;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = this.userRepo.getUserByUsername(username);
        if (u == null) {
            throw new UsernameNotFoundException("Invalid");
        }
        Set<GrantedAuthority> authorities = new HashSet<>();
        User authenticatedUser = getUserByUn(u.getUsername());
        authorities.add(new SimpleGrantedAuthority(authenticatedUser.getRoleID().getRoleName()));
        return new org.springframework.security.core.userdetails.User(
                u.getUsername(), u.getPassword(), authorities);
    }

    @Override
    public User getUserByUn(String username) {
        return this.userRepo.getUserByUsername(username);
    }

    @Override
    public boolean authUser(String username, String password) {
        return this.userRepo.authUser(username, password);
    }

    @Override
    public User addUser(Map<String, String> params) {
        String email = params.get("email");
        // Tạo EntityManager

        // Lấy danh sách kết quả
        List<Student> students = this.userRepo.getStudentbyEmail(email);

        if (!students.isEmpty()) {
            // Tìm thấy Student với email tương ứng
            Student foundStudent = students.get(0);
            Role role = new Role();
            role.setId(3);
            // Bây giờ bạn có thể sử dụng foundStudent để thực hiện các hành động mong muốn
            User user = new User();
            user.setUsername(email);
            user.setName(foundStudent.getLastName());
            user.setGender(foundStudent.getGender());
            user.setIdentifyCard(foundStudent.getIdentifyCard());
            user.setHometown(foundStudent.getHometown());
//            user.setBirthdate(foundStudent.getBirthdate());
            user.setPhone(foundStudent.getPhone());
            user.setPassword(this.passwordEncoder.encode(params.get("password")));
            user.setBirthdate(foundStudent.getBirthdate());
            user.setActive(foundStudent.getStatus());

            user.setRoleID(role);
            if (params.get("avatar") != null) {
                try {
                    Map uploadResult = this.cloudinary.uploader().upload("data:image/png;base64," + params.get("avatar"),
                            ObjectUtils.asMap("resource_type", "auto"));

                    // Lấy URL của ảnh từ phản hồi của Cloudinary
                    String imageUrl = uploadResult.get("secure_url").toString();

                    // Cập nhật URL ảnh vào đối tượng User (u) hoặc lưu lại ở đâu đó tùy bạn
                    user.setImage(imageUrl);
                } catch (IOException ex) {
                    Logger.getLogger(UserServiceImp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            this.userRepo.addUser(user);
            return user;
        } else {
            // Không tìm thấy Student với email tương ứng
            return null;
        }
    }

    @Override
    public boolean isEmailExists(String email) {

        return userRepo.findEmail(email);
    }

    @Override
    public User addTeacherUser(Map<String, String> params) {
        String email = params.get("email");
        // Lấy danh sách kết quả
        List<Teacher> teachers = this.userRepo.getTeacherByEmail(email);
        if (!teachers.isEmpty()) {
            Teacher foundTeacher = teachers.get(0);
            Role role = new Role();
            role.setId(2);
            User user = new User();
            user.setUsername(email);
            user.setName(foundTeacher.getTeacherName());
            user.setGender(foundTeacher.getGender());
            user.setHometown(foundTeacher.getAddress());
            user.setBirthdate(foundTeacher.getBirthdate());
            user.setPhone(foundTeacher.getPhoneNumber());
            user.setPassword(this.passwordEncoder.encode(params.get("password")));
            user.setActive("Active");
            user.setRoleID(role);

            this.userRepo.addTeacherUser(user);
            return user;
        } else {
            // Không tìm thấy Teacher với email tương ứng
            return null;
        }

    }

    @Override
    public boolean isTeacherEmailExists(String email) {
        return this.userRepo.findTeacherEmail(email);
    }

    @Override
    public boolean authAdminUser(String username, String password) {
        return this.userRepo.authAdminUser(username, password);
    }
}
