package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import data.entidades.Staff;
import controller.StaffController;
import remote.ServiceLocator;

public class MainStaff extends JFrame {
	private Staff staff;
	private StaffController staffController;
	private ServiceLocator serviceLocator;

	private JPanel contentPane;

	public MainStaff(Staff staff, StaffController staffController, ServiceLocator serviceLocator) {
		this.staff = staff;
		this.staffController = staffController;
		this.serviceLocator = serviceLocator;
		initComponents();
	}

	private void initComponents() {
		setTitle("Staff Inicio");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1400, 499);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new GridBagLayout());
		setContentPane(contentPane);

		GridBagConstraints c = new GridBagConstraints();

		JButton btnNewButton = new JButton("Control de usuarios");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(0, 153, 255));
		btnNewButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StaffControlUsuarios cu = new StaffControlUsuarios(serviceLocator);
				cu.setVisible(true);
				dispose();
			}
		});
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 2;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.insets = new Insets(10, 10, 10, 5);
		contentPane.add(btnNewButton, c);
		
		JButton btnNewButton_1 = new JButton("Control de tickets");
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBackground(new Color(0, 153, 255));
		btnNewButton_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaffControlTickets ct = new StaffControlTickets();
				ct.setVisible(true);
				dispose();
			}
		});
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 2;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.insets = new Insets(10, 5, 10, 5);
		contentPane.add(btnNewButton_1, c);
		
		JButton btnEscanearQr = new JButton("Escanear QR");
		btnEscanearQr.setForeground(Color.WHITE);
		btnEscanearQr.setBackground(new Color(0, 153, 255));
		btnEscanearQr.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		btnEscanearQr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				StaffEscanearQR eq = new StaffEscanearQR();
				eq.setVisible(true);
				dispose();
			
			}
		});
		c.gridx = 2;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 2;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.insets = new Insets(10, 5, 10, 10);
		contentPane.add(btnEscanearQr, c);
	}

}
