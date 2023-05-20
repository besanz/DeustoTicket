package gui;

import controller.UserController;
import remote.ServiceLocator;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

import data.entidades.User;

public class RegisterUser extends JFrame {
    private ServiceLocator serviceLocator;
    private UserController userController;
    private JTextField jTextFieldDNI;
    private JTextField jTextFieldName;
    private JTextField jTextFieldSurname;
    private JTextField jTextFieldEmail;
    private JPasswordField jPasswordField;
    private JPasswordField jPasswordFieldConfirm;

    public RegisterUser(ServiceLocator serviceLocator) {
        initComponents();
        userController = new UserController(serviceLocator);
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GuTicket - Registro de usuario");
        setBounds(100, 100, 1000, 900);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(new Color(54, 57, 63));
        contentPane.add(mainPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel lblRegistro = new JLabel("Registro - GuTicket");
        lblRegistro.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 38));
        lblRegistro.setForeground(new Color(114, 137, 218));
        GridBagConstraints gbc_lblRegistro = new GridBagConstraints();
        gbc_lblRegistro.gridx = 0;
        gbc_lblRegistro.gridy = 0;
        gbc_lblRegistro.gridwidth = 2;
        gbc_lblRegistro.anchor = GridBagConstraints.CENTER;
        gbc_lblRegistro.insets = new Insets(0, 0, 40, 0);
        mainPanel.add(lblRegistro, gbc_lblRegistro);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(47, 49, 54));
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(114, 137, 218), 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.gridx = 0;
        gbc_panel.gridy = 1;
        mainPanel.add(panel, gbc_panel);

        // DNI label and text field
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel lblDni = new JLabel("DNI:");
        lblDni.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblDni.setForeground(Color.WHITE);
        panel.add(lblDni, gbc);
        jTextFieldDNI = new JTextField(20);
        jTextFieldDNI.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gbc.gridx = 1;
        panel.add(jTextFieldDNI, gbc);

        // Name label and text field
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel lblName = new JLabel("Nombre:");
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblName.setForeground(Color.WHITE);
        panel.add(lblName, gbc);
        jTextFieldName = new JTextField(20);
        jTextFieldName.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gbc.gridx = 1;
        panel.add(jTextFieldName, gbc);

        // Surname label and text field
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel lblSurname = new JLabel("Apellidos:");
        lblSurname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblSurname.setForeground(Color.WHITE);
        panel.add(lblSurname, gbc);
        jTextFieldSurname = new JTextField(20);
        jTextFieldSurname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gbc.gridx = 1;
        panel.add(jTextFieldSurname, gbc);

        // Email label and text field
        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblEmail.setForeground(Color.WHITE);
        panel.add(lblEmail, gbc);
        jTextFieldEmail = new JTextField(20);
        jTextFieldEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gbc.gridx = 1;
        panel.add(jTextFieldEmail, gbc);

        // Password label and password field
        gbc.gridx = 0;
        gbc.gridy = 4;
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblPassword.setForeground(Color.WHITE);
        panel.add(lblPassword, gbc);
        jPasswordField = new JPasswordField(20);
        jPasswordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gbc.gridx = 1;
        panel.add(jPasswordField, gbc);

        // Confirm password label and password field
        gbc.gridx = 0;
        gbc.gridy = 5;
        JLabel lblConfirmPassword = new JLabel("Confirmar password:");
        lblConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblConfirmPassword.setForeground(Color.WHITE);
        panel.add(lblConfirmPassword, gbc);
        jPasswordFieldConfirm = new JPasswordField(20);
        jPasswordFieldConfirm.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gbc.gridx = 1;
        panel.add(jPasswordFieldConfirm, gbc);

        // Register button
        JButton jButtonRegister = new JButton("Registrarse");
        jButtonRegister.addActionListener(evt -> jButtonRegisterActionPerformed(evt));
        jButtonRegister.setBackground(new Color(114, 137, 218));
        jButtonRegister.setFont(new Font("Tahoma", Font.BOLD, 16));
        jButtonRegister.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(jButtonRegister, gbc);

        pack();
        setLocationRelativeTo(null);
    }

    private void jButtonRegisterActionPerformed(ActionEvent evt) {
        String dni = jTextFieldDNI.getText();
        String name = jTextFieldName.getText();
        String surname = jTextFieldSurname.getText();
        String email = jTextFieldEmail.getText();
        String password = new String(jPasswordField.getPassword());
        String confirmPassword = new String(jPasswordFieldConfirm.getPassword());
      
        if(dni != null && name != null && email != null && surname != null && password != null)
        {
            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden");
                return;
            }
            User newUser = userController.registerUser(dni, name, surname, email, password);
                    if (newUser != null) {
                JOptionPane.showMessageDialog(this, "Usuario registrado con exito");
                new LoginUser(serviceLocator).setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error al registrar usuario");
            }
        }else{
            JOptionPane.showMessageDialog(this, "Por favor, rellena todos los campos");
        }
    }
}