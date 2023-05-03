package com.example.zuccknowledge.entity;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tag", schema = "test", catalog = "")
@Proxy(lazy = false)
public class TagEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "tagid")
    private int tagid;
    @Basic
    @Column(name = "tagname")
    private String tagname;
    @Basic
    @Column(name = "tagroot")
    private Integer tagroot;

    @Id
    @Column(name = "tagid")
    public int getTagid() {
        return tagid;
    }

    public void setTagid(int tagid) {
        this.tagid = tagid;
    }

    @Basic
    @Column(name = "tagname")
    public String getTagname() {
        return tagname;
    }

    public void setTagname(String tagname) {
        this.tagname = tagname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagEntity tagEntity = (TagEntity) o;
        return tagid == tagEntity.tagid && Objects.equals(tagname, tagEntity.tagname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tagid, tagname);
    }

    public Integer getTagroot() {
        return tagroot;
    }

    public void setTagroot(Integer tagroot) {
        this.tagroot = tagroot;
    }
}
