<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">	
	<meta charset="ISO-8859-1">
	<title>Instructor Home</title>
</head>
<body>
 	<div class="container mt-5">
        <div class="row">
            <div class="col-md-8">
                <h1>Welcome, ${instructor.name}!</h1>
            </div>
            <div class="col-md-4 text-right">
                <div class="mb-2">
                   	<a href="/logout" class="btn btn-primary w-100">Logout</a>
                </div>
            </div>
        </div>
        <hr>
        <p>Course Schedule:</p>
        <table class="table table-striped">
			<thead>
			    <tr>
			        <th>Course Name</th>
			        <th>Instructor</th>
			        <th>Weekday</th>
			        <th>Price</th>
			        <th>Time</th>
			        <th>Edit</th>
			        <th>Delete</th>
			    </tr>
			</thead>
			<tbody>
			    <c:forEach items="${courses}" var="course">
			        <tr>
			            <td><a href="/courses/${course.id}/details">${course.name}</a></td>
			            <td>${course.instructor.name}</td>
			            <td>${course.dayOfWeek}</td>
			            <td>$${course.price}</td>
			            <td>${course.time}</td>
			            <c:if test="${course.instructor.id == instructor.id}">
			                <td><a href="/courses/${course.id}/edit" class="btn btn-warning">Edit</a></td>
			                <td><a href="/courses/${course.id}/delete" class="btn btn-danger">Delete</a></td>
			            </c:if>
			            <c:if test="${course.instructor.id != instructor.id}">
			                <td></td>
			                <td></td>
			            </c:if>
			        </tr>
			    </c:forEach>
			</tbody>

        </table>
        <div class="text-right mt-3">
            <a href="/createCourse" class="btn btn-secondary">Add New Course</a>
        </div>
    </div>
</body>
</html>