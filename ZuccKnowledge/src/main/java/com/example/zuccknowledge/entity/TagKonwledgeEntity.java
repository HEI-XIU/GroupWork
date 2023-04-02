package com.example.zuccknowledge.entity;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tag_konwledge", schema = "courses", catalog = "")
@Proxy(lazy = false)
public class TagKonwledgeEntity {
    private int tkid;
    private int tid;
    private int kid;

    @Id
    @Column(name = "tkid")
    public int getTkid() {
        return tkid;
    }

    public void setTkid(int tkid) {
        this.tkid = tkid;
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
    @Column(name = "kid")
    public int getKid() {
        return kid;
    }

    public void setKid(int kid) {
        this.kid = kid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagKonwledgeEntity that = (TagKonwledgeEntity) o;
        return tkid == that.tkid && tid == that.tid && kid == that.kid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tkid, tid, kid);
    }
}
