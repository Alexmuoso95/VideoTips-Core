package com.videotips.app.validator.header;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.videotips.app.exceptions.email.EmailException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EmailValidator {
	private final String EMAIL_PATTERN = "^(.+)@(.+)$";
	private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
	public List<String> validateAddress(String addressModel) {
		List<String> addresses = Arrays.asList(addressModel.split(","));
		if (addresses.size()>1) {
			addresses.stream().forEach(address -> validateCharacters(address));
			return addresses;
		} else {
			validateCharacters(addressModel);
			addresses.add(addressModel.substring(0, addressModel.length()));
			return addresses;
		}
	}

	private void validateCharacters(String address) {
		Matcher matcher = pattern.matcher(address.substring(0, address.length()));
		if (!matcher.matches()) {
			log.info("Invalid Address {} ", address);
			throw new EmailException("Review email Address : " + address);
		}
	}
}
