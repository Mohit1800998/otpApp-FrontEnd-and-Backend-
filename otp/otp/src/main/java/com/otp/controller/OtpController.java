package com.otp.controller;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.otp.dao.EmailRepository;
import com.otp.entities.Check;
import com.otp.entities.Email;
import com.otp.entities.Validate;
import com.otp.exception.InvalidEmailException;
import com.otp.exception.ResourceNotFoundException;
import com.otp.sender.EmailSenderService;
import com.otp.service.EmailService;
import com.otp.util.Util;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class OtpController {

	private static final Logger LOGGER = LoggerFactory.getLogger(OtpController.class);

	@Autowired
	private EmailSenderService services;

	@Autowired
	private EmailService service;

	@Autowired
	private EmailRepository repo;
	
	@Autowired
	private Util util;

	@GetMapping("/emails")
	public Check checkMail() {
		LOGGER.info("Inside the Check Url");
		return this.service.checkMail();
	}

	@GetMapping("/users")
	public List<Email> getUsers() {

		LOGGER.info("Inside the User's Email List ...");
		if(this.service.getUsers() == null) {
			
		}
		return this.service.getUsers();
	}

	@PostMapping("/emails")
	public String addUser(@RequestBody Email email) {

		LOGGER.info("Inside the Add Email ...");
		if(util.isValidEmailAddress(email.getEmail())==false) {
    		return new InvalidEmailException("Wrong email pattern").toString();
    	}
		try {
			this.service.addUser(email);
			return "User Added";
		}
		catch(Exception e) {
			return e.getMessage();
		}

	}

	@PostMapping("/validate")
	public void validateUser(@RequestBody Validate validate) throws ResourceNotFoundException {

		LOGGER.info("Inside the Validate ... ");
		if(validate == null) {
			throw new ResourceNotFoundException("Email does not exist");
		}
		this.service.validateUser(validate);
	}
	
	@PostMapping("/resend")
	public void resendMail(@RequestBody Email email) {

		LOGGER.info("Inside the Validate ... ");
		this.service.resendMail(email);
		
	}

}
