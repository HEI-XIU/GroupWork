package com.example.zuccknowledge.entity;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cases", schema = "courses", catalog = "")
@Proxy(lazy = false)
public class CasesEntity {
    private int caseid;
    private String casename;
    private int knowledgeid;
    private String casecontent;
    private byte iscode;
    private String type;

    @Id
    @Column(name = "caseid")
    public int getCaseid() {
        return caseid;
    }

    public void setCaseid(int caseid) {
        this.caseid = caseid;
    }

    @Basic
    @Column(name = "casename")
    public String getCasename() {
        return casename;
    }

    public void setCasename(String casename) {
        this.casename = casename;
    }

    @Basic
    @Column(name = "knowledgeid")
    public int getKnowledgeid() {
        return knowledgeid;
    }

    public void setKnowledgeid(int knowledgeid) {
        this.knowledgeid = knowledgeid;
    }

    @Basic
    @Column(name = "casecontent")
    public String getCasecontent() {
        return casecontent;
    }

    public void setCasecontent(String casecontent) {
        this.casecontent = casecontent;
    }

    @Basic
    @Column(name = "iscode")
    public byte getIscode() {
        return iscode;
    }

    public void setIscode(byte iscode) {
        this.iscode = iscode;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CasesEntity that = (CasesEntity) o;
        return caseid == that.caseid && knowledgeid == that.knowledgeid && iscode == that.iscode && Objects.equals(casename, that.casename) && Objects.equals(casecontent, that.casecontent) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(caseid, casename, knowledgeid, casecontent, iscode, type);
    }
}
