package com.example.zuccknowledge.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "like_cases", schema = "course", catalog = "")
public class LikeCasesEntity {
    @Basic
    @Column(name = "time")
    private Timestamp time;
    @Basic
    @Column(name = "Username")
    private String username;
    @Basic
    @Column(name = "caseid")
    private String caseid;
    @Basic
    @Column(name = "liked")
    private String liked;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "likeid")
    private int likeid;

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCaseid() {
        return caseid;
    }

    public void setCaseid(String caseid) {
        this.caseid = caseid;
    }

    public String getLiked() {
        return liked;
    }

    public void setLiked(String liked) {
        this.liked = liked;
    }

    public int getLikeid() {
        return likeid;
    }

    public void setLikeid(int likeid) {
        this.likeid = likeid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LikeCasesEntity that = (LikeCasesEntity) o;

        if (likeid != that.likeid) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (caseid != null ? !caseid.equals(that.caseid) : that.caseid != null) return false;
        if (liked != null ? !liked.equals(that.liked) : that.liked != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = time != null ? time.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (caseid != null ? caseid.hashCode() : 0);
        result = 31 * result + (liked != null ? liked.hashCode() : 0);
        result = 31 * result + likeid;
        return result;
    }
}
