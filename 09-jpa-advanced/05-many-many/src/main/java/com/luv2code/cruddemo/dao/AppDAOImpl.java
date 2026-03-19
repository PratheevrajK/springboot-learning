package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO {

    private EntityManager entityManager;

    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        entityManager.persist(instructor);
    }

    @Override
    public Instructor findInstructorById(int id) {
        return entityManager.find(Instructor.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        Instructor ins = findInstructorById(id);
        entityManager.remove(ins);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        return entityManager.find(InstructorDetail.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorAndDetailsById(int id) {
        InstructorDetail instructorDetail = findInstructorDetailById(id);
        entityManager.remove(instructorDetail);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int instructorId) {
        TypedQuery<Course> query = entityManager.createQuery("SELECT c FROM Course c where instructor.id = :data", Course.class);
        query.setParameter("data", instructorId);

        List<Course> courses = query.getResultList();
        return courses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int instructorId) {
        TypedQuery<Instructor> query = entityManager.createQuery(
                "SELECT i FROM Instructor i " +
                        "JOIN FETCH i.courses " +
                        "JOIN FETCH i.instructorDetail " + //Query works without instructorDetail join as well, but this will minimize the two separate query fetch.
                        "WHERE i.id = :data", Instructor.class);
        query.setParameter("data", instructorId);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void updateInstructor(Instructor instructor) {
        entityManager.merge(instructor);
    }

    @Override
    @Transactional
    public void updateCourse(Course course) {
        entityManager.merge(course);
    }

    @Override
    public Course findCourseById(int courseId) {
        return entityManager.find(Course.class, courseId);
    }

    @Override
    @Transactional
    public void deleteInstructor(int instructorId) {
        Instructor ins = findInstructorById(instructorId);
        System.out.println("Instructor: " + ins);
        List<Course> courses = ins.getCourses();
        for (Course course : courses) {
            course.setInstructor(null);
        }
        entityManager.remove(ins);
    }

    @Override
    @Transactional
    public void deleteCourse(int courseId) {
        Course course = findCourseById(courseId);
        entityManager.remove(course);
    }

    // one-to-many uni relationships
    @Override
    @Transactional
    public void save(Course course) {
        entityManager.persist(course);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int courseId) {
        TypedQuery<Course> query = entityManager.createQuery(
                "SELECT c FROM Course c " +
                        "JOIN FETCH c.reviews " +
                        "WHERE c.id = :data", Course.class
        );
        query.setParameter("data", courseId);
        Course course = query.getSingleResult();
        return course;
    }

    @Override
    public Course findCourseAndStudentsByCourseId(int courseId) {
        TypedQuery<Course> query = entityManager.createQuery(
                "SELECT c FROM Course c " +
                        "JOIN FETCH c.students " +
                        "WHERE c.id = :data", Course.class
        );
        query.setParameter("data", courseId);
        Course course = query.getSingleResult();
        return course;
    }

    @Override
    public Student findStudentAndCoursesByStudentId(int studentId) {
        TypedQuery<Student> query = entityManager.createQuery(
                "SELECT s FROM Student s " +
                        "JOIN FETCH s.courses " +
                        "WHERE s.id = :data", Student.class
        );
        query.setParameter("data", studentId);
        Student student = query.getSingleResult();
        return student;
    }

    @Override
    @Transactional
    public void update(Student student) {
        entityManager.merge(student);
    }

    @Override
    @Transactional
    public void deleteStudentById(int studentId) {
        Student student = entityManager.find(Student.class, studentId);
        if (student != null) {
            List<Course> courses = student.getCourses();
            // break association of all courses for the student
            for(Course course : courses) {
                course.getStudents().remove(student);
            }
            entityManager.remove(student);
        }
    }
}
