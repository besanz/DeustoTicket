package gui;

import controller.StaffController;
import data.entidades.Staff;
import remote.ServiceLocator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RegisterStaff extends JFrame {
    private StaffController staffController;
    private JTextField jTextFieldUsername;
    private JPasswordField jPasswordField;
    private JPasswordField jPasswordFieldConfirm;

    public RegisterStaff(ServiceLocator serviceLocator) {
        initComponents();
        staffController = new StaffController(serviceLocator);
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GuTicket - Registro de Staff");

        JPanel contentPane = new JPanel(new GridBagLayout());
        setContentPane(contentPane);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);

        // Username label and text field
        gbc.gridx = 0;
        gbc.gridy = 3;
        contentPane.add(new JLabel("Usuario:"), gbc);
        jTextFieldUsername = new JTextField(20);
        gbc.gridx = 1;
        contentPane.add(jTextFieldUsername, gbc);

        // Password label and password field
        gbc.gridx = 0;
        gbc.gridy = 4;
        contentPane.add(new JLabel("Password:"), gbc);
        jPasswordField = new JPasswordField(20);
        gbc.gridx = 1;
        contentPane.add(jPasswordField, gbc);

        // Confirm password label and password field
        gbc.gridx = 0;
        gbc.gridy = 5;
        contentPane.add(new JLabel("Confirmar password:"), gbc);
        jPasswordFieldConfirm = new JPasswordField(20);
        gbc.gridx = 1;
        contentPane.add(jPasswordFieldConfirm, gbc);

        // Register button
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton jButtonRegister = new JButton("Registrarse");
        jButtonRegister.addActionListener(evt -> jButtonRegisterActionPerformed(evt));
        contentPane.add(jButtonRegister, gbc);

        pack();
        setLocationRelativeTo(null);
    }

    private void jButtonRegisterActionPerformed(ActionEvent evt) {
        String username = jTextFieldUsername.getText();
        String password = new String(jPasswordField.getPassword());
        String confirmPassword = new String(jPasswordFieldConfirm.getPassword());

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden");
            return;
        }

        Staff newStaff = staffController.registerStaff(username, password);
                if (newStaff != null) {
            JOptionPane.showMessageDialog(this, "Staff registrado con exito");
            // Lanza la ventana principal y cierra la ventana de registro
            // Ejemplo: new MainStaffWindow(newStaff).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Error al registrar usuario");
        }
    }
}