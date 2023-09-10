/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hcmou.repository.impl;

import com.hcmou.pojo.Schoolyear;
import com.hcmou.pojo.Subjectteacher;
import com.hcmou.pojo.Typescore;
import com.hcmou.pojo.User;
import com.hcmou.repository.SchoolYearRepository;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vhuunghia
 */
@Repository
@Transactional
public class SchoolYearRepositoryImp implements SchoolYearRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private Environment env;

    @Override
    public List<Schoolyear> getListSchoolYear(String currentYear) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM Schoolyear WHERE NameYear = :currentYear");
        q.setParameter("currentYear", currentYear);
        List<Schoolyear> schoolYears = q.getResultList();
        return schoolYears;
    }

    @Override
    public Schoolyear getSchoolYearById(int schoolYearId) {
        Session session = this.factory.getObject().getCurrentSession();
        Query query = session.createQuery("FROM Schoolyear WHERE id = :schoolYearId");
        query.setParameter("schoolYearId", schoolYearId);

        List<Schoolyear> schoolYears = query.getResultList();

        if (!schoolYears.isEmpty()) {
            return schoolYears.get(0);
        } else {
            // Trả về null hoặc một giá trị mặc định khác tùy vào yêu cầu của bạn
            return null;
        }
    }
    
    @Override
    public Subjectteacher getSubJectTeacherById(int subJectTeacherId){
        Session session = this.factory.getObject().getCurrentSession();
        Query query = session.createQuery("FROM Subjectteacher WHERE id = :subJectTeacherId");
        query.setParameter("subJectTeacherId", subJectTeacherId);

        List<Subjectteacher> subJectTeacher = query.getResultList();

        if (!subJectTeacher.isEmpty()) {
            return subJectTeacher.get(0);
        } else {
            // Trả về null hoặc một giá trị mặc định khác tùy vào yêu cầu của bạn
            return null;
        }
    }
    @Override
    public Typescore getScoreTypeByName(String typeScore){
        Session session = this.factory.getObject().getCurrentSession();
        Query query = session.createQuery("FROM Typescore WHERE scoreType = :typeScore");
        query.setParameter("typeScore", typeScore);

        List<Typescore> typeScoreName = query.getResultList();

        if (!typeScoreName.isEmpty()) {
            return typeScoreName.get(0);
        } else {
            // Trả về null hoặc một giá trị mặc định khác tùy vào yêu cầu của bạn
            return null;
        }
    }

}
