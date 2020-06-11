package ui;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import db_class.*;
import classes.*;
import exec.*;

public class UILogin extends JFrame {
	private JLabel sign_in;
	private JLabel error;
	private JTextField login;
	private JPasswordField password;
	private JButton btn_ok;
	private JButton btn_quit;
	private SQLConnection sql;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UILogin frame = new UILogin(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public UILogin(SQLConnection sql) {
		setTitle("PharmaEurope");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 300);
		getContentPane().setBackground(Color.DARK_GRAY);
		getContentPane().setForeground(Color.DARK_GRAY);

		sign_in = new JLabel("Identifiez-vous :");
		sign_in.setBounds(120, 40, 500, 40);
		sign_in.setForeground(Color.WHITE);
		sign_in.setFont(new Font("Consolas", Font.BOLD, 18));
		
		login = new JTextField();
		login.setBounds(120, 100, 100, 20);
		login.setHorizontalAlignment(SwingConstants.LEFT);
		login.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		
		password = new JPasswordField();
		password.setBounds(120, 130, 100, 20);
		password.setHorizontalAlignment(SwingConstants.LEFT);
		password.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		
		error = new JLabel("Error login/password !");
		error.setBounds(120, 170, 500, 40);
		error.setForeground(Color.red);
		error.setFont(new Font("Consolas", Font.BOLD, 16));
		error.setVisible(false);
		
		btn_ok = new JButton("OK");
		btn_ok.setBounds(240, 130, 60, 20);
		btn_ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(login.getText());
				System.out.println(sql);
				if (!sql.userConnection(login.getText(), new String(password.getPassword()))) {
					error.setVisible(true);
					login.setBorder(BorderFactory.createLineBorder(Color.RED));
					password.setBorder(BorderFactory.createLineBorder(Color.RED));
				} else {
					// Call page accueil
					UIHub hub = new UIHub(sql);
	                hub.setVisible(true);
	                setVisible(false);
	                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				}
		    }
		});
		
		btn_quit = new JButton("Quitter");
		btn_quit.setBounds(395, 235, 90, 30);
		btn_quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { System.exit(0); }
		});
		
		getContentPane().setLayout(null);
		getContentPane().add(sign_in);
		getContentPane().add(login, BorderLayout.CENTER);
		getContentPane().add(password);
		getContentPane().add(error);
		getContentPane().add(btn_ok);
		getContentPane().add(btn_quit);
	}
}











