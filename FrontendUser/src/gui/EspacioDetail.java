package gui;

import data.entidades.Espacio;

import javax.swing.*;
import java.awt.*;

public class EspacioDetail extends JFrame {
    private final Espacio espacio;
    private final int aforo;

    public EspacioDetail(Espacio espacio, int aforo) {
        this.espacio = espacio;
        this.aforo = aforo;
        initComponents();
    }

    private void initComponents() {
        this.setTitle("GuTicket - V1");
        this.setSize(400, 300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(71, 61, 57));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Top panel for name
        JPanel namePanel = new JPanel();
        namePanel.setBackground(new Color(71, 61, 57));
        namePanel.setBorder(BorderFactory.createLineBorder(new Color(134, 108, 98), 2));
        mainPanel.add(namePanel, BorderLayout.NORTH);
        JLabel nameLabel = new JLabel(espacio.getNombre());
        nameLabel.setForeground(new Color(134, 108, 98));
        nameLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        namePanel.add(nameLabel);

        // Center panel for address
        JPanel addressPanel = new JPanel();
        addressPanel.setBackground(new Color(71, 61, 57));
        addressPanel.setBorder(BorderFactory.createLineBorder(new Color(134, 108, 98), 2));
        mainPanel.add(addressPanel, BorderLayout.CENTER);
        JLabel addressLabel = new JLabel("Direccion: " + espacio.getDireccion());
        addressLabel.setForeground(new Color(134, 108, 98));
        addressLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
        addressPanel.add(addressLabel);

        // Bottom panel for capacity
        JPanel capacityPanel = new JPanel();
        capacityPanel.setBackground(new Color(71, 61, 57));
        capacityPanel.setBorder(BorderFactory.createLineBorder(new Color(134, 108, 98), 2));
        mainPanel.add(capacityPanel, BorderLayout.SOUTH);
        JLabel capacityLabel = new JLabel("Aforo: " + aforo);
        capacityLabel.setForeground(new Color(134, 108, 98));
        capacityLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
        capacityPanel.add(capacityLabel);

        this.getContentPane().add(mainPanel);
    }
}
