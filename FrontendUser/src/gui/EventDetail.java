package gui;

import data.entidades.Artista;
import data.entidades.Espacio;
import data.entidades.Evento;
import data.entidades.Precio;
import data.entidades.User;

import remote.IFacadeUser;
import controller.UserController;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
import java.awt.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class EventDetail extends JFrame {
    private final Evento evento;
    private final IFacadeUser remoteFacade;
    private final List<Artista> artistas;
    private final Espacio espacio;
    private final List<Precio> precios;
    private final User user;
    private UserController userController;

    public EventDetail(Evento evento, IFacadeUser remoteFacade, User user, UserController userController) {
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
        this.setTitle("GuTicket - " + evento.getTitulo());
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\ALUMNO\\Desktop\\GuTicket\\FrontendUser\\src\\gui\\element\\gu.png"));

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
        descripcionArea.setBackground(new Color(114, 137, 218));
        descripcionArea.setForeground(Color.WHITE);
        descripcionArea.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
        descripcionArea.setBorder(BorderFactory.createLineBorder(new Color(114, 137, 218), 2));
        topPanel.add(descripcionArea);

        JButton espacioButton = new JButton(espacio.getNombre());
        espacioButton.setBackground(new Color(114, 137, 218));
        espacioButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
        espacioButton.setForeground(Color.WHITE);
        espacioButton.setBorder(BorderFactory.createLineBorder(new Color(114, 137, 218), 2));
        
        espacioButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
                EspacioDetail espacioDetail = new EspacioDetail(espacio, evento.getAforo());
                espacioDetail.setVisible(true);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                    espacioButton.setBackground(Color.WHITE);
                    espacioButton.setForeground(new Color(114, 137, 218));
                    espacioButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 26));
                    
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    espacioButton.setBackground(new Color(114, 137, 218)); 
                    espacioButton.setForeground(Color.WHITE);
                    espacioButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
                   
                }
        });
        topPanel.add(espacioButton);

        // Center panel
        JPanel artistasPanel = new JPanel();
        artistasPanel.setLayout(new BoxLayout(artistasPanel, BoxLayout.Y_AXIS)); // BoxLayout to center vertically
        artistasPanel.setBackground(new Color(54, 57, 63));
        artistasPanel.setBorder(BorderFactory.createLineBorder(new Color(54, 57, 63), 2));
        mainPanel.add(artistasPanel, BorderLayout.CENTER);

        // Panel for artist buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0)); // FlowLayout para colocar los botones uno al lado del otro
        buttonPanel.setBackground(new Color(54, 57, 63));
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT); 

        for (Artista artista : artistas) {
            JButton artistaButton = new JButton(artista.getNombre());
            artistaButton.setBackground(Color.WHITE);
            artistaButton.setForeground(new Color(114, 137, 218));
            artistaButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
            
            // Establecer márgenes y padding
            artistaButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Sin borde, solo espacio interno
            artistaButton.setContentAreaFilled(false); // No rellenar área de contenido
            
            // Establecer borde personalizado
            artistaButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(114, 137, 218), 2), // Borde morado
                BorderFactory.createEmptyBorder(5, 10, 5, 10) // Espacio entre el borde y el contenido
            ));
            
            artistaButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    ArtistDetail artistaDetail = new ArtistDetail(artista.getId(), remoteFacade);
                    artistaDetail.setVisible(true);
                }
                
                @Override
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    artistaButton.setBackground(new Color(114, 137, 218)); 
                    artistaButton.setForeground(Color.WHITE);
                    artistaButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
                }
                
                @Override
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    artistaButton.setBackground(Color.WHITE);
                    artistaButton.setForeground(new Color(114, 137, 218));
                    artistaButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
                }
            });
            
            buttonPanel.add(artistaButton);
        }

        artistasPanel.add(Box.createVerticalGlue()); // Glue para centrar buttonPanel
        artistasPanel.add(buttonPanel);
        artistasPanel.add(Box.createVerticalGlue());


        // Bottom panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, precios.size(), 10, 0));
        bottomPanel.setBackground(new Color(54, 57, 63));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        for (Precio precio : precios) {
            String[] stringSplit = precio.getNombre().split("-");
            String botonTexto = stringSplit[0] + ": $" + precio.getValor();
            JButton precioButton = new JButton(botonTexto);
            precioButton.setBackground(new Color(114, 137, 218));
            precioButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
            precioButton.setForeground(Color.WHITE);
            precioButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
                TicketDetail ticketDetail = new TicketDetail(evento, espacio, precio, user, userController);
                ticketDetail.setVisible(true);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                    precioButton.setBackground(Color.WHITE);
                    precioButton.setForeground(new Color(114, 137, 218));
                    
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    precioButton.setBackground(new Color(114, 137, 218)); 
                    precioButton.setForeground(Color.WHITE);
                   
                }
        });
            bottomPanel.add(precioButton);
        }

        this.getContentPane().add(mainPanel);
    }
}
