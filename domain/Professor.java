package com.waaproject.registrationsystem.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "professors")
public class Professor implements Serializable {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
    
    private String bio;
    private String title;
    @OneToMany(mappedBy = "advisor")
    private List<Student> studentList;
    @OneToMany(mappedBy = "professor")
    private List<Section> sectionList;

    //Constructors

    public Professor() {
    }

    public Professor(String firstName, String lastName, String bio, String title) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.title = title;
        sectionList=new ArrayList<>();
        studentList=new ArrayList<>();
    }


    //Getters

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBio() {
        return bio;
    }

    public String getTitle() {
        return title;
    }

    public List<Section> getSectionList() {
        return sectionList;
    }

    public List<Student> getStudents() {
        return studentList;
    }

    //Setters

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSectionList(Section section) {
        sectionList.add(section);
    }

    public void setStudents(Student student) {
         this.studentList.add(student);
    }

    @Override
    public String toString(){
        return ""+firstName+" "+lastName;
    }
}
