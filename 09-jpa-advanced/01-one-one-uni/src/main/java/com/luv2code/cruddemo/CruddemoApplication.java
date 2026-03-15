package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

    // Executed after the Springs Beans have been loaded.
    @Bean
    public CommandLineRunner commandLineRunner(AppDAO appDAO) {
        return runner -> {
//            createInstructor(appDAO);
//            findInstructor(appDAO, 1);
//            deleteInstructor(appDAO, 3);
        };
    }

    private void deleteInstructor(AppDAO appDAO, int id) {
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
