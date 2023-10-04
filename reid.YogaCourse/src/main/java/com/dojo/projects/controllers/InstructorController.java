package com.dojo.projects.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dojo.projects.models.Course;
import com.dojo.projects.models.Instructor;
import com.dojo.projects.models.Student;
import com.dojo.projects.services.CourseService;
import com.dojo.projects.services.InstructorService;
import com.dojo.projects.services.StudentService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class InstructorController {
	@Autowired
    private InstructorService instructorService;
    
    @Autowired
    private CourseService courseService;
    
    @Autowired
    private StudentService studentService;

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {
        Long instructorId = (Long) session.getAttribute("instructorId");
        if (instructorId == null) {
            return "redirect:/"; 
        }
        Instructor instructor = instructorService.findById(instructorId); 
        if (instructor == null) {
            return "redirect:/"; 
        }
        model.addAttribute("instructor", instructor);
        model.addAttribute("courses", courseService.findAllCourses()); 
        return "homepage.jsp";
    }
    
    @GetMapping("/createCourse")
    public String displayCreateCourseForm(@ModelAttribute("course") Course course, HttpSession session) {
        Long instructorId = (Long) session.getAttribute("instructorId");
        if (instructorId == null) {
            return "redirect:/";
        }
        return "createCourse.jsp";
    }

    @PostMapping("/createCourse")
    public String createCourse(@Valid @ModelAttribute("course") Course course, BindingResult result, HttpSession session) {
        Long instructorId = (Long) session.getAttribute("instructorId");
        if (instructorId == null) {
            return "redirect:/";
        }
        if (result.hasErrors()) {
            return "createCourse.jsp";
        }
        Instructor instructor = instructorService.findById(instructorId);
        if (instructor == null) {
            return "redirect:/"; 
        }
        course.setInstructor(instructor);
        courseService.createCourse(course);
        return "redirect:/dashboard"; 
    }
    
    @GetMapping("/courses/{id}/edit")
    public String displayEditCourseForm(@PathVariable("id") Long id, Model model, HttpSession session) {
    	Long instructorId = (Long) session.getAttribute("instructorId");
    	if (instructorId == null) {
    		return "redirect:/"; 
    	}
        Course course = courseService.findCourseById(id);
        if (course == null) {
            return "redirect:/dashboard";
        }
        model.addAttribute("course", course);
        return "updateCourse.jsp";
    }
    
    @PostMapping("/courses/{id}/edit")
    public String updateCourse(@PathVariable("id") Long id, @Valid @ModelAttribute("course") Course course, 
    		BindingResult result, HttpSession session) {
    	Long instructorId = (Long) session.getAttribute("instructorId");
    	if (instructorId == null) {
    		return "redirect:/";
    	}
        if (result.hasErrors()) {
            return "updateCourse.jsp";
        }

        Instructor instructor = instructorService.findById(instructorId);
        if (instructor == null) {
            return "redirect:/dashboard"; 
        }

        course.setInstructor(instructor);
        courseService.updateCourse(course); 
        return "redirect:/dashboard";
    }
    @GetMapping("/courses/{id}/delete")
    public String deleteCourse(@PathVariable("id") Long id, HttpSession session) {
    	Long instructorId = (Long) session.getAttribute("instructorId");
    	if (instructorId == null) {
    		return "redirect:/"; 
    	}
        Course course = courseService.findCourseById(id);
        if (course == null) {
            return "redirect:/dashboard";
        }
        courseService.deleteCourse(id);  
        return "redirect:/dashboard";
    }
    
    // display course details
    @GetMapping("/{courseId}/details")
    public String courseDetails(@PathVariable("courseId") Long courseId, Model model, HttpSession session) {
    	Long instructorId = (Long) session.getAttribute("instructorId");
    	if (instructorId == null) {
    		return "redirect:/"; 
    	}
    	Course course = courseService.findCourseById(courseId);
        if (course != null) {
            model.addAttribute("course", course);
            model.addAttribute("allStudents", studentService.findAll());
            return "courseDetails";
        }
        return "redirect:/courses";
    }
    
    @PostMapping("/{courseId}/addStudent")
    public String addStudentToCourse(@PathVariable("courseId") Long courseId,
                                     @RequestParam("studentName") String studentName,
                                     @RequestParam("email") String email, HttpSession session) {
    	Long instructorId = (Long) session.getAttribute("instructorId");
    	if (instructorId == null) {
    		return "redirect:/"; 
    	}
        Course course = courseService.findCourseById(courseId);
        if (course != null) {
            Student newStudent = new Student();
            newStudent.setName(studentName);
            newStudent.setEmail(email);
            studentService.createStudent(newStudent);

            course.getStudents().add(newStudent);
            courseService.updateCourse(course);
        }
        return "redirect:/courses/" + courseId + "/details";
    }
    
    @PostMapping("/{courseId}/addExistingStudent")
    public String addExistingStudentToCourse(@PathVariable("courseId") Long courseId,
                                             @RequestParam("studentId") Long studentId, HttpSession session) {
    	Long instructorId = (Long) session.getAttribute("instructorId");
    	if (instructorId == null) {
    		return "redirect:/"; 
    	}
        Course course = courseService.findCourseById(courseId);
        Student student = studentService.findStudentById(studentId);
        if (course != null && student != null) {
            course.getStudents().add(student);
            courseService.updateCourse(course);
        }
        return "redirect:/courses/" + courseId + "/details";
    }
}
