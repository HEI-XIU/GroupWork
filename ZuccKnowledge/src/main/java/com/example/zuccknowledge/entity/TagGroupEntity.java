package com.example.zuccknowledge.entity;

import javax.persistence.*;

@Entity
@Table(name = "tag_group", schema = "test", catalog = "")
public class TagGroupEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "tgid")
    private int tgid;
    @Basic
    @Column(name = "tid")
    private int tid;
    @Basic
    @Column(name = "gid")
    private int gid;

    public int getTgid() {
        return tgid;
    }

    public void setTgid(int tgid) {
        this.tgid = tgid;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TagGroupEntity that = (TagGroupEntity) o;

        if (tgid != that.tgid) return false;
        if (tid != that.tid) return false;
        if (gid != that.gid) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tgid;
        result = 31 * result + tid;
        result = 31 * result + gid;
        return result;
    }
}
