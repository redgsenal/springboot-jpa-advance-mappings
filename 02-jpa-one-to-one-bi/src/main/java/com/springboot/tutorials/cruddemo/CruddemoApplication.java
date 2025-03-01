package com.springboot.tutorials.cruddemo;

import com.springboot.tutorials.cruddemo.dao.AppDAO;
import com.springboot.tutorials.cruddemo.entity.Instructor;
import com.springboot.tutorials.cruddemo.entity.InstructorDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class CruddemoApplication {


    public static void main(String[] args) {
        SpringApplication.run(CruddemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AppDAO appDAO) {
        return runner -> {
            // createInstructor(appDAO);

            //deleteInstructor(appDAO);

            //findInstructor(appDAO);

            findInstructorDetail(appDAO);
        };
    }

    private void findInstructorDetail(AppDAO appDAO) {
        var id = 3;
        log.info("Find instructor detail id: {}", id);
        InstructorDetail instructorDetail = appDAO.findInstructorDetailById(id);

        if (instructorDetail == null) {
            log.warn("No instructor detail found with id {}:", id);
            return;
        }
        log.info("Instructor detail: {}", instructorDetail);
        log.info("Associated instructor: {}", instructorDetail.getInstructor());
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
        InstructorDetail instructorDetail = InstructorDetail.builder()
                .youtubeChannel("http://www.yt.com/ABCDEF")
                .hobby("cooking")
                .build();

        Instructor instructor1 = Instructor.builder()
                .firstName("Sally")
                .lastName("Mills")
                .email("sally_mills@test.com")
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
