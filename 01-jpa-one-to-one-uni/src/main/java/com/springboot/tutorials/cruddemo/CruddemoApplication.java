package com.springboot.tutorials.cruddemo;

import com.springboot.tutorials.cruddemo.dao.AppDAO;
import com.springboot.tutorials.cruddemo.entity.Instructor;
import com.springboot.tutorials.cruddemo.entity.InstructorDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.github.javafaker.Faker;

@Slf4j
@SpringBootApplication
public class CruddemoApplication {

    public static final Faker faker = Faker.instance();

    public static void main(String[] args) {
        SpringApplication.run(CruddemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AppDAO appDAO) {
        return runner -> {
            /*int i = 0;
            while (i++ < 10) {
                createInstructor(appDAO);
            }*/

            //deleteInstructor(appDAO);

            //findInstructor(appDAO);

            // deleteInstructor(appDAO);

            deleteInstructorDetail(appDAO);
        };
    }

    private void deleteInstructorDetail(AppDAO appDAO) {
        var id = 9;
        if (appDAO.deleteInstructorDetailById(id)) {
            log.warn("delete instuctor detail by id {} success", id);
            return;
        }
        log.warn("delete instructor by id {} fail", id);
    }

    private void deleteInstructor(AppDAO appDAO) {
        int instructorId = 2;
        log.info("Deleting instructor with id {}", instructorId);
        appDAO.deleteInstructorById(instructorId);
    }

    private void findInstructor(AppDAO appDAO) {
        int instructorId = 3;
        Instructor instructor = appDAO.findInstructorById(instructorId);
        if (instructor == null) {
            log.warn("Instructor id {} not found", instructorId);
            return;
        }
        log.info("Instructor with id of {}", instructorId);
        log.info(instructor.toString());
        log.info(instructor.getInstructorDetail().toString());
    }

    private void createInstructor(AppDAO appDAO) {
        log.info("-> create instructors");
        var name = faker.name();
        InstructorDetail instructorDetail = InstructorDetail.builder()
                .youtubeChannel("http://www.yt.com/".concat(faker.random().hex(10)))
                .hobby(faker.esports().game())
                .build();

        Instructor instructor1 = Instructor.builder()
                .firstName(name.firstName())
                .lastName(name.lastName())
                .email(name.username().concat("_").concat(String.valueOf(faker.random().nextInt(1000))).concat("@test.com"))
                .instructorDetail(instructorDetail)
                .build();

        log.info("saving to database instructor and instructor detail");
        log.info(instructor1.toString());
        log.info(instructorDetail.toString());

        // saves both instructor and instructor details to db (CascadeType = ALL)
        appDAO.save(instructor1);
        log.info("Done...");
    }
}
