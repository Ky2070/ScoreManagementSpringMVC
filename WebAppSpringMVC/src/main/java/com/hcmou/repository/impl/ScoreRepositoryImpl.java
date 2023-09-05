/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hcmou.repository.impl;


import com.hcmou.pojo.Score;
import com.hcmou.pojo.Student;
import com.hcmou.pojo.Studentsubjectteacher;
import com.hcmou.pojo.Subject;
import com.hcmou.pojo.Subjectteacher;
import com.hcmou.repository.ScoreRepository;

import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
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
public class ScoreRepositoryImpl implements ScoreRepository {

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

    @Override
    public List<Score> getScoreByStudentFullName(String firstName, String lastName) {
        Session s;
        s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Score> cq = b.createQuery(Score.class);
        Root<Score> scoreRoot = cq.from(Score.class);

        // Join với bảng Student để lấy thông tin dựa trên họ tên
        Join<Score, Student> studentJoin = scoreRoot.join("studentID", JoinType.INNER); // "student" là tên thuộc tính trong Score class

        // Thêm điều kiện truy vấn theo họ tên sinh viên
        cq.select(scoreRoot);
        cq.where(
                b.and(
                        b.equal(studentJoin.get("firstName"), firstName),
                        b.equal(studentJoin.get("lastName"), lastName)
                )
        );

        Query query = s.createQuery(cq);

        return query.getResultList();
    }

    //TEST
    @Override
    public List<Score> getSubjectScoresByStudentCode(String studentCode) {
        Session session = factory.getObject().getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Score> criteriaQuery = criteriaBuilder.createQuery(Score.class);
        Root<Score> scoreRoot = criteriaQuery.from(Score.class);

        // Join với bảng Student để lấy thông tin dựa trên studentCode
        Join<Score, Student> studentJoin = scoreRoot.join("studentID", JoinType.INNER);
        criteriaQuery.where(criteriaBuilder.equal(studentJoin.get("studentCode"), studentCode));

        // Join với bảng SubjectTeacher để lấy thông tin môn học và giáo viên
        Join<Score, Subjectteacher> subjectTeacherJoin = scoreRoot.join("subjectTeacherID", JoinType.INNER);
        Join<Subjectteacher, Subject> subjectJoin = subjectTeacherJoin.join("subjectId", JoinType.INNER);

        criteriaQuery.select(scoreRoot);

        Query query = session.createQuery(criteriaQuery);

        return query.getResultList();
    }

    @Override
    public List<Score> getSubjectScoresByStudentCodeAndSchoolYear(String studentCode, int schoolYearId) {
        Session session = factory.getObject().getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Score> criteriaQuery = criteriaBuilder.createQuery(Score.class);
        Root<Score> scoreRoot = criteriaQuery.from(Score.class);

        Join<Score, Student> studentJoin = scoreRoot.join("studentID", JoinType.INNER);
        Join<Score, Subjectteacher> subjectTeacherJoin = scoreRoot.join("subjectTeacherID", JoinType.INNER);
        Join<Subjectteacher, Studentsubjectteacher> studentsubjectJoin = subjectTeacherJoin.join("studentsubjectteacherList", JoinType.INNER);

        criteriaQuery.where(
                criteriaBuilder.and(
                        criteriaBuilder.equal(studentJoin.get("studentCode"), studentCode),
                        criteriaBuilder.equal(studentsubjectJoin.get("schoolYearId"), schoolYearId)
                )
        );

        criteriaQuery.select(scoreRoot);

        Query query = session.createQuery(criteriaQuery);

        return query.getResultList();
    }

}
