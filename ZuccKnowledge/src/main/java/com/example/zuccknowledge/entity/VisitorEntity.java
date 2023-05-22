package com.example.zuccknowledge.entity;

import javax.persistence.*;

@Entity
@Table(name = "visitor", schema = "course", catalog = "")
public class VisitorEntity {
    private int vid;
    private String vname;

    @Id
    @Column(name = "vid")
    public int getVid() {
        return vid;
    }

    public void setVid(int vid) {
        this.vid = vid;
    }

    @Basic
    @Column(name = "vname")
    public String getVname() {
        return vname;
    }

    public void setVname(String vname) {
        this.vname = vname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VisitorEntity that = (VisitorEntity) o;

        if (vid != that.vid) return false;
        if (vname != null ? !vname.equals(that.vname) : that.vname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = vid;
        result = 31 * result + (vname != null ? vname.hashCode() : 0);
        return result;
    }
}
