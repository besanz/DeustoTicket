package gui;

import data.entidades.Artista;
import remote.IFacadeUser;

import javax.swing.*;
import java.awt.*;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;

public class ArtistDetail extends JFrame {
    private final Artista artista;
    private final IFacadeUser remoteFacade;

    public ArtistDetail(int artistaID, IFacadeUser remoteFacade) {
        this.remoteFacade = remoteFacade;
        Artista tempArtista = null;
        try {
            tempArtista = this.remoteFacade.obtenerArtistaPorID(artistaID);
        } catch (RemoteException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar el artista.");
        }
        artista = tempArtista;
        initComponents();
    }

    private void initComponents() {
        this.setTitle("GuTicket - " + artista.getNombre());
        this.setSize(400, 300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\ALUMNO\\Desktop\\GuTicket\\FrontendUser\\src\\gui\\element\\gu.png"));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(255, 255, 255));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Top panel for name
        JPanel namePanel = new JPanel();
        namePanel.setBackground(new Color(114, 137, 218));
        mainPanel.add(namePanel, BorderLayout.NORTH);
        JLabel nameLabel = new JLabel(artista.getNombre());
        nameLabel.setForeground(new Color(255, 255, 255));
        nameLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 24));
        namePanel.add(nameLabel);

        // Center panel for description
        JTextArea descArea = new JTextArea(artista.getDescripcion());
        JScrollPane scrollPane = new JScrollPane(descArea);
        descArea.setLineWrap(true);
        descArea.setWrapStyleWord(true);
        descArea.setEditable(false);
        descArea.setBackground(new Color(255, 255, 255));
        descArea.setForeground(new Color(0, 0, 0));
        descArea.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Bottom panel for birthdate
        JPanel birthPanel = new JPanel();
        birthPanel.setBackground(new Color(114, 137, 218));
        mainPanel.add(birthPanel, BorderLayout.SOUTH);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        JLabel birthLabel = new JLabel("Fecha de nacimiento: " + dateFormat.format(artista.getFechaNacimiento()));
        birthLabel.setForeground(new Color(255, 255, 255));
        birthLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
        birthPanel.add(birthLabel);

        this.getContentPane().setBackground(new Color(255, 255, 255));
        this.getContentPane().add(mainPanel);
    }
}
