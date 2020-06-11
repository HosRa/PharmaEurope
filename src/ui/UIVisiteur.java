package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import classes.Praticien;
import classes.Visiteur;
import db_class.DAOVisiteur;
import db_class.SQLConnection;

public class UIVisiteur extends JFrame {
	private JLabel title;
	private JLabel l_search;
	private JComboBox search;

	private JButton ok;
	private JButton quit;
	private JButton prev;
	private JButton next, modify;
	
	private JLabel l_number;
	private JLabel l_lastname;
	private JLabel l_name;
	private JLabel l_addr;
	private JLabel l_city;
	private JLabel l_secteur;
	private JLabel l_labo;
	
	private JTextField number;
	private JTextField lastname;
	private JTextField name;
	private JTextField cp;
	private JTextField addr;
	private JTextField city;
	private JComboBox secteur;
	private JComboBox labo;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIVisiteur frame = new UIVisiteur(null, null);
					frame.setVisible(true);
				} catch (Exception e) { e.printStackTrace(); }
			}
		});
	}

	public UIVisiteur(SQLConnection sql, Visiteur leVisiteur) {
		setTitle("PharmaEurope - Visiteurs");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 500);
		getContentPane().setBackground(Color.DARK_GRAY);
		getContentPane().setForeground(Color.DARK_GRAY);
		
		title = new JLabel("Visiteurs :");
		title.setBounds(10, 10, 575, 45);
		title.setForeground(Color.WHITE);
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setFont(new Font("Consolas", Font.BOLD, 24));
		title.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		l_search = new JLabel("Chercher");
		l_search.setBounds(10, 60, 200, 30);
		l_search.setForeground(Color.GREEN);
		l_search.setHorizontalAlignment(JLabel.LEFT);
		l_search.setFont(new Font("Consolas", Font.BOLD, 18));
		
		DAOVisiteur DAOV = new DAOVisiteur(sql.getConnection());
		ArrayList<Visiteur> lesVisiteurs = DAOV.getVisiteurs();
		String[] p = new String[lesVisiteurs.size()];
		int i = 0;

		for (Visiteur v : lesVisiteurs) {
			p[i] = v.getNom() + " " + v.getPrenom();
			i++;
		}
		search = new JComboBox(p);
		
		search.setBounds(250, 60, 200, 20);
		search.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		ok = new JButton("OK");
		ok.setBounds(470, 60, 60, 20);
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UIVisiteur prac = new UIVisiteur(sql, DAOV.getVisiteurByName(p[search.getSelectedIndex()].split(" ")[0])); // Modifier l'index
				prac.setVisible(true);
				setVisible(false);
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});
		
		quit = new JButton("Fermer");
		quit.setBounds(470, 437, 120, 30);
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { dispose(); }
		});
		
		getContentPane().setLayout(null);
		getContentPane().add(title);
		getContentPane().add(l_search);
		getContentPane().add(search);
		getContentPane().add(ok);
		getContentPane().add(quit);
		

			l_number = new JLabel("Numéro");
			l_number.setBounds(10, 100, 200, 30);
			l_number.setForeground(Color.GREEN);
			l_number.setHorizontalAlignment(JLabel.LEFT);
			l_number.setFont(new Font("Consolas", Font.BOLD, 18));
			
			number = new JTextField();
			number.setBounds(250, 100, 200, 20);
			number.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			number.setEditable(false);
			number.setText(leVisiteur.getMatricule());
			
			l_lastname = new JLabel("Nom");
			l_lastname.setBounds(10, 130, 200, 30);
			l_lastname.setForeground(Color.WHITE);
			l_lastname.setHorizontalAlignment(JLabel.LEFT);
			l_lastname.setFont(new Font("Consolas", Font.BOLD, 18));
			
			lastname = new JTextField();
			lastname.setBounds(250, 130, 200, 20);
			lastname.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			lastname.setText(leVisiteur.getNom());
			
			l_name = new JLabel("Prénom");
			l_name.setBounds(10, 160, 200, 30);
			l_name.setForeground(Color.WHITE);
			l_name.setHorizontalAlignment(JLabel.LEFT);
			l_name.setFont(new Font("Consolas", Font.BOLD, 18));
			
			name = new JTextField();
			name.setBounds(250, 160, 200, 20);
			name.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			name.setText(leVisiteur.getPrenom());
			
			l_addr = new JLabel("Adresse");
			l_addr.setBounds(10, 190, 200, 30);
			l_addr.setForeground(Color.WHITE);
			l_addr.setHorizontalAlignment(JLabel.LEFT);
			l_addr.setFont(new Font("Consolas", Font.BOLD, 18));
			
			cp = new JTextField();
			cp.setBounds(250, 190, 74, 20);
			cp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			cp.setText(leVisiteur.getCp());
			
			addr = new JTextField();
			addr.setBounds(326, 190, 124, 20);
			addr.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			addr.setText(leVisiteur.getAdresse());
			
			l_city = new JLabel("Ville");
			l_city.setBounds(10, 220, 200, 30);
			l_city.setForeground(Color.WHITE);
			l_city.setHorizontalAlignment(JLabel.LEFT);
			l_city.setFont(new Font("Consolas", Font.BOLD, 18));
			
			city = new JTextField();
			city.setBounds(250, 220, 200, 20);
			city.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			city.setText(leVisiteur.getVille());
			
			l_secteur = new JLabel("Secteur");
			l_secteur.setBounds(10, 250, 200, 30);
			l_secteur.setForeground(Color.WHITE);
			l_secteur.setHorizontalAlignment(JLabel.LEFT);
			l_secteur.setFont(new Font("Consolas", Font.BOLD, 18));
			
			secteur = new JComboBox();
			secteur.addItem("Est");
			secteur.addItem("Nord");
			secteur.addItem("Ouest");
			secteur.addItem("Paris-centre");
			secteur.addItem("Sud");
			secteur.addItem("Non défini");
			secteur.setSelectedItem(DAOV.getSecteurType(leVisiteur.getCodeSection()));
			secteur.setBounds(250, 250, 200, 20);
			secteur.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			secteur.setEditable(false);
			
			
			l_labo = new JLabel("Laboratoire");
			l_labo.setBounds(10, 280, 200, 30);
			l_labo.setForeground(Color.WHITE);
			l_labo.setHorizontalAlignment(JLabel.LEFT);
			l_labo.setFont(new Font("Consolas", Font.BOLD, 18));

			labo = new JComboBox();
			labo.addItem("Bichat");
			labo.addItem("Gyverny");
			labo.addItem("Swiss Kane");
			labo.setSelectedItem(DAOV.getLaboratoireType(leVisiteur.getCodeLaboratoire()));
			labo.setBounds(250, 280, 200, 20);
			labo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			labo.setEditable(false);
			
			modify = new JButton("Modifier");
			modify.setBounds(10, 437, 150, 30);
			modify.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					if (DAOV.updateVisiteur(number.getText(), lastname.getText(), name.getText(), cp.getText(), addr.getText(), city.getText())) { 
						JOptionPane jop = new JOptionPane();
						jop.showMessageDialog(null, "Erreur lors de l'ajout, veuillez réessayer.", "Erreur", JOptionPane.ERROR_MESSAGE);
					} else {
						UIHub cr = new UIHub(sql);
						cr.setVisible(true);
						dispose();
					
					}
				}
			});
			
			getContentPane().add(modify);
			getContentPane().add(l_number);
			getContentPane().add(l_lastname);
			getContentPane().add(l_name);
			getContentPane().add(l_addr);
			getContentPane().add(l_city);
			getContentPane().add(l_secteur);
			getContentPane().add(l_labo);
			getContentPane().add(number);
			getContentPane().add(lastname);
			getContentPane().add(name);
			getContentPane().add(cp);
			getContentPane().add(addr);
			getContentPane().add(city);
			getContentPane().add(secteur);
			getContentPane().add(labo);
		}
	

}
