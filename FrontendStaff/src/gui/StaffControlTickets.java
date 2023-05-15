package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class StaffControlTickets extends JFrame {

	private JPanel contentPane;

	
	public StaffControlTickets() {
		setTitle("Admin - Control de Tickets");
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 812, 571);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JList list = new JList();
		list.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 19));
		list.setBounds(50, 104, 673, 331);
		contentPane.add(list);
		
		JButton btnNewButton = new JButton("Eliminar Ticket");
		btnNewButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btnNewButton.setBounds(100, 470, 205, 29);
		contentPane.add(btnNewButton);
		
		JButton btnEliminarUsuario = new JButton("A\u00F1adir Ticket");
		btnEliminarUsuario.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btnEliminarUsuario.setBounds(468, 470, 205, 29);
		contentPane.add(btnEliminarUsuario);
		
		JLabel lblControlDeUsuarios = new JLabel("Control de Tickets");
		lblControlDeUsuarios.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		lblControlDeUsuarios.setBounds(50, 27, 354, 49);
		contentPane.add(lblControlDeUsuarios);
	}

}
