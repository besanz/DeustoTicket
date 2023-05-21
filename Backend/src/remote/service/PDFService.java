package remote.service;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import com.google.zxing.*;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.awt.image.BufferedImage;
import java.io.IOException;

import data.entidades.Ticket;
import remote.service.QRService;

public class PDFService {
    private final QRService qrService;

    public PDFService(QRService qrService) {
        this.qrService = qrService;
    }

    public File generateTicketPDF(Ticket ticket) throws IOException, WriterException {
        // Create the PDF document
        PDDocument document = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        document.addPage(page);
        String ticketId = String.valueOf(ticket.getId());

        // Generate the QR code
        String qrCodeImagePath = "qrcode.png";
        qrService.generateQRCodeImage(ticketId, qrCodeImagePath);

        // Create the page content stream
        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        // Write ticket data to the PDF
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
        contentStream.newLineAtOffset(25, 700);
        contentStream.showText("ID: " + ticket.getId());
        contentStream.newLineAtOffset(0, -15);
        contentStream.showText("Evento: " + ticket.getNombreEvento());
        contentStream.newLineAtOffset(0, -15);
        contentStream.showText("Precio: " + ticket.getPrecio());
        contentStream.newLineAtOffset(0, -15);
        contentStream.showText("Comprado por: " + ticket.getDni());
        contentStream.newLineAtOffset(0, -15);
        contentStream.showText("Titular: " + ticket.getTitular());
        contentStream.endText();

        // Add the QR code to the PDF
        PDImageXObject qrCodeImage = PDImageXObject.createFromFile(qrCodeImagePath, document);
        contentStream.drawImage(qrCodeImage, 25, 150, 150, 150); // Adjust the coordinates and size according to your needs
        contentStream.close();

        // Save the PDF document to a file
        File file = new File("ticket.pdf");
        document.save(file);
        document.close();

        return file;
    }
}



