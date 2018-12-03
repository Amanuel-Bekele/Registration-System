package com.waaproject.registrationsystem.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sections")
public class Section implements Serializable {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer id;

    private Integer capacity;

    private String location;

    @Column(name = "start_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @Column(name = "end_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "block_id")
    private Block block;

    // Removed section_student direct many to many relation and changed to helper class, i.e StudentSectionService
//    @ManyToMany(cascade=CascadeType.ALL)
//    @JoinTable(name="sections_students", joinColumns=@JoinColumn(name="section_id"),
//            inverseJoinColumns=@JoinColumn(name="student_it"))
    @OneToMany(mappedBy = "section")
    private List<StudentSection> studentList;

    @OneToMany(mappedBy = "section")
    private List<Preference> preferenceList;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

    //Constructors
    public Section() {
    }

    public Section(Integer capacity, String location, LocalDate startDate, LocalDate endDate, Course course, Block block, Professor professor) {
        this.capacity = capacity;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.course = course;
        this.block = block;
        this.professor=professor;
        studentList=new ArrayList<>();
        preferenceList= new ArrayList<>();
    }

    //Getters

    public Integer getId() {
        return id;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public String getLocation() {
        return location;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Course getCourse() {
        return course;
    }

    public Block getBlock() {
        return block;
    }

    public Professor getProfessor() {
        return professor;
    }

    public List<StudentSection> getStudentList(){return studentList;}

    public List<Preference> getPreferenceList() {
        return preferenceList;
    }
    //setters

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public void addStudent(StudentSection stdSection, Student student) {
        studentList.add(stdSection);
        stdSection.setSection(this);
        stdSection.setStudent(student);
    }

    public void setPreferenceList(Preference preference) {
        preferenceList.add(preference);
    }
}
