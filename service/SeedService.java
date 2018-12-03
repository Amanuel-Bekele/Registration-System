package com.waaproject.registrationsystem.service;

import com.waaproject.registrationsystem.config.HibernateUtil;
import com.waaproject.registrationsystem.domain.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;

@Service
@Transactional
public class SeedService {

    private final PasswordEncoder encoder;

    private SessionFactory factory = HibernateUtil.getSessionFactory();

    @Autowired
    public SeedService(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    /**
     * Creates two users with two roles
     * Adds them to the db
     * returns List containing the primary keys of the created users.
     *
     * @return
     */

    public List<Integer> loadUserData() {

        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        User studentUser = new User("am.bekele@mum.edu", encoder.encode("fun123"), true, "Amanuel");

        User adminUser = new User("ashok@mum.edu", encoder.encode("fun123"), true, "Ashok");


        List<Integer> roles = this.getRoles();

        Role student = session.get(Role.class, roles.get(0));

        Role admin = session.get(Role.class, roles.get(1));

        studentUser.setRole(student);
        adminUser.setRole(admin);

        int stdUserId = (Integer) session.save(studentUser);
        int adminUserId = (Integer) session.save(adminUser);

        tx.commit();

        return Arrays.asList(stdUserId, adminUserId);
    }

    private List<Integer> getRoles() {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        Role student = new Role("STUDENT");

        Role admin = new Role("ADMIN");

        int stdId = (Integer) session.save(student);

        int adminId = (Integer) session.save(admin);

        tx.commit();

        return Arrays.asList(stdId, adminId);
    }


    private List<Integer> profKeys = new ArrayList<>();
    private List<Integer> courseKeys = new ArrayList<>();
    private List<Integer> blockKeys = new ArrayList<>();
    private List<Integer> studentKeys = new ArrayList<>();
    private List<Integer> sectionKeys = new ArrayList<>();
    private List<Integer> prereqKeys = new ArrayList<>();

    /**
     * Method to add the data
     */

    public void loadData() {
        profKeys = this.createProfessors();
        courseKeys = this.createCourses();
        blockKeys = this.createBlocks();
        studentKeys = this.createStudents();
        sectionKeys = this.createSections();
        prereqKeys = this.createPreReq();


        /**
         * Method calls to add relationships
         */

        block_sections();
        professor_sections();
        section_students();


    }

    /**
     * Creates Professor Objects
     * Add to db
     *
     * @return List of primary keys of added Prof Objects.
     */
    private List<Integer> createProfessors() {
        Session session = factory.openSession();

        Transaction tx = session.beginTransaction();


        Professor mwaProfessor = new Professor(
                "Asaad",
                "Saad",
                "Have been teaching here for 4 years",
                "Ms in CS"

        );

        Professor waaProfessor = new Professor(
                "Xing",
                "Xang",
                "Born and raised in China",
                "Ms in CS"

        );

        Professor mppProfessor = new Professor(
                "Paul",
                "Corazza",
                "Long time experience in Java development",
                "Phd in Information Theory"

        );

        Professor fppProfessor = new Professor(
                "Renuka",
                "Mohanraj",
                "Born and raised in India",
                "Ms in CS"

        );

        int mwaProfId = (Integer) session.save(mwaProfessor);
        int waaProfId = (Integer) session.save(waaProfessor);
        int mppProfId = (Integer) session.save(mppProfessor);
        int fppProfId = (Integer) session.save(fppProfessor);

        tx.commit();

        return Arrays.asList(mwaProfId, waaProfId, mppProfId, fppProfId);


    }

    /**
     * Creates Course Objects
     * Add to db
     *
     * @return List of primary keys of added Course Objects.
     */

    private List<Integer> createCourses() {
        Session session = factory.openSession();

        Transaction tx = session.beginTransaction();

        Course fppCourse = new Course(
                "FPP",
                "Fundamental of Programming",
                3d
        );
        Course mppCourse = new Course(
                "MPP",
                "Modern Programming Practices",
                4d
        );
        Course waaCourse = new Course(
                "WAA",
                "Web Application Architecture",
                5d
        );
        Course mwaCourse = new Course(
                "MWA",
                "Modern Web Applications",
                5d
        );

        int fppId = (Integer) session.save(fppCourse);
        int mppId = (Integer) session.save(mppCourse);
        int waaId = (Integer) session.save(waaCourse);
        int mwaId = (Integer) session.save(mwaCourse);

        tx.commit();

        return Arrays.asList(fppId, mppId, waaId, mwaId);
    }

    /**
     * Creates Block Objects
     * Add to db
     *
     * @return List of primary keys of added Block Objects.
     */
    private List<Integer> createBlocks() {
        Session session = factory.openSession();

        Transaction tx = session.beginTransaction();


        Block october = new Block(
                "2018",
                "October"
        );

        Block november = new Block(
                "2018",
                "November"
        );

        Block december = new Block(
                "2018",
                "December"
        );

        Block january = new Block(
                "2018",
                "January"
        );

        int octId = (Integer) session.save(october);
        int novId = (Integer) session.save(november);
        int decId = (Integer) session.save(december);
        int janId = (Integer) session.save(january);

        tx.commit();

        return Arrays.asList(octId, novId, decId, janId);

    }

    /**
     * Creates Student Objects
     * Add to db
     *
     * @return List of primary keys of added Student Objects.
     */

    private List<Integer> createStudents() {
        Session session = factory.openSession();

        Transaction tx = session.beginTransaction();

        Student stdAmanuel = new Student(
                "Amanuel",
                "Bekele",
                "Ethiopian",
                "am.bekele@mum.edu",
                LocalDate.now(),
                "FPP"
        );

        Student stdAshok = new Student(
                "Ashok",
                "Jackson",
                "Nepal",
                "ashok@mum.edu",
                LocalDate.now(),
                "MPP"
        );

        Student stdDeus = new Student(
                "Deus",
                "Johnatan",
                "Ugandan",
                "deus@mum.edu",
                LocalDate.now(),
                "MPP"
        );
        Student stdJohn = new Student(
                "John",
                "Doe",
                "UAA",
                "john.doe@mum.edu",
                LocalDate.now(),
                "FPP"
        );

        int amId = (Integer) session.save(stdAmanuel);
        int asId = (Integer) session.save(stdAshok);
        int duId = (Integer) session.save(stdDeus);
        int joId = (Integer) session.save(stdJohn);

        tx.commit();


        return Arrays.asList(amId, asId, duId, joId);
    }

    /**
     * Creates Section Objects
     * Add to db
     *
     * @return List of primary keys of added Section Objects.
     */

    private List<Integer> createSections() {

        Session session = factory.openSession();

        Transaction tx = session.beginTransaction();

        Block octBlock = session.get(Block.class, blockKeys.get(0));
        Block novBlock = session.get(Block.class, blockKeys.get(1));
        Block decBlock = session.get(Block.class, blockKeys.get(2));
        Block janBlock = session.get(Block.class, blockKeys.get(3));

        Course fpp = session.get(Course.class, courseKeys.get(0));
        Course mpp = session.get(Course.class, courseKeys.get(1));
        Course waa = session.get(Course.class, courseKeys.get(2));
        Course mwa = session.get(Course.class, courseKeys.get(0));

        Professor saad = session.get(Professor.class, profKeys.get(0));
        Professor xing = session.get(Professor.class, profKeys.get(1));
        Professor paul = session.get(Professor.class, profKeys.get(2));
        Professor renuka = session.get(Professor.class, profKeys.get(3));


        Section mwaOctSec = new Section(
                24,
                "Library",
                LocalDate.of(2018, 10, 6),
                LocalDate.of(2018, 11, 10),
                mwa,
                octBlock,
                saad
        );
        Section waaNovSec = new Section(
                30,
                "McLaughing",
                LocalDate.of(2018, 11, 14),
                LocalDate.of(2018, 12, 19),
                waa,
                novBlock,
                xing
        );
        Section fppDecSec = new Section(
                20,
                "Verill",
                LocalDate.of(2018, 12, 13),
                LocalDate.of(2019, 1, 18),
                fpp,
                decBlock,
                renuka
        );
        Section mppJanSec = new Section(
                30,
                "Library",
                LocalDate.of(2019, 1, 23),
                LocalDate.of(2019, 2, 26),
                mpp,
                janBlock,
                paul
        );


        int mwaOctId = (Integer) session.save(mwaOctSec);
        int waaNovId = (Integer) session.save(waaNovSec);
        int fppDecId = (Integer) session.save(fppDecSec);
        int mppJanId = (Integer) session.save(mppJanSec);

        tx.commit();


        return Arrays.asList(mwaOctId, waaNovId, fppDecId, mppJanId);
    }

    /**
     * Creates Prerequisite Objects
     * Add to db, Add prerequisite to course objects
     *
     * @return List of primary keys of added Prof Objects.
     */
    private List<Integer> createPreReq() {
        Session session = factory.openSession();

        Transaction tx = session.beginTransaction();


        // FPP is inserted. - Should have been wap object!
        Course wapCourse = session.get(Course.class, courseKeys.get(0));

//        wap.setCourseList(wapCourse);

        Course waa = session.get(Course.class, courseKeys.get(2));
//        waa.setPrerequisite(wap);

        Course mwa = session.get(Course.class, courseKeys.get(3));
//        mwa.setPrerequisite(wap);


        Prerequisite wap = new Prerequisite(wapCourse, waa, false, true);
        Prerequisite wap2 = new Prerequisite(wapCourse, mwa, false, true);
//        Prerequisite db = new Prerequisite(false, true);

        waa.setPrerequisite(wap);

        mwa.setPrerequisite(wap2);

        Integer wapPrereqId = (int) session.save(wap);

        tx.commit();

        return Arrays.asList(wapPrereqId);


    }

    /**
     * Retries Blocks from db
     * Adds sections to blocks
     */

    private void block_sections() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Block blockOct = session.get(Block.class, blockKeys.get(0));
        Block blockNov = session.get(Block.class, blockKeys.get(1));
        Block blockDec = session.get(Block.class, blockKeys.get(2));
        Block blockJan = session.get(Block.class, blockKeys.get(3));


        blockOct.addSection(session.get(Section.class, sectionKeys.get(0)));

        blockNov.addSection(session.get(Section.class, sectionKeys.get(1)));

        blockDec.addSection(session.get(Section.class, sectionKeys.get(2)));

        blockJan.addSection(session.get(Section.class, sectionKeys.get(3)));

        tx.commit();


    }

