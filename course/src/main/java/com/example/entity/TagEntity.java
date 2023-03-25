package com.example.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tag", schema = "course", catalog = "")
public class TagEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "tagid")
    private int tagid;
    @Basic
    @Column(name = "tagname")
    private String tagname;
    @Basic
    @Column(name = "taggroupname")
    private String taggroupname;

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

    public String getTaggroupname() {
        return taggroupname;
    }

    public void setTaggroupname(String taggroupname) {
        this.taggroupname = taggroupname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagEntity tagEntity = (TagEntity) o;
        return tagid == tagEntity.tagid && Objects.equals(tagname, tagEntity.tagname) && Objects.equals(taggroupname, tagEntity.taggroupname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tagid, tagname, taggroupname);
    }
}
