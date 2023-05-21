package gui;

import data.entidades.Evento;
import data.entidades.Espacio;
import data.entidades.Precio;
import data.entidades.User;

import remote.IRemoteFacade;
import java.io.IOException;

import controller.UserController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicketDetail extends JFrame {
    private final Evento evento;
    private final Espacio espacio;
    private final Precio precio;
    private final User user;
    private final UserController userController;
    private IRemoteFacade remoteFacade;

    public TicketDetail(Evento evento, Espacio espacio, Precio precio, User user, UserController userController) {
        this.evento = evento;
        this.espacio = espacio;
        this.precio = precio;
        this.user = user;
        this.userController = userController;
        this.remoteFacade = userController.getRemoteFacade();
        initComponents();
    }

    private void initComponents() {
        setTitle("GuTicket - Confirmación de compra");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(255, 255, 255));
        mainPanel.setLayout(new BorderLayout());

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(4, 1));
        infoPanel.setBackground(new Color(230, 230, 230));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel eventLabel = new JLabel("Evento: " + evento.getTitulo());
        eventLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel dateLabel = new JLabel("Fecha: " + evento.getFecha());
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel venueLabel = new JLabel("Lugar: " + espacio.getNombre());
        venueLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel priceLabel = new JLabel("Precio: $" + precio.getValor());
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        infoPanel.add(eventLabel);
        infoPanel.add(dateLabel);
        infoPanel.add(venueLabel);
        infoPanel.add(priceLabel);

        JButton buyButton = new JButton("Comprar ticket");
        buyButton.setBackground(new Color(114, 137, 218));
        buyButton.setForeground(Color.WHITE);
        buyButton.setFont(new Font("Arial", Font.BOLD, 16));
        buyButton.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));
        buyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                userController.buyTicket(evento, espacio, precio, user);
                System.out.println("Precio ID: " + precio.getId());
                try {
                    Precio updatedPrecio = remoteFacade.getPrecioByID(precio.getId());
                    remoteFacade.updateTickets(updatedPrecio);
                } catch (IOException ex) {
                    System.out.println("Error al actualizar los tickets: " + ex.getMessage());
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(255, 255, 255));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        buttonPanel.add(buyButton);

        mainPanel.add(infoPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        getContentPane().add(mainPanel);
    }
}
