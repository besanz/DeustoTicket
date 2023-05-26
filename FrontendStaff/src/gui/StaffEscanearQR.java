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
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import controller.StaffController;


public class StaffEscanearQR extends JFrame {
    private JPanel contentPane;
    private Webcam webcam;
    private MultiFormatReader qrCodeReader;
    private StaffController staffController;
    private Timer timer;
    private final long QR_WAIT_TIME = 10000; // 10 seconds

    public StaffEscanearQR(StaffController staffController) {
        if (staffController == null) {
            throw new IllegalArgumentException("El objeto StaffController no puede ser nulo");
        }

        this.staffController = staffController;
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Admin - Escanear QR");
        setBounds(100, 100, 640, 480);
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\ALUMNO\\Desktop\\GuTicket\\FrontendUser\\src\\gui\\element\\gu.png"));

        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);
        qrCodeReader = new MultiFormatReader();
        timer = new Timer();
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
        executor.scheduleAtFixedRate(this::scanQRCode, 0, 2000, TimeUnit.MILLISECONDS);
        pack();
        setVisible(true);
    }

    private void scanQRCode() {
        BufferedImage image = webcam.getImage();
        if (image != null) {
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
            try {
                Result qrCodeResult = qrCodeReader.decode(binaryBitmap);

                if (qrCodeResult != null) {
                    timer.cancel(); // Stop timer when QR is found
                    String qrCodeText = qrCodeResult.getText();
                    staffController.updateTicketValido(qrCodeText);
                    Toolkit.getDefaultToolkit().beep(); // Sound alert
                    JOptionPane.showMessageDialog(this, "Ticket Validado");
                    timer = new Timer(); // Reset timer after QR is found
                } 
            } catch (Exception e) {
                // No QR code detected, start timer to wait 10 seconds
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        System.out.println("No se ha detectado QR");
                    }
                }, QR_WAIT_TIME);
            }
        }
    }
}
