package remote.service;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.mail.*;
import com.google.zxing.WriterException;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import data.entidades.Ticket;

public class EmailService {

    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String SMTP_PORT = "587";
    private static final String USERNAME = "guticketss@gmail.com";
    private static final String PASSWORD = "ddbdsdalmawmhiqw";

    private final PDFService pdfService;

    public EmailService(PDFService pdfService) {
        this.pdfService = pdfService;
    }

    public void sendEmailWithPDFAndQR(String recipientEmail, String subject, String body, Ticket ticket) {
        // Configure SMTP server properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", SMTP_PORT);

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(subject);

            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText(body);

            // Create the PDF file
            File pdfFile;
            try {
                pdfFile = pdfService.generateTicketPDF(ticket);
            } catch (WriterException e) {
                throw new RuntimeException(e);
            }

            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.attachFile(pdfFile);

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(textPart);
            multipart.addBodyPart(attachmentPart);

            message.setContent(multipart);

            // Send the email
            Transport.send(message);

            System.out.println("Email sent successfully to " + recipientEmail);
        } catch (MessagingException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
