/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hcmou.pojo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kiet
 */
@Entity
@Table(name = "score")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Score.findAll", query = "SELECT s FROM Score s"),
    @NamedQuery(name = "Score.findById", query = "SELECT s FROM Score s WHERE s.id = :id"),
    @NamedQuery(name = "Score.findByScoreValue", query = "SELECT s FROM Score s WHERE s.scoreValue = :scoreValue"),
    @NamedQuery(name = "Score.findByIsDraft", query = "SELECT s FROM Score s WHERE s.isDraft = :isDraft"),
    @NamedQuery(name = "Score.findByIsLocked", query = "SELECT s FROM Score s WHERE s.isLocked = :isLocked")})
public class Score implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ScoreValue")
    private Float scoreValue;
    @Column(name = "IsDraft")
    private Boolean isDraft;
    @Column(name = "IsLocked")
    private Boolean isLocked;
    @JoinColumn(name = "SchoolYearId", referencedColumnName = "Id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Schoolyear schoolYearId;
    @JoinColumn(name = "StudentID", referencedColumnName = "Id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Student studentID;
    @JoinColumn(name = "SubjectTeacherID", referencedColumnName = "Id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Subjectteacher subjectTeacherID;
    @JoinColumn(name = "ScoreType", referencedColumnName = "ScoreType")
    @ManyToOne(fetch = FetchType.EAGER)
    private Typescore scoreType;

    public Score() {
    }

    public Score(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getScoreValue() {
        return scoreValue;
    }

    public void setScoreValue(Float scoreValue) {
        this.scoreValue = scoreValue;
    }

    public Boolean getIsDraft() {
        return isDraft;
    }

    public void setIsDraft(Boolean isDraft) {
        this.isDraft = isDraft;
    }

    public Boolean getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(Boolean isLocked) {
        this.isLocked = isLocked;
    }

    public Schoolyear getSchoolYearId() {
        return schoolYearId;
    }

    public void setSchoolYearId(Schoolyear schoolYearId) {
        this.schoolYearId = schoolYearId;
    }

    public Student getStudentID() {
        return studentID;
    }

    public void setStudentID(Student studentID) {
        this.studentID = studentID;
    }

    public Subjectteacher getSubjectTeacherID() {
        return subjectTeacherID;
    }

    public void setSubjectTeacherID(Subjectteacher subjectTeacherID) {
        this.subjectTeacherID = subjectTeacherID;
    }

    public Typescore getScoreType() {
        return scoreType;
    }

    public void setScoreType(Typescore scoreType) {
        this.scoreType = scoreType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Score)) {
            return false;
        }
        Score other = (Score) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hcmou.pojo.Score[ id=" + id + " ]";
    }
    
}
