package com.waaproject.registrationsystem.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "course_code")
    private Integer courseCode;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "course_desc")
    private String courseDesc;

    @Column(name = "course_credit")
    private Double courseCredit;

    @ManyToMany(mappedBy = "courseList", cascade = CascadeType.ALL)
    private List<Prerequisite> prerequisiteList;

    //Constructors
    public Course(String courseName, String courseDesc, Double courseCredit) {
        this.courseName = courseName;
        this.courseDesc = courseDesc;
        this.courseCredit = courseCredit;
        prerequisiteList=new ArrayList<>();
    }

    public Course(){}

    //Getters
    public Integer getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseDesc() {
        return courseDesc;
    }

    public Double getCourseCredit() {
        return courseCredit;
    }

    public List<Prerequisite> getPrerequisiteList() {
        return prerequisiteList;
    }

    //Setters
    public void setCourseCode(Integer courseCode) {
        this.courseCode = courseCode;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCourseDesc(String courseDesc) {
        this.courseDesc = courseDesc;
    }

    public void setCourseCredit(Double courseCredit) {
        this.courseCredit = courseCredit;
    }

    public void setPrerequisite(Prerequisite prerequisite){
        this.prerequisiteList.add(prerequisite);
        prerequisite.addCourseList(this);
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseCode=" + courseCode +
                ", courseName='" + courseName + '\'' +
                ", courseDesc='" + courseDesc + '\'' +
                ", courseCredit=" + courseCredit +
                '}';
    }
}
