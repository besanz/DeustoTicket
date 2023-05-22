package gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import data.entidades.Staff;
import controller.StaffController;
import remote.ServiceLocator;

public class MainStaff extends JFrame {
    private Staff staff;
    private StaffController staffController;
    private ServiceLocator serviceLocator;

    private JPanel contentPane;
    private JPanel mainPanel;

    public MainStaff(Staff staff, StaffController staffController, ServiceLocator serviceLocator) {
        this.staff = staff;
        this.staffController = staffController;
        this.serviceLocator = serviceLocator;
        initComponents();
    }

    private void initComponents() {
        setTitle("Admin - Inicio");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout());
        contentPane.setBackground(new Color(54, 57, 63));
        setContentPane(contentPane);

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(new Color(54, 57, 63));
        contentPane.add(mainPanel, BorderLayout.CENTER);

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.insets = new Insets(5, 5, 5, 5);

        JLabel title = new JLabel("Bienvenido a la interfaz de Staff", SwingConstants.CENTER);
        title.setForeground(new Color(114, 137, 218));
        title.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 24));
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 0;
        mainPanel.add(title, c);

        c.gridwidth = 1;
        c.gridy = 1;
        c.weighty = 0;
        addButton("Control de usuarios", new Color(114, 137, 218), c, 0, 1, new StaffControlUsuarios(staffController));
        addButton("Control de tickets", new Color(114, 137, 218), c, 1, 1, new StaffControlTickets(staffController));

        c.gridwidth = 2;
        c.gridy = 2;
        c.weighty = 0;
        addButton("Escanear QR", new Color(114, 137, 218), c, 0, 2, new StaffEscanearQR(staffController));
    }

    private void addButton(String text, Color color, GridBagConstraints c, int gridx, int gridy, JFrame frame) {
        JButton button = new JButton(text);
        button.setForeground(Color.WHITE);
        button.setBackground(color);
        button.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
        button.setBorder(new RoundedBorder(10)); //10 is the radius
        button.setFocusPainted(false);
        Dimension buttonSize = gridy == 2 ? new Dimension(200, 60) : new Dimension(100, 60);
        
        button.setPreferredSize(buttonSize);
        button.setMaximumSize(buttonSize);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(true);
                if (frame instanceof StaffEscanearQR) {
                    ((StaffEscanearQR) frame).initializeWebcam();
                }
            }
        });
        c.gridx = gridx;
        c.gridy = gridy;
        mainPanel.add(button, c);
    }
}
