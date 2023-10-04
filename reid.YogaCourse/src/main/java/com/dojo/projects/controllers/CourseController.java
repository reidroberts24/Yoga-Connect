package com.dojo.projects.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dojo.projects.models.Course;
import com.dojo.projects.models.Student;
import com.dojo.projects.services.CourseService;
import com.dojo.projects.services.StudentService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;

    // Display Course Details
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
            return "viewCourse.jsp";
        }
        // Handle scenario where course is not found, maybe redirect to an error page or course list page
        return "redirect:/courses";
    }

    // Add New Student to a Course
    @PostMapping("/{courseId}/addStudent")
    public String addStudentToCourse(@PathVariable("courseId") Long courseId,
                                     @RequestParam("studentName") String studentName,
                                     @RequestParam("email") String email,
                                     HttpSession session) {
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

    // Add Existing Student to a Course
    @PostMapping("/{courseId}/addExistingStudent")
    public String addExistingStudentToCourse(@PathVariable("courseId") Long courseId,
                                             @RequestParam("studentId") Long studentId,
                                             HttpSession session) {
    	Long instructorId = (Long) session.getAttribute("instructorId");
        if (instructorId == null) {
            return "redirect:/";
        }

        courseService.addStudentToCourse(courseId, studentId);
        return "redirect:/courses/" + courseId + "/details";
    }
}
