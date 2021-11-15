package com.otp.entities;

import java.time.LocalTime;
import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Email {
    
    @Id
    private String email;
    private  String name;
    private LocalTime startDate; 
    private LocalTime endDate;
    private int otp;
    
	public Email( String name, String email) {
		super();
		this.name = name;
		this.email = email;
		this.startDate = LocalTime.now();
		this.endDate = startDate.plusMinutes(5);
		this.otp = getOtp();
	}
	
	
	public int getOtp() {
		return otp;
	}


	public void setOtp(int otp) {
		this.otp = otp;
	}


	public LocalTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalTime startDate) {
		this.startDate = startDate;
	}

	public LocalTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalTime endDate) {
		this.endDate = endDate;
	}

	public Email() {
		super();
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
    
    // standard constructors / setters / getters / toString
    
	
    
}