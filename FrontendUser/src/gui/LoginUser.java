package gui;

import controller.LoginController;
import data.entidades.User;
import remote.ServiceLocator;
import javax.swing.*;

public class LoginUser extends javax.swing.JFrame {
    private LoginController loginController;

    public LoginUser(ServiceLocator serviceLocator) {
        initComponents();
        loginController = new LoginController(serviceLocator);
    }

    private void initComponents() {
        // Crea e inicializa los componentes faltantes
        jTextFieldLogin = new JTextField();
        jPasswordField = new JPasswordField();
        JButton jButtonLogin = new JButton("Iniciar sesión");
        jButtonLogin.addActionListener(evt -> jButtonLoginActionPerformed(evt));

        // Añade los componentes a la ventana y establece su diseño
        // ...
    }

    private void jButtonLoginActionPerformed(java.awt.event.ActionEvent evt) {
        String login = jTextFieldLogin.getText();
        String password = new String(jPasswordField.getPassword());

        User user = loginController.loginUser(login, password);

        if (user != null) {
            JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso");
            // Lanza la ventana principal y cierra la ventana de inicio de sesión
        } else {
            JOptionPane.showMessageDialog(this, "Credenciales incorrectas");
        }
    }

    // Aquí va el código generado por el diseñador de la ventana
    private JTextField jTextFieldLogin;
    private JPasswordField jPasswordField;
}
