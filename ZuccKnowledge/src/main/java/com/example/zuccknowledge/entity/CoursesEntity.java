package com.example.zuccknowledge.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "courses", schema = "courses", catalog = "")
public class CoursesEntity {
    private int courseid;
    private String coursename;
    private String introduction;
    private String textbook;
    private String teacher;

    @Id
    @Column(name = "courseid")
    public int getCourseid() {
        return courseid;
    }

    public void setCourseid(int courseid) {
        this.courseid = courseid;
    }

    @Basic
    @Column(name = "coursename")
    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    @Basic
    @Column(name = "introduction")
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Basic
    @Column(name = "textbook")
    public String getTextbook() {
        return textbook;
    }

    public void setTextbook(String textbook) {
        this.textbook = textbook;
    }


    @Basic
    @Column(name = "teacher")
    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoursesEntity that = (CoursesEntity) o;
        return courseid == that.courseid && Objects.equals(coursename, that.coursename) && Objects.equals(introduction, that.introduction) && Objects.equals(textbook, that.textbook) && Objects.equals(teacher, that.teacher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseid, coursename, introduction, textbook, teacher);
    }
}
