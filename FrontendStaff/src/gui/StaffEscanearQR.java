package gui;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import remote.IRemoteFacade;
import controller.StaffController;

public class StaffEscanearQR extends JFrame {
    private JPanel contentPane;
    private Webcam webcam;
    private MultiFormatReader qrCodeReader;
    private IRemoteFacade remoteFacade;

    public StaffEscanearQR(StaffController staffController) {
        this.remoteFacade = staffController.getRemoteFacade();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Admin - Escanear QR");
        setBounds(100, 100, 858, 516);
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);
        qrCodeReader = new MultiFormatReader();
    }

    public void initializeWebcam() {
        List<Webcam> webcams = Webcam.getWebcams();
        if (webcams.size() > 1) {
            webcam = webcams.get(1);
        } else {
            webcam = Webcam.getDefault();
        }
        webcam.open();

        WebcamPanel panel = new WebcamPanel(webcam);
        panel.setPreferredSize(new Dimension(640, 480));
        contentPane.add(panel, BorderLayout.CENTER);

        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(this::scanQRCode, 0, 500, TimeUnit.MILLISECONDS);
        pack();
        setVisible(true);
    }

    private void scanQRCode() {
        BufferedImage image = webcam.getImage();
        if (image != null) {
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
            try {
                Result qrCodeResult = qrCodeReader.decode(binaryBitmap);
                String qrCodeText = qrCodeResult.getText();
                remoteFacade.updateTicketValido(qrCodeText);
                System.out.println("QR Code detected: " + qrCodeText);
            } catch (Exception e) {
                // No se detectó ningún código QR, simplemente ignorar
            }
        }
    }
}
