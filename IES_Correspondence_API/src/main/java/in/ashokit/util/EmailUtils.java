package in.ashokit.util;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtils {

	@Autowired
	private JavaMailSender mailSender;

	public boolean sendEmail(String to, String subject, String body,File f) {

		try {

			MimeMessage mimMsg = mailSender.createMimeMessage();

			MimeMessageHelper helper = new MimeMessageHelper(mimMsg,true);

			helper.setSubject(subject);
			helper.setText(body, true);
			helper.setTo(to);
			helper.addAttachment("Citizen_plan_Details", f);

			mailSender.send(mimMsg);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}
}
