package com.waaproject.registrationsystem.domain;


import com.waaproject.registrationsystem.validator.UniqueEmail;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "students")
public class Student implements Serializable {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer id;

    @OneToOne
    @JoinColumn(name = "userid")
    private User userId;

    @NotBlank
    @Column(name = "first_name")
    private String firstName;

    @NotBlank
    @Column(name = "last_name")
    private String lastName;

    @NotBlank
    private String nationality;

    @ManyToOne
    @JoinColumn(name = "advisor_id")
    private Professor advisor;

    @NotBlank
    @Email
    @UniqueEmail(message = "{UniqueEmail.email}")
    private String email;

    private String track;

    @Column(name = "image_url")
    private String imageUrl;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate entry;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "student")
    private List<StudentSection> sectionList;

    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
    private List<Preference> preferenceList;

    //Constructors

    public Student() {
    }

    public Student(String firstName, String lastName, String nationality, String title) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationality = nationality;
        sectionList=new ArrayList<>();
        preferenceList=new ArrayList<>();
    }

    public Student(String firstName, String lastName, String nationality) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationality = nationality;
    }

    public Student(String firstName, String lastName, String nationality, String email, LocalDate date, String track) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationality = nationality;
        this.entry = date;
        this.track = track;
        this.email = email;
        sectionList=new ArrayList<>();
        preferenceList=new ArrayList<>();
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

    public String getNationality() {
        return nationality;
    }

    public List<StudentSection> getSectionList() {
        return sectionList;
    }

    public List<Preference> getPreferenceList() {
        return preferenceList;
    }

    public Professor getAdvisor() {
        return advisor;
    }

    public String getEmail() {
        return email;
    }

    public String getTrack() {
        return track;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public LocalDate getEntry() {
        return entry;
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

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setSection(StudentSection stdSection, Section section) {
        sectionList.add(stdSection);
        stdSection.setStudent(this);
        stdSection.setSection(section);
    }

    public void setPreferenceList(Preference preference) {
        this.getPreferenceList().add(preference);}

    public void setAdvisor(Professor advisor) {
        this.advisor = advisor;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setEntry(LocalDate entry) {
        this.entry = entry;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nationality='" + nationality + '\'' +
                ", email='" + email + '\'' +
                ", track='" + track + '\'' +
                ", entry=" + entry +
                '}';
    }
}
