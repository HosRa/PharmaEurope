package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import db_class.DAOVisiteur;
import db_class.SQLConnection;

@SuppressWarnings("serial")
public class UIHub extends JFrame {
	private JLabel title;
	private JButton btn_cr;
	private JButton btn_visitors;
	private JButton btn_practices;
	private JButton btn_medication;
	private JButton btn_quit;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIHub frame = new UIHub(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public UIHub(SQLConnection sql) {
		setTitle("PharmaEurope - Accueil");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 300);
		getContentPane().setBackground(Color.DARK_GRAY);
		getContentPane().setForeground(Color.DARK_GRAY);
		
		title = new JLabel("Accueil :");
		title.setBounds(120, 40, 500, 30);
		title.setForeground(Color.WHITE);
		title.setFont(new Font("Consolas", Font.BOLD, 18));
		
		btn_cr = new JButton("Comptes-Rendus");
		btn_cr.setBounds(120, 70, 150, 20);
		btn_cr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UICRList cr = new UICRList(sql);
				cr.setVisible(true);
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		
		btn_visitors = new JButton("Visiteurs");
		btn_visitors.setBounds(120, 100, 150, 20);
		btn_visitors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				DAOVisiteur DAOV = new DAOVisiteur(sql.getConnection());
				UIVisiteur UIv = new UIVisiteur(sql, DAOV.getVisiteur("a131"));
				UIv.setVisible(true);
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		
		btn_practices = new JButton("Praticiens");
		btn_practices.setBounds(120, 130, 150, 20);
		btn_practices.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				UIPraticien UIP = new UIPraticien(sql, 0);
				UIP.setVisible(true);
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		
		btn_medication = new JButton("Médicaments");
		btn_medication.setBounds(120, 160, 150, 20);
		btn_medication.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UIMedicList UIMList = new UIMedicList(sql);
				UIMList.setVisible(true);
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});

		btn_quit = new JButton("Quitter");
		btn_quit.setBounds(395, 235, 90, 30);
		btn_quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { dispose(); }
		});
		
		getContentPane().setLayout(null);
		getContentPane().add(title);
		getContentPane().add(btn_cr);
		getContentPane().add(btn_visitors);
		getContentPane().add(btn_practices);
		getContentPane().add(btn_medication);
		getContentPane().add(btn_quit);
	}
}







