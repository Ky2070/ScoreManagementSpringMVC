/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hcmou.repository.impl;

import com.hcmou.pojo.Class;
import com.hcmou.pojo.Student;
import com.hcmou.repository.ClassRepository;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author nguye
 */
@Repository
@Transactional
public class ClassRepositoryImpl implements ClassRepository {

    @Autowired
    LocalSessionFactoryBean factory;
    @Autowired
    private Environment env;

    @Override
    public List<Class> getClasses() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Class> q = b.createQuery(Class.class);
        Root root = q.from(Class.class);
        q.select(root);
        Query query = s.createQuery(q);
        return query.getResultList();
    }

    @Override
    public List<Class> getClassesByMajorId(int majorId) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Class> q = b.createQuery(Class.class);
        Root root = q.from(Class.class);
        q.select(root);
        q.where(b.equal(root.get("majorId"), majorId));
        Query query = s.createQuery(q);
        return query.getResultList();
    }

    @Override
    public boolean deleteClass(int classId) {
        Session s = this.factory.getObject().getCurrentSession();
        Class classToDelete = s.get(Class.class, classId);
        if (classToDelete != null) {
            s.delete(classToDelete);
            return true; // Trả về true nếu xóa thành công
        }
        return false; // Trả về false nếu không tìm thấy lớp để xóa
    }

    @Override
    public boolean addOrUpdateClass(Class classes) {
        Session s = this.factory.getObject().getCurrentSession();
    try {
        if (classes.getId() == null) {
            s.save(classes);
        } else {
            s.update(classes);
        }

        return true;
    } catch (HibernateException ex) {
        ex.printStackTrace();
        return false;
    }
    }

}
