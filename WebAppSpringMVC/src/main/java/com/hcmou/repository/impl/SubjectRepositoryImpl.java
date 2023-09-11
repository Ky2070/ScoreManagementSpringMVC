/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hcmou.repository.impl;

import com.hcmou.pojo.Department;
import com.hcmou.pojo.Schoolyear;
import com.hcmou.pojo.Studentsubjectteacher;
import com.hcmou.pojo.Subject;
import com.hcmou.pojo.Subjectteacher;
import com.hcmou.repository.SubjectRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
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
public class SubjectRepositoryImpl implements SubjectRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Subject> getSubjects() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Subject> q = b.createQuery(Subject.class);
        Root root = q.from(Subject.class);
        q.select(root);
        Query query = s.createQuery(q);
        return query.getResultList();
    }

    @Override
    public List<Subject> getListSubjectById(List<Integer> listSubjectID) {
        Session s = this.factory.getObject().getCurrentSession();

        // Xây dựng một danh sách các chuỗi thể hiện danh sách Subject.id
        List<String> parameterStrings = new ArrayList<>();
        for (int i = 0; i < listSubjectID.size(); i++) {
            parameterStrings.add(":subjectId" + i);
        }

        // Tạo chuỗi HQL động cho phần WHERE, sử dụng IN để so sánh danh sách Subject.id
        String parameterHql = "id IN (" + String.join(", ", parameterStrings) + ")";

        // Tạo câu truy vấn HQL hoàn chỉnh
        String queryString = "FROM Subject WHERE " + parameterHql;

        Query q = s.createQuery(queryString);

        // Đặt giá trị cho từng tham số subjectId
        for (int i = 0; i < listSubjectID.size(); i++) {
            q.setParameter("subjectId" + i, listSubjectID.get(i));
        }

        List<Subject> resultList = q.getResultList();
        return resultList;
    }

    @Override
    public List<Subjectteacher> getSubjectTeacherByTeacherID(int TeacherID) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM Subjectteacher WHERE teacherId.id = :TeacherID");
        q.setParameter("TeacherID", TeacherID);
        List<Subjectteacher> subjectTeacher = q.getResultList();
        return subjectTeacher;
    }

    @Override
    public List<Studentsubjectteacher> getStudentsubjectteacherBySubjectTeacherID(List<Subjectteacher> listsubjectteacher,
            int schoolYearID) {
        Session s = this.factory.getObject().getCurrentSession();

        // Lấy danh sách id của teacher từ listsubjectteacher
        List<Integer> teacherIds = listsubjectteacher.stream()
                .map(subjectTeacher -> subjectTeacher.getId())
                .collect(Collectors.toList());

        // Tạo danh sách tham số
        List<Integer> parameterList = new ArrayList<>();

        // Tạo chuỗi HQL cho phần IN
        String parameterHql = " AND subjectTeacherId.id IN (";
        for (int i = 0; i < teacherIds.size(); i++) {
            parameterList.add(teacherIds.get(i));
            parameterHql += ":teacherId" + i;
            if (i < teacherIds.size() - 1) {
                parameterHql += ", ";
            }
        }
        parameterHql += ")";

        // Tạo câu truy vấn HQL hoàn chỉnh
        String queryString = "FROM Studentsubjectteacher WHERE schoolYearId.id = :schoolYearID" + parameterHql;

        Query q = s.createQuery(queryString);
        q.setParameter("schoolYearID", schoolYearID);

        // Đặt giá trị cho từng tham số teacherId
        for (int i = 0; i < teacherIds.size(); i++) {
            q.setParameter("teacherId" + i, teacherIds.get(i));
        }

        List<Studentsubjectteacher> resultList = q.getResultList();
        return resultList;
    }

    @Override
    public List<Integer> getSubjectTeacherId(List<Studentsubjectteacher> studentSubjectTeacher) {
        // Sử dụng một Set để lưu trữ các SubjectTeacherId.id duy nhất
        Set<Integer> uniqueSubjectTeacherIds = new HashSet<>();

        // Duyệt qua danh sách studentSubjectTeacher
        for (Studentsubjectteacher studentSubject : studentSubjectTeacher) {
            // Lấy SubjectTeacherId.id từ mỗi phần tử và thêm vào Set
            uniqueSubjectTeacherIds.add(studentSubject.getSubjectTeacherId().getId());
        }

        List<Integer> uniqueSubjectTeacherIdList = new ArrayList<>(uniqueSubjectTeacherIds);

        return uniqueSubjectTeacherIdList;
    }

    @Override
    public List<Integer> getSubjectIdByListSubjectTeacherId(List<Integer> listSubjectTeacherId) {
        Session s = this.factory.getObject().getCurrentSession();

        // Xây dựng một danh sách các chuỗi thể hiện danh sách Subjectteacher.id
        List<String> parameterStrings = new ArrayList<>();
        for (int i = 0; i < listSubjectTeacherId.size(); i++) {
            parameterStrings.add(":teacherId" + i);
        }

        // Tạo chuỗi HQL động cho phần WHERE, sử dụng IN để so sánh danh sách Subjectteacher.id
        String parameterHql = "id IN (" + String.join(", ", parameterStrings) + ")";

        // Tạo câu truy vấn HQL hoàn chỉnh
        String queryString = "SELECT DISTINCT subjectId.id FROM Subjectteacher WHERE " + parameterHql;

        Query q = s.createQuery(queryString);

        // Đặt giá trị cho từng tham số teacherId
        for (int i = 0; i < listSubjectTeacherId.size(); i++) {
            q.setParameter("teacherId" + i, listSubjectTeacherId.get(i));
        }

        List<Integer> resultList = q.getResultList();
        return resultList;
    }

    @Override
    public List<Studentsubjectteacher> getListStudentsubjectteacher(int subjectteacherID, int selectedSchoolYearId) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM Studentsubjectteacher WHERE subjectTeacherId.id = :subjectteacherID and schoolYearId.id = :selectedSchoolYearId ");

        q.setParameter("subjectteacherID", subjectteacherID);
        q.setParameter("selectedSchoolYearId", selectedSchoolYearId);
        List<Studentsubjectteacher> listStudentsubjectteacher = q.getResultList();
        return listStudentsubjectteacher;
    }

    @Override
    public List<Studentsubjectteacher> getListStudentsubjectteacherByStudentID(int studentID, int schoolyearID) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM Studentsubjectteacher WHERE studentId.id = :studentID and schoolYearId.id = :schoolyearID ");

        q.setParameter("studentID", studentID);
        q.setParameter("schoolyearID", schoolyearID);
        List<Studentsubjectteacher> listStudentsubjectteacher = q.getResultList();
        return listStudentsubjectteacher;
    }

    @Override
    public List<Subjectteacher> getSubjectTeacherByListSubjectTeacherId(List<Studentsubjectteacher> listStudentSubjectTeacher) {
        Session s = this.factory.getObject().getCurrentSession();

        List<Integer> subjectTeacherIds = new ArrayList<>();

        // Lặp qua danh sách và lấy ra subjectTeacherId của từng phần tử
        for (Studentsubjectteacher studentSubjectTeacher : listStudentSubjectTeacher) {
            subjectTeacherIds.add(studentSubjectTeacher.getSubjectTeacherId().getId());
        }

        // Sử dụng việc ghép chuỗi HQL
        String queryString = "FROM Subjectteacher WHERE id IN (";
        for (int i = 0; i < subjectTeacherIds.size(); i++) {
            queryString += ":id" + i;
            if (i < subjectTeacherIds.size() - 1) {
                queryString += ", ";
            }
        }
        queryString += ")";

        Query q = s.createQuery(queryString);

        // Đặt các tham số
        for (int i = 0; i < subjectTeacherIds.size(); i++) {
            q.setParameter("id" + i, subjectTeacherIds.get(i));
        }

        List<Subjectteacher> resultList = q.getResultList();
        return resultList;
    }

    @Override
    public boolean addOrUpdateSubject(Subject subject) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            if (subject.getId() == null) {
                s.save(subject);
            } else {
                s.update(subject);
            }

            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteSubject(int subjectId) {
        Session s = this.factory.getObject().getCurrentSession();
        Subject subjectToDelete = s.get(Subject.class, subjectId);
        if (subjectToDelete != null) {
            try {
                s.delete(subjectToDelete);
                return true; // Trả về true nếu xóa thành công
            } catch (HibernateException ex) {
                ex.printStackTrace();
            }
        }
        return false; // Trả về false nếu không tìm thấy Môn học để xóa hoặc có lỗi xảy ra 
    }

}
