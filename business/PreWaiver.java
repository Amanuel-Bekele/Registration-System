package com.waaproject.registrationsystem.business;

public class PreWaiver {

    public PreWaiver(Integer courseCode, String courseName) {
        this.courseCode = courseCode;
        this.couseName = courseName;
    }

    public PreWaiver() {
    }

    private Integer courseCode;

    private String couseName;

    public Integer getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(Integer courseCode) {
        this.courseCode = courseCode;
    }

    public String getCouseName() {
        return couseName;
    }

    public void setCourseName(String couseName) {
        this.couseName = couseName;
    }
}
