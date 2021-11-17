package com.otp.otp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.otp.dao.EmailRepository;
import com.otp.entities.Email;
import com.otp.sender.EmailSenderService;
import com.otp.service.EmailService;
import com.otp.util.Util;

@SpringBootTest

@ExtendWith(MockitoExtension.class)
class OtpApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	private EmailRepository repo;
	
	@Autowired
	private EmailService mailService;
	
	@Autowired
    private EmailSenderService services;
	
	@Autowired
	private Util util;
	
	@Test
	void getUser() {
		Email email = new Email("mohit.18bit1106@abes.ac.in");
		repo.save(email);
		List<Email> actualResult = mailService.getUsers();
		actualResult.contains(email);
		Boolean result = false;
		if(actualResult != null) {
			result = true;
		}
		assertThat(result).isTrue();
	}
	
	@Test
	void generateOtp() {

		Email email = new Email("mohit.18bit1106@abes.ac.in");
		mailService.addUser(email);
		int expectedresult = 342132;
		int actualResult = email.getOtp();
		assertNotEquals(expectedresult, actualResult);
	}
	
	@Test
	void getInvalidUser() {
		Email email = new Email("mohit.18bit1106@abes.ac.in");
		List<Email> actualResult = mailService.getUsers();
		actualResult.contains(email);
		Boolean result = false;
		if(actualResult != null) {
			result = true;
		}
		assertThat(result).isTrue();
	}
	@Test
	void sendMail() {
		Email email = new Email("mohit.18bit1106@abes.ac.in");
		
     	String result = services.sendSimpleMail(email.getEmail(),"Get Your OTP :: "+ 4567 + " ","This is the system generated OTP");
     	
     	Boolean actualResult;
     	if(result.equals("send success")) {
     		actualResult = true;
     	}
     	else {
     		actualResult = false;
     	}
     	assertThat(actualResult).isTrue();
//     	
//     	Validate validate = new Validate("mohit.18bit1106@abes.ac.in","4567");
//
//     	services.sendSuccessMail(email.getEmail(),"Get Your OTP :: "+ 4567 + " ","This is the system generated OTP");
	}
	
	@Test
	void sendSuccessMail() {
		Email email = new Email("mohit.18bit1106@abes.ac.in");
		String result = services.sendSuccessMail(email.getEmail(),"Get Your OTP :: "+ 4567 + " ","This is the system generated OTP");
		

     	Boolean actualResult;
     	if(result.equals("Otp validated Successfully")) {
     		actualResult = true;
     	}
     	else {
     		actualResult = false;
     	}
     	assertThat(actualResult).isTrue();
	}
	
	
	@Test
	void validateMail() {
		String mailAddress = "mohit.18bit1106@abes.ac.in";
		Boolean result = util.isValidEmailAddress(mailAddress);
		assertThat(result).isTrue();
	}
	
	@Test
	void inValidateMail() {
		String mailAddress = "mohit.18bit1106aben";
		Boolean result = util.isValidEmailAddress(mailAddress);
		assertThat(result).isFalse();
	}
	
	@Test
	void regenerateMail() {

		Email email = new Email("mohit.18bit1106@abes.ac.in");
		repo.save(email);
		String result = mailService.resendMail(email);
		Boolean actualResult;
		if (result.equals("success")) {
			actualResult = true;
		}
		else {
			actualResult = false;
		}
		assertThat(actualResult).isFalse();
		
	}

}
