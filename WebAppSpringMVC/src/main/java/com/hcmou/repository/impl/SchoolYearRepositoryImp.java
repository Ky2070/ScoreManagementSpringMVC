/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hcmou.repository.impl;

import com.hcmou.pojo.Schoolyear;
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
}
