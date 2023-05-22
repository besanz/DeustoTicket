package remote.service.qr;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class QRService {
    private static final int DEFAULT_SIZE = 350;
    private static final String DEFAULT_IMAGE_FORMAT = "PNG";

    public void generateQRCodeImage(String text, String filePath, int size) {
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, size, size);

            Path path = Paths.get(filePath);
            MatrixToImageWriter.writeToPath(bitMatrix, DEFAULT_IMAGE_FORMAT, path);
        } catch (WriterException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String readQRCodeImage(String filePath) {
        try {
            BufferedImage bufferedImage = ImageIO.read(Paths.get(filePath).toFile());
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(bufferedImage)));

            Result qrCodeResult = new MultiFormatReader().decode(binaryBitmap);
            return qrCodeResult.getText();
        } catch (IOException | NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void generateQRCodeImage(String text, String filePath) {
        generateQRCodeImage(text, filePath, DEFAULT_SIZE);
    }
}
