package com.example.zuccknowledge.entity;

import javax.persistence.*;

@Entity
@Table(name = "tag_knowledge", schema = "test", catalog = "")
public class TagKnowledgeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "tkid")
    private int tkid;
    @Basic
    @Column(name = "tid")
    private int tid;
    @Basic
    @Column(name = "kid")
    private int kid;

    public int getTkid() {
        return tkid;
    }

    public void setTkid(int tkid) {
        this.tkid = tkid;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

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

        TagKnowledgeEntity that = (TagKnowledgeEntity) o;

        if (tkid != that.tkid) return false;
        if (tid != that.tid) return false;
        if (kid != that.kid) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tkid;
        result = 31 * result + tid;
        result = 31 * result + kid;
        return result;
    }
}
