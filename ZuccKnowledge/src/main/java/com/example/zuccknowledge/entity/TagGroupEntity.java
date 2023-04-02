package com.example.zuccknowledge.entity;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tag_group", schema = "courses", catalog = "")
@Proxy(lazy = false)
@IdClass(TagGroupEntityPK.class)
public class TagGroupEntity {
    private int tgid;
    private int tid;
    private int gid;

    @Id
    @Column(name = "tgid")
    public int getTgid() {
        return tgid;
    }

    public void setTgid(int tgid) {
        this.tgid = tgid;
    }

    @Id
    @Column(name = "tid")
    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    @Id
    @Column(name = "gid")
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
        return tgid == that.tgid && tid == that.tid && gid == that.gid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tgid, tid, gid);
    }
}
