<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">	
	<meta charset="ISO-8859-1">
	<title>View Course Details</title>
</head>
<body>
	<div class="container mt-4">
	    <h2 class="mb-3">${course.name}</h2>
        <a href="/dashboard" class="btn btn-secondary mb-3">Return to Dashboard</a>
	
	    <div class="card mb-4">
	        <div class="card-body">
                <p class="mb-2"><strong>Instructor:</strong> ${course.instructor.name}</p>
	            <p class="mb-2"><strong>Day:</strong> ${course.dayOfWeek}</p>
	            <p class="mb-2"><strong>Cost:</strong> ${course.price}</p>
	            <p class="mb-2"><strong>Time:</strong> ${course.time}</p>
	            <p class="mb-2"><strong>Description:</strong> ${course.description}</p>
	        </div>
	    </div>
	
	    <h3 class="mb-3">Students:</h3>
	    <ul class="list-group mb-4">
	        <c:forEach items="${course.students}" var="student">
	            <li class="list-group-item">${student.name} (${student.email})</li>
	        </c:forEach>
	    </ul>
	
	    <div class="row">
	        <!-- Form to Add New Student to Course -->
	        <div class="col-md-6">
	            <h4 class="mb-3">Add New Student</h4>
	            <form action="/courses/${course.id}/addStudent" method="post" class="mb-4">
	                <div class="form-group">
	                    <label for="studentName">Student Name:</label>
	                    <input type="text" class="form-control" name="studentName" id="studentName" required>
	                </div>
	                <div class="form-group">
	                    <label for="email">Email:</label>
	                    <input type="email" class="form-control" name="email" id="email" required>
	                </div>
	                <input type="submit" value="Add Student" class="btn btn-primary">
	            </form>
	        </div>
	
	        <!-- Form to Add Existing Student to Course -->
	        <div class="col-md-6">
	            <h4 class="mb-3">Select Existing Student</h4>
	            <form action="/courses/${course.id}/addExistingStudent" method="post" class="mb-4">
	                <div class="form-group">
	                    <label for="studentId">Choose student:</label>
	                    <select class="form-control" name="studentId" id="studentId">
	                        <c:forEach items="${allStudents}" var="student">
	                            <option value="${student.id}">${student.name} (${student.email})</option>
	                        </c:forEach>
	                    </select>
	                </div>
	                <input type="submit" value="Add Student to Course" class="btn btn-success">
	            </form>
	        </div>
	    </div>
	</div>


</body>
</html>