package com.waaproject.registrationsystem.service.presentationobjects;


import org.springframework.stereotype.Component;

@Component
public class AdminSectionView {
    private Integer courseCode;
    private String courseTitle;
    private String block;
    private String professor;
    private Integer capacity;
    private Integer available;
    private String location;

    //Getters
    public Integer getCourseCode() {
        return courseCode;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public String getBlock() {
        return block;
    }

    public String getProfessor() {
        return professor;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public Integer getAvailable() {
        return available;
    }

    public String getLocation() {
        return location;
    }

    //Setters

    public void setCourseCode(Integer courseCode) {
        this.courseCode = courseCode;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
