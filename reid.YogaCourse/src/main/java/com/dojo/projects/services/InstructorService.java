package com.dojo.projects.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.dojo.projects.models.Instructor;
import com.dojo.projects.models.InstructorLogin;
import com.dojo.projects.repositories.InstructorRepository;

@Service
public class InstructorService {
	@Autowired
	private InstructorRepository instructorRepo;
	
    public Instructor register(Instructor newInstructor, BindingResult result) {
    	Optional<Instructor> findInstructor = instructorRepo.findByEmail(newInstructor.getEmail());
    	if (findInstructor.isPresent()) {
    		result.rejectValue("email", "Unique", "Account with this email already exists.");
    	}
    	
    	if (!newInstructor.getPassword().equals(newInstructor.getConfirm())) {
    		result.rejectValue("confirm", "matches", "The confirmation password must match!");
    	}
    	
    	if (result.hasErrors()) {
    		return null;
    	}
    	
    	String hashed = BCrypt.hashpw(newInstructor.getPassword(), BCrypt.gensalt());
    	newInstructor.setPassword(hashed);
    	
    	newInstructor = instructorRepo.save(newInstructor);
    	System.out.println("New instructor created with ID: " + newInstructor.getId());
    	
    	return newInstructor;
    }

    public Instructor login(InstructorLogin newInstructorLogin, BindingResult result) {
    	Optional<Instructor> findInstructor = instructorRepo.findByEmail(newInstructorLogin.getEmail());
    	if (!findInstructor.isPresent()) {
    		result.rejectValue("email", "Matches", "User not found!");
    		return null;
    	}
    	
    	Instructor instructor = findInstructor.get();
    	if (!BCrypt.checkpw(newInstructorLogin.getPassword(), instructor.getPassword())) {
    		result.rejectValue("password", "Matches", "Invalid password");
    	}
    	if (result.hasErrors()) {
    		return null;
    	}
    	return instructor;
    }
   
    
    public Instructor findById(Long id) {
    	Optional<Instructor> potentialInstructor= instructorRepo.findById(id);
    	if(potentialInstructor.isPresent()) {
    		return potentialInstructor.get();
    	}
    	return null;
    }
}
