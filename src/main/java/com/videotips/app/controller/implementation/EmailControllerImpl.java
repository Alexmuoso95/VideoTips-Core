package com.videotips.app.controller.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.videotips.app.controller.definition.EmailController;
import com.videotips.app.model.EmailModel;
import com.videotips.app.service.definition.EmailService;

@RestController
public class EmailControllerImpl implements EmailController{

	@Autowired
	public EmailService emailService;
	
	public ResponseEntity<?> sendEmail(EmailModel emailModel) {
		return new ResponseEntity<>(emailService.sendEmail(emailModel), HttpStatus.OK);
	}
}
