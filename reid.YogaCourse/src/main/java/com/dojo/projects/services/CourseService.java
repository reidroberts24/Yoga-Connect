package com.dojo.projects.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dojo.projects.models.Course;
import com.dojo.projects.models.Student;
import com.dojo.projects.repositories.CourseRepository;
import com.dojo.projects.repositories.StudentRepository;

@Service
public class CourseService {
	
    @Autowired
    private CourseRepository courseRepo;
    @Autowired
    private StudentRepository studentRepo;
    
    public List<Course> findAllCourses() {
        return courseRepo.findAll();
    }
    
    public Course createCourse(Course course) {
        return courseRepo.save(course);
    }

    public Course findCourseById(Long id) {
    	Optional<Course> findCourse = courseRepo.findById(id);
    	if (findCourse.isPresent()) {
            return findCourse.get();
        }
        return null;
    }
    // Update course
    public Course updateCourse(Course course) {
        return courseRepo.save(course);
    }
    
    // delete course by ID
    public void deleteCourse(Long id) {
        courseRepo.deleteById(id);
    }
    public boolean addStudentToCourse(Long courseId, Long studentId) {
        Optional<Course> optCourse = courseRepo.findById(courseId);
        Optional<Student> optStudent = studentRepo.findById(studentId);

        if (optCourse.isPresent() && optStudent.isPresent()) {
            Course course = optCourse.get();
            Student student = optStudent.get();
            
            // check if student is already enrolled
            if (course.getStudents().contains(student)) {
                return false; 
            }

            course.getStudents().add(student);
            courseRepo.save(course);
            return true;
        }
        
        return false;
    }
}
