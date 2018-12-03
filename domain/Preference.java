package com.waaproject.registrationsystem.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "preferences")
public class Preference implements Serializable {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;

    private Integer priority;

    @ManyToOne
    @JoinColumn(name = "block_id")
    private Block block;

    //constructors
    public Preference() {
    }

    public Preference(Student student, Section section, Integer priority, Block block) {
        this.student = student;
        this.section = section;
        this.priority = priority;
        this.block = block;
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

    public Integer getPriority() {
        return priority;
    }

    public Block getBlock() {
        return block;
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

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public void setBlock(Block block) {
        this.block = block;
    }
}