    /**
     * Retries Professors from db
     * Adds sections to professors
     */

    private void professor_sections() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction tx = session.beginTransaction();

        Professor saad = session.get(Professor.class, profKeys.get(0));
        Professor xing = session.get(Professor.class, profKeys.get(1));
        Professor paul = session.get(Professor.class, profKeys.get(2));
        Professor renuka = session.get(Professor.class, profKeys.get(3));

        saad.setSectionList(session.get(Section.class, sectionKeys.get(0)));
        xing.setSectionList(session.get(Section.class, sectionKeys.get(1)));
        paul.setSectionList(session.get(Section.class, sectionKeys.get(2)));
        renuka.setSectionList(session.get(Section.class, sectionKeys.get(3)));

        tx.commit();

    }

    /**
     * Deus know what exactly this code is doing!
     * <p>
     * Uncomment the lines for more data!
     */

    private void section_students() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction tx = session.beginTransaction();

        StudentSection stdSecOne = new StudentSection();

        StudentSection stdSecTwo = new StudentSection();
        StudentSection stdSecThree = new StudentSection();


//        StudentSectionService stdSecFour = new StudentSectionService();
//        StudentSectionService stdSecFive = new StudentSectionService();

        Student amaStudent = session.get(Student.class, studentKeys.get(0));
        Student ashStudent = session.get(Student.class, studentKeys.get(1));
        Student stdDues = session.get(Student.class, studentKeys.get(2));
//        Student stdJohn = session.get(Student.class, studentKeys.get(3));


        Section mwaOctSec = session.get(Section.class, sectionKeys.get(0));

        Section waaNovSec = session.get(Section.class, sectionKeys.get(1));

        Section fppDecSec = session.get(Section.class, sectionKeys.get(2));

//        Section mppJanSec = session.get(Section.class, sectionKeys.get(3));


        //StudentSectionService stdSecOne
        amaStudent.setSection(stdSecOne, mwaOctSec);
        mwaOctSec.addStudent(stdSecOne, amaStudent);


        ashStudent.setSection(stdSecTwo, waaNovSec);
        waaNovSec.addStudent(stdSecTwo, ashStudent);

        stdDues.setSection(stdSecThree, fppDecSec);
        fppDecSec.addStudent(stdSecThree, stdDues);


        tx.commit();


    }
}


