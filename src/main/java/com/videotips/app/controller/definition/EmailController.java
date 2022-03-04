package com.videotips.app.controller.definition;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.videotips.app.model.EmailModel;

import io.swagger.annotations.Api;

@Api(value="Email Controller")
@RequestMapping("v1/email")
public interface EmailController {
	
	@PostMapping("/send")
	public ResponseEntity<?> sendEmail(@RequestBody @Valid EmailModel emailModel);
	
}
