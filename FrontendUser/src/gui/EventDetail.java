package gui;

import data.entidades.Evento;
import remote.IRemoteFacade;

import javax.swing.*;
import java.awt.*;

public class EventDetail extends JFrame {
    private Evento evento;
    private IRemoteFacade remoteFacade;

    public EventDetail(Evento evento, IRemoteFacade remoteFacade) {
        this.evento = evento;
        this.remoteFacade = remoteFacade;
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
        infoPanel.setLayout(new GridLayout(0, 2));
        infoPanel.setBackground(new Color(54, 57, 63));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        getContentPane().add(infoPanel, BorderLayout.CENTER);

        JLabel aforoLabel = new JLabel("Aforo: " + evento.getAforo());
        aforoLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
        aforoLabel.setForeground(new Color(114, 137, 218));
        infoPanel.add(aforoLabel);

        pack();
        setLocationRelativeTo(null);
    }
}
