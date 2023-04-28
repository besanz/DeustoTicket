package gui;

import controller.LoginController;
import data.entidades.*;
import remote.ServiceLocator;
import javax.swing.*;

public class LoginStaff extends javax.swing.JFrame {
    private LoginController loginController;

    public LoginStaff(ServiceLocator serviceLocator) {
        initComponents();
        loginController = new LoginController(serviceLocator);
    }

    private void initComponents() {
        // Crea e inicializa los componentes faltantes
        jTextFieldLogin = new JTextField();
        jPasswordField = new JPasswordField();
        JButton jButtonLogin = new JButton("Iniciar sesion");
        jButtonLogin.addActionListener(evt -> jButtonLoginActionPerformed(evt));

        // Añade los componentes a la ventana y establece su diseño
        // ...
    }

    private void jButtonLoginActionPerformed(java.awt.event.ActionEvent evt) {
        String login = jTextFieldLogin.getText();
        String password = new String(jPasswordField.getPassword());

        Staff staff = loginController.loginStaff(login, password);

        if (staff != null) {
            JOptionPane.showMessageDialog(this, "Inicio de sesion exitoso");
            // Lanza la ventana principal y cierra la ventana de inicio de sesión
        } else {
            JOptionPane.showMessageDialog(this, "Credenciales incorrectas");
        }
    }


    // Aquí va el código generado por el diseñador de la ventana
    private JTextField jTextFieldLogin;
    private JPasswordField jPasswordField;
}
