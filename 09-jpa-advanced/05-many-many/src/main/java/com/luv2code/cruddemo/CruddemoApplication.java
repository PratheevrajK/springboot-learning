package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

    // Executed after the Springs Beans have been loaded.
    @Bean
    public CommandLineRunner commandLineRunner(AppDAO appDAO) {
        return runner -> {
//            createCourseAndStudents(appDAO);
//            retrieveCourseAndStudents(appDAO, 10);
//            retrieveStudentAndCourses(appDAO, 1);
//            addMoreCoursesForStudent(appDAO, 1);
            deleteCourse(appDAO, 12); // existing method is reused. //Deleting the course will delete the related entry in join table as well.
//            deleteStudent(appDAO, 2); //Deleting the student will delete the related entry in join table as well.
        };
    }

    private void deleteStudent(AppDAO appDAO, int studentId) {
        System.out.println("Deleting student with id: " + studentId);
        appDAO.deleteStudentById(studentId);
        System.out.println("Done!");
        //Deleting student with id: 2
        //2026-03-19T13:36:52.279+05:30 DEBUG 26060 --- [cruddemo] [           main] org.hibernate.SQL                        : select s1_0.id,s1_0.email,s1_0.first_name,s1_0.last_name from student s1_0 where s1_0.id=?
        //2026-03-19T13:36:52.294+05:30 TRACE 26060 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [2]
        //2026-03-19T13:36:52.348+05:30 DEBUG 26060 --- [cruddemo] [           main] org.hibernate.SQL                        : select c1_0.student_id,c1_1.id,i1_0.id,i1_0.email,i1_0.first_name,id1_0.id,id1_0.hobby,id1_0.youtube_channel,i1_0.last_name,c1_1.title from course_student c1_0 join course c1_1 on c1_1.id=c1_0.course_id left join instructor i1_0 on i1_0.id=c1_1.instructor_id left join instructor_detail id1_0 on id1_0.id=i1_0.instructor_detail_id where c1_0.student_id=?
        //2026-03-19T13:36:52.350+05:30 TRACE 26060 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [2]
        //2026-03-19T13:36:52.396+05:30 DEBUG 26060 --- [cruddemo] [           main] org.hibernate.SQL                        : delete from student where id=?
        //2026-03-19T13:36:52.397+05:30 TRACE 26060 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [2]
        //Done!
    }

    private void addMoreCoursesForStudent(AppDAO appDAO, int studentId) {
        Student student = appDAO.findStudentAndCoursesByStudentId(studentId);

        Course c1 = new Course("Rubik's cube - How to speed cube");
        Course c2 = new Course("Atari 2600 - Game Development");
        student.addCourse(c1);
        student.addCourse(c2);

        System.out.println("Updating students: " + student);
        System.out.println("Associated courses: " + student.getCourses());
        appDAO.update(student);
        System.out.println("Done!");
        //2026-03-19T13:18:11.894+05:30 DEBUG 33848 --- [cruddemo] [           main] org.hibernate.SQL                        : select s1_0.id,c1_0.student_id,c1_1.id,c1_1.instructor_id,c1_1.title,s1_0.email,s1_0.first_name,s1_0.last_name from student s1_0 join course_student c1_0 on s1_0.id=c1_0.student_id join course c1_1 on c1_1.id=c1_0.course_id where s1_0.id=?
        //2026-03-19T13:18:11.920+05:30 TRACE 33848 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [1]
        //Updating students: Student{id=1, first_name='John', last_name='Cena', email='j.c@email.com'}
        //Associated courses: [Course{id=10, title='Pacman - How to Score 1 Million points'}, Course{id=0, title='Rubik's cube - How to speed cube'}, Course{id=0, title='Atari 2600 - Game Development'}]
        //2026-03-19T13:18:12.013+05:30 DEBUG 33848 --- [cruddemo] [           main] org.hibernate.SQL                        : select s1_0.id,s1_0.email,s1_0.first_name,s1_0.last_name,c1_0.student_id,c1_1.id,i1_0.id,i1_0.email,i1_0.first_name,i1_0.instructor_detail_id,i1_0.last_name,c1_1.title from student s1_0 left join course_student c1_0 on s1_0.id=c1_0.student_id left join course c1_1 on c1_1.id=c1_0.course_id left join instructor i1_0 on i1_0.id=c1_1.instructor_id where s1_0.id=?
        //2026-03-19T13:18:12.014+05:30 TRACE 33848 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [1]
        //2026-03-19T13:18:12.023+05:30 DEBUG 33848 --- [cruddemo] [           main] org.hibernate.SQL                        : select s1_0.course_id,s1_1.id,s1_1.email,s1_1.first_name,s1_1.last_name from course_student s1_0 join student s1_1 on s1_1.id=s1_0.student_id where s1_0.course_id=?
        //2026-03-19T13:18:12.024+05:30 TRACE 33848 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [10]
        //2026-03-19T13:18:12.028+05:30 DEBUG 33848 --- [cruddemo] [           main] org.hibernate.SQL                        : select r1_0.course_id,r1_0.id,r1_0.comment from review r1_0 where r1_0.course_id=?
        //2026-03-19T13:18:12.030+05:30 TRACE 33848 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [10]
        //2026-03-19T13:18:12.057+05:30 DEBUG 33848 --- [cruddemo] [           main] org.hibernate.SQL                        : insert into course (instructor_id,title) values (?,?)
        //2026-03-19T13:18:12.060+05:30 TRACE 33848 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [null]
        //2026-03-19T13:18:12.061+05:30 TRACE 33848 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (2:VARCHAR) <- [Rubik's cube - How to speed cube]
        //2026-03-19T13:18:12.106+05:30 DEBUG 33848 --- [cruddemo] [           main] org.hibernate.SQL                        : insert into course (instructor_id,title) values (?,?)
        //2026-03-19T13:18:12.107+05:30 TRACE 33848 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [null]
        //2026-03-19T13:18:12.107+05:30 TRACE 33848 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (2:VARCHAR) <- [Atari 2600 - Game Development]
        //2026-03-19T13:18:12.231+05:30 DEBUG 33848 --- [cruddemo] [           main] org.hibernate.SQL                        : insert into course_student (course_id,student_id) values (?,?)
        //2026-03-19T13:18:12.232+05:30 TRACE 33848 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [11]
        //2026-03-19T13:18:12.232+05:30 TRACE 33848 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (2:INTEGER) <- [1]
        //2026-03-19T13:18:12.236+05:30 DEBUG 33848 --- [cruddemo] [           main] org.hibernate.SQL                        : insert into course_student (course_id,student_id) values (?,?)
        //2026-03-19T13:18:12.237+05:30 TRACE 33848 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [12]
        //2026-03-19T13:18:12.238+05:30 TRACE 33848 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (2:INTEGER) <- [1]
        //Done!
    }

    private void retrieveStudentAndCourses(AppDAO appDAO, int studentId) {
        Student student = appDAO.findStudentAndCoursesByStudentId(studentId);
        System.out.println("Fetched student: " + student);
        System.out.println("Fetched courses: " + student.getCourses());
        //2026-03-19T13:12:11.565+05:30 DEBUG 31524 --- [cruddemo] [           main] org.hibernate.SQL                        : select s1_0.id,c1_0.student_id,c1_1.id,c1_1.instructor_id,c1_1.title,s1_0.email,s1_0.first_name,s1_0.last_name from student s1_0 join course_student c1_0 on s1_0.id=c1_0.student_id join course c1_1 on c1_1.id=c1_0.course_id where s1_0.id=?
        //2026-03-19T13:12:11.582+05:30 TRACE 31524 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [1]
        //Fetched student: Student{id=1, first_name='John', last_name='Cena', email='j.c@email.com'}
        //Fetched courses: [Course{id=10, title='Pacman - How to Score 1 Million points'}]
    }

    private void retrieveCourseAndStudents(AppDAO appDAO, int courseId) {
        Course course = appDAO.findCourseAndStudentsByCourseId(courseId);
        System.out.println("Fetched course: " + course);
        System.out.println("Fetched students: " + course.getStudents());
        //2026-03-19T13:07:52.356+05:30 DEBUG 30872 --- [cruddemo] [           main] org.hibernate.SQL                        : select c1_0.id,c1_0.instructor_id,s1_0.course_id,s1_1.id,s1_1.email,s1_1.first_name,s1_1.last_name,c1_0.title from course c1_0 join course_student s1_0 on c1_0.id=s1_0.course_id join student s1_1 on s1_1.id=s1_0.student_id where c1_0.id=?
        //2026-03-19T13:07:52.378+05:30 TRACE 30872 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [10]
        //Fetched course: Course{id=10, title='Pacman - How to Score 1 Million points'}
        //Fetched students: [Student{id=1, first_name='John', last_name='Cena', email='j.c@email.com'}, Student{id=2, first_name='Randy', last_name='Ortan', email='randy.o@email.com'}]
    }

    private void createCourseAndStudents(AppDAO appDAO) {
        Course course = new Course("Pacman - How to Score 1 Million points");
        Student s1 = new Student("John", "Cena", "j.c@email.com");
        Student s2 = new Student("Randy", "Ortan", "randy.o@email.com");
        course.addStudent(s1);
        course.addStudent(s2);
        System.out.println("Saving the course:" + course);
        System.out.println("Associated students:" + course.getStudents());
        appDAO.save(course);
        System.out.println("Done!");
        //Saving the course:Course{id=0, title='Pacman - How to Score 1 Million points'}
        //Associated students:[Student{id=0, first_name='John', last_name='Cena', email='j.c@email.com'}, Student{id=0, first_name='Randy', last_name='Ortan', email='randy.o@email.com'}]
        //2026-03-19T13:02:15.261+05:30 DEBUG 16556 --- [cruddemo] [           main] org.hibernate.SQL                        : insert into course (instructor_id,title) values (?,?)
        //2026-03-19T13:02:15.276+05:30 TRACE 16556 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [null]
        //2026-03-19T13:02:15.277+05:30 TRACE 16556 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (2:VARCHAR) <- [Pacman - How to Score 1 Million points]
        //2026-03-19T13:02:15.327+05:30 DEBUG 16556 --- [cruddemo] [           main] org.hibernate.SQL                        : insert into student (email,first_name,last_name) values (?,?,?)
        //2026-03-19T13:02:15.328+05:30 TRACE 16556 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:VARCHAR) <- [j.c@email.com]
        //2026-03-19T13:02:15.328+05:30 TRACE 16556 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (2:VARCHAR) <- [John]
        //2026-03-19T13:02:15.328+05:30 TRACE 16556 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (3:VARCHAR) <- [Cena]
        //2026-03-19T13:02:15.331+05:30 DEBUG 16556 --- [cruddemo] [           main] org.hibernate.SQL                        : insert into student (email,first_name,last_name) values (?,?,?)
        //2026-03-19T13:02:15.332+05:30 TRACE 16556 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:VARCHAR) <- [randy.o@email.com]
        //2026-03-19T13:02:15.332+05:30 TRACE 16556 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (2:VARCHAR) <- [Randy]
        //2026-03-19T13:02:15.332+05:30 TRACE 16556 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (3:VARCHAR) <- [Ortan]
        //2026-03-19T13:02:15.354+05:30 DEBUG 16556 --- [cruddemo] [           main] org.hibernate.SQL                        : insert into course_student (course_id,student_id) values (?,?)
        //2026-03-19T13:02:15.355+05:30 TRACE 16556 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [10]
        //2026-03-19T13:02:15.355+05:30 TRACE 16556 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (2:INTEGER) <- [1]
        //2026-03-19T13:02:15.358+05:30 DEBUG 16556 --- [cruddemo] [           main] org.hibernate.SQL                        : insert into course_student (course_id,student_id) values (?,?)
        //2026-03-19T13:02:15.359+05:30 TRACE 16556 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [10]
        //2026-03-19T13:02:15.359+05:30 TRACE 16556 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (2:INTEGER) <- [2]
        //Done!
    }

    private void deleteCourseAndReviews(AppDAO appDAO, int courseId) {
        System.out.println("Deleting course with id: " + courseId);
        appDAO.deleteCourse(courseId);
        System.out.println("Done!");
        //Deleting course with id: 12
        //2026-03-19T11:19:14.865+05:30 DEBUG 21560 --- [cruddemo] [           main] org.hibernate.SQL                        : select c1_0.id,i1_0.id,i1_0.email,i1_0.first_name,id1_0.id,id1_0.hobby,id1_0.youtube_channel,i1_0.last_name,c1_0.title from course c1_0 left join instructor i1_0 on i1_0.id=c1_0.instructor_id left join instructor_detail id1_0 on id1_0.id=i1_0.instructor_detail_id where c1_0.id=?
        //2026-03-19T11:19:14.922+05:30 TRACE 21560 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [12]
        //2026-03-19T11:19:15.051+05:30 DEBUG 21560 --- [cruddemo] [           main] org.hibernate.SQL                        : select r1_0.course_id,r1_0.id,r1_0.comment from review r1_0 where r1_0.course_id=?
        //2026-03-19T11:19:15.053+05:30 TRACE 21560 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [12]
        //2026-03-19T11:19:15.129+05:30 DEBUG 21560 --- [cruddemo] [           main] org.hibernate.SQL                        : update review set course_id=null where course_id=?
        //2026-03-19T11:19:15.135+05:30 TRACE 21560 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [12]
        //2026-03-19T11:19:15.154+05:30 DEBUG 21560 --- [cruddemo] [           main] org.hibernate.SQL                        : delete from review where id=?
        //2026-03-19T11:19:15.155+05:30 TRACE 21560 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [3]
        //2026-03-19T11:19:15.163+05:30 DEBUG 21560 --- [cruddemo] [           main] org.hibernate.SQL                        : delete from review where id=?
        //2026-03-19T11:19:15.164+05:30 TRACE 21560 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [4]
        //2026-03-19T11:19:15.168+05:30 DEBUG 21560 --- [cruddemo] [           main] org.hibernate.SQL                        : delete from review where id=?
        //2026-03-19T11:19:15.169+05:30 TRACE 21560 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [5]
        //2026-03-19T11:19:15.172+05:30 DEBUG 21560 --- [cruddemo] [           main] org.hibernate.SQL                        : delete from course where id=?
        //2026-03-19T11:19:15.173+05:30 TRACE 21560 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [12]
        //Done!
    }

    private void retrieveCourseAndReviews(AppDAO appDAO, int courseId) {
        Course course = appDAO.findCourseAndReviewsByCourseId(courseId);
        System.out.println("Fetched course: " + course);
        System.out.println("Fetched reviews: " + course.getReviews());
        //2026-03-19T11:14:22.533+05:30 DEBUG 7032 --- [cruddemo] [           main] org.hibernate.SQL                        : select c1_0.id,c1_0.instructor_id,r1_0.course_id,r1_0.id,r1_0.comment,c1_0.title from course c1_0 join review r1_0 on c1_0.id=r1_0.course_id where c1_0.id=?
        //2026-03-19T11:14:22.552+05:30 TRACE 7032 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [12]
        //Fetched course: Course{id=12, title='LEO'}
        //Fetched reviews: [Review{id=3, comment='crazy film!!!'}, Review{id=4, comment='LokiUniverse awesome!!!'}, Review{id=5, comment='average enemy character..'}]
    }

    private void createCourseAndReviews(AppDAO appDAO) {
        Course course = new Course("LEO");
        course.addReview(new Review("crazy film!!!"));
        course.addReview(new Review("LokiUniverse awesome!!!"));
        course.addReview(new Review("average enemy character.."));
        System.out.println("Saving the course...");
        System.out.println("course: " + course);
        System.out.println("reviews: " + course.getReviews());
        appDAO.save(course);
        System.out.println("Done!");
        //Saving the course...
        //course: Course{id=0, title='LEO'}
        //reviews: [Review{id=0, comment='crazy film!!!'}, Review{id=0, comment='LokiUniverse awesome!!!'}, Review{id=0, comment='average enemy character..'}]
        //2026-03-19T11:08:32.827+05:30 DEBUG 29756 --- [cruddemo] [           main] org.hibernate.SQL                        : insert into course (instructor_id,title) values (?,?)
        //2026-03-19T11:08:32.855+05:30 TRACE 29756 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [null]
        //2026-03-19T11:08:32.857+05:30 TRACE 29756 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (2:VARCHAR) <- [LEO]
        //2026-03-19T11:08:32.923+05:30 DEBUG 29756 --- [cruddemo] [           main] org.hibernate.SQL                        : insert into review (comment) values (?)
        //2026-03-19T11:08:32.924+05:30 TRACE 29756 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:VARCHAR) <- [crazy film!!!]
        //2026-03-19T11:08:32.928+05:30 DEBUG 29756 --- [cruddemo] [           main] org.hibernate.SQL                        : insert into review (comment) values (?)
        //2026-03-19T11:08:32.929+05:30 TRACE 29756 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:VARCHAR) <- [LokiUniverse awesome!!!]
        //2026-03-19T11:08:32.931+05:30 DEBUG 29756 --- [cruddemo] [           main] org.hibernate.SQL                        : insert into review (comment) values (?)
        //2026-03-19T11:08:32.933+05:30 TRACE 29756 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:VARCHAR) <- [average enemy character..]
        //2026-03-19T11:08:32.959+05:30 DEBUG 29756 --- [cruddemo] [           main] org.hibernate.SQL                        : update review set course_id=? where id=?
        //2026-03-19T11:08:32.960+05:30 TRACE 29756 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [12]
        //2026-03-19T11:08:32.961+05:30 TRACE 29756 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (2:INTEGER) <- [3]
        //2026-03-19T11:08:32.965+05:30 DEBUG 29756 --- [cruddemo] [           main] org.hibernate.SQL                        : update review set course_id=? where id=?
        //2026-03-19T11:08:32.966+05:30 TRACE 29756 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [12]
        //2026-03-19T11:08:32.966+05:30 TRACE 29756 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (2:INTEGER) <- [4]
        //2026-03-19T11:08:32.968+05:30 DEBUG 29756 --- [cruddemo] [           main] org.hibernate.SQL                        : update review set course_id=? where id=?
        //2026-03-19T11:08:32.969+05:30 TRACE 29756 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [12]
        //2026-03-19T11:08:32.969+05:30 TRACE 29756 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (2:INTEGER) <- [5]
        //Done!
    }

    private void deleteCourse(AppDAO appDAO, int courseId) {
        appDAO.deleteCourse(courseId);
        //2026-03-17T21:52:37.758+05:30 DEBUG 21324 --- [cruddemo] [           main] org.hibernate.SQL                        : select c1_0.id,i1_0.id,i1_0.email,i1_0.first_name,id1_0.id,id1_0.hobby,id1_0.youtube_channel,i1_0.last_name,c1_0.title from course c1_0 left join instructor i1_0 on i1_0.id=c1_0.instructor_id left join instructor_detail id1_0 on id1_0.id=i1_0.instructor_detail_id where c1_0.id=?
        //2026-03-17T21:52:37.799+05:30 TRACE 21324 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [10]
        //2026-03-17T21:52:37.924+05:30 DEBUG 21324 --- [cruddemo] [           main] org.hibernate.SQL                        : delete from course where id=?
        //2026-03-17T21:52:37.925+05:30 TRACE 21324 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [10]
        //2026-03-17T21:52:37.954+05:30 DEBUG 21324 --- [cruddemo] [           main] org.hibernate.SQL                        : select c1_0.id,i1_0.id,i1_0.email,i1_0.first_name,id1_0.id,id1_0.hobby,id1_0.youtube_channel,i1_0.last_name,c1_0.title from course c1_0 left join instructor i1_0 on i1_0.id=c1_0.instructor_id left join instructor_detail id1_0 on id1_0.id=i1_0.instructor_detail_id where c1_0.id=?
        //2026-03-17T21:52:37.956+05:30 TRACE 21324 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [11]
        //2026-03-17T21:52:37.959+05:30 DEBUG 21324 --- [cruddemo] [           main] org.hibernate.SQL                        : delete from course where id=?
        //2026-03-17T21:52:37.959+05:30 TRACE 21324 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [11]
    }

    private void deleteInstructor(AppDAO appDAO, int instructorId) {
        appDAO.deleteInstructor(instructorId);
        //2026-03-17T21:47:34.501+05:30 DEBUG 19036 --- [cruddemo] [           main] org.hibernate.SQL                        : select i1_0.id,i1_0.email,i1_0.first_name,id1_0.id,id1_0.hobby,id1_0.youtube_channel,i1_0.last_name from instructor i1_0 left join instructor_detail id1_0 on id1_0.id=i1_0.instructor_detail_id where i1_0.id=?
        //2026-03-17T21:47:34.517+05:30 TRACE 19036 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [1]
        //Instructor: Instructor{id=1, firstName='Joseph', lastName='VIJAY', email='vijay.j@gmail.com', instructorDetail=InstructorDetail{id=1, youtubeChannel='Thalapathi Vijay', hobby='Acting'}}
        //2026-03-17T21:47:34.562+05:30 DEBUG 19036 --- [cruddemo] [           main] org.hibernate.SQL                        : select c1_0.instructor_id,c1_0.id,c1_0.title from course c1_0 where c1_0.instructor_id=?
        //2026-03-17T21:47:34.562+05:30 TRACE 19036 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [1]
        //2026-03-17T21:47:34.596+05:30 DEBUG 19036 --- [cruddemo] [           main] org.hibernate.SQL                        : update course set instructor_id=?,title=? where id=?
        //2026-03-17T21:47:34.598+05:30 TRACE 19036 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [null]
        //2026-03-17T21:47:34.599+05:30 TRACE 19036 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (2:VARCHAR) <- [GOAT]
        //2026-03-17T21:47:34.599+05:30 TRACE 19036 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (3:INTEGER) <- [10]
        //2026-03-17T21:47:34.605+05:30 DEBUG 19036 --- [cruddemo] [           main] org.hibernate.SQL                        : update course set instructor_id=?,title=? where id=?
        //2026-03-17T21:47:34.606+05:30 TRACE 19036 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [null]
        //2026-03-17T21:47:34.606+05:30 TRACE 19036 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (2:VARCHAR) <- [JanaNayagan]
        //2026-03-17T21:47:34.606+05:30 TRACE 19036 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (3:INTEGER) <- [11]
        //2026-03-17T21:47:34.613+05:30 DEBUG 19036 --- [cruddemo] [           main] org.hibernate.SQL                        : delete from instructor where id=?
        //2026-03-17T21:47:34.613+05:30 TRACE 19036 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [1]
        //2026-03-17T21:47:34.616+05:30 DEBUG 19036 --- [cruddemo] [           main] org.hibernate.SQL                        : delete from instructor_detail where id=?
        //2026-03-17T21:47:34.616+05:30 TRACE 19036 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [1]
    }

    private void updateCourse(AppDAO appDAO, int courseId) {
        System.out.println("Finding Course with courseId:" + courseId);
        Course course = appDAO.findCourseById(courseId);

        System.out.println("Updating Course with courseId:" + courseId);
        course.setTitle("JanaNayagan");
        appDAO.updateCourse(course);
        System.out.println("Done!");

        //Finding Course with courseId:11
        //2026-03-17T21:36:54.026+05:30 DEBUG 912 --- [cruddemo] [           main] org.hibernate.SQL                        : select c1_0.id,i1_0.id,i1_0.email,i1_0.first_name,id1_0.id,id1_0.hobby,id1_0.youtube_channel,i1_0.last_name,c1_0.title from course c1_0 left join instructor i1_0 on i1_0.id=c1_0.instructor_id left join instructor_detail id1_0 on id1_0.id=i1_0.instructor_detail_id where c1_0.id=?
        //2026-03-17T21:36:54.046+05:30 TRACE 912 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [11]
        //Updating Course with courseId:11
        //2026-03-17T21:36:54.104+05:30 DEBUG 912 --- [cruddemo] [           main] org.hibernate.SQL                        : select c1_0.id,i1_0.id,i1_0.email,i1_0.first_name,id1_0.id,id1_0.hobby,id1_0.youtube_channel,i1_0.last_name,c1_0.title from course c1_0 left join instructor i1_0 on i1_0.id=c1_0.instructor_id left join instructor_detail id1_0 on id1_0.id=i1_0.instructor_detail_id where c1_0.id=?
        //2026-03-17T21:36:54.105+05:30 TRACE 912 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [11]
        //2026-03-17T21:36:54.114+05:30 DEBUG 912 --- [cruddemo] [           main] org.hibernate.SQL                        : select c1_0.instructor_id,c1_0.id,c1_0.title from course c1_0 where c1_0.instructor_id=?
        //2026-03-17T21:36:54.114+05:30 TRACE 912 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [1]
        //2026-03-17T21:36:54.156+05:30 DEBUG 912 --- [cruddemo] [           main] org.hibernate.SQL                        : update course set instructor_id=?,title=? where id=?
        //2026-03-17T21:36:54.158+05:30 TRACE 912 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [1]
        //2026-03-17T21:36:54.158+05:30 TRACE 912 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (2:VARCHAR) <- [JanaNayagan]
        //2026-03-17T21:36:54.158+05:30 TRACE 912 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (3:INTEGER) <- [11]
        //Done!
    }

    private void updateInstructor(AppDAO appDAO, int instructorId) {
        System.out.println("Finding instructor with instructorId:" + instructorId);
        Instructor ins = appDAO.findInstructorById(instructorId);
        System.out.println("Updating instructor with instructorId:" + instructorId);
        ins.setLastName("VIJAY");
        appDAO.updateInstructor(ins);
        System.out.println("Done!");
        //Finding instructor with instructorId:1
        //2026-03-17T20:49:53.600+05:30 DEBUG 29476 --- [cruddemo] [           main] org.hibernate.SQL                        : select i1_0.id,i1_0.email,i1_0.first_name,id1_0.id,id1_0.hobby,id1_0.youtube_channel,i1_0.last_name from instructor i1_0 left join instructor_detail id1_0 on id1_0.id=i1_0.instructor_detail_id where i1_0.id=?
        //2026-03-17T20:49:53.636+05:30 TRACE 29476 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [1]\
        //Updating instructor with instructorId:1
        //2026-03-17T20:49:53.748+05:30 DEBUG 29476 --- [cruddemo] [           main] org.hibernate.SQL                        : select i1_0.id,i1_0.email,i1_0.first_name,id1_0.id,id1_0.hobby,id1_0.youtube_channel,i1_0.last_name,c1_0.instructor_id,c1_0.id,c1_0.title from instructor i1_0 left join instructor_detail id1_0 on id1_0.id=i1_0.instructor_detail_id left join course c1_0 on i1_0.id=c1_0.instructor_id where i1_0.id=?
        //2026-03-17T20:49:53.751+05:30 TRACE 29476 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [1]
        //2026-03-17T20:49:53.840+05:30 DEBUG 29476 --- [cruddemo] [           main] org.hibernate.SQL                        : update instructor set email=?,first_name=?,instructor_detail_id=?,last_name=? where id=?
        //2026-03-17T20:49:53.844+05:30 TRACE 29476 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:VARCHAR) <- [vijay.j@gmail.com]
        //2026-03-17T20:49:53.844+05:30 TRACE 29476 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (2:VARCHAR) <- [Joseph]
        //2026-03-17T20:49:53.844+05:30 TRACE 29476 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (3:INTEGER) <- [1]
        //2026-03-17T20:49:53.845+05:30 TRACE 29476 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (4:VARCHAR) <- [VIJAY]
        //2026-03-17T20:49:53.845+05:30 TRACE 29476 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (5:INTEGER) <- [1]
        //Done!
    }

    private void findInstructorWithCoursesJoinFetch(AppDAO appDAO, int instructorId) {
        System.out.println("Finding instructor with instructorId:" + instructorId);
        Instructor ins = appDAO.findInstructorByIdJoinFetch(instructorId);
        System.out.println("Instructor:" + ins);
        System.out.println("Associated courses: " + ins.getCourses());
        //Finding instructor with instructorId:1
        //2026-03-17T20:01:47.373+05:30 DEBUG 5676 --- [cruddemo] [           main] org.hibernate.SQL                        : select i1_0.id,c1_0.instructor_id,c1_0.id,c1_0.title,i1_0.email,i1_0.first_name,id1_0.id,id1_0.hobby,id1_0.youtube_channel,i1_0.last_name from instructor i1_0 join course c1_0 on i1_0.id=c1_0.instructor_id join instructor_detail id1_0 on id1_0.id=i1_0.instructor_detail_id where i1_0.id=?
        //2026-03-17T20:01:47.395+05:30 TRACE 5676 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [1]
        //Instructor:Instructor{id=1, firstName='Joseph', lastName='Vijay', email='vijay.j@gmail.com', instructorDetail=InstructorDetail{id=1, youtubeChannel='Thalapathi Vijay', hobby='Acting'}}
        //Associated courses: [Course{id=10, title='GOAT'}, Course{id=11, title='Gilli'}]
    }

    private void findCoursesForInstructor(AppDAO appDAO, int instructorId) {
        System.out.println("Finding instructor with instructorId:" + instructorId);
        Instructor ins = appDAO.findInstructorById(instructorId);
        System.out.println("Instructor:" + ins);
        System.out.println("Finding courses for the instructorId:" + instructorId);
        List<Course> courses = appDAO.findCoursesByInstructorId(instructorId);
        ins.setCourses(courses);
        System.out.println("Associated courses: " + ins.getCourses());
        System.out.println("Done!");
        //Finding instructor with instructorId:1
        //2026-03-17T19:30:27.997+05:30 DEBUG 18420 --- [cruddemo] [           main] org.hibernate.SQL                        : select i1_0.id,i1_0.email,i1_0.first_name,id1_0.id,id1_0.hobby,id1_0.youtube_channel,i1_0.last_name from instructor i1_0 left join instructor_detail id1_0 on id1_0.id=i1_0.instructor_detail_id where i1_0.id=?
        //2026-03-17T19:30:28.034+05:30 TRACE 18420 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [1]
        //Instructor:Instructor{id=1, firstName='Joseph', lastName='Vijay', email='vijay.j@gmail.com', instructorDetail=InstructorDetail{id=1, youtubeChannel='Thalapathi Vijay', hobby='Acting'}}
        //Finding courses for the instructorId:1
        //2026-03-17T19:30:29.139+05:30 DEBUG 18420 --- [cruddemo] [           main] org.hibernate.SQL                        : select c1_0.id,c1_0.instructor_id,c1_0.title from course c1_0 where c1_0.instructor_id=?
        //2026-03-17T19:30:29.143+05:30 TRACE 18420 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [1]
        //2026-03-17T19:30:29.156+05:30 DEBUG 18420 --- [cruddemo] [           main] org.hibernate.SQL                        : select i1_0.id,i1_0.email,i1_0.first_name,id1_0.id,id1_0.hobby,id1_0.youtube_channel,i1_0.last_name from instructor i1_0 left join instructor_detail id1_0 on id1_0.id=i1_0.instructor_detail_id where i1_0.id=?
        //2026-03-17T19:30:29.158+05:30 TRACE 18420 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [1]
        //Associated courses: [Course{id=10, title='GOAT'}, Course{id=11, title='Gilli'}]
        //Done!
    }

    private void findInstructorWithCourses(AppDAO appDAO, int id) {
        //Used EAGER loading in instructor.
        System.out.println("Finding instructor with id:" + id);
        Instructor ins = appDAO.findInstructorById(id);
        System.out.println(ins);
        System.out.println("Associated courses:" + ins.getCourses());
        //Finding instructor with id:1
        //2026-03-17T19:09:57.161+05:30 DEBUG 8504 --- [cruddemo] [           main] org.hibernate.SQL                        : select i1_0.id,i1_0.email,i1_0.first_name,id1_0.id,id1_0.hobby,id1_0.youtube_channel,i1_0.last_name,c1_0.instructor_id,c1_0.id,c1_0.title from instructor i1_0 left join instructor_detail id1_0 on id1_0.id=i1_0.instructor_detail_id left join course c1_0 on i1_0.id=c1_0.instructor_id where i1_0.id=?
        //2026-03-17T19:09:57.181+05:30 TRACE 8504 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [1]
        //Instructor{id=1, firstName='Joseph', lastName='Vijay', email='vijay.j@gmail.com', instructorDetail=InstructorDetail{id=1, youtubeChannel='Thalapathi Vijay', hobby='Acting'}}
        //Associated courses:[Course{id=10, title='GOAT'}, Course{id=11, title='Gilli'}]
    }

    private void createInstructorWithCourse(AppDAO appDAO) {
        Instructor ins1 = new Instructor("Joseph", "Vijay", "vijay.j@gmail.com");
        InstructorDetail insDet1 = new InstructorDetail("Thalapathi Vijay", "Acting");
        ins1.setInstructorDetail(insDet1);

        Course c1 = new Course("GOAT");
        Course c2 = new Course("Gilli");
        ins1.add(c1);
        ins1.add(c2);

        System.out.println("Saving instructor...");
        System.out.println(ins1.getCourses());
        appDAO.save(ins1);

        System.out.println("Done!");
    }

    private void deleteInstructorDetail(AppDAO appDAO, int id) {
        appDAO.deleteInstructorAndDetailsById(id);
        System.out.println("Done!");
        //2026-03-15T14:03:17.302+05:30 DEBUG 25196 --- [cruddemo] [           main] org.hibernate.SQL                        : select id1_0.id,id1_0.hobby,i1_0.id,i1_0.email,i1_0.first_name,i1_0.last_name,id1_0.youtube_channel from instructor_detail id1_0 left join instructor i1_0 on id1_0.id=i1_0.instructor_detail_id where id1_0.id=?
        //2026-03-15T14:03:17.314+05:30 TRACE 25196 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [2]
        //2026-03-15T14:03:17.365+05:30 DEBUG 25196 --- [cruddemo] [           main] org.hibernate.SQL                        : delete from instructor where id=?
        //2026-03-15T14:03:17.366+05:30 TRACE 25196 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [2]
        //2026-03-15T14:03:17.372+05:30 DEBUG 25196 --- [cruddemo] [           main] org.hibernate.SQL                        : delete from instructor_detail where id=?
        //2026-03-15T14:03:17.372+05:30 TRACE 25196 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [2]
        //Done!
    }

    private void findInstructorDetail(AppDAO appDAO, int id) {
        InstructorDetail instructorDetail = appDAO.findInstructorDetailById(id);
        System.out.println(instructorDetail);
        System.out.println(instructorDetail.getInstructor());
        //2026-03-15T13:54:12.755+05:30 DEBUG 17944 --- [cruddemo] [           main] org.hibernate.SQL                        : select id1_0.id,id1_0.hobby,i1_0.id,i1_0.email,i1_0.first_name,i1_0.last_name,id1_0.youtube_channel from instructor_detail id1_0 left join instructor i1_0 on id1_0.id=i1_0.instructor_detail_id where id1_0.id=?
        //2026-03-15T13:54:12.774+05:30 TRACE 17944 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [1]
        //InstructorDetail{id=1, youtubeChannel='You Can't See Me', hobby='Wrestling'}
        //Instructor{id=1, firstName='John', lastName='Cena', email='john.cena@gmail.com', instructorDetail=InstructorDetail{id=1, youtubeChannel='You Can't See Me', hobby='Wrestling'}}
    }

    private void deleteInstructorById(AppDAO appDAO, int id) {
        appDAO.deleteInstructorById(id);
        System.out.println("Deletion done!");
        //2026-03-14T18:22:13.242+05:30 DEBUG 23380 --- [cruddemo] [           main] org.hibernate.SQL                        : select i1_0.id,i1_0.email,i1_0.first_name,id1_0.id,id1_0.hobby,id1_0.youtube_channel,i1_0.last_name from instructor i1_0 left join instructor_detail id1_0 on id1_0.id=i1_0.instructor_detail_id where i1_0.id=?
        //2026-03-14T18:22:13.253+05:30 TRACE 23380 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [3]
        //2026-03-14T18:22:13.297+05:30 DEBUG 23380 --- [cruddemo] [           main] org.hibernate.SQL                        : delete from instructor where id=?
        //2026-03-14T18:22:13.298+05:30 TRACE 23380 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [3]
        //2026-03-14T18:22:13.309+05:30 DEBUG 23380 --- [cruddemo] [           main] org.hibernate.SQL                        : delete from instructor_detail where id=?
        //2026-03-14T18:22:13.310+05:30 TRACE 23380 --- [cruddemo] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [3]
        //Deletion done!
    }

    private void findInstructor(AppDAO appDAO, int id) {
        Instructor ins = appDAO.findInstructorById(id);
        System.out.println(ins);
//        Instructor{id=1, firstName='John', lastName='Cena', email='john.cena@gmail.com', instructorDetail=InstructorDetail{id=1, youtubeChannel='You Can't See Me', hobby='Wrestling'}}
    }

    private void createInstructor(AppDAO appDAO) {
//        Instructor ins1 = new Instructor("John", "Cena", "john.cena@gmail.com");
//        InstructorDetail insDet1 = new InstructorDetail("You Can't See Me", "Wrestling");
//        ins1.setInstructorDetail(insDet1);

        Instructor ins1 = new Instructor("Dhoni", "MS", "dhoni.ms@gmail.com");
        InstructorDetail insDet1 = new InstructorDetail("DhoniCopter", "Cricket");
        ins1.setInstructorDetail(insDet1);

//        Note: this will also save the details object because of CascadeType.ALL
        System.out.println("Saving instructor: " + ins1);
        appDAO.save(ins1);
        System.out.println("Instructor saved!");
    }

}
