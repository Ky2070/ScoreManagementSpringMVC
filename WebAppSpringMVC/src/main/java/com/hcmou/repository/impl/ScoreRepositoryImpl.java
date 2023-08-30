/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hcmou.repository.impl;

import com.hcmou.pojo.Score;
import com.hcmou.pojo.Student;
import com.hcmou.repository.ScoreRepository;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Kiet
 */
//Tầng này để thực hiện những truy vấn ORM và để cho tầng Service kết nối
//Lưu ý gắn annotation @Repository và @Transactional
@Transactional
@Repository
public class ScoreRepositoryImpl implements ScoreRepository{
    
    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Override
    @Transactional
    public List<Score> getScores() {
        Session s;
        s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Score> cq = b.createQuery(Score.class);
        Root root = cq.from(Score.class);
        cq.select(root);
        
        Query query = s.createQuery(cq);
        
        return query.getResultList();
    }

    @Override
    public Score getScoreById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Score.class, id);
    }

    @Override
    public List<Score> getScoreByStudentCode(String studentCode) {
        Session s;
    s = this.factory.getObject().getCurrentSession();
    CriteriaBuilder b = s.getCriteriaBuilder();
    CriteriaQuery<Score> cq = b.createQuery(Score.class);
    Root<Score> scoreRoot = cq.from(Score.class);
    
    // Join với bảng Student để lấy thông tin dựa trên studentCode
    Join<Score, Student> studentJoin = scoreRoot.join("studentID", JoinType.INNER); // "student" là tên thuộc tính trong Score class
    
    cq.select(scoreRoot);
    cq.where(b.equal(studentJoin.get("studentCode"), studentCode));

    Query query = s.createQuery(cq);

    return query.getResultList();
    }
    
}
