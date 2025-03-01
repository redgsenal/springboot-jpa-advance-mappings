package com.springboot.tutorials.cruddemo.dao;

import com.springboot.tutorials.cruddemo.entity.Instructor;
import com.springboot.tutorials.cruddemo.entity.InstructorDetail;

public interface AppDAO {

    void save(Instructor instructor);

    Instructor findInstructorById(int id);

    void deleteInstructorById(int id);

    InstructorDetail findInstructorDetailById(int id);
}
