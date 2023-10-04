<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">	
	<meta charset="ISO-8859-1">
	<title>Create New Course</title>
</head>
<body>
	<div class="container mt-5">
        <h2>Create a Course</h2>
        <form:form action="/createCourse" method="post" modelAttribute="course" class="mb-4">
            <div class="form-group">
                <form:input path="name" placeholder="Course Name" class="form-control"/>
                <form:errors path="name" cssClass="text-danger" />
            </div>
            
			<div class="form-group">
			    <form:select path="dayOfWeek" class="form-control">
			        <form:option value="" label="Select a day of the week" /> <!-- Optional default empty option -->
			        <form:option value="Monday">Monday</form:option>
			        <form:option value="Tuesday">Tuesday</form:option>
			        <form:option value="Wednesday">Wednesday</form:option>
			        <form:option value="Thursday">Thursday</form:option>
			        <form:option value="Friday">Friday</form:option>
			        <form:option value="Saturday">Saturday</form:option>
			        <form:option value="Sunday">Sunday</form:option>
			    </form:select>
			    <form:errors path="dayOfWeek" cssClass="text-danger" />
			</div>
            
			<div class="form-group">
			    <form:input path="time" type="time" placeholder="Time of day" class="form-control"/>
			    <form:errors path="time" cssClass="text-danger" />
			</div>

            <div class="form-group">
                <form:input path="price" type="number" placeholder="Drop-in Price" class="form-control"/>
                <form:errors path="price" cssClass="text-danger" />
            </div>
            
            <div class="form-group">
                <form:textarea path="description" placeholder="Description" class="form-control" rows="5"></form:textarea>
                <form:errors path="description" cssClass="text-danger" />
            </div>
            
            <div class="form-group text-right">
                <button type="submit" class="btn btn-primary">Submit</button>
                <a href="/dashboard" class="btn btn-secondary">Cancel</a>
            </div>
        </form:form>
    </div>
</body>
</html>