
package gui;

import data.entidades.User;
import remote.IRemoteFacade;
import remote.ServiceLocator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.List;

public class MainStaffWindow extends JFrame {
    private IRemoteFacade remoteFacade;
    private JTable usersTable;
    private DefaultTableModel usersTableModel;

    public MainStaffWindow(ServiceLocator serviceLocator) {
        // Obtiene el objeto IRemoteFacade a partir de ServiceLocator
        this.remoteFacade = serviceLocator.getRemoteFacade();
        initComponents();
        getAllUsers();
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GuTicket - Staff");
        setSize(new Dimension(800, 600));
        getContentPane().setLayout(new BorderLayout(0, 0));
        getContentPane().setBackground(new Color(54, 57, 63));

        usersTableModel = new DefaultTableModel(new String[]{"DNI", "Nombre", "Apellidos", "Email"}, 0);
        usersTable = new JTable(usersTableModel);
        usersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(usersTable);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        JButton deleteButton = new JButton("Eliminar");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = usersTable.getSelectedRow();
                if (selectedRow != -1) {
                    String dni = (String) usersTable.getValueAt(selectedRow, 0);
                    int result = JOptionPane.showConfirmDialog(null,
                            "¿Está seguro de que desea eliminar al usuario con DNI " + dni + "?",
                            "Eliminar usuario",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE);

                    if (result == JOptionPane.YES_OPTION) {
                        deleteUser(dni);
                        usersTableModel.removeRow(selectedRow);
                    }
                }
            }
        });
        getContentPane().add(deleteButton, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    private void getAllUsers() {
        try {
            List<User> users = remoteFacade.findAllUsers();
            for (User user : users) {
                usersTableModel.addRow(new Object[]{user.getDni(), user.getNombre(), user.getApellidos(), user.getEmail()});
            }
        } catch (RemoteException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar usuarios");
        }
    }

    private void deleteUser(String dni) {
        try {
            remoteFacade.deleteUserByDni(dni);
            JOptionPane.showMessageDialog(this, "Usuario eliminado correctamente");
        } catch (RemoteException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al eliminar usuario");
        }
    }
}
