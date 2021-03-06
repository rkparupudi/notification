package com.continental.assignment.notification.serviceimpl;

import com.continental.assignment.notification.domain.VehiclePenalty;
import com.continental.assignment.notification.service.NotificationService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Override
    public Boolean sendEmail(List<VehiclePenalty> penalties) throws AddressException, MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("<email>", "<password>");
            }
        });
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("<email>", false));

        msg.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse("<email>"));
        msg.setSubject("Traffic Violators");
        penalties.forEach(penalty -> {
            try {
                msg.setContent(
                        "Penalties of the vehicle standing near the signal " + '\n' + penalty.getVehicleNumber() + '\n' + penalty.getOwner() + '\n' + penalty.getPenalty(),
                        "text/html");
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        });

        msg.setSentDate(new Date());

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(penalties, "text/html");
        Transport.send(msg);
        return true;
    }
}
