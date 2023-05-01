package gui;

import data.entidades.Evento;
import data.entidades.User;
import rest.TicketProviderClient;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class MainUserWindow extends JFrame {
    private User user;

    public MainUserWindow(User user) {
        this.user = user;
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GuTicket - Ventana Principal");

        JPanel contentPane = new JPanel(new GridBagLayout());
        setContentPane(contentPane);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);

        // Bienvenida al usuario
        JLabel welcomeLabel = new JLabel("Bienvenido, " + user.getNombre());
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPane.add(welcomeLabel, gbc);

        // Crea la tabla y el modelo de datos
        String[] columnNames = {"ID", "Titulo", "Descripcion", "Fecha", "Aforo"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable eventosTable = new JTable(tableModel);

        // Llama al metodo getEventos y llena la tabla con los eventos
        TicketProviderClient client = new TicketProviderClient();
        try {
            List<Evento> eventos = client.getEventos();
            for (Evento evento : eventos) {
                Object[] rowData = {evento.getId(), evento.getTitulo(), evento.getDescripcion(), evento.getFecha(), evento.getAforo()};
                tableModel.addRow(rowData);
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar eventos");
            return; // Return to avoid showing an empty window when there's an error
        }

        // Agrega la tabla a la ventana
        JScrollPane tableScrollPane = new JScrollPane(eventosTable);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        contentPane.add(tableScrollPane, gbc);

        pack();
        setLocationRelativeTo(null);
    }
}
