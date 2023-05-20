package gui;

import data.entidades.Evento;
import data.entidades.Espacio;
import data.entidades.Precio;
import data.entidades.User;

import remote.IRemoteFacade;
import controller.UserController;

import javax.swing.*;

public class TicketDetail extends JFrame {
    private final Evento evento;
    private final Espacio espacio;
    private final Precio precio;
    private final User user;
    private final UserController userController;

    public TicketDetail(Evento evento, Espacio espacio, Precio precio, User user, UserController userController) {
        this.evento = evento;
        this.espacio = espacio;
        this.precio = precio;
        this.user = user;
        this.userController = userController;
        initComponents();
    }

    private void initComponents() {
        this.setTitle("GuTicket - Confirmación de compra");
        this.setSize(400, 300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JLabel eventLabel = new JLabel("Evento: " + evento.getTitulo());
        JLabel dateLabel = new JLabel("Fecha: " + evento.getFecha()); // fecha del evento
        JLabel venueLabel = new JLabel("Lugar: " + espacio.getNombre()); // lugar del evento
        JLabel priceLabel = new JLabel("Precio: " + precio.getNombre() + " - " + precio.getValor());
        JButton buyButton = new JButton("Comprar ticket");
        buyButton.addActionListener(e -> {
            // Llamar al método buyTicket en UserController
            userController.buyTicket(evento, espacio, precio, user);
        });

        mainPanel.add(eventLabel);
        mainPanel.add(dateLabel);
        mainPanel.add(venueLabel);
        mainPanel.add(priceLabel);
        mainPanel.add(buyButton);

        this.getContentPane().add(mainPanel);
    }
}
