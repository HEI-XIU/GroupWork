package com.example.zuccknowledge.entity;

import javax.persistence.*;

@Entity
@Table(name = "tag_cases", schema = "test", catalog = "")
public class TagCasesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "tagcaseid")
    private int tagcaseid;
    @Basic
    @Column(name = "casesid")
    private int casesid;
    @Basic
    @Column(name = "tagid")
    private int tagid;

    public int getTagcaseid() {
        return tagcaseid;
    }

    public void setTagcaseid(int tagcaseid) {
        this.tagcaseid = tagcaseid;
    }

    public int getCasesid() {
        return casesid;
    }

    public void setCasesid(int casesid) {
        this.casesid = casesid;
    }

    public int getTagid() {
        return tagid;
    }

    public void setTagid(int tagid) {
        this.tagid = tagid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TagCasesEntity that = (TagCasesEntity) o;

        if (tagcaseid != that.tagcaseid) return false;
        if (casesid != that.casesid) return false;
        if (tagid != that.tagid) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tagcaseid;
        result = 31 * result + casesid;
        result = 31 * result + tagid;
        return result;
    }
}
