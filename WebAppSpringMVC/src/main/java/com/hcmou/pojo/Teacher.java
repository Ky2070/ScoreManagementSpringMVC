/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hcmou.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Kiet
 */
@Entity
@Table(name = "teacher")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Teacher.findAll", query = "SELECT t FROM Teacher t"),
    @NamedQuery(name = "Teacher.findById", query = "SELECT t FROM Teacher t WHERE t.id = :id"),
    @NamedQuery(name = "Teacher.findByTeacherName", query = "SELECT t FROM Teacher t WHERE t.teacherName = :teacherName"),
    @NamedQuery(name = "Teacher.findByEmail", query = "SELECT t FROM Teacher t WHERE t.email = :email"),
    @NamedQuery(name = "Teacher.findByPhoneNumber", query = "SELECT t FROM Teacher t WHERE t.phoneNumber = :phoneNumber"),
    @NamedQuery(name = "Teacher.findByAddress", query = "SELECT t FROM Teacher t WHERE t.address = :address"),
    @NamedQuery(name = "Teacher.findByGender", query = "SELECT t FROM Teacher t WHERE t.gender = :gender"),
    @NamedQuery(name = "Teacher.findByBirthdate", query = "SELECT t FROM Teacher t WHERE t.birthdate = :birthdate")})
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "TeacherName")
    private String teacherName;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 125)
    @Column(name = "Email")
    private String email;
    @Size(max = 20)
    @Column(name = "PhoneNumber")
    private String phoneNumber;
    @Size(max = 255)
    @Column(name = "Address")
    private String address;
    @Column(name = "Gender")
    private Short gender;
    @Column(name = "Birthdate")
    @Temporal(TemporalType.DATE)
    private Date birthdate;
    @JoinColumn(name = "DepartmentId", referencedColumnName = "Id")
    @ManyToOne
    private Department departmentId;
    @OneToMany(mappedBy = "teacherId")
    @JsonIgnore
    private List<Subjectteacher> subjectteacherList;
    @OneToMany(mappedBy = "teacherId")
    @JsonIgnore
    private List<Class> classList;

    public Teacher() {
    }

    public Teacher(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Short getGender() {
        return gender;
    }

    public void setGender(Short gender) {
        this.gender = gender;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Department getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Department departmentId) {
        this.departmentId = departmentId;
    }

    @XmlTransient
    public List<Subjectteacher> getSubjectteacherList() {
        return subjectteacherList;
    }

    public void setSubjectteacherList(List<Subjectteacher> subjectteacherList) {
        this.subjectteacherList = subjectteacherList;
    }

    @XmlTransient
    public List<Class> getClassList() {
        return classList;
    }

    public void setClassList(List<Class> classList) {
        this.classList = classList;
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
        if (!(object instanceof Teacher)) {
            return false;
        }
        Teacher other = (Teacher) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return teacherName;
    }
    
}
