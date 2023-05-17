package gui;

import data.entidades.Artista;
import data.entidades.Espacio;
import data.entidades.Evento;
import data.entidades.Precio;
import remote.IRemoteFacade;

import javax.swing.*;
import java.awt.*;
import java.rmi.RemoteException;
import java.util.List;

public class EventDetail extends JFrame {
    private Evento evento;
    private IRemoteFacade remoteFacade;
    private List<Artista> artistas;
    private Espacio espacio;
    private List<Precio> precios;

    public EventDetail(Evento evento, IRemoteFacade remoteFacade) {
        this.remoteFacade = remoteFacade;
        this.evento = evento;
        try {
            this.artistas = this.remoteFacade.obtenerArtistas(evento.getId());
            this.espacio = this.remoteFacade.obtenerEspacioDeEvento(evento.getId());
            //this.precios = evento.getPrecios();
        } catch (RemoteException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar detalles del evento.");
        }
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GuTicket - Detalle del Evento");
        setSize(new Dimension(800, 600));
        getContentPane().setLayout(new GridLayout(2, 2));
        getContentPane().setBackground(new Color(54, 57, 63));

        // Panel de información del evento
        JPanel eventoPanel = new JPanel();
        eventoPanel.setLayout(new BoxLayout(eventoPanel, BoxLayout.Y_AXIS));
        eventoPanel.setBackground(new Color(54, 57, 63));
        eventoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel tituloLabel = new JLabel("Evento: " + evento.getTitulo());
        tituloLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
        tituloLabel.setForeground(new Color(114, 137, 218));
        eventoPanel.add(tituloLabel);

        JTextArea descripcionArea = new JTextArea("Descripción: " + evento.getDescripcion());
        descripcionArea.setLineWrap(true);
        descripcionArea.setWrapStyleWord(true);
        descripcionArea.setEditable(false);
        descripcionArea.setBackground(new Color(54, 57, 63));
        descripcionArea.setForeground(new Color(114, 137, 218));
        descripcionArea.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
        eventoPanel.add(descripcionArea);

        JLabel aforoLabel = new JLabel("Aforo: " + evento.getAforo());
        aforoLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
        aforoLabel.setForeground(new Color(114, 137, 218));
        eventoPanel.add(aforoLabel);

        getContentPane().add(eventoPanel);

        // Panel de información del espacio
        JPanel espacioPanel = new JPanel();
        espacioPanel.setLayout(new BorderLayout());
        espacioPanel.setBackground(new Color(54, 57, 63));
        espacioPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel espacioLabel = new JLabel("Espacio: " + espacio.getNombre());
        espacioLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
        espacioLabel.setForeground(new Color(114, 137, 218));
        espacioPanel.add(espacioLabel, BorderLayout.NORTH);

        JButton espacioButton = new JButton("Ver detalles del Espacio");
        // espacioButton.addActionListener(e -> new EspacioDetail(espacio));
        espacioPanel.add(espacioButton, BorderLayout.SOUTH);

        getContentPane().add(espacioPanel);

        // Panel de artistas
        JPanel artistasPanel = new JPanel();
        artistasPanel.setLayout(new BoxLayout(artistasPanel, BoxLayout.Y_AXIS));
        artistasPanel.setBackground(new Color(54, 57, 63));
        artistasPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel artistasLabel = new JLabel("En " + evento.getTitulo() + " disfrutarás de la música de:");
        artistasLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
        artistasLabel.setForeground(new Color(114, 137, 218));
        artistasPanel.add(artistasLabel);

        for (Artista artista : artistas) {
            JLabel artistaLabel = new JLabel("- " + artista.getNombre());
            artistaLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
            artistaLabel.setForeground(new Color(114, 137, 218));
            artistasPanel.add(artistaLabel);

            JButton artistaButton = new JButton("Ver detalles del Artista");
            // artistaButton.addActionListener(e -> new ArtistaDetail(artista));
            artistasPanel.add(artistaButton);
        }

        getContentPane().add(artistasPanel);

        // Panel de precios
        JPanel preciosPanel = new JPanel();
        preciosPanel.setLayout(new BoxLayout(preciosPanel, BoxLayout.Y_AXIS));
        preciosPanel.setBackground(new Color(54, 57, 63));
        preciosPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel preciosLabel = new JLabel("Entradas disponibles:");
        preciosLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
        preciosLabel.setForeground(new Color(114, 137, 218));
        preciosPanel.add(preciosLabel);

        // for (Precio precio : precios) {
        //     JLabel precioLabel = new JLabel(precio.getNombre() + ": " + precio.getValor());
        //     precioLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
        //     precioLabel.setForeground(new Color(114, 137, 218));
        //     preciosPanel.add(precioLabel);
        // }

        getContentPane().add(preciosPanel);

        pack();
        setLocationRelativeTo(null);
    }
}
