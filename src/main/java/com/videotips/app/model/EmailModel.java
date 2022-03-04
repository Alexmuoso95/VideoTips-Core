package com.videotips.app.model;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailModel {

	@NotBlank
	private String to;
	@NotBlank
	private String subject;
	@NonNull
	private String body;
	
}

