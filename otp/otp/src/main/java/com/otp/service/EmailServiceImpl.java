package com.otp.service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.otp.controller.OtpController;
import com.otp.dao.EmailRepository;
import com.otp.entities.Check;
import com.otp.entities.Email;
import com.otp.entities.Validate;
import com.otp.sender.EmailSenderService;

@Service
public class EmailServiceImpl implements EmailService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OtpController.class);

	int maxAttempts = 3;

	@Autowired
	private EmailRepository repo;
	
	@Autowired
    private EmailSenderService services;
	
	Check check = new Check();
	
	@Override
	public Check checkMail() {
		// TODO Auto-generated method stub

		LOGGER.info("Inside the checkMail Service");
		return check;
	}
	
	@Override
	public List<Email> getUsers() {
		// TODO Auto-generated method stub
		return (List<Email>) repo.findAll();
	}

	@Override
	public void addUser(Email email) {
		// TODO Auto-generated method stub
		maxAttempts = 3;
		repo.save(email);
		email.setStartDate(LocalTime.now());
		email.setEndDate(email.getStartDate().plusMinutes(5));
		int randomPin   =(int) (Math.random()*900000)+100000;
        String otp1  = String.valueOf(randomPin);
		int otp = Integer.parseInt(otp1);
		email.setOtp(otp);
		repo.save(email);
        check.setCheck("False");
     	services.sendSimpleMail(email.getEmail(),"Get Your OTP :: "+ email.getOtp() + " ","This is the system generated OTP :: Validation is for 2 Minutes ");

//		sendSimpleMail(email);
	}
	
	@Override
	public void validateUser(Validate validate) {
		// TODO Auto-generated method stub
		
		String mail = null;
    	int otp = 0;
    	LocalTime start = null;
    	LocalTime end = null;
    	int count = 0;
    	Email mail1 = null;
    	
    	List<Email> l = repo.findAll();
    	for(int i = 0;i<l.size();i++) {
    		Email h = l.get(i);
    		if(h.getEmail().equals(validate.getEmail())) {
    			mail1 = h;
    			mail = h.getEmail();
    			otp = h.getOtp();
    			start = h.getStartDate();
    			end = h.getEndDate();
    			count = h.getCount();
    			
    		}
    	}
    	
    	LocalTime now = LocalTime.now();
    	if(validate.getEmail().equals(mail) && Integer.parseInt(validate.getOtp()) == otp && now.isBefore(end) && now.isAfter(start) && count <= 3) {
    		check.setCheck("True");
    		((Email) mail1).setCount(count++);
    		System.out.println(check.getCheck());
    		services.sendSuccessMail(validate.getEmail(), "OTP Is Verified", "This is a system generated success mail.");
        	
    	}
    	else {
    		check.setCheck("False");
    		System.out.println("False");
    	}
		
	}

	@Override
	public String resendMail(Email email) {

		LOGGER.info("Inside the checkMail Service");
		// TODO Auto-generated method stub
		LocalTime t1 = LocalTime.now();
		Optional<Email> mail = repo.findById(email.getEmail());
		
		Email mail1 = mail.get();

        System.out.println(mail1.getStartDate() + " " + mail1.getEndDate());
		if((t1.getMinute() - mail1.getStartDate().getMinute()) >= 1 && maxAttempts >= 1) {
			System.out.println("inside the program"); 
			repo.save(email); 
			email.setStartDate(LocalTime.now());
			email.setEndDate(email.getStartDate().plusMinutes(5));
			int randomPin   =(int) (Math.random()*900000)+100000;
	        String otp1  = String.valueOf(randomPin);
			int otp = Integer.parseInt(otp1);
			email.setOtp(otp);
			email.setStartDate(t1);
			email.setEndDate(email.getStartDate().plusMinutes(5));
			repo.save(email);
	        check.setCheck("False");
	        System.out.println(email.getStartDate() + " " + email.getEndDate() + " " + maxAttempts);
	     	services.sendSimpleMail(email.getEmail(),"Get Your OTP :: "+ email.getOtp() + " ","This is the system Regenerated OTP :: Validation is for 2 Minutes ");
	     	maxAttempts--;
	     	return "success";
		}
		else if(maxAttempts < 1){

			System.out.println("Max Attempts");
			services.sendSimpleMail(email.getEmail(),"Oops OTP cannot be Send." + " ","Max Attempts Reached");
			return "not send";
	     	
		}
		return "not send";
		
	}

	
	
}
