/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hcmou.repository.impl;

import com.hcmou.pojo.Studentsubjectteacher;
import com.hcmou.pojo.Subject;
import com.hcmou.repository.StudentSubjectTeacherRepository;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author nguye
 */
@Repository
@Transactional
public class StudentSubjectTeacherRepositoryImpl implements StudentSubjectTeacherRepository {
    @Autowired
    private LocalSessionFactoryBean factory;
    @Override
    public List<Studentsubjectteacher> getStudsubjteachs() {
        Session s = this.factory.getObject().getCurrentSession();
       CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Studentsubjectteacher> q = b.createQuery(Studentsubjectteacher.class);
        Root root = q.from(Studentsubjectteacher.class);
        q.select(root); 
        Query query = s.createQuery(q);
        return query.getResultList(); 
        
    }
    
}
