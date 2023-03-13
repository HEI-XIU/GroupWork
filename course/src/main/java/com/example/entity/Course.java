package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Course {
    private String courseName;
    private String courseId;
    private String introduction;
    private String textbook;
    private String tags;
    private String teacher;

    @Override
    public String toString() {
        return "Course{" +
                "courseName='" + courseName + '\'' +
                ", courseId='" + courseId + '\'' +
                ", introduction='" + introduction + '\'' +
                ", textbook='" + textbook + '\'' +
                ", tags='" + tags + '\'' +
                ", teacher='" + teacher + '\'' +
                '}';
    }
}
