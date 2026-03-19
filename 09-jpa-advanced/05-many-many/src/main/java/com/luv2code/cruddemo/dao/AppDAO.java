package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import com.luv2code.cruddemo.entity.Student;

import java.util.List;

public interface AppDAO {
    void save(Instructor instructor);
    Instructor findInstructorById(int id);
    void deleteInstructorById(int id);

    InstructorDetail findInstructorDetailById(int id);
    void deleteInstructorAndDetailsById(int id);
    List<Course> findCoursesByInstructorId(int instructorId);
    Instructor findInstructorByIdJoinFetch(int instructorId);

    void updateInstructor(Instructor instructor);
    void updateCourse(Course course);
    Course findCourseById(int courseId);

    void deleteInstructor(int instructorId);
    void deleteCourse(int courseId);


    // one-to-many uni relationships
    void save(Course course); //this method is used for many-many save also.
    Course findCourseAndReviewsByCourseId(int courseId);

    // many-many
    Course findCourseAndStudentsByCourseId(int courseId);
    Student findStudentAndCoursesByStudentId(int studentId);
    void update(Student student);
    void deleteStudentById(int studentId);

}
