package com.springboot.tutorials.cruddemo.dao;

import com.springboot.tutorials.cruddemo.entity.Instructor;
import com.springboot.tutorials.cruddemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AppDAOImpl implements AppDAO {

    // define field for entity manager
    private EntityManager entityManager;

    // inject entity manager using constructor injects
    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        this.entityManager.persist(instructor);
    }

    @Override
    public Instructor findInstructorById(int id) {
        return this.entityManager.find(Instructor.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        Instructor instructor = findInstructorById(id);
        if (instructor != null) {
            this.entityManager.remove(instructor);
        }
    }

    @Override
    @Transactional
    public boolean deleteInstructorDetailById(int id) {
        var instructorDetail = this.entityManager.find(InstructorDetail.class, id);
        if (instructorDetail != null){
            // will delete instructor detail and associated instructor
        	// break the bi-directional link
        	instructorDetail.getInstructor().setInstructorDetail(null);
            this.entityManager.remove(instructorDetail);
            return true;
        }
        return false;
    }
}
