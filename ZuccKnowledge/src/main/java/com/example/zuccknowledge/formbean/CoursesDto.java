package com.example.zuccknowledge.formbean;

import lombok.Data;

@Data
public class CoursesDto {
    private int courseid;
    private String coursename;
    private String introduction;
    private String textbook;
    private String tags;
    private String teacher;
}
