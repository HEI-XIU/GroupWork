package com.example.zuccknowledge.entity;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tag_course", schema = "courses", catalog = "")
@Proxy(lazy = false)
public class TagCourseEntity {
    private int tcid;
    private int tid;
    private int cid;

    @Id
    @Column(name = "tcid")
    public int getTcid() {
        return tcid;
    }

    public void setTcid(int tcid) {
        this.tcid = tcid;
    }

    @Basic
    @Column(name = "tid")
    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    @Basic
    @Column(name = "cid")
    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagCourseEntity that = (TagCourseEntity) o;
        return tcid == that.tcid && tid == that.tid && cid == that.cid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tcid, tid, cid);
    }
}
