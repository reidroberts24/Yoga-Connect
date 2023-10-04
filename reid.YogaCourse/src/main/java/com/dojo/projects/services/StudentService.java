package com.dojo.projects.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dojo.projects.models.Student;
import com.dojo.projects.repositories.StudentRepository;

@Service
public class StudentService {
	@Autowired
	private StudentRepository studentRepo;
	
    public List<Student> findAll() {
        return studentRepo.findAll();
    }
    public Student createStudent(Student student) {
        return studentRepo.save(student);
    }

    public Student findStudentById(Long id) {
        Optional<Student> findStudent = studentRepo.findById(id);
        if (findStudent.isPresent()) {
            return findStudent.get();
        }
        return null;
    }

    public Student updateStudent(Student student) {
        return studentRepo.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepo.deleteById(id);
    }

}
