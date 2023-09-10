/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hcmou.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Kiet
 */
@Entity
@Table(name = "subjectteacher")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Subjectteacher.findAll", query = "SELECT s FROM Subjectteacher s"),
    @NamedQuery(name = "Subjectteacher.findById", query = "SELECT s FROM Subjectteacher s WHERE s.id = :id")})
public class Subjectteacher implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;
    @OneToMany(mappedBy = "subjectTeacherId")
    @JsonIgnore
    private List<Forum> forumList;
    @OneToMany(mappedBy = "subjectTeacherId")
    @JsonIgnore
    private List<Notification> notificationList;
    @OneToMany(mappedBy = "subjectTeacherID")
    @JsonIgnore
    private List<Score> scoreList;
    @OneToMany(mappedBy = "subjectTeacherId")
    @JsonIgnore
    private List<Studentsubjectteacher> studentsubjectteacherList;
    @JoinColumn(name = "SubjectId", referencedColumnName = "Id")
    @ManyToOne
    private Subject subjectId;
    @JoinColumn(name = "TeacherId", referencedColumnName = "Id")
    @ManyToOne
    private Teacher teacherId;

    public Subjectteacher() {
    }

    public Subjectteacher(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @XmlTransient
    public List<Forum> getForumList() {
        return forumList;
    }

    public void setForumList(List<Forum> forumList) {
        this.forumList = forumList;
    }

    @XmlTransient
    public List<Notification> getNotificationList() {
        return notificationList;
    }

    public void setNotificationList(List<Notification> notificationList) {
        this.notificationList = notificationList;
    }

    @XmlTransient
    public List<Score> getScoreList() {
        return scoreList;
    }

    public void setScoreList(List<Score> scoreList) {
        this.scoreList = scoreList;
    }

    @XmlTransient
    public List<Studentsubjectteacher> getStudentsubjectteacherList() {
        return studentsubjectteacherList;
    }

    public void setStudentsubjectteacherList(List<Studentsubjectteacher> studentsubjectteacherList) {
        this.studentsubjectteacherList = studentsubjectteacherList;
    }

    public Subject getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Subject subjectId) {
        this.subjectId = subjectId;
    }

    public Teacher getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Teacher teacherId) {
        this.teacherId = teacherId;
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
        if (!(object instanceof Subjectteacher)) {
            return false;
        }
        Subjectteacher other = (Subjectteacher) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return teacherId + " - " + subjectId;
    }
    
}
