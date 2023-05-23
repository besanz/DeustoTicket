package remote.service.pdf;

import java.io.File;
import java.io.IOException;
import java.text.Normalizer;
import remote.service.pdf.strategy.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.font.PDTrueTypeFont;
import com.google.zxing.WriterException;

import data.entidades.Ticket;
import remote.service.qr.QRService;

public class PDFService {
    private final QRService qrService;
    private TemplateStrategy templateStrategy;
    private PDDocument document;

    public PDFService(QRService qrService) {
        this.qrService = qrService;
    }

    public void setTemplateStrategy(TemplateStrategy templateStrategy) {
        this.templateStrategy = templateStrategy;
    }

    public File generateTicketPDF(Ticket ticket) throws IOException, WriterException {
        String evento = ticket.getNombreEvento();
        String normalizedEvent = Normalizer.normalize(evento, Normalizer.Form.NFD);
        normalizedEvent = normalizedEvent.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");

        // Determinar la estrategia
        switch (normalizedEvent) {
            case "Reggaeton Fest":
                setTemplateStrategy(new ReggaetonFestStrategy());
                break;
            case "EH Fest":
                setTemplateStrategy(new EHFestStrategy());
                break;
            case "Latin Fest":
                setTemplateStrategy(new LatinFestStrategy());
                break;
            default:
                setTemplateStrategy(new DefaultStrategy());
                break;
        }

        String pdf = templateStrategy.getTemplateName();
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
        contentStream.showText(" - ");

        contentStream.setFont(PDType1Font.TIMES_BOLD, 20);
        contentStream.newLineAtOffset(30, 0);
        contentStream.showText("" + ticket.getDni());
        contentStream.endText();

        // Add the QR code to the PDF
        PDImageXObject qrCodeImage = PDImageXObject.createFromFile(qrCodeImagePath, document);
        contentStream.drawImage(qrCodeImage, 500, 375, 200, 200); // Adjust the coordinates and size according to your needs
        contentStream.close();

        String fileName = "Ticket-" + ticket.getDni() + ".pdf";
        File file = new File(fileName);
        document.save(file);
        document.close();

        return file;
    }
}
