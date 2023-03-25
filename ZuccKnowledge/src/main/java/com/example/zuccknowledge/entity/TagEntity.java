package com.example.zuccknowledge.entity;

import javax.persistence.*;

@Entity
@Table(name = "tag", schema = "test", catalog = "")
public class TagEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "tagid")
    private int tagid;
    @Basic
    @Column(name = "tagname")
    private String tagname;

    public int getTagid() {
        return tagid;
    }

    public void setTagid(int tagid) {
        this.tagid = tagid;
    }

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

        if (tagid != tagEntity.tagid) return false;
        if (tagname != null ? !tagname.equals(tagEntity.tagname) : tagEntity.tagname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tagid;
        result = 31 * result + (tagname != null ? tagname.hashCode() : 0);
        return result;
    }
}
