package com.waaproject.registrationsystem.validator;

import com.waaproject.registrationsystem.domain.Student;
import com.waaproject.registrationsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    private StudentService studentService;

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Student student = null;

        try {
            student = studentService.findByEmail(value);
        } catch (Exception e) {
            System.out.println("Exception occur in UniqueEmail Validation");

        }

        return student == null;
    }
}
