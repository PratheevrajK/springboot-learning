package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	// Executed after the Springs Beans have been loaded.
    @Bean
    public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
        return runner -> {
//            createStudent(studentDAO);
            createMultipleStudent(studentDAO);
//            Student myStudent = fetchStudentsById(studentDAO, 2);
//            System.out.println(myStudent); //Student{id=2, firstName='John', lastName='Cena', email='john.cena@email.com'}
//            fetchAllStudents(studentDAO);
//            fetchStudentsByLastName(studentDAO, "Doe");
//            updateStudent(studentDAO);
//            deleteStudent(studentDAO, 4);
//            deleteAll(studentDAO);
        };
    }

    private void deleteAll(StudentDAO studentDAO) {
        int deleteCount = studentDAO.deleteAll();
        System.out.println("No. of rows deleted " + deleteCount); // No. of rows deleted 4
    }

    private void deleteStudent(StudentDAO studentDAO, int id) {
        Student student = fetchStudentsById(studentDAO, id);
        System.out.println("Before delete " + student);// Before delete Student{id=4, firstName='Chris', lastName='Gayle', email='abc.gayle@email.com'}
        studentDAO.delete(id);
        System.out.println("After delete " + fetchStudentsById(studentDAO, id));// After delete null
    }

    private void updateStudent(StudentDAO studentDAO) {
        Student student = fetchStudentsById(studentDAO, 5);
        System.out.println("Before update " + student); //Before update Student{id=5, firstName='Jonita', lastName='Gandhi', email='joni.g@email.com'}
        student.setLastName("Garg");
        studentDAO.update(student);
        System.out.println("After update " + fetchStudentsById(studentDAO, 5)); //After update Student{id=5, firstName='Jonita', lastName='Garg', email='joni.g@email.com'}
    }

    private void fetchStudentsByLastName(StudentDAO studentDAO, String lastName) {
        List<Student> students = studentDAO.findByLastName(lastName);
        for (Student s : students) {
            System.out.println(s);
        }
    }

    private void fetchAllStudents(StudentDAO studentDAO) {
        List<Student> students = studentDAO.findAll();
        for (Student s : students) {
            System.out.println(s);
        }
    }

    private Student fetchStudentsById(StudentDAO studentDAO, int id) {
        return studentDAO.findById(id);
    }

    private void createMultipleStudent(StudentDAO studentDAO) {
        Student s1 = new Student("John", "Doe", "john.cena@email.com");
        Student s2 = new Student("Chris", "Gayle", "abc.gayle@email.com");
        Student s3 = new Student("Jonita", "Gandhi", "joni.g@email.com");
        studentDAO.save(s1);
        studentDAO.save(s2);
        studentDAO.save(s3);
        System.out.println("Students added!");
    }

    public void createStudent(StudentDAO studentDAO) {
        Student s1 = new Student("John", "Cena", "john.cena@email.com");
        studentDAO.save(s1);
        System.out.println("Student added. Generated id: " + s1.getId());
    }

}
