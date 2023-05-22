package remote.service;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.font.PDTrueTypeFont;

import com.google.zxing.WriterException;

import data.entidades.Ticket;
import remote.service.QRService;

public class PDFService {
    private final QRService qrService;

    public PDFService(QRService qrService) {
        this.qrService = qrService;
    }

    public File generateTicketPDF(Ticket ticket) throws IOException, WriterException {
        // Load the template PDF
        PDDocument document = new PDDocument();
        String evento = ticket.getNombreEvento();
        System.out.println("Nombre evento: "+ evento);
        String pdf = null;

        if (evento.equals("Reggaetón Fest")){
            pdf = "template1.pdf";
        }else if(evento.equals("EH Fest")){
            pdf = "template3.pdf";
        }else if(evento.equals("Latin Fest")){
            pdf = "template2.pdf";
        }
        
        document = PDDocument.load(new File(pdf));
        
        
        // Get the first page of the loaded template
        PDPage page = document.getPage(0);

        // Generate the QR code
        String qrCodeImagePath = "qrcode.png";
        qrService.generateQRCodeImage(String.valueOf(ticket.getId()), qrCodeImagePath);

        // Create the page content stream
        PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, true);

        // Write ticket data to the PDF
        contentStream.setNonStrokingColor(1f);
        contentStream.beginText();

        contentStream.setFont(PDType1Font.TIMES_BOLD, 50);
        contentStream.newLineAtOffset(125, 460);
        contentStream.showText("" + ticket.getFechaEvento());

        contentStream.setFont(PDType1Font.TIMES_BOLD, 50);
        contentStream.newLineAtOffset(825, 0);
        contentStream.showText("" + ticket.getPrecio());

        contentStream.setFont(PDType1Font.TIMES_BOLD, 20);
        contentStream.newLineAtOffset(-450, -300);
        contentStream.showText("Titular: " + ticket.getTitular());

        contentStream.setFont(PDType1Font.TIMES_BOLD, 20);
        contentStream.newLineAtOffset(160, 0);
        contentStream.showText("-");

        contentStream.setFont(PDType1Font.TIMES_BOLD, 20);
        contentStream.newLineAtOffset(30, 0);
        contentStream.showText(""+ticket.getDni());
        contentStream.endText();

        // Add the QR code to the PDF
        PDImageXObject qrCodeImage = PDImageXObject.createFromFile(qrCodeImagePath, document);
        contentStream.drawImage(qrCodeImage, 500, 375, 200, 200); // Adjust the coordinates and size according to your needs
        contentStream.close();

        // Save the PDF document to a file
        File file = new File("ticket.pdf");
        document.save(file);
        document.close();

        return file;
    }
}
