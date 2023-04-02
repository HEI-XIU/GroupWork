package com.example.zuccknowledge.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class TagGroupEntityPK implements Serializable {
    private int tgid;
    private int tid;
    private int gid;

    @Column(name = "tgid")
    @Id
    public int getTgid() {
        return tgid;
    }

    public void setTgid(int tgid) {
        this.tgid = tgid;
    }

    @Column(name = "tid")
    @Id
    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    @Column(name = "gid")
    @Id
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
        TagGroupEntityPK that = (TagGroupEntityPK) o;
        return tgid == that.tgid && tid == that.tid && gid == that.gid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tgid, tid, gid);
    }
}
