package com.example.zuccknowledge.entity;

import javax.persistence.*;

@Entity
@Table(name = "tag_course", schema = "test", catalog = "")
public class TagCourseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "tcid")
    private int tcid;
    @Basic
    @Column(name = "tid")
    private int tid;
    @Basic
    @Column(name = "cid")
    private int cid;

    public int getTcid() {
        return tcid;
    }

    public void setTcid(int tcid) {
        this.tcid = tcid;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

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

        if (tcid != that.tcid) return false;
        if (tid != that.tid) return false;
        if (cid != that.cid) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tcid;
        result = 31 * result + tid;
        result = 31 * result + cid;
        return result;
    }
}
