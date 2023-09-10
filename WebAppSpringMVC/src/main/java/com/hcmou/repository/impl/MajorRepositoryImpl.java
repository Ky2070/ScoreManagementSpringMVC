/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hcmou.repository.impl;

import com.hcmou.pojo.Major;
import com.hcmou.repository.MajorRepository;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
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
public class MajorRepositoryImpl implements MajorRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Major> getMajorsByDepartmentId(int departmentId) {
        Session session = factory.getObject().getCurrentSession();
        Query query = session.createQuery("FROM Major WHERE departmentId.id = :departmentId");
        query.setParameter("departmentId", departmentId);
        return query.getResultList();
    }

    @Override
    public List<Major> getMajorsByTrainingTypeId(int trainingtypeId) {
         // Lấy phiên hiện tại từ LocalSessionFactoryBean
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Major> criteriaQuery = criteriaBuilder.createQuery(Major.class);
        
        Root<Major> root = criteriaQuery.from(Major.class);
        
        // Thêm điều kiện để lọc các Major theo trainingtypeId
        criteriaQuery.where(criteriaBuilder.equal(root.get("trainingTypeId"), trainingtypeId));
        
        criteriaQuery.select(root);
        
        Query query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }

}
