package com.waaproject.registrationsystem.business;

public class EnrolledCourses {

    public EnrolledCourses(Integer courseCode, String courseTitle, String blockTime, String profName) {
        this.courseCode = courseCode;
        this.courseTitle = courseTitle;
        this.blockTime = blockTime;
        this.profName = profName;
    }

    public EnrolledCourses() {
    }

    private Integer courseCode;

    private String courseTitle;

    private String blockTime;

    private String profName;

    public Integer getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(Integer courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getBlockTime() {
        return blockTime;
    }

    public void setBlockTime(String blockTime) {
        this.blockTime = blockTime;
    }

    public String getProfName() {
        return profName;
    }

    public void setProfName(String profName) {
        this.profName = profName;
    }
}
