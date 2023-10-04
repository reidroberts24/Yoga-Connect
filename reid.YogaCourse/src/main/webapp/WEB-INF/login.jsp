<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">	
	<meta charset="ISO-8859-1">
	<title>Login and Registration</title>
</head>
<body>

	<div class="container mt-5">
	    <h1 class="text-center mb-5">Welcome to the Yoga Course Platform!</h1>
	    <div class="row">
	        <!-- Registration Form -->
	        <div class="col-md-6">
	            <h3 class="mb-4">New Instructor</h3>
	            <form:form action="/register" method="POST" modelAttribute="newInstructor">
	                <div class="form-group">
	                    <label for="name">Name:</label>
	                    <form:input path="name" type="text" class="form-control" id="name" placeholder="Enter Name" />
                        <form:errors path="name" cssClass="text-danger"/>    
	                </div>
	                <div class="form-group">
	                    <label for="email">Email:</label>
	                    <form:input path="email" type="email" class="form-control" id="email" placeholder="Enter Email" />
                        <form:errors path="email" cssClass="text-danger"/>    
	                </div>
	                <div class="form-group">
	                    <label for="password">Password:</label>
	                    <form:password path="password" class="form-control" id="password" placeholder="Enter Password" />
                        <form:errors path="password" cssClass="text-danger"/>
	                    
	                </div>
	                <div class="form-group">
	                    <label for="confirm">Confirm Password:</label>
	                    <form:password path="confirm" class="form-control" id="confirm" placeholder="Confirm Password" />
                        <form:errors path="confirm" cssClass="text-danger"/>
	                    
	                </div>
	                <button type="submit" class="btn btn-primary">Register</button>
	                
	            </form:form>
	        </div>
	
	        <!-- Login Form -->
	        <div class="col-md-6">
            <h3 class="mb-4">Log In</h3>
            <form:form action="/login" method="POST" modelAttribute="newInstructorLogin">
                <div class="form-group">
                    <label for="loginEmail">Email:</label>
                    <form:input path="email" type="email" class="form-control" id="loginEmail" placeholder="Enter Email" />
                    <form:errors path="email" cssClass="text-danger"/>
                
                </div>
                <div class="form-group">
                    <label for="loginPassword">Password:</label>
                    <form:password path="password" class="form-control" id="loginPassword" placeholder="Enter Password" />
                    <form:errors path="password" cssClass="text-danger"/>
                
                </div>
                <button type="submit" class="btn btn-primary">Login</button>
            </form:form>
        </div>
	</div>
</body>
</html>