package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import data.entidades.Ticket;
import controller.StaffController;

public class StaffControlTickets extends JFrame {
    private StaffController staffController;
    private JTable ticketsTable;
    private DefaultTableModel ticketsTableModel;

    public StaffControlTickets(StaffController staffController) {
        if (staffController == null) {
            throw new IllegalArgumentException("El objeto StaffController no puede ser nulo");
        }

        this.staffController = staffController;
        initComponents();
        getAllTickets();
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Admin - Control de Tickets");
        setPreferredSize(new Dimension(800, 600));
        getContentPane().setBackground(new Color(54, 57, 63));
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\ALUMNO\\Pictures\\Saved Pictures\\gu.png"));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(new Color(54, 57, 63));
        GridBagConstraints c = new GridBagConstraints();

        ticketsTableModel = new DefaultTableModel(new String[]{"ID", "Precio", "Titular", "DNI", "Valido"}, 0);
        ticketsTable = new JTable(ticketsTableModel);
        ticketsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JTableHeader header = ticketsTable.getTableHeader();
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                        boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setForeground(Color.WHITE);
                label.setBackground(new Color(220, 53, 69));
                label.setFont(label.getFont().deriveFont(Font.BOLD));
                return label;
            }
        });

        JScrollPane scrollPane = new JScrollPane(ticketsTable);
        scrollPane.setPreferredSize(new Dimension(600, 400));
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;  // Modificado para abarcar 2 columnas
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 10, 10, 10);
        mainPanel.add(scrollPane, c);

        JPanel buttonsPanel = new JPanel();  // Nuevo panel para los botones
        buttonsPanel.setLayout(new GridLayout(1, 2, 10, 0));
        buttonsPanel.setBackground(new Color(54, 57, 63)); 

        JButton deleteButton = new JButton("Eliminar Ticket");
        deleteButton.setBackground(new Color(220, 53, 69));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFocusPainted(false);
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = ticketsTable.getSelectedRow();
                if (selectedRow != -1) {
                    String id = (String) ticketsTable.getValueAt(selectedRow, 0);
                    int result = JOptionPane.showConfirmDialog(null,
                            "Estas seguro de que deseas eliminar el ticket con ID " + id + "?",
                            "Eliminar ticket",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE);

                    if (result == JOptionPane.YES_OPTION) {
                        eliminarTicket(id);
                        ticketsTableModel.removeRow(selectedRow);
                    }
                }
            }
        });
        buttonsPanel.add(deleteButton);  // Agrega el botón de eliminar al panel de botones

        JButton validateButton = new JButton("Validar Ticket");
        validateButton.setBackground(new Color(220, 53, 69));
        validateButton.setForeground(Color.WHITE);
        validateButton.setFocusPainted(false);
        validateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = ticketsTable.getSelectedRow();
                if (selectedRow != -1) {
                    String id = (String) ticketsTable.getValueAt(selectedRow, 0);
                    validarTicket(id);
                }
            }
        });
        buttonsPanel.add(validateButton);  // Agrega el botón de validar al panel de botones

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;  // Modificado para abarcar 2 columnas
        c.weightx = 1.0;
        c.weighty = 0.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10, 10, 10, 10);
        mainPanel.add(buttonsPanel, c);  // Agrega el panel de botones al panel principal

        getContentPane().add(mainPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }


    private void getAllTickets() {
        List<Ticket> tickets = staffController.getAllTickets();
        if (tickets == null) {
            JOptionPane.showMessageDialog(this, "Error al cargar tickets");
            return;
        }
        for (Ticket ticket : tickets) {
            ticketsTableModel.addRow(new Object[]{ticket.getId(), ticket.getPrecio(), ticket.getTitular(), ticket.getDni(), ticket.getValido()});
        }
    }

    private void eliminarTicket(String id) {
        staffController.removeTicketById(id);
        JOptionPane.showMessageDialog(this, "Ticket eliminado correctamente");
    }

    private void validarTicket(String id) {
        staffController.updateTicketValido(id);
        JOptionPane.showMessageDialog(this, "Ticket validado correctamente");
    }
}
