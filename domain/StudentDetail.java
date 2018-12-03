package com.waaproject.registrationsystem.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "studentdetails")
public class StudentDetail implements Serializable {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer id;
    @Transient
    private Professor advisor;
    private String email;
    private String track;

    @Column(name = "image_url")
    private String imageUrl;
    private LocalDate entry;

    //Constructors


    public StudentDetail() {
    }

    public StudentDetail(String email, LocalDate entry, String track, String imageUrl) {
        this.email = email;
        this.track = track;
        this.imageUrl = imageUrl;
        this.entry=entry;
    }

    //Getters

    public Integer getId() {
        return id;
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
}
