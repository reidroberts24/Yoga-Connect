package com.dojo.projects.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class InstructorLogin {
	
	@NotEmpty(message="Email is required")
    @Email(message="Email should be valid")
    private String email;
    
    @NotEmpty(message="Password is required")
    @Size(min=8, max=255, message="Password should be between 8 to 255 characters")
    private String password;

    public InstructorLogin() {}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
