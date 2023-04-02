package com.example.zuccknowledge.entity;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "prerelation", schema = "courses", catalog = "")
@Proxy(lazy = false)
public class PrerelationEntity {
    private int preid;
    private int kid;
    private int prekid;

    @Id
    @Column(name = "preid")
    public int getPreid() {
        return preid;
    }

    public void setPreid(int preid) {
        this.preid = preid;
    }

    @Basic
    @Column(name = "kid")
    public int getKid() {
        return kid;
    }

    public void setKid(int kid) {
        this.kid = kid;
    }

    @Basic
    @Column(name = "prekid")
    public int getPrekid() {
        return prekid;
    }

    public void setPrekid(int prekid) {
        this.prekid = prekid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrerelationEntity that = (PrerelationEntity) o;
        return preid == that.preid && kid == that.kid && prekid == that.prekid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(preid, kid, prekid);
    }
}
