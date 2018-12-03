package com.waaproject.registrationsystem.service.StudentServiceImpl;

import com.waaproject.registrationsystem.business.*;

import java.util.List;

public interface IStudentService {

    List<AvailableCourse> getAvailableCourses(int stdId);

    List<EnrolledCourses> getEnrolledCourses(int id);

    List<PreWaiver> getRequiredPrerequisite();

    List<PreWaiver> getApprovedWaivers(int id);

    List<AvailableCourse> getPreferences(int stdId);

    void addPreference(int stdId, List<PreferenceBusiness> preferences);

    List<CourseAvailable> getCoursesAvailable(int stdId);
}
