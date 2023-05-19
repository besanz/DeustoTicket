package mail;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender {
    private String host = "smtp.gmail.com"; // El servidor SMTP que se utilizará
    private String username = "guticketss@gmail.com"; // Tu dirección de correo electrónico
    private String password = "ddbdsdalmawmhiqw"; // Tu contraseña de correo electrónico
    private Properties props;

    public EmailSender() {
        // Configurar las propiedades del servidor SMTP
        props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
    }

    public void sendEmail(String recipient, String subject, String body) {
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setText(body);
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    
}
