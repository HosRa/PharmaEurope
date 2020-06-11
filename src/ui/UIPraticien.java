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
import javax.swing.JTextField;

import classes.Praticien;
import db_class.DAOPraticien;
import db_class.SQLConnection;

public class UIPraticien extends JFrame {
	private JLabel title;
	private JLabel l_search;
	private JComboBox search;

	private JButton ok;
	private JButton quit;
	private JButton prev;
	private JButton next;
	
	private JLabel l_number;
	private JLabel l_lastname;
	private JLabel l_name;
	private JLabel l_addr;
	private JLabel l_city;
	private JLabel l_coef;
	private JLabel l_exo;
	
	private JTextField number;
	private JTextField lastname;
	private JTextField name;
	private JTextField cp;
	private JTextField addr;
	private JTextField city;
	private JTextField coef;
	private JTextField exo;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIPraticien frame = new UIPraticien(null, -1);
					frame.setVisible(true);
				} catch (Exception e) { e.printStackTrace(); }
			}
		});
	}

	public UIPraticien(SQLConnection sql, int prat) {
		DAOPraticien DAOP = new DAOPraticien(sql.getConnection());
		Praticien p = DAOP.getPraticien(prat);
		setTitle("PharmaEurope - Praticiens");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 500);
		getContentPane().setBackground(Color.DARK_GRAY);
		getContentPane().setForeground(Color.DARK_GRAY);
		
		title = new JLabel("Praticiens :");
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
		
		
		ArrayList<Praticien> lesPraticiens = DAOP.getPraticiens();
		String[] pp = new String[lesPraticiens.size()];
		int i = 0;

		for (Praticien praticien : lesPraticiens) {
			pp[i] = praticien.getNom() + " " + praticien.getPrenom();
			i++;
		}
		search = new JComboBox(pp);
		search.setBounds(250, 60, 200, 20);
		search.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		ok = new JButton("OK");
		ok.setBounds(470, 60, 60, 20);
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UIPraticien prac = new UIPraticien(sql, DAOP.getIdPraticien(pp[search.getSelectedIndex()].split(" ")[0])); // Modifier l'index
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
		
		if (prat > 0) {
			l_number = new JLabel("NUMERO");
			l_number.setBounds(10, 100, 200, 30);
			l_number.setForeground(Color.GREEN);
			l_number.setHorizontalAlignment(JLabel.LEFT);
			l_number.setFont(new Font("Consolas", Font.BOLD, 18));
			
			number = new JTextField();
			number.setBounds(250, 100, 200, 20);
			number.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			number.setText(String.valueOf(prat));
			number.setEditable(false);
			
			l_lastname = new JLabel("NOM");
			l_lastname.setBounds(10, 130, 200, 30);
			l_lastname.setForeground(Color.WHITE);
			l_lastname.setHorizontalAlignment(JLabel.LEFT);
			l_lastname.setFont(new Font("Consolas", Font.BOLD, 18));
			
			lastname = new JTextField();
			lastname.setBounds(250, 130, 200, 20);
			lastname.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			lastname.setEditable(false);
			lastname.setText(p.getNom());
			
			l_name = new JLabel("PRENOM");
			l_name.setBounds(10, 160, 200, 30);
			l_name.setForeground(Color.WHITE);
			l_name.setHorizontalAlignment(JLabel.LEFT);
			l_name.setFont(new Font("Consolas", Font.BOLD, 18));
			
			name = new JTextField();
			name.setBounds(250, 160, 200, 20);
			name.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			name.setEditable(false);
			name.setText(p.getPrenom());
			
			l_addr = new JLabel("ADDRESSE");
			l_addr.setBounds(10, 190, 200, 30);
			l_addr.setForeground(Color.WHITE);
			l_addr.setHorizontalAlignment(JLabel.LEFT);
			l_addr.setFont(new Font("Consolas", Font.BOLD, 18));
			
			cp = new JTextField();
			cp.setBounds(250, 190, 74, 20);
			cp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			cp.setEditable(false);
			cp.setText(p.getCp());
			
			addr = new JTextField();
			addr.setBounds(326, 190, 124, 20);
			addr.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			addr.setEditable(false);
			addr.setText(p.getAdresse());
			
			l_city = new JLabel("VILLE");
			l_city.setBounds(10, 220, 200, 30);
			l_city.setForeground(Color.WHITE);
			l_city.setHorizontalAlignment(JLabel.LEFT);
			l_city.setFont(new Font("Consolas", Font.BOLD, 18));
			
			city = new JTextField();
			city.setBounds(250, 220, 200, 20);
			city.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			city.setEditable(false);
			city.setText(p.getVille());
			
			l_coef = new JLabel("COEFF. NOTORIETE");
			l_coef.setBounds(10, 250, 200, 30);
			l_coef.setForeground(Color.WHITE);
			l_coef.setHorizontalAlignment(JLabel.LEFT);
			l_coef.setFont(new Font("Consolas", Font.BOLD, 18));
			
			coef = new JTextField();
			coef.setBounds(250, 250, 200, 20);
			coef.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			coef.setEditable(false);
			coef.setText(String.valueOf(p.getCoefNotoriete()));
			
			l_exo = new JLabel("LIEU D'EXERCICE");
			l_exo.setBounds(10, 280, 200, 30);
			l_exo.setForeground(Color.WHITE);
			l_exo.setHorizontalAlignment(JLabel.LEFT);
			l_exo.setFont(new Font("Consolas", Font.BOLD, 18));

			exo = new JTextField();
			exo.setBounds(250, 280, 200, 20);
			exo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			exo.setEditable(false);
			exo.setText(DAOP.getPraticienType(String.valueOf(prat)));
			
			prev = new JButton("Précédent");
			prev.setBounds(120, 437, 120, 30);
			prev.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					UIPraticien prac = new UIPraticien(sql, prat - 1); // Modifier l'index
					prac.setVisible(true);
					setVisible(false);
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
			});
			
			next = new JButton("Suivant");
			next.setBounds(250, 437, 120, 30);
			next.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					UIPraticien prac = new UIPraticien(sql, prat + 1); // Modifier l'index
					prac.setVisible(true);
					setVisible(false);
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
			});
			
			getContentPane().add(prev);
			getContentPane().add(next);
			
			getContentPane().add(l_number);
			getContentPane().add(l_lastname);
			getContentPane().add(l_name);
			getContentPane().add(l_addr);
			getContentPane().add(l_city);
			getContentPane().add(l_coef);
			getContentPane().add(l_exo);
			getContentPane().add(number);
			getContentPane().add(lastname);
			getContentPane().add(name);
			getContentPane().add(cp);
			getContentPane().add(addr);
			getContentPane().add(city);
			getContentPane().add(coef);
			getContentPane().add(exo);
		}
	}

}
