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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Kiet
 */
@Entity
@Table(name = "schoolyear")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Schoolyear.findAll", query = "SELECT s FROM Schoolyear s"),
    @NamedQuery(name = "Schoolyear.findById", query = "SELECT s FROM Schoolyear s WHERE s.id = :id"),
    @NamedQuery(name = "Schoolyear.findByNameYear", query = "SELECT s FROM Schoolyear s WHERE s.nameYear = :nameYear"),
    @NamedQuery(name = "Schoolyear.findByYearStart", query = "SELECT s FROM Schoolyear s WHERE s.yearStart = :yearStart"),
    @NamedQuery(name = "Schoolyear.findByYearEnd", query = "SELECT s FROM Schoolyear s WHERE s.yearEnd = :yearEnd"),
    @NamedQuery(name = "Schoolyear.findBySemesterName", query = "SELECT s FROM Schoolyear s WHERE s.semesterName = :semesterName")})
public class Schoolyear implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;
    @Size(max = 50)
    @Column(name = "NameYear")
    private String nameYear;
    @Size(max = 30)
    @Column(name = "YearStart")
    private String yearStart;
    @Size(max = 30)
    @Column(name = "YearEnd")
    private String yearEnd;
    @Size(max = 55)
    @Column(name = "SemesterName")
    private String semesterName;
    @JsonIgnore
    @OneToMany(mappedBy = "schoolYearId")
    private List<Score> scoreList;
    @JsonIgnore
    @OneToMany(mappedBy = "schoolYearId")
    private List<Studentsubjectteacher> studentsubjectteacherList;

    public Schoolyear() {
    }

    public Schoolyear(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameYear() {
        return nameYear;
    }

    public void setNameYear(String nameYear) {
        this.nameYear = nameYear;
    }

    public String getYearStart() {
        return yearStart;
    }

    public void setYearStart(String yearStart) {
        this.yearStart = yearStart;
    }

    public String getYearEnd() {
        return yearEnd;
    }

    public void setYearEnd(String yearEnd) {
        this.yearEnd = yearEnd;
    }

    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Schoolyear)) {
            return false;
        }
        Schoolyear other = (Schoolyear) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nameYear + " - " + semesterName;
    }
    
}
