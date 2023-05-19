package mail;

import java.io.*;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.common.*;
import org.apache.pdfbox.pdmodel.font.*;
import org.apache.pdfbox.pdmodel.graphics.image.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import data.entidades.*;

public class EmailSenderConPDF {
    private String host = "smtp.gmail.com"; // El servidor SMTP que se utilizará
    private String username = "guticketss@gmail.com"; // Tu dirección de correo electrónico
    private String password = "ddbdsdalmawmhiqw"; // Tu contraseña de correo electrónico
    private Properties props;

    public EmailSenderConPDF() {
        // Configurar las propiedades del servidor SMTP
        props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
    }
    public void sendEmailWithAttachment(String toEmail, String subject, String body, Ticket ticket) {
        // Crear el documento PDF
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        try {
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            // Escribir los datos del ticket en el PDF
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.newLineAtOffset(25, 700);
            contentStream.showText("ID: " + ticket.getId());
            contentStream.newLineAtOffset(0, -15);
            contentStream.showText("Evento: " + ticket.getEvento().getTitulo());
            contentStream.newLineAtOffset(0, -15);
            contentStream.showText("Precio: " + ticket.getPrecio().getNombre());
            contentStream.newLineAtOffset(0, -15);
            contentStream.showText("Comprado por: " + ticket.getUser().getNombre());
            contentStream.newLineAtOffset(0, -15);
            contentStream.showText("Titulat: " + ticket.getTitular());
            
            // Agregar más datos del ticket aquí

            contentStream.endText();
            contentStream.close();

            // Guardar el documento PDF en un archivo
            File file = new File("ticket.pdf");
            document.save(file);
            document.close();

            // Crear una sesión de correo electrónico
            Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            // Crear un mensaje de correo electrónico
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(body);

            // Crear el adjunto con el archivo PDF
            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.attachFile(file);

            // Crear el contenido del correo electrónico
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(attachmentPart);

            // Asignar el contenido al mensaje
            message.setContent(multipart);

            // Enviar el mensaje
            Transport.send(message);

            System.out.println("Correo electrónico enviado exitosamente.");
        } catch (MessagingException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
