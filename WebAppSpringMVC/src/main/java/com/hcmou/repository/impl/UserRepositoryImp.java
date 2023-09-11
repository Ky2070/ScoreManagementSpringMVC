/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hcmou.repository.impl;

import com.hcmou.pojo.Role;
import com.hcmou.pojo.Student;
import com.hcmou.pojo.Teacher;
import com.hcmou.pojo.User;
import com.hcmou.repository.UserRepository;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vhuunghia
 */
@Repository
@Transactional
public class UserRepositoryImp implements UserRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private Environment env;
    @Autowired
    private BCryptPasswordEncoder passEncoder;

//    @Override
//    public List<User> getUsers() {
//        Session s = this.factory.getObject().getCurrentSession();
//        CriteriaBuilder b = s.getCriteriaBuilder();
//        CriteriaQuery<User> q = b.createQuery(User.class);
//        Root root = q.from(User.class);
//        q.select(root); 
//        Query query = s.createQuery(q);
//        return query.getResultList(); 
//
//    }
    @Override
    public User getUserByUsername(String username) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM User WHERE username=:un");
        q.setParameter("un", username);

        List<User> users = q.getResultList();
        if (users.isEmpty()) {
            return null; // Không tìm thấy người dùng, trả về null
        } else {
            return users.get(0); // Trả về người dùng đầu tiên trong danh sách (có thể chỉ có 1)
        }
    }

    @Override
    public boolean authUser(String username, String password) {
        User u = this.getUserByUsername(username);
        
        return this.passEncoder.matches(password, u.getPassword());
    }

    @Override
    public User addUser(User u) {
        Session s = this.factory.getObject().getCurrentSession();
        s.save(u);

        return u;
    }

    @Override
    public boolean findEmail(String email) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM Student WHERE email=:email");
        q.setParameter("email", email);

        // Lấy danh sách kết quả
        List<Student> students = q.getResultList();

        // Kiểm tra xem danh sách có phần tử nào không
        return !students.isEmpty();
    }

    @Override
    public List<Student> getStudentbyEmail(String email) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM Student WHERE email=:email");
        q.setParameter("email", email);

        // Lấy danh sách kết quả
        List<Student> students = q.getResultList();

        // Kiểm tra xem danh sách có phần tử nào không
        return students;
    }

    @Override
    public boolean findTeacherEmail(String email) {
        Session session = this.factory.getObject().getCurrentSession();
        Query query = session.createQuery("FROM Teacher WHERE email = :email", Teacher.class);
        query.setParameter("email", email);

        List<Teacher> teachers = query.getResultList();

        return !teachers.isEmpty();

    }

    @Override
    public List<Teacher> getTeacherByEmail(String email) {
        Session session = this.factory.getObject().getCurrentSession();
    Query query = session.createQuery("FROM Teacher WHERE email = :email", Teacher.class);
    query.setParameter("email", email);

    List<Teacher> teachers = query.getResultList();

    return teachers;
    }

    @Override
    public User addTeacherUser(User user) {
         Session s = this.factory.getObject().getCurrentSession();
        s.save(user);

        return user;
    }

    @Override
    public boolean authAdminUser(String username, String password) {
        User user = this.getUserByUsername(username);
       if (user != null) {
        // Lấy vai trò của người dùng từ trường roleID của user
        Role userRole = user.getRoleID();
        
        if (userRole != null && userRole.getId() == 1) {
            // Kiểm tra xem vai trò có roleId = 1 (Admin) không
            return passEncoder.matches(password, user.getPassword());
            }
        else
        {
            return false;
        }
        }
    return false;
    }
}

