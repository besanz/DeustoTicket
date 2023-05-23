package gui;

import data.entidades.Artista;
import remote.IFacadeUser;
import java.rmi.RemoteException;

import javax.swing.*;
import java.awt.*;
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
        this.setTitle("GuTicket - V1");
        this.setSize(400, 300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(54, 57, 63));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Top panel for name
        JPanel namePanel = new JPanel();
        namePanel.setBackground(new Color(54, 57, 63));
        namePanel.setBorder(BorderFactory.createLineBorder(new Color(114, 137, 218), 2));
        mainPanel.add(namePanel, BorderLayout.NORTH);
        JLabel nameLabel = new JLabel(artista.getNombre());
        nameLabel.setForeground(new Color(114, 137, 218));
        nameLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        namePanel.add(nameLabel);

        // Center panel for description
        JPanel descPanel = new JPanel();
        descPanel.setBackground(new Color(54, 57, 63));
        descPanel.setBorder(BorderFactory.createLineBorder(new Color(114, 137, 218), 2));
        mainPanel.add(descPanel, BorderLayout.CENTER);
        JTextArea descArea = new JTextArea(artista.getDescripcion());
        descArea.setLineWrap(true);
        descArea.setWrapStyleWord(true);
        descArea.setEditable(false);
        descArea.setBackground(new Color(54, 57, 63));
        descArea.setForeground(new Color(114, 137, 218));
        descArea.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
        descArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        descPanel.add(descArea);

        // Bottom panel for birthdate
        JPanel birthPanel = new JPanel();
        birthPanel.setBackground(new Color(54, 57, 63));
        birthPanel.setBorder(BorderFactory.createLineBorder(new Color(114, 137, 218), 2));
        mainPanel.add(birthPanel, BorderLayout.SOUTH);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        JLabel birthLabel = new JLabel("Fecha de nacimiento: " + dateFormat.format(artista.getFechaNacimiento()));
        birthLabel.setForeground(new Color(114, 137, 218));
        birthLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
        birthPanel.add(birthLabel);

        this.getContentPane().add(mainPanel);
    }
}
