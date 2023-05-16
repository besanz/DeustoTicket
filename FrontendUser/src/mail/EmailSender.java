// import javax.activation.DataHandler;
// import javax.activation.DataSource;
// import javax.activation.FileDataSource;
// import javax.mail.Message;
// import javax.mail.MessagingException;
// import javax.mail.Multipart;
// import javax.mail.Session;
// import javax.mail.Transport;
// import javax.mail.internet.AddressException;
// import javax.mail.internet.InternetAddress;
// import javax.mail.internet.MimeBodyPart;
// import javax.mail.internet.MimeMessage;
// import javax.mail.internet.MimeMultipart;
// import java.io.ByteArrayOutputStream;
// import java.io.IOException;
// import java.util.Properties;

// public class EmailSender {

//     public static void sendEmailWithAttachment(String recipientEmail, String subject, String messageBody,String attachmentName, ByteArrayOutputStream attachmentContent) throws MessagingException, IOException {

//         String senderEmail = "guticket@gmail.com"; // Remitente del correo electrónico
//         String senderPassword = "guticket"; // Contraseña del remitente

//         Properties props = new Properties();
//         props.put("mail.smtp.auth", "true");
//         props.put("mail.smtp.starttls.enable", "true");
//         props.put("mail.smtp.host", "smtp.gmail.com");
//         props.put("mail.smtp.port", "587");

//         Session session = Session.getInstance(props, new javax.mail.Authenticator() {
//             protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
//                 return new javax.mail.PasswordAuthentication(senderEmail, senderPassword);
//             }
//         });

//         Message message = new MimeMessage(session);
//         message.setFrom(new InternetAddress(senderEmail));
//         message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
//         message.setSubject(subject);

//         // Crear el cuerpo del mensaje
//         MimeBodyPart messageBodyPart = new MimeBodyPart();
//         messageBodyPart.setContent(messageBody, "text/html");

//         // Crear la parte adjunta
//         DataSource source = new FileDataSource(attachmentName);
//         MimeBodyPart attachmentBodyPart = new MimeBodyPart();
//         attachmentBodyPart.setDataHandler(new DataHandler(source));
//         attachmentBodyPart.setFileName(attachmentName);
//         attachmentBodyPart.setContent(attachmentContent.toByteArray(), "application/pdf");

//         // Combinar las partes en un multipart
//         Multipart multipart = new MimeMultipart();
//         multipart.addBodyPart(messageBodyPart);
//         multipart.addBodyPart(attachmentBodyPart);

//         // Agregar el multipart al mensaje
//         message.setContent(multipart);

//         // Enviar el mensaje
//         Transport.send(message);
//     }
// }
