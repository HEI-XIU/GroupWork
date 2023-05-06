package com.example.zuccknowledge.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "k_read_record", schema = "course", catalog = "")
public class KReadRecordEntity {
    private int krecordid;
    private int kid;
    private int reader;
    private Timestamp opentime;

    @Id
    @Column(name = "krecordid")
    public int getKrecordid() {
        return krecordid;
    }

    public void setKrecordid(int krecordid) {
        this.krecordid = krecordid;
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
    @Column(name = "reader")
    public int getReader() {
        return reader;
    }

    public void setReader(int reader) {
        this.reader = reader;
    }

    @Basic
    @Column(name = "opentime")
    public Timestamp getOpentime() {
        return opentime;
    }

    public void setOpentime(Timestamp opentime) {
        this.opentime = opentime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KReadRecordEntity that = (KReadRecordEntity) o;

        if (krecordid != that.krecordid) return false;
        if (kid != that.kid) return false;
        if (reader != that.reader) return false;
        if (opentime != null ? !opentime.equals(that.opentime) : that.opentime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(krecordid, kid, reader) + (opentime != null ? opentime.hashCode() : 0);
    }
}
