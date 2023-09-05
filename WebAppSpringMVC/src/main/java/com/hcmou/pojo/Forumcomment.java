/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hcmou.pojo;

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
@Table(name = "forumcomment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Forumcomment.findAll", query = "SELECT f FROM Forumcomment f"),
    @NamedQuery(name = "Forumcomment.findById", query = "SELECT f FROM Forumcomment f WHERE f.id = :id"),
    @NamedQuery(name = "Forumcomment.findByTitle", query = "SELECT f FROM Forumcomment f WHERE f.title = :title"),
    @NamedQuery(name = "Forumcomment.findByContent", query = "SELECT f FROM Forumcomment f WHERE f.content = :content"),
    @NamedQuery(name = "Forumcomment.findByCreatedAt", query = "SELECT f FROM Forumcomment f WHERE f.createdAt = :createdAt")})
public class Forumcomment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "Title")
    private String title;
    @Size(max = 3000)
    @Column(name = "Content")
    private String content;
    @Column(name = "CreatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @JoinColumn(name = "ForumId", referencedColumnName = "Id")
    @ManyToOne
    private Forum forumId;
    @OneToMany(mappedBy = "parentCommentId")
    private List<Forumcomment> forumcommentList;
    @JoinColumn(name = "ParentCommentId", referencedColumnName = "Id")
    @ManyToOne
    private Forumcomment parentCommentId;
    @JoinColumn(name = "UserId", referencedColumnName = "Id")
    @ManyToOne
    private User userId;

    public Forumcomment() {
    }

    public Forumcomment(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Forum getForumId() {
        return forumId;
    }

    public void setForumId(Forum forumId) {
        this.forumId = forumId;
    }

    @XmlTransient
    public List<Forumcomment> getForumcommentList() {
        return forumcommentList;
    }

    public void setForumcommentList(List<Forumcomment> forumcommentList) {
        this.forumcommentList = forumcommentList;
    }

    public Forumcomment getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(Forumcomment parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
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
        if (!(object instanceof Forumcomment)) {
            return false;
        }
        Forumcomment other = (Forumcomment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hcmou.pojo.Forumcomment[ id=" + id + " ]";
    }
    
}
