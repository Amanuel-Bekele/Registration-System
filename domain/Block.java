package com.waaproject.registrationsystem.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "blocks")
public class Block implements Serializable {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer id;
    //Changed from localdate to string as in class diagram..
    private String year;

    private String month;

    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "block")
    private List<Section> sectionList;

    //This is an enabler entity for hibernate to create block Id column in preference.
    //It therefore does not imply any direct association between these two classes.
    @OneToMany(mappedBy = "block")
    private List<Preference> preferenceList;

    //Default constructor
    public Block() {
    }

    // Constructor with all args except ID
    public Block(String year, String month, Section section) {
        this.year = year;
        this.month = month;
        sectionList = new ArrayList<>();
        sectionList.add(section);
        preferenceList=new ArrayList<>();
    }

    public Block(String year, String month) {
        this.year = year;
        this.month = month;
    }
    //Getters

    public Integer getId() {
        return id;
    }

    public String getYear() {
        return year;
    }

    public String getMonth() {
        return month;
    }

    public List<Section> getSectionList() {
        return sectionList;
    }

    //Setters

    public void setId(Integer id) {
        this.id = id;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void addSection(Section section) {
        sectionList.add(section);
    }

    @Override
    public String toString() {
        return ""+month+", "+year;
    }
}
