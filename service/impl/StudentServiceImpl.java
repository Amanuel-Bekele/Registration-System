package com.waaproject.registrationsystem.service.impl;

import com.waaproject.registrationsystem.domain.Role;
import com.waaproject.registrationsystem.domain.Student;
import com.waaproject.registrationsystem.domain.User;
import com.waaproject.registrationsystem.helper.EmailSender;
import com.waaproject.registrationsystem.helper.PasswordGenerator;
import com.waaproject.registrationsystem.repository.RoleRepository;
import com.waaproject.registrationsystem.repository.StudentRepository;
import com.waaproject.registrationsystem.repository.UserRepository;
import com.waaproject.registrationsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private EmailSender emailSender;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, EmailSender emailSender) {
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailSender = emailSender;
    }

    @Override
    public Student save(Student student) {
        studentRepository.save(student);
        Role role = roleRepository.findByName("STUDENT");
        String newPassword = new PasswordGenerator().nextString();

        System.out.println(role);
        System.out.println(newPassword);
        User newUser = new User(student.getEmail(),passwordEncoder.encode(newPassword),true,student.getEmail());
        newUser.setRole(role);

        userRepository.save(newUser);

        sendUserCreationEmail(student,newPassword);

        return student;
    }

    @Override
    public List<Student> getAll() {
        return (List<Student>) studentRepository.findAll();
    }

    @Override
    public Student findById(Integer id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Student> getAllPaginated(Pageable pageable) {

        return studentRepository.findAll(pageable);
    }

    @Override
    public Student update(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student findByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    private void sendUserCreationEmail(Student student,String password){
        String message = "Your student account is created with username: "+student.getEmail()+" and password: "+password;
        String subject = "Your student account created";

        emailSender.sendUserCreationEmail(student.getEmail(),subject, message);
    }

    public Student getByEmail(String email){
        return studentRepository.findStudentByEmail(email);

    }
}
