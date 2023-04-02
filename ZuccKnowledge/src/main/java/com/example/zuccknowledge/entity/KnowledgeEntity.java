package com.example.zuccknowledge.entity;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "knowledge", schema = "courses", catalog = "")
@Proxy(lazy = false)
public class KnowledgeEntity {
    private int kid;
    private String kname;
    private int courseid;
    private String description;
    private String emphasis;

    @Id
    @Column(name = "kid")
    public int getKid() {
        return kid;
    }

    public void setKid(int kid) {
        this.kid = kid;
    }

    @Basic
    @Column(name = "kname")
    public String getKname() {
        return kname;
    }

    public void setKname(String kname) {
        this.kname = kname;
    }

    @Basic
    @Column(name = "courseid")
    public int getCourseid() {
        return courseid;
    }

    public void setCourseid(int courseid) {
        this.courseid = courseid;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "emphasis")
    public String getEmphasis() {
        return emphasis;
    }

    public void setEmphasis(String emphasis) {
        this.emphasis = emphasis;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KnowledgeEntity that = (KnowledgeEntity) o;
        return kid == that.kid && courseid == that.courseid && Objects.equals(kname, that.kname) && Objects.equals(description, that.description) && Objects.equals(emphasis, that.emphasis);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kid, kname, courseid, description, emphasis);
    }
}
