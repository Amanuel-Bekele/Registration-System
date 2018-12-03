package com.waaproject.registrationsystem.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "enrollments")
public class StudentSection implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name="section_id")
    private Section section;

    @Column(name = "is_completed")
    private Boolean isCompleted;

    @Column(name = "is_waived")
    private Boolean isWaived;

    //Constructors

    public StudentSection() {
    }

    public StudentSection(Student student, Section section, Boolean isCompleted, Boolean isWaived) {
        this.student = student;
        this.section = section;
        this.isCompleted = isCompleted;
        this.isWaived = isWaived;
    }

    //Getters

    public Integer getId() {
        return id;
    }

    public Student getStudent() {
        return student;
    }

    public Section getSection() {
        return section;
    }

    public Boolean getCompleted() {
        return isCompleted;
    }

    public Boolean getWaived() {
        return isWaived;
    }

    //Setters

    public void setId(Integer id) {
        this.id = id;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }

    public void setWaived(Boolean waived) {
        isWaived = waived;
    }

}
