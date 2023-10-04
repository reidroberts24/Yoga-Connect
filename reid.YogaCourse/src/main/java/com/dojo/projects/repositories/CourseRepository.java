package com.dojo.projects.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dojo.projects.models.Course;
import com.dojo.projects.models.Instructor;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {
	List<Course> findAll();
    List<Course> findByInstructor(Instructor instructor);

}
