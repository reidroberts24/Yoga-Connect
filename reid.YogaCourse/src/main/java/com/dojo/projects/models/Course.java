package com.dojo.projects.models;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="courses")
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message="Course name is required!")
    @Size(min=1, max=255, message="Course name must be between 1 and 255 characters")
	private String name;
	
    @NotEmpty(message="Day of week is required!")
	private String dayOfWeek;
    
    @NotNull(message = "Time is required")
	private LocalTime time;
	
    @Size(min=3, max=250, message="Description must be between 3 and 250 characters")
	private String description;

    //only making it min of 0 since someone could do a free class
    @Min(value=0, message="Price must be positive!") 
    private Long price;
    
    public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	private Date createdAt;

	private Date updatedAt;

	@ManyToOne
	@JoinColumn(name="instructor_id")
	private Instructor instructor;

	@ManyToMany(fetch = FetchType.LAZY)
   	@JoinTable(
		name = "students_courses", 
		joinColumns = @JoinColumn(name = "course_id"), 
		inverseJoinColumns = @JoinColumn(name = "student_id")
	)
	private List<Student> students;
	
	public Course() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
}

