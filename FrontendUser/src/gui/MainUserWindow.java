package gui;

import data.entidades.Evento;
import data.entidades.User;
import rest.TicketProviderClient;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;

public class MainUserWindow extends JFrame {
    private User user;

    public MainUserWindow(User user) {
        this.user = user;
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GuTicket - Ventana Principal");
        setSize(new Dimension(1400, 900));
        getContentPane().setLayout(new BorderLayout(0, 0));
        getContentPane().setBackground(new Color(54, 57, 63));

        // Bienvenida al usuario
        JLabel welcomeLabel = new JLabel("Bienvenido a GuTicket, " + user.getNombre());
        welcomeLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 24));
        welcomeLabel.setForeground(new Color(114, 137, 218));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(welcomeLabel, BorderLayout.NORTH);

        // Crea un panel contenedor para los eventos
        JPanel eventosPanel = new JPanel();
        eventosPanel.setLayout(new GridLayout(0, 2, 20, 20));
        eventosPanel.setBackground(new Color(54, 57, 63));
        eventosPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        TicketProviderClient client = new TicketProviderClient();
        try {
            List<Evento> eventos = client.getEventos();
            for (Evento evento : eventos) {
                System.out.println("Evento: " + evento.getTitulo() + ", Fecha: " + evento.getFecha() + ", Imagen URL: " + evento.getImagenUrl());
                eventosPanel.add(createEventoPanel(evento));
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar eventos");
            return; // Return to avoid showing an empty window when there's an error
        }

        // Agrega el panel de eventos a un JScrollPane
        JScrollPane scrollPane = new JScrollPane(eventosPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(null);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }

    private JPanel createEventoPanel(Evento evento) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        JPanel eventoPanel = new JPanel();
        eventoPanel.setLayout(new BorderLayout());
        eventoPanel.setBackground(new Color(47, 49, 54));
        eventoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Muestra la imagen del evento
        JLabel imageLabel = new JLabel();
        if (evento.getImagenUrl() != null && !evento.getImagenUrl().isEmpty()) {
            try {
                ImageIcon icon = new ImageIcon(new URL(evento.getImagenUrl()));
                icon = new ImageIcon(icon.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
                imageLabel.setIcon(icon);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } else {
            imageLabel.setText("Sin imagen");
            imageLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
            imageLabel.setForeground(new Color(114, 137, 218));
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        }
        eventoPanel.add(imageLabel, BorderLayout.CENTER);

        // Panel de información del evento
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(new Color(47, 49, 54));
        eventoPanel.add(infoPanel, BorderLayout.SOUTH);

        // Muestra el título del evento
        JLabel titleLabel = new JLabel(evento.getTitulo());
        titleLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
        titleLabel.setForeground(new Color(114, 137, 218));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoPanel.add(titleLabel);

        // Muestra la fecha del evento
        if (evento.getFecha() != null) {
            JLabel fechaLabel = new JLabel("Fecha: " + sdf.format(evento.getFecha()));
            fechaLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
            fechaLabel.setForeground(new Color(114, 137, 218));
            fechaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            infoPanel.add(fechaLabel);
        }

        return eventoPanel;
    }
}

