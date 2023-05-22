package com.example.zuccknowledge.entity;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "like_cases", schema = "test", catalog = "")
@Proxy(lazy = false)
public class LikeCasesEntity {
    private Timestamp time;
    private String username;
    private int caseid;
    private int liked;
    private int likeid;

    @Basic
    @Column(name = "time")
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Basic
    @Column(name = "Username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "caseid")
    public int getCaseid() {
        return caseid;
    }

    public void setCaseid(int caseid) {
        this.caseid = caseid;
    }

    @Basic
    @Column(name = "liked")
    public int getLiked() {
        return liked;
    }

    public void setLiked(int liked) {
        this.liked = liked;
    }

    @Id
    @Column(name = "likeid")
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
        return caseid == that.caseid && liked == that.liked && likeid == that.likeid && Objects.equals(time, that.time) && Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, username, caseid, liked, likeid);
    }
}
