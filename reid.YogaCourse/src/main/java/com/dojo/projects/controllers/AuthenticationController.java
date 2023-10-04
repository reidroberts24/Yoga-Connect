package com.dojo.projects.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.dojo.projects.models.Instructor;
import com.dojo.projects.models.InstructorLogin;
import com.dojo.projects.services.InstructorService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class AuthenticationController {
	
    @Autowired
    private InstructorService instructors;
    
    @GetMapping("/")
    public String loginAndRegistration(Model model) {
        model.addAttribute("newInstructor", new Instructor());
        model.addAttribute("newInstructorLogin", new InstructorLogin());
        return "login.jsp";
    }
	
    @GetMapping("/logout")
	public String logout(HttpSession session) {
		 session.setAttribute("instructorId", null);
		 return "redirect:/";
	}
	
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newInstructor") Instructor newInstructor, 
                           BindingResult result, 
                           HttpSession session, 
                           Model model) {
        
        Instructor registeredInstructor = instructors.register(newInstructor, result);
        
        if (result.hasErrors()) {
            model.addAttribute("newInstructorLogin", new InstructorLogin());
            return "login.jsp";
        }
        
        session.setAttribute("instructorId", registeredInstructor.getId());
        return "redirect:/dashboard";  
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newInstructorLogin") InstructorLogin newInstructorLogin, 
                        BindingResult result, 
                        HttpSession session, 
                        Model model) {
        
        Instructor loggedInInstructor = instructors.login(newInstructorLogin, result);
        
        if (result.hasErrors()) {
            model.addAttribute("newInstructor", new Instructor());
            return "login.jsp";
        }
        
        session.setAttribute("instructorId", loggedInInstructor.getId());
        return "redirect:/dashboard";  
    }


}


