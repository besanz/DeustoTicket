package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import data.entidades.Ticket;
import remote.IRemoteFacade;
import remote.ServiceLocator;

public class StaffControlTickets extends JFrame {
    private IRemoteFacade remoteFacade;
    private JTable ticketsTable;
    private DefaultTableModel ticketsTableModel;

    public StaffControlTickets(ServiceLocator serviceLocator) {
        if (serviceLocator == null) {
            throw new IllegalArgumentException("El objeto ServiceLocator no puede ser nulo");
        }

        this.remoteFacade = serviceLocator.getRemoteFacade();
        initComponents();
        getAllTickets();
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Admin - Control de Tickets");
        setPreferredSize(new Dimension(800, 600));
        getContentPane().setBackground(new Color(54, 57, 63));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(new Color(54, 57, 63));
        GridBagConstraints c = new GridBagConstraints();

        ticketsTableModel = new DefaultTableModel(new String[]{"ID", "Precio", "Valido"}, 0);
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
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 10, 10, 10);
        mainPanel.add(scrollPane, c);

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
                            "¿Estás seguro de que deseas eliminar el ticket con ID " + id + "?",
                            "Eliminar ticket",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE);

                    if (result == JOptionPane.YES_OPTION) {
                        removeTicket(id);
                        ticketsTableModel.removeRow(selectedRow);
                    }
                }
            }
        });
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(0, 0, 10, 0);
        mainPanel.add(deleteButton, c);

        JButton validateButton = new JButton("Validar Ticket");
        validateButton.setBackground(new Color(220, 53, 69));
        validateButton.setForeground(Color.WHITE);
        validateButton.setFocusPainted(false);
        validateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = ticketsTable.getSelectedRow();
                if (selectedRow != -1) {
                    String id = (String) ticketsTable.getValueAt(selectedRow, 0);
                    validateTicket(id);
                }
            }
        });
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(0, 0, 10, 0);
        mainPanel.add(validateButton, c);

        getContentPane().add(mainPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }

    private void getAllTickets() {
        try {
            List<Ticket> tickets = remoteFacade.getAllTickets();
            for (Ticket ticket : tickets) {
                ticketsTableModel.addRow(new Object[]{ticket.getId(), ticket.getPrecio(), ticket.getValido()});
            }
        } catch (RemoteException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar tickets");
        }
    }

    private void removeTicket(String id) {
        try {
            remoteFacade.removeTicketById(id);
            JOptionPane.showMessageDialog(this, "Ticket eliminado correctamente");
        } catch (RemoteException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al eliminar ticket");
        }
    }

    private void validateTicket(String id) {
        try {
            remoteFacade.updateTicketValido(id);
            JOptionPane.showMessageDialog(this, "Ticket validado correctamente");
        } catch (RemoteException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al validar ticket");
        }
    }
}
