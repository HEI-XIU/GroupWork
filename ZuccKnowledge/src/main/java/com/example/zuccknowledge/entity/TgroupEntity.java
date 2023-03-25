package com.example.zuccknowledge.entity;

import javax.persistence.*;

@Entity
@Table(name = "tgroup", schema = "test", catalog = "")
public class TgroupEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "gid")
    private int gid;
    @Basic
    @Column(name = "gname")
    private String gname;

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

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

        if (gid != that.gid) return false;
        if (gname != null ? !gname.equals(that.gname) : that.gname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = gid;
        result = 31 * result + (gname != null ? gname.hashCode() : 0);
        return result;
    }
}
