/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hcmou.repository.impl;

import com.hcmou.pojo.Schoolyear;
import com.hcmou.pojo.Teacher;
import com.hcmou.repository.TeacherRepository;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vhuunghia
 */
@Repository
@Transactional
public class TeacherRepositoryImp implements TeacherRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public int getidTeacherByEmail(String email) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT id FROM Teacher WHERE Email = :email");
        q.setParameter("email", email);

        List<Integer> results = q.getResultList();

        // Kiểm tra nếu danh sách kết quả không rỗng và chỉ lấy phần tử đầu tiên
        if (!results.isEmpty()) {
            return results.get(0);
        } else {
            // Hoặc bạn có thể trả về một giá trị mặc định khác nếu không tìm thấy
            return -1; // Ví dụ: -1 để biểu thị rằng không tìm thấy giáo viên
        }
    }
    
    @Override
    public int getidStudentByEmail(String email) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT id FROM Student WHERE Email = :email");
        q.setParameter("email", email);

        List<Integer> results = q.getResultList();

        // Kiểm tra nếu danh sách kết quả không rỗng và chỉ lấy phần tử đầu tiên
        if (!results.isEmpty()) {
            return results.get(0);
        } else {
            // Hoặc bạn có thể trả về một giá trị mặc định khác nếu không tìm thấy
            return -1; // Ví dụ: -1 để biểu thị rằng không tìm thấy giáo viên
        }
    }

}
