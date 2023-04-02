package com.example.zuccknowledge.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tgroup", schema = "courses", catalog = "")
public class TgroupEntity {
    private int gid;
    private String gname;

    @Id
    @Column(name = "gid")
    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    @Basic
    @Column(name = "gname")
    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TgroupEntity that = (TgroupEntity) o;
        return gid == that.gid && Objects.equals(gname, that.gname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gid, gname);
    }
}
