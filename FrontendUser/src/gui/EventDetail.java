package gui;

import data.entidades.Artista;
import data.entidades.Espacio;
import data.entidades.Evento;
import data.entidades.Precio;
import data.entidades.User;

import remote.IRemoteFacade;
import controller.UserController;

import javax.swing.*;
import java.awt.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class EventDetail extends JFrame {
    private final Evento evento;
    private final IRemoteFacade remoteFacade;
    private final List<Artista> artistas;
    private final Espacio espacio;
    private final List<Precio> precios;
    private final User user;
    private UserController userController;

    public EventDetail(Evento evento, IRemoteFacade remoteFacade, User user, UserController userController) {
        this.user = user;
        this.remoteFacade = remoteFacade;
        this.evento = evento;
        this.userController = userController;
        List<Artista> tempArtistas = new ArrayList<>();
        Espacio tempEspacio = null;
        List<Precio> tempPrecios = new ArrayList<>();
        try {
            tempArtistas = this.remoteFacade.obtenerArtistas(evento.getId());
            tempEspacio = this.remoteFacade.obtenerEspacioDeEvento(evento.getId());
            tempPrecios = evento.getPrecios();
            if (tempPrecios == null) {
                tempPrecios = new ArrayList<>();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar detalles del evento.");
        }
        this.artistas = tempArtistas;
        this.espacio = tempEspacio;
        this.precios = tempPrecios;
        initComponents();
    }

    private void initComponents() {
        this.setTitle("GuTicket - " + user.getNombre());
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(54, 57, 63));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("GuTicket - " + user.getNombre(), SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 24));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Top panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1, 2, 20, 0));
        topPanel.setBackground(new Color(54, 57, 63));
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.add(topPanel, BorderLayout.NORTH);

        JTextArea descripcionArea = new JTextArea(evento.getDescripcion());
        descripcionArea.setLineWrap(true);
        descripcionArea.setWrapStyleWord(true);
        descripcionArea.setEditable(false);
        descripcionArea.setBackground(new Color(54, 57, 63));
        descripcionArea.setForeground(new Color(114, 137, 218));
        descripcionArea.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
        descripcionArea.setBorder(BorderFactory.createLineBorder(new Color(114, 137, 218), 2));
        topPanel.add(descripcionArea);

        JButton espacioButton = new JButton("Donde? " + espacio.getNombre());
        espacioButton.setBackground(new Color(114, 137, 218));
        espacioButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        espacioButton.setForeground(Color.WHITE);
        espacioButton.setBorder(BorderFactory.createLineBorder(new Color(114, 137, 218), 2));
        espacioButton.addActionListener(e -> {
            EspacioDetail espacioDetail = new EspacioDetail(espacio, evento.getAforo());
            espacioDetail.setVisible(true);
        });
        topPanel.add(espacioButton);

        // Center panel
        JPanel artistasPanel = new JPanel();
        artistasPanel.setLayout(new BoxLayout(artistasPanel, BoxLayout.Y_AXIS)); // BoxLayout to center vertically
        artistasPanel.setBackground(new Color(54, 57, 63));
        artistasPanel.setBorder(BorderFactory.createLineBorder(new Color(114, 137, 218), 2));
        mainPanel.add(artistasPanel, BorderLayout.CENTER);

        // Panel for artist buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0)); // FlowLayout to place buttons side by side
        buttonPanel.setBackground(new Color(54, 57, 63));
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT); 
        for (Artista artista : artistas) {
            JButton artistaButton = new JButton(artista.getNombre());
            artistaButton.setBackground(Color.WHITE); // Fondo blanco
            artistaButton.setFont(new Font("Tahoma", Font.BOLD, 16));
            artistaButton.setForeground(new Color(114, 137, 218)); // Texto morado
            artistaButton.setBorder(BorderFactory.createLineBorder(new Color(114, 137, 218), 2)); // Borde morado
            artistaButton.setMargin(new Insets(10,20,10,20));
            artistaButton.addActionListener(e -> {
            ArtistDetail artistaDetail = new ArtistDetail(artista.getId(), remoteFacade);
            artistaDetail.setVisible(true);
            });
            buttonPanel.add(artistaButton);
        }

        artistasPanel.add(Box.createVerticalGlue()); // Glue to push buttonPanel to center
        artistasPanel.add(buttonPanel);
        artistasPanel.add(Box.createVerticalGlue());

        // Bottom panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, precios.size(), 10, 0));
        bottomPanel.setBackground(new Color(54, 57, 63));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        for (Precio precio : precios) {
            JButton precioButton = new JButton(precio.getNombre() + ": " + precio.getValor());
            precioButton.setBackground(new Color(114, 137, 218));
            precioButton.setFont(new Font("Tahoma", Font.BOLD, 14));
            precioButton.setForeground(Color.WHITE);
            bottomPanel.add(precioButton);
        }

        this.getContentPane().add(mainPanel);
    }
}
