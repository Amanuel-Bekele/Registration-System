package com.waaproject.registrationsystem.domain;

import java.util.List;

public class PreCourse {

    private Course course;

    private List<Course> prerequisites;

    public PreCourse(){}

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Course> getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(List<Course> prerequisites) {
        this.prerequisites = prerequisites;
    }

    @Override
    public String toString() {
        return "PreCourse{" +
                "course=" + course +
                '}';
    }
}
