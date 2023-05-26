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
    this.setTitle("GuTicket - " + espacio.getNombre());
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
    namePanel.setBackground(new Color(134, 108, 98));
    namePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0)); // Remove border
    mainPanel.add(namePanel, BorderLayout.NORTH);
    JLabel nameLabel = new JLabel(espacio.getNombre());
    nameLabel.setForeground(new Color(255, 255, 255));
    nameLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 24));
    namePanel.add(nameLabel);

    // Center panel for address
    JPanel addressPanel = new JPanel();
    addressPanel.setBackground(new Color(255, 255, 255));
    addressPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0)); // Remove border
    mainPanel.add(addressPanel, BorderLayout.CENTER);
    JLabel addressLabel = new JLabel("Direccion: " + espacio.getDireccion());
    addressLabel.setForeground(Color.BLACK);
    addressLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
    addressPanel.add(addressLabel);

    // Bottom panel for capacity
    JPanel capacityPanel = new JPanel();
    capacityPanel.setBackground(new Color(134, 108, 98));
    capacityPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0)); // Remove border
    mainPanel.add(capacityPanel, BorderLayout.SOUTH);
    JLabel capacityLabel = new JLabel("Aforo: " + aforo);
    capacityLabel.setForeground(new Color(255, 255, 255));
    capacityLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
    capacityPanel.add(capacityLabel);

    this.getContentPane().setBackground(new Color(255, 255, 255));
    this.getContentPane().add(mainPanel);
}

}
