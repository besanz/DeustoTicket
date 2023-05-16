// import java.awt.Color;
// import java.io.ByteArrayOutputStream;
// import java.io.IOException;
// import java.util.Objects;

// import javax.imageio.ImageIO;

// import org.apache.pdfbox.pdmodel.PDDocument;
// import org.apache.pdfbox.pdmodel.PDPage;
// import org.apache.pdfbox.pdmodel.PDPageContentStream;
// import org.apache.pdfbox.pdmodel.common.PDRectangle;
// import org.apache.pdfbox.pdmodel.font.PDType1Font;
// import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
// import org.apache.pdfbox.qrcode.QRCode;
// import org.apache.pdfbox.qrcode.QRCodeWriter;

// import data.entidades.Ticket;

// public class TicketPDFGenerator {
    
//     public static byte[] generateTicketPDF(Ticket ticket) throws IOException {
//         ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        
//         try (PDDocument document = new PDDocument()) {
//             PDPage page = new PDPage(PDRectangle.A4);
//             document.addPage(page);
//             PDPageContentStream contentStream = new PDPageContentStream(document, page);
//             contentStream.beginText();
//             contentStream.setFont(PDType1Font.HELVETICA_BOLD, 20);
//             contentStream.newLineAtOffset(100, 700);
//             contentStream.showText("TICKET DE COMPRA");
//             contentStream.endText();
            
//             contentStream.beginText();
//             contentStream.setFont(PDType1Font.HELVETICA, 12);
//             contentStream.newLineAtOffset(50, 650);
//             contentStream.showText("Evento: " + ticket.getEvento().getNombre());
//             contentStream.newLine();
//             contentStream.showText("Precio: " + ticket.getPrecio().getValor());
//             contentStream.newLine();
//             contentStream.showText("Cliente: " + ticket.getUser().getNombre()+ticket.getUser().getApellidos());
//             contentStream.newLine();
//             contentStream.showText("Email: " + ticket.getUser().getEmail());
//             contentStream.endText();
            
//             // Generar QR
//             QRCodeWriter writer = new QRCodeWriter();
//             QRCode qrCode = writer.encode(ticket.toString(), QRCodeWriter.DEFAULT_HINTS);
//             int width = qrCode.getMatrix().getWidth();
//             PDImageXObject image = PDImageXObject.createFromByteArray(document,
//                     ImageIO.write(qrCode.toImage(5, 5), "png", outputStream).toByteArray(),
//                     "qr_code");
//             contentStream.drawImage(image, 400, 600 - width, width, width);
            
//             contentStream.close();
//             document.save(outputStream);
//         }
        
//         return outputStream.toByteArray();
//     }
    
// }
