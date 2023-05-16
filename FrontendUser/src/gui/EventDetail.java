package gui;

import data.entidades.Artista;
import data.entidades.Espacio;
import data.entidades.Evento;
import remote.IRemoteFacade;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;

public class EventDetail extends JFrame {
    private Evento evento;
    private IRemoteFacade remoteFacade;
    private List<Artista> artistas;
    private Espacio espacio;

    public EventDetail(Evento evento, IRemoteFacade remoteFacade) {
        this.remoteFacade = remoteFacade;
        this.evento = evento;
        try {
            this.artistas = this.remoteFacade.obtenerArtistas(evento.getId());
            this.espacio = this.remoteFacade.obtenerEspacioDeEvento(evento.getId());
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
        getContentPane().setLayout(new BorderLayout(0, 0));
        getContentPane().setBackground(new Color(54, 57, 63));

        JLabel titleLabel = new JLabel(evento.getTitulo());
        titleLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 30));
        titleLabel.setForeground(new Color(114, 137, 218));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(40, 0, 40, 0));
        getContentPane().add(titleLabel, BorderLayout.NORTH);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(0, 1));
        infoPanel.setBackground(new Color(54, 57, 63));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel descripcionLabel = new JLabel("Descripción: " + evento.getDescripcion());
        descripcionLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
        descripcionLabel.setForeground(new Color(114, 137, 218));
        infoPanel.add(descripcionLabel);

        // Como Artista no tiene un atributo de precio, no podemos mostrar el precio de cada Artista. 

        JLabel espacioLabel = new JLabel("Espacio: " + espacio.getNombre());
        espacioLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
        espacioLabel.setForeground(new Color(114, 137, 218));
        infoPanel.add(espacioLabel);

        getContentPane().add(infoPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }
}
