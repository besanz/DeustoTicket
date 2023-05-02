package gui;

import controller.StaffController;
import data.entidades.Staff;
import remote.ServiceLocator;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginStaff extends JFrame {

    private StaffController staffController;
    private ServiceLocator serviceLocator;
    private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

    public LoginStaff(ServiceLocator serviceLocator) {
        initComponents();
        staffController = new StaffController(serviceLocator);
    }
    

 private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GuTicket - Iniciar sesion");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(new Color(54, 57, 63));
        contentPane.add(mainPanel, BorderLayout.CENTER);

        JLabel lblInicioDeSesion = new JLabel("Admin");
        lblInicioDeSesion.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 38));
        lblInicioDeSesion.setForeground(new Color(114, 137, 218));
        GridBagConstraints gbc_lblInicioDeSesion = new GridBagConstraints();
        gbc_lblInicioDeSesion.gridx = 0;
        gbc_lblInicioDeSesion.gridy = 0;
        gbc_lblInicioDeSesion.anchor = GridBagConstraints.CENTER;
        gbc_lblInicioDeSesion.insets = new Insets(130, 0, 20, 0); // Incrementa el espacio superior a 60
        mainPanel.add(lblInicioDeSesion, gbc_lblInicioDeSesion);

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

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel lblEmail = new JLabel("Username: ");
        lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblEmail.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(lblEmail, gbc);

        textField = new JTextField(20);
        textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(textField, gbc);

        JLabel lblPassword = new JLabel("Password: ");
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblPassword.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(lblPassword, gbc);

        passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(passwordField, gbc);

        JButton btnIniciarSesion = new JButton("Iniciar Sesion");
        btnIniciarSesion.addActionListener(evt -> jButtonLoginActionPerformed(evt));
        btnIniciarSesion.setBackground(new Color(114, 137, 218));
        btnIniciarSesion.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnIniciarSesion.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(btnIniciarSesion, gbc);

        JButton btnRegistro = new JButton("No tienes cuenta? Registrate");
        btnRegistro.addActionListener(evt -> jButtonRegisterActionPerformed(evt));
        btnRegistro.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnRegistro.setForeground(Color.WHITE);
        btnRegistro.setContentAreaFilled(false);
        btnRegistro.setBorderPainted(false);
        btnRegistro.setOpaque(false);
        btnRegistro.setFocusPainted(false);
        btnRegistro.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(btnRegistro, gbc);

 }


    private void jButtonLoginActionPerformed(java.awt.event.ActionEvent evt) {
        String login = textField.getText();
        String password = new String(passwordField.getPassword());

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