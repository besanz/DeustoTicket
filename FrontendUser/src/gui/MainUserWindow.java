package gui;

import data.entidades.Evento;
import data.entidades.User;
import rest.TicketProviderClient;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
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
        setSize(new Dimension(800, 600));
        getContentPane().setLayout(new BorderLayout(0, 0));
        getContentPane().setBackground(new Color(54, 57, 63));

        // Bienvenida al usuario
        JLabel welcomeLabel = new JLabel("Bienvenido a GuTicket, " + user.getNombre());
        welcomeLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 24));
        welcomeLabel.setForeground(new Color(114, 137, 218));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(welcomeLabel, BorderLayout.NORTH);

        // Crea la tabla y el modelo de datos
        String[] columnNames = {"ID", "Titulo", "Descripcion", "Fecha", "Aforo"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable eventosTable = new JTable(tableModel);
        eventosTable.getTableHeader().setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
        eventosTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
        eventosTable.setRowHeight(25);

        // Ajusta el color y la alineación de las celdas
        eventosTable.setForeground(new Color(114, 137, 218));
        eventosTable.setBackground(new Color(54, 57, 63));
        eventosTable.getTableHeader().setForeground(new Color(54, 57, 63));
        eventosTable.getTableHeader().setBackground(new Color(114, 137, 218));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < eventosTable.getColumnCount(); i++) {
            eventosTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Llama al método getEventos y llena la tabla con los eventos
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
        tableScrollPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        getContentPane().add(tableScrollPane, BorderLayout.CENTER);

        // Secciones inferiores
        JPanel bottomPanel = new JPanel(new GridLayout(1, 2));
        getContentPane().add(bottomPanel, BorderLayout.SOUTH);

        JPanel section1Panel = new JPanel();
        section1Panel.setBorder(BorderFactory.createLineBorder(new Color(114, 137, 218)));
        section1Panel.setBackground(new Color(47, 49, 54));
        bottomPanel.add(section1Panel);

        JPanel section2Panel = new JPanel();
        section2Panel.setBorder(BorderFactory.createLineBorder(new Color(114, 137, 218)));
        section2Panel.setBackground(new Color(47, 49, 54));
        section2Panel.setBackground(new Color(47, 49, 54));
        bottomPanel.add(section2Panel);

        pack();
        setLocationRelativeTo(null);
    }
}

