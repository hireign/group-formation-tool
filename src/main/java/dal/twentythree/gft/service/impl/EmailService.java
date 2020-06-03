package dal.twentythree.gft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	private JavaMailSender jms;

	@Autowired
	public EmailService(JavaMailSender jms) {
		this.jms = jms;
	}
	
	@Async
	public void sendEmail(SimpleMailMessage email) {
		jms.send(email);
	}
}
