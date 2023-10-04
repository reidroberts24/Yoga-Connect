package com.dojo.projects.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dojo.projects.models.Instructor;

@Repository
public interface InstructorRepository extends CrudRepository<Instructor, Long> {
    Optional<Instructor> findByEmail(String email);
    
    List<Instructor> findAll();
}
