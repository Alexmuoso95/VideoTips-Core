package com.videotips.app.service.implementation;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.videotips.app.exceptions.email.EmailException;
import com.videotips.app.model.EmailModel;
import com.videotips.app.service.definition.EmailService;
import com.videotips.app.validator.header.EmailValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	EmailValidator emailValidator;
	
	@Value("${mail.smtp.user}")
	private String smtpFrom;
	@Value("${mail.smtp.password}")
	private String smtpPasword;
	@Value("${mail.smtp.host}")
	private String smtpHost;
	@Value("${mail.smtp.port}")
	private String smtpPort;
	@Value("${mail.smtp.starttls.enable}")
	private String smtpTLS;
	@Value("${mail.smtp.auth}")
	private String smtpAuth;
	@Value("${mail.debug}")
	private String smtpDebug;

	public final String SMTP_USER = "mail.smtp.user";
	public final String SMTP_PASS = "mail.smtp.clave";
	public final String SMTP_HOST = "mail.smtp.host";
	public final String SMTP_PORT = "mail.smtp.port";
	public final String SMTP_TLS = "mail.smtp.starttls.enable";
	public final String SMTP_AUTH = "mail.smtp.auth";
	public final String SMTP_DEBUG = "mail.debug";
	public final String SMTP = "smtp";

	public String sendEmail(EmailModel emailModel) {
		try {
			List<String> recipients = emailValidator.validateAddress(emailModel.getTo());
			for (String recipient : recipients) {
				Session session = Session.getDefaultInstance(getProperties(), null);
				MimeMessage message = prepareMessage(session, emailModel,recipient);

				Transport transport = session.getTransport(SMTP);
				transport.connect(smtpHost, smtpFrom, smtpPasword);
				transport.sendMessage(message, message.getAllRecipients());
				transport.close();
			}
		} catch (Exception ex) {
			log.info("Exception caught: {}",ex.getMessage(),ex);
			throw new EmailException(ex.getMessage());
		}
		return "Email Send";
	}
	private Properties getProperties() throws Exception {
		Properties prop = System.getProperties();
				prop.put(SMTP_TLS, smtpTLS);
				prop.put(SMTP_HOST, smtpHost);
				prop.put(SMTP_USER, smtpFrom);
				prop.put(SMTP_PASS, smtpPasword);
				prop.put(SMTP_PORT, smtpPort);
				prop.put(SMTP_AUTH, smtpAuth);
				prop.put(SMTP_DEBUG, smtpDebug);
		return prop;
	}

	private MimeMessage prepareMessage(Session session, EmailModel emailModel,String recipient) throws Exception {
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(smtpFrom));
			message.addRecipients(Message.RecipientType.TO,recipient);
			message.setSubject(emailModel.getSubject());
			message.setText(emailModel.getBody(),"utf-8","html");
			return message;
		} catch (Exception ex) {
			throw new Exception(ex);
		}
	}
}
