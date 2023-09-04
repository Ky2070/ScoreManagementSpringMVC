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
@Table(name = "typescore")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Typescore.findAll", query = "SELECT t FROM Typescore t"),
    @NamedQuery(name = "Typescore.findByScoreType", query = "SELECT t FROM Typescore t WHERE t.scoreType = :scoreType")})
public class Typescore implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ScoreType")
    private String scoreType;
    @OneToMany(mappedBy = "scoreType")
    @JsonIgnore
    private List<Score> scoreList;

    public Typescore() {
    }

    public Typescore(String scoreType) {
        this.scoreType = scoreType;
    }

    public String getScoreType() {
        return scoreType;
    }

    public void setScoreType(String scoreType) {
        this.scoreType = scoreType;
    }

    @XmlTransient
    public List<Score> getScoreList() {
        return scoreList;
    }

    public void setScoreList(List<Score> scoreList) {
        this.scoreList = scoreList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (scoreType != null ? scoreType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Typescore)) {
            return false;
        }
        Typescore other = (Typescore) object;
        if ((this.scoreType == null && other.scoreType != null) || (this.scoreType != null && !this.scoreType.equals(other.scoreType))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return scoreType;
    }
    
}
