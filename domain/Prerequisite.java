package com.waaproject.registrationsystem.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "prerequisites")
public class Prerequisite implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "course_id")
    private Course prerequisite;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "courses_prerequisites", joinColumns = @JoinColumn(name = "prerequisite_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Course> courseList;

    @Column(name = "waive_status")
    private Boolean waiveStatus;

    @Column(name = "is_required")
    private Boolean isRequired;

    //Constructors


    public Prerequisite() {
            courseList = new ArrayList<>();
    }

    public Prerequisite(Course prerequisite, Course course, Boolean waiveStatus, Boolean isRequired) {
        this.prerequisite = prerequisite;
        this.courseList = new ArrayList<>();
        courseList.add(course);
        this.isRequired = isRequired;

    }

    public Prerequisite(Boolean waiveStatus, Boolean isRequired) {
        this.waiveStatus = waiveStatus;
        this.isRequired = isRequired;
    }

    //Getters
    public Integer getId() {
        return id;
    }

    public Course getPrerequisite() {
        return prerequisite;
    }

    public List<Course> getCourseList() {
        return courseList;
    }


    public Boolean getRequired() {
        return isRequired;
    }

    //Setters

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPrerequisite(Course prerequisite) {
        this.prerequisite = prerequisite;
    }

    public void addCourseList(Course course) {
        courseList.add(course);
    }

    public void setRequired(Boolean required) {
        isRequired = required;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public void setWaiveStatus(Boolean waiveStatus) {
        this.waiveStatus = waiveStatus;
    }
}
