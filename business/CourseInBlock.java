package com.waaproject.registrationsystem.business;

public class CourseInBlock {

    private String courseTitle;

    private String profName;

    private int capacity;

    private int enrolled;

    private int available;

    private Integer courseCode;

    private Integer section;

    public CourseInBlock(){}
    public CourseInBlock(String courseTitle, String profName, int capacity, int enrolled, int available) {
        this.courseTitle = courseTitle;
        this.profName = profName;
        this.capacity = capacity;
        this.enrolled = enrolled;
        this.available = available;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getProfName() {
        return profName;
    }

    public void setProfName(String profName) {
        this.profName = profName;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getEnrolled() {
        return enrolled;
    }

    public void setEnrolled(int enrolled) {
        this.enrolled = enrolled;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public Integer getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(Integer courseCode) {
        this.courseCode = courseCode;
    }

    public Integer getSection() {
        return section;
    }

    public void setSection(Integer section) {
        this.section = section;
    }
}
