package gui;

import controller.StaffController;
import data.entidades.Staff;
import remote.ServiceLocator;
import javax.swing.*;
import java.awt.*;

public class LoginStaff extends javax.swing.JFrame {
    private StaffController staffController;
    private ServiceLocator serviceLocator;
    private JTextField jTextFieldLogin;
    private JPasswordField jPasswordField;

    public LoginStaff(ServiceLocator serviceLocator) {
        initComponents();
        staffController = new StaffController(serviceLocator);
    }
    

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GuTicket - Admin");

        // Create components
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Iniciar sesion en GuTicket");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(titleLabel, gbc);

        JLabel staffLabel = new JLabel("Usuario:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        mainPanel.add(staffLabel, gbc);

        jTextFieldLogin = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(jTextFieldLogin, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(passwordLabel, gbc);

        jPasswordField = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        mainPanel.add(jPasswordField, gbc);

        JButton jButtonLogin = new JButton("Iniciar sesion");
        jButtonLogin.addActionListener(evt -> jButtonLoginActionPerformed(evt));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        mainPanel.add(jButtonLogin, gbc);

        // Add the "Registrarse" button
        JButton jButtonRegister = new JButton("Registrarse");
        jButtonRegister.addActionListener(evt -> jButtonRegisterActionPerformed(evt));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        mainPanel.add(jButtonRegister, gbc);

        getContentPane().add(mainPanel);
        pack();
    }


    private void jButtonLoginActionPerformed(java.awt.event.ActionEvent evt) {
        String login = jTextFieldLogin.getText();
        String password = new String(jPasswordField.getPassword());

        Staff staff = staffController.loginStaff(login, password);

        if (staff != null) {
            System.out.println("LoginStaff: Login successful.");
            JOptionPane.showMessageDialog(this, "Inicio de sesion exitoso");
            // Lanza la ventana principal y cierra la ventana de inicio de sesion
            //new MainStaffWindow(staff).setVisible(true);
            this.dispose();
        } else {
            System.out.println("LoginStaff: Login failed.");
            JOptionPane.showMessageDialog(this, "Credenciales incorrectas");
        }
    }

    private void jButtonRegisterActionPerformed(java.awt.event.ActionEvent evt) {
        RegisterStaff registerStaffWindow = new RegisterStaff(serviceLocator);
        registerStaffWindow.setVisible(true);
        this.dispose();
    }
}