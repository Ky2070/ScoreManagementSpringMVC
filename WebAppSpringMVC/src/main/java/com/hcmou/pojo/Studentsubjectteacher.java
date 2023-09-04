/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hcmou.pojo;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kiet
 */
@Entity
@Table(name = "studentsubjectteacher")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Studentsubjectteacher.findAll", query = "SELECT s FROM Studentsubjectteacher s"),
    @NamedQuery(name = "Studentsubjectteacher.findById", query = "SELECT s FROM Studentsubjectteacher s WHERE s.id = :id")})
public class Studentsubjectteacher implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;
    @JoinColumn(name = "SchoolYearId", referencedColumnName = "Id")
    @ManyToOne
    private Schoolyear schoolYearId;
    @JoinColumn(name = "StudentId", referencedColumnName = "Id")
    @ManyToOne
    private Student studentId;
    @JoinColumn(name = "SubjectTeacherId", referencedColumnName = "Id")
    @ManyToOne
    private Subjectteacher subjectTeacherId;

    public Studentsubjectteacher() {
    }

    public Studentsubjectteacher(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Schoolyear getSchoolYearId() {
        return schoolYearId;
    }

    public void setSchoolYearId(Schoolyear schoolYearId) {
        this.schoolYearId = schoolYearId;
    }

    public Student getStudentId() {
        return studentId;
    }

    public void setStudentId(Student studentId) {
        this.studentId = studentId;
    }

    public Subjectteacher getSubjectTeacherId() {
        return subjectTeacherId;
    }

    public void setSubjectTeacherId(Subjectteacher subjectTeacherId) {
        this.subjectTeacherId = subjectTeacherId;
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
        if (!(object instanceof Studentsubjectteacher)) {
            return false;
        }
        Studentsubjectteacher other = (Studentsubjectteacher) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hcmou.pojo.Studentsubjectteacher[ id=" + id + " ]";
    }
    
}
