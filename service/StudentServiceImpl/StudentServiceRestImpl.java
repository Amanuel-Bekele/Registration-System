package com.waaproject.registrationsystem.service.StudentServiceImpl;

import com.waaproject.registrationsystem.business.*;
import com.waaproject.registrationsystem.domain.*;
import com.waaproject.registrationsystem.exception.BlockNotFoundException;
import com.waaproject.registrationsystem.exception.SectionNotFoundException;
import com.waaproject.registrationsystem.exception.StudentNotFoundException;
import com.waaproject.registrationsystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@Transactional
public class StudentServiceRestImpl implements IStudentService {

    private final StudentRepository studentRepository;

    private final PrerequisiteRepository prerequisiteRepository;

    private final SectionRepository sectionRepository;

    private final BlockRepository blockRepository;

    private final StudentSectionRepository studentSectionRepository;

    @Autowired
    public StudentServiceRestImpl(StudentRepository studentRepository, PrerequisiteRepository prerequisiteRepository,
                                  SectionRepository sectionRepository, BlockRepository blockRepository,
                                  StudentSectionRepository studentSectionRepository) {

        this.studentRepository = studentRepository;
        this.prerequisiteRepository = prerequisiteRepository;
        this.sectionRepository = sectionRepository;
        this.blockRepository = blockRepository;
        this.studentSectionRepository = studentSectionRepository;
    }

    @Override
    public List<AvailableCourse> getAvailableCourses(int stdId) {

        System.out.println(blockRepository.findByYearAndMonth("2018", "November").getId());

        Optional<Student> theStudent = studentRepository.findById(stdId);
        Student student;
        if (theStudent.isPresent()) {
            student = theStudent.get();
        } else throw new StudentNotFoundException("The Specified Student Does not exist!");

        String stdTrack = student.getTrack();
        int blockStartingFrom = 0;

        if (stdTrack.toUpperCase().equals("MPP"))
            blockStartingFrom = 2;
        else blockStartingFrom = 3;

        LocalDate stdEntryDate = student.getEntry();

        List<Block> blocks = new ArrayList<>();
        List<Block> all = blockRepository.findAll();


        for (int i = blockStartingFrom; i < blockStartingFrom + 4; i++) {
            try {
                System.out.println(i);
                Block theBlock = blockRepository.findByYearAndMonth(
                        String.valueOf(stdEntryDate.getYear()),
                        String.valueOf(stdEntryDate.getMonth().plus(i).getDisplayName(TextStyle.FULL,
                                Locale.US)));
                if (theBlock == null)
                    continue;
                    blocks.add(theBlock);

            } catch (NumberFormatException ex) {
                throw new NumberFormatException("Server Cannot fulfill the request");
            }
            if (blocks.size() == 0)
                throw new BlockNotFoundException("Can't Find any matching block.");
        }


        List<AvailableCourse> availableCourses = new ArrayList<>();


        for (Block b : all) {

            for (Section s : b.getSectionList()) {
                AvailableCourse course = new AvailableCourse();
                course.setBlockTime(s.getBlock().getMonth()+ ", " + s.getBlock().getYear() );

                int capacity = s.getCapacity();

                course.setCapacity(capacity);
                course.setCourseTitle(s.getCourse().getCourseName());
                course.setProfName(s.getProfessor().getLastName());

                int enrolled = countSection(s.getId());

                course.setEnrolled(enrolled);
                course.setAvailable(capacity - enrolled);
                availableCourses.add(course);


            }

        }

        return availableCourses;


    }


    private int countSection(int sectionId) {

        Optional<Section> section = sectionRepository.findById(sectionId);

        Section theSection;
        if (section.isPresent()) {
            theSection = section.get();
        } else throw new SectionNotFoundException("Can't find a section by id " + sectionId);

        List<StudentSection> result =
                studentSectionRepository.findBySection(theSection);

        return result.size();

    }

    @Override
    public List<EnrolledCourses> getEnrolledCourses(int id) {

        List<EnrolledCourses> enrolledCourses = new ArrayList<>();
        List<Section> sections = getSection(id);

        for (Section section : sections) {
            EnrolledCourses course = new EnrolledCourses();
            course.setBlockTime(section.getBlock().getMonth() + ", " + section.getBlock().getYear());
            course.setCourseCode(section.getCourse().getCourseCode());
            course.setCourseTitle(section.getCourse().getCourseName());
            course.setProfName(section.getProfessor().getLastName());
            enrolledCourses.add(course);
        }

        return enrolledCourses;

    }

    private List<Section> getSection(int studentId) {

        Student theStudent;

        Optional<Student> student =
                studentRepository.findById(studentId);

        if (student.isPresent())
            theStudent = student.get();
        else
            throw new StudentNotFoundException("Student cannot be found");

        List<StudentSection> enrollements =
                studentSectionRepository.findByStudentAndIsWaivedFalse(theStudent);


        List<Section> sections = new ArrayList<>();

        for (StudentSection s : enrollements) {
            sections.add(s.getSection());
        }
        return sections;


    }


