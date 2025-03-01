package com.springboot.tutorials.cruddemo.dao;

import com.springboot.tutorials.cruddemo.entity.Instructor;

public interface AppDAO {

    void save(Instructor instructor);

    Instructor findInstructorById(int id);

    void deleteInstructorById(int id);

    boolean deleteInstructorDetailById(int id);

}
