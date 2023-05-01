package gui;

import controller.UserController;
import data.entidades.User;
import remote.ServiceLocator;
import javax.swing.*;
import java.awt.*;

public class LoginUser extends javax.swing.JFrame {
    private UserController userController;
    private ServiceLocator serviceLocator;
    private JTextField jTextFieldLogin;
    private JPasswordField jPasswordField;

    public LoginUser(ServiceLocator serviceLocator) {
        initComponents();
        userController = new UserController(serviceLocator);
    }
    

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GuTicket - Iniciar sesion");

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

        JLabel userLabel = new JLabel("Email:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        mainPanel.add(userLabel, gbc);

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

        User user = userController.loginUser(login, password);

        if (user != null) {
            System.out.println("LoginUser: Login successful.");
            JOptionPane.showMessageDialog(this, "Inicio de sesion exitoso");
            // Lanza la ventana principal y cierra la ventana de inicio de sesion
            new MainUserWindow(user).setVisible(true);
            this.dispose();
        } else {
            System.out.println("LoginUser: Login failed.");
            JOptionPane.showMessageDialog(this, "Credenciales incorrectas");
        }
    }

    private void jButtonRegisterActionPerformed(java.awt.event.ActionEvent evt) {
        RegisterUser registerUserWindow = new RegisterUser(serviceLocator);
        registerUserWindow.setVisible(true);
        this.dispose();
    }
}