/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hcmou.repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcmou.pojo.ListScoreDTO;
import com.hcmou.pojo.Schoolyear;
import com.hcmou.pojo.Score;
import com.hcmou.pojo.Student;
import com.hcmou.pojo.Studentsubjectteacher;
import com.hcmou.pojo.Subject;
import com.hcmou.pojo.Subjectteacher;
import com.hcmou.pojo.Typescore;
import com.hcmou.repository.SchoolYearRepository;
import com.hcmou.repository.ScoreRepository;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
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

    @Autowired
    private SchoolYearRepository schoolRepo;

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

    @Override
    public List<Score> getListScoreBySubjectTeacherIdAndSchoolYearId(int subjectTeacherID, int schoolYearId) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM Score WHERE schoolYearId.id = :schoolYearId and subjectTeacherID.id = :subjectTeacherID");
        q.setParameter("schoolYearId", schoolYearId);
        q.setParameter("subjectTeacherID", subjectTeacherID);
        List<Score> listscores = q.getResultList();
        return listscores;
    }

    @Override
    public List<Score> getListScoreBySubjectTeacherIdAndSchoolYearIdAndStudentId(int subjectTeacherID, int schoolYearId, int studentID) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM Score WHERE schoolYearId.id = :schoolYearId and subjectTeacherID.id = :subjectTeacherID and studentID.id = :studentID");
        q.setParameter("studentID", studentID);
        q.setParameter("schoolYearId", schoolYearId);
        q.setParameter("subjectTeacherID", subjectTeacherID);
        List<Score> listscores = q.getResultList();
        return listscores;
    }

    @Override
    public List<Map<String, Object>> convertListScoreByListScoreDTO(ListScoreDTO listScoreDTO) {
        List<Map<String, Object>> listScore = listScoreDTO.getListScore();
        if (listScore != null && !listScore.isEmpty()) {
            for (int i = 0; i < listScore.size(); i++) {
                Map<String, Object> scoreMap = listScore.get(i);

                if (scoreMap.containsKey("finalTermScore") && scoreMap.get("finalTermScore") != null) {
                    String finalTermScoreStr = scoreMap.get("finalTermScore").toString();
                    if (!finalTermScoreStr.isEmpty()) {
                        float finalTermScore = Float.parseFloat(finalTermScoreStr);
                        finalTermScore = Math.round(finalTermScore * 10.0f) / 10.0f;
                        scoreMap.put("finalTermScore", finalTermScore);
                    }
                }

                if (scoreMap.containsKey("midTermScore") && scoreMap.get("midTermScore") != null) {
                    String midTermScoreStr = scoreMap.get("midTermScore").toString();
                    if (!midTermScoreStr.isEmpty()) {
                        float midTermScore = Float.parseFloat(midTermScoreStr);
                        midTermScore = Math.round(midTermScore * 10.0f) / 10.0f;
                        scoreMap.put("midTermScore", midTermScore);
                    }
                }
                // Kiểm tra và chuyển đổi các cột Bonus3, Bonus4, Bonus5
                if (scoreMap.containsKey("Bonus3") && scoreMap.get("Bonus3") != null) {
                    String bonus3Str = scoreMap.get("Bonus3").toString();
                    if (!bonus3Str.isEmpty()) {
                        float bonus3Score = Float.parseFloat(bonus3Str);
                        bonus3Score = Math.round(bonus3Score * 10.0f) / 10.0f;
                        scoreMap.put("Bonus3", bonus3Score);
                    }
                }
                if (scoreMap.containsKey("Bonus4") && scoreMap.get("Bonus4") != null) {
                    String bonus3Str = scoreMap.get("Bonus4").toString();
                    if (!bonus3Str.isEmpty()) {
                        float bonus3Score = Float.parseFloat(bonus3Str);
                        bonus3Score = Math.round(bonus3Score * 10.0f) / 10.0f;
                        scoreMap.put("Bonus4", bonus3Score);
                    }
                }
                if (scoreMap.containsKey("Bonus5") && scoreMap.get("Bonus5") != null) {
                    String bonus3Str = scoreMap.get("Bonus5").toString();
                    if (!bonus3Str.isEmpty()) {
                        float bonus3Score = Float.parseFloat(bonus3Str);
                        bonus3Score = Math.round(bonus3Score * 10.0f) / 10.0f;
                        scoreMap.put("Bonus5", bonus3Score);
                    }
                }

                listScore.set(i, scoreMap); // Cập nhật lại đúng dòng đó trong listScore

            }

        }

        return listScore;
    }

    @Override
    public List<Student> convertListScoreToStudent(ListScoreDTO listScoreDTO) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, Object>> listScore = listScoreDTO.getListScore();

        List<Student> studentList = new ArrayList<>();

        for (int i = 0; i < listScore.size(); i++) {
            Map<String, Object> scoreMap = listScore.get(i);
            Map<String, Object> studentMap = (Map<String, Object>) scoreMap.get("student");
            Student student = objectMapper.convertValue(studentMap, Student.class);
            studentList.add(student);
        }
        return studentList;
    }

    @Override
    public boolean saveListScoreByListScoreDTO(ListScoreDTO listScoreDTO) {
        Session s = this.factory.getObject().getCurrentSession();
        ObjectMapper objectMapper = new ObjectMapper();
        int selectedSchoolYearId = listScoreDTO.getSelectedSchoolYearId();
        int selectedSubjectTeacherId = listScoreDTO.getSelectedSubjectTeacherId();
        boolean isSuccess = false;
        List<Student> studentList = this.convertListScoreToStudent(listScoreDTO);

        List<Map<String, Object>> listScore = this.convertListScoreByListScoreDTO(listScoreDTO);

        Map<String, String> columnNameMapping = new HashMap<>();
        columnNameMapping.put("midTermScore", "Giữa kỳ");
        columnNameMapping.put("finalTermScore", "Cuối kỳ");
        columnNameMapping.put("Bonus3", "Bonus3");
        columnNameMapping.put("Bonus4", "Bonus4");
        columnNameMapping.put("Bonus5", "Bonus5");

        for (Student student : studentList) {
            // Kiểm tra xem điểm đã tồn tại cho học sinh, năm học, môn học và loại điểm nào
            Query q = s.createQuery("FROM Score WHERE schoolYearId.id = :schoolYearId and subjectTeacherID.id = :subjectTeacherID and studentID.id = :studentID and scoreType.scoreType in ('Giữa kỳ', 'Cuối kỳ', 'Bonus3', 'Bonus4', 'Bonus5')");
            q.setParameter("schoolYearId", selectedSchoolYearId);
            q.setParameter("subjectTeacherID", selectedSubjectTeacherId);
            q.setParameter("studentID", student.getId());

            List<Score> scores = q.getResultList();

            // Kiểm tra nếu có điểm đã tồn tại
            if (!scores.isEmpty()) {
                for (Map<String, Object> listScoreItem : listScore) {
                    Map<String, Object> studentMap = (Map<String, Object>) listScoreItem.get("student");
                    Student student1 = objectMapper.convertValue(studentMap, Student.class);
                    for (Score score : scores) {
                        int scoreStudentId = score.getStudentID().getId();
                        String scoreType = score.getScoreType().getScoreType();

                        if (student1 != null) {
                            if (student1.getId() == scoreStudentId) {
                                // So sánh và cập nhật điểm từ listScore
                                if ("Giữa kỳ".equals(scoreType)) {
                                    Float midTermScore = (Float) listScoreItem.get("midTermScore");
                                    if (midTermScore != null) {
                                        score.setScoreValue(midTermScore);
                                        s.update(score);
                                        isSuccess = true;

                                    }
                                } else if ("Cuối kỳ".equals(scoreType)) {
                                    Float finalTermScore = (Float) listScoreItem.get("finalTermScore");
                                    if (finalTermScore != null) {
                                        score.setScoreValue(finalTermScore);
                                        s.update(score);
                                        isSuccess = true;

                                    }
                                } else if ("Bonus3".equals(scoreType)) {
                                    Float bonus3 = (Float) listScoreItem.get("Bonus3");
                                    if (bonus3 != null) {
                                        score.setScoreValue(bonus3);
                                        s.update(score);
                                        isSuccess = true;

                                    }
                                } else if ("Bonus4".equals(scoreType)) {
                                    Float bonus4 = (Float) listScoreItem.get("Bonus4");
                                    if (bonus4 != null) {
                                        score.setScoreValue(bonus4);
                                        s.update(score);
                                        isSuccess = true;

                                    }
                                } else if ("Bonus5".equals(scoreType)) {
                                    Float bonus5 = (Float) listScoreItem.get("Bonus5");
                                    if (bonus5 != null) {
                                        score.setScoreValue(bonus5);
                                        s.update(score);
                                        isSuccess = true;

                                    }
                                }

                            }
                        }
                    }

                }

            }
            for (Map<String, Object> listScoreItem : listScore) {
//               
                Schoolyear schoolyear = this.schoolRepo.getSchoolYearById(selectedSchoolYearId);
                Subjectteacher subjectTeacher = this.schoolRepo.getSubJectTeacherById(selectedSubjectTeacherId);
                Map<String, Object> studentMap = (Map<String, Object>) listScoreItem.get("student");
                    Student student1 = objectMapper.convertValue(studentMap, Student.class);

                if (student1 != null) {
                    if (student.getId() == student1.getId()) {
                        // Lấy danh sách các cột điểm trong listScore của học sinh
                        Set<String> listScoreColumns = listScoreItem.keySet();
                        listScoreColumns.remove("student");
                        for (String column : listScoreColumns) {
                            boolean columnExistsInScores = false;

                            // Kiểm tra xem cột điểm đã tồn tại trong scores của học sinh hay chưa
                            for (Score score : scores) {
                                String scoreType = score.getScoreType().getScoreType();

                                // Sử dụng mapping để ánh xạ tên cột điểm sang tên loại điểm
                                if (columnNameMapping.containsKey(column) && columnNameMapping.get(column).equals(scoreType)) {
                                    columnExistsInScores = true;
                                    break;
                                }
                            }

                            // Nếu cột điểm không tồn tại trong scores, thêm mới
                            if (!columnExistsInScores) {
                                Object columnValue = listScoreItem.get(column);

                                // Kiểm tra xem columnValue có trả về null không
                                if (columnValue != null && !columnValue.toString().isEmpty()) {
                                    float scoreValue = Float.parseFloat(columnValue.toString());
                                    scoreValue = Math.round(scoreValue * 10.0f) / 10.0f;
                                    // Tạo một bản ghi Score mới và thêm vào cơ sở dữ liệu
                                    Score newScore = new Score();
                                    newScore.setSchoolYearId(schoolyear);
                                    newScore.setSubjectTeacherID(subjectTeacher);
                                    newScore.setStudentID(student);
                                    newScore.setScoreValue(scoreValue);

                                    // Sử dụng mapping để ánh xạ tên cột điểm sang tên loại điểm
                                    if (columnNameMapping.containsKey(column)) {
                                        newScore.setScoreType(this.schoolRepo.getScoreTypeByName(columnNameMapping.get(column)));
                                    } else {
                                        // Xử lý trường hợp không có ánh xạ tên cột điểm
                                        // Có thể đặt một giá trị mặc định hoặc xử lý tùy theo logic của bạn
                                    }

                                    newScore.setIsDraft(false);
                                    newScore.setIsLocked(true);

                                    s.save(newScore);
                                    isSuccess = true;
                                }
                            }

                        }
                        break;
                    }
                }
            }
        }
        return isSuccess;

    }
}
