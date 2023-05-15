package com.example.zuccknowledge.entity;

import javax.persistence.*;

@Entity
@Table(name = "cases", schema = "course", catalog = "")

public class CasesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "caseid")
    private int caseid;
    @Basic
    @Column(name = "casename")
    private String casename;
    @Basic
    @Column(name = "knowledgeid")
    private int knowledgeid;
    @Basic
    @Column(name = "casecontent")
    private String casecontent;
    @Basic
    @Column(name = "iscode")
    private byte iscode;
    @Basic
    @Column(name = "type")
    private String type;
    @Basic
    @Column(name = "likes")
    private String likes;

    public int getCaseid() {
        return caseid;
    }

    public void setCaseid(int caseid) {
        this.caseid = caseid;
    }

    public String getCasename() {
        return casename;
    }

    public void setCasename(String casename) {
        this.casename = casename;
    }

    public int getKnowledgeid() {
        return knowledgeid;
    }

    public void setKnowledgeid(int knowledgeid) {
        this.knowledgeid = knowledgeid;
    }

    public String getCasecontent() {
        return casecontent;
    }

    public void setCasecontent(String casecontent) {
        this.casecontent = casecontent;
    }

    public byte getIscode() {
        return iscode;
    }

    public void setIscode(byte iscode) {
        this.iscode = iscode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CasesEntity that = (CasesEntity) o;

        if (caseid != that.caseid) return false;
        if (knowledgeid != that.knowledgeid) return false;
        if (iscode != that.iscode) return false;
        if (casename != null ? !casename.equals(that.casename) : that.casename != null) return false;
        if (casecontent != null ? !casecontent.equals(that.casecontent) : that.casecontent != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (likes != null ? !likes.equals(that.likes) : that.likes != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = caseid;
        result = 31 * result + (casename != null ? casename.hashCode() : 0);
        result = 31 * result + knowledgeid;
        result = 31 * result + (casecontent != null ? casecontent.hashCode() : 0);
        result = 31 * result + (int) iscode;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (likes != null ? likes.hashCode() : 0);
        return result;
    }
}