    @Override
    public List<PreWaiver> getRequiredPrerequisite() {
        List<PreWaiver> required = new ArrayList<>();

        for (Course c : getRequired()) {
            PreWaiver prerequisite = new PreWaiver();
            prerequisite.setCourseName(c.getCourseDesc());
            prerequisite.setCourseCode(c.getCourseCode());
            required.add(prerequisite);
        }
        return required;

    }

    private List<Course> getRequired() {

        List<Course> courses = new ArrayList<>();
        List<Prerequisite> prerequisites =
                prerequisiteRepository.findAll();
        for (Prerequisite p: prerequisites)
            courses.add(p.getPrerequisite());
            return courses;
    }

    @Override
    public List<PreWaiver> getApprovedWaivers(int id) {
        List<PreWaiver> approvedWaiver = new ArrayList<>();

        Optional<Student> theStudent = studentRepository.findById(id);
        Student student;
        if (theStudent.isPresent()) {
            student = theStudent.get();
        } else {
            throw new StudentNotFoundException("No Student with this id");
        }

        List<StudentSection> studentSections =
                studentSectionRepository.getWaived(student, true);

        for (StudentSection s : studentSections) {
            approvedWaiver.add(new PreWaiver(s.getStudent().getId(), s.getSection().getCourse().getCourseDesc()));
        }

        return approvedWaiver;

    }


    @Override
    public List<AvailableCourse> getPreferences(int stdId) {
        Optional<Student> theStudent = studentRepository.findById(stdId);
        Student student;
        if (theStudent.isPresent()) {
            student = theStudent.get();
        } else {
            throw new StudentNotFoundException("No Student with this id");
        }

        if (student.getPreferenceList() == null || student.getPreferenceList().size() == 0) {
//            return this.getAvailableCourses(stdId);
        }

        List<AvailableCourse> availableCourses = new ArrayList<>();

        for (Preference p : student.getPreferenceList()) {
            AvailableCourse course = new AvailableCourse();
            Section theSection = p.getSection();

            int enrolled = countSection(theSection.getId());
            course.setAvailable(theSection.getCapacity() - enrolled);

            course.setCapacity(theSection.getCapacity());
            course.setCourseTitle(theSection.getCourse().getCourseName());
            course.setProfName(theSection.getProfessor().getLastName());

            availableCourses.add(course);


        }


        return availableCourses;


    }
    public void addPreference(int stdId, List<PreferenceBusiness> preferences){
//
//        Optional<Student> theStudent = studentRepository.findById(stdId);
//        Student student;
//        if (theStudent.isPresent()) {
//            student = theStudent.get();
//        } else {
//            throw new StudentNotFoundException("No Student with this id");
//        }
//
//
//        for (PreferenceBusiness business : preferences){
//
//        }
    }



    public List<CourseAvailable> getCoursesAvailable(int stdId) {

        System.out.println(blockRepository.findByYearAndMonth("2018", "November").getId());

        Optional<Student> theStudent = studentRepository.findById(stdId);
        Student student;
        if (theStudent.isPresent()) {
            student = theStudent.get();
        } else throw new StudentNotFoundException("The Specified Student Does not exist!");

        String stdTrack = student.getTrack();
        int blockStartingFrom = 0;

        if (stdTrack.toUpperCase().equals("MPP"))
            blockStartingFrom = 2;
        else blockStartingFrom = 3;

        LocalDate stdEntryDate = student.getEntry();

        String entryMonth = student.getEntry().getMonth().name();
        String entryYear = Integer.toString(student.getEntry().getYear());

        List<Block> blocks = blockRepository.getUpcomingBlocks(entryMonth, entryYear );
        List<Block> all = blockRepository.findAll();

        System.out.println("The Size of Blocks form Query : "+blocks.size());

        // Ashok
        List<CourseAvailable> courseAvailables = new ArrayList<>();

        for (Block b : blocks) {
            List<CourseInBlock> courseInBlocks = new ArrayList<>();

            for (Section s : b.getSectionList()) {
                AvailableCourse course = new AvailableCourse();
                course.setBlockTime(s.getBlock().getMonth()+ ", " + s.getBlock().getYear() );

                int capacity = s.getCapacity();

                course.setCapacity(capacity);
                course.setCourseTitle(s.getCourse().getCourseName());
                course.setProfName(s.getProfessor().getLastName());

                int enrolled = countSection(s.getId());

                course.setEnrolled(enrolled);
                course.setAvailable(capacity - enrolled);


                // Ashok
                CourseInBlock courseInBlock = new CourseInBlock();
                courseInBlock.setCapacity(capacity);
                courseInBlock.setCourseCode(s.getCourse().getCourseCode());
                courseInBlock.setCourseTitle(s.getCourse().getCourseName());
                courseInBlock.setProfName(s.getProfessor().getLastName());
                courseInBlock.setEnrolled(enrolled);
                courseInBlock.setAvailable(capacity - enrolled);
                courseInBlock.setSection(s.getId());
                courseInBlocks.add(courseInBlock);

            }
            CourseAvailable courseAvailable = new CourseAvailable();
            courseAvailable.setBlock(b.getMonth()+", "+b.getYear());
            courseAvailable.setBlockCourses(courseInBlocks);

            courseAvailables.add(courseAvailable);
        }

        return courseAvailables;

    }



}
