package gui;

import controller.StaffController;
import data.entidades.Staff;
import remote.ServiceLocator;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RegisterStaff extends JFrame {
    private StaffController staffController;
    private JTextField jUsernameField;
    private JPasswordField jPasswordField;
    private JPasswordField jPasswordFieldConfirm;
    private ServiceLocator serviceLocator;

    public RegisterStaff(ServiceLocator serviceLocator) {
        initComponents();
        this.serviceLocator = serviceLocator;
        staffController = new StaffController(serviceLocator);
    }

private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Admin - Registro de Staff");
        setBounds(100, 100, 800, 600);
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\ALUMNO\\Pictures\\Saved Pictures\\gu.png"));


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

        JLabel lblRegistro = new JLabel("Registro - Staff");
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

        // Username label and text field
        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblUsername.setForeground(Color.WHITE);
        panel.add(lblUsername, gbc);
        jUsernameField = new JTextField(20);
        jUsernameField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gbc.gridx = 1;
        panel.add(jUsernameField, gbc);

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
        String username = jUsernameField.getText();
        String password = new String(jPasswordField.getPassword());
        String confirmPassword = new String(jPasswordFieldConfirm.getPassword());
        if(username != null && password != null)
        {
            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden");
                return;
            }

            Staff newStaff = staffController.registerStaff(username, password);
                    if (newStaff != null) {
                JOptionPane.showMessageDialog(this, "Staff registrado con exito");
                this.dispose();
                new LoginStaff(serviceLocator).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Error al registrar usuario");
            }
        }else{
            JOptionPane.showMessageDialog(this, "Por favor, rellena todos los campos");
        }
    }
}