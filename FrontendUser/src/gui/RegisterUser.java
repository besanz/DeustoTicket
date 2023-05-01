package gui;

import controller.UserController;
import data.entidades.User;
import remote.ServiceLocator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RegisterUser extends JFrame {
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
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GuTicket - Registro de usuario");

        JPanel contentPane = new JPanel(new GridBagLayout());
        setContentPane(contentPane);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);

        // DNI label and text field
        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPane.add(new JLabel("DNI:"), gbc);
        jTextFieldDNI = new JTextField(20);
        gbc.gridx = 1;
        contentPane.add(jTextFieldDNI, gbc);

        // Name label and text field
        gbc.gridx = 0;
        gbc.gridy = 1;
        contentPane.add(new JLabel("Nombre:"), gbc);
        jTextFieldName = new JTextField(20);
        gbc.gridx = 1;
        contentPane.add(jTextFieldName, gbc);

        // Surname label and text field
        gbc.gridx = 0;
        gbc.gridy = 2;
        contentPane.add(new JLabel("Apellidos:"), gbc);
        jTextFieldSurname = new JTextField(20);
        gbc.gridx = 1;
        contentPane.add(jTextFieldSurname, gbc);

        // Email label and text field
        gbc.gridx = 0;
        gbc.gridy = 3;
        contentPane.add(new JLabel("Email:"), gbc);
        jTextFieldEmail = new JTextField(20);
        gbc.gridx = 1;
        contentPane.add(jTextFieldEmail, gbc);

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
        String dni = jTextFieldDNI.getText();
        String name = jTextFieldName.getText();
        String surname = jTextFieldSurname.getText();
        String email = jTextFieldEmail.getText();
        String password = new String(jPasswordField.getPassword());
        String confirmPassword = new String(jPasswordFieldConfirm.getPassword());

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden");
            return;
        }

        User newUser = userController.registerUser(dni, name, surname, email, password);
                if (newUser != null) {
            JOptionPane.showMessageDialog(this, "Usuario registrado con éxito");
            // Lanza la ventana principal y cierra la ventana de registro
            // Ejemplo: new MainUserWindow(newUser).setVisible(true);
            // this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Error al registrar usuario");
        }
    }
}
