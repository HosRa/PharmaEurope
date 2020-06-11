package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import classes.Medicament;
import db_class.DAOMedicament;
import db_class.SQLConnection;

public class UIMedic extends JFrame {
	private JLabel title;
	private JLabel l_code;
	private JLabel l_name;
	private JLabel l_familly;
	private JLabel l_compo;
	private JLabel l_effects;
	private JLabel l_indic;
	private JLabel l_price;

	private JTextField code;
	private JTextField name;
	private JTextField familly;
	private JTextField compo;
	private JTextArea effects;
	private JTextArea indic;
	private JTextField price;

	private JButton back, modify;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIMedic frame = new UIMedic(null, "");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public UIMedic(SQLConnection sql, String numMedicament) {
		DAOMedicament DAOM = new DAOMedicament(sql.getConnection());
		Medicament m = DAOM.getMedicamentById(numMedicament);
		setTitle("PharmaEurope - Médicaments");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		getContentPane().setBackground(Color.DARK_GRAY);
		getContentPane().setForeground(Color.DARK_GRAY);

		title = new JLabel("Médicaments :");
		title.setBounds(10, 10, 775, 45);
		title.setForeground(Color.WHITE);
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setFont(new Font("Consolas", Font.BOLD, 24));
		title.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		l_code = new JLabel("Code");
		l_code.setBounds(10, 60, 200, 30);
		l_code.setForeground(Color.GREEN);
		l_code.setHorizontalAlignment(JLabel.LEFT);
		l_code.setFont(new Font("Consolas", Font.BOLD, 18));

		code = new JTextField();
		code.setBounds(250, 60, 96, 20);
		code.setColumns(10);
		code.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		code.setText(numMedicament);
		code.setEditable(false);

		l_name = new JLabel("Nom commercial");
		l_name.setBounds(10, 90, 200, 30);
		l_name.setForeground(Color.WHITE);
		l_name.setHorizontalAlignment(JLabel.LEFT);
		l_name.setFont(new Font("Consolas", Font.BOLD, 18));

		name = new JTextField();
		name.setBounds(250, 90, 96, 20);
		name.setColumns(10);
		name.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		name.setText(m.getNom());

		l_familly = new JLabel("Famille");
		l_familly.setBounds(10, 120, 200, 30);
		l_familly.setForeground(Color.WHITE);
		l_familly.setHorizontalAlignment(JLabel.LEFT);
		l_familly.setFont(new Font("Consolas", Font.BOLD, 18));

		familly = new JTextField();
		familly.setBounds(250, 120, 500, 20);
		familly.setColumns(10);
		familly.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		familly.setText(m.getFamille_code());

		l_compo = new JLabel("Composition");
		l_compo.setBounds(10, 150, 200, 30);
		l_compo.setForeground(Color.WHITE);
		l_compo.setHorizontalAlignment(JLabel.LEFT);
		l_compo.setFont(new Font("Consolas", Font.BOLD, 18));

		compo = new JTextField();
		compo.setBounds(250, 150, 500, 20);
		compo.setColumns(10);
		compo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		compo.setText(m.getComposition());

		l_effects = new JLabel("Effects indésirables");
		l_effects.setBounds(10, 180, 200, 30);
		l_effects.setForeground(Color.WHITE);
		l_effects.setHorizontalAlignment(JLabel.LEFT);
		l_effects.setFont(new Font("Consolas", Font.BOLD, 18));

		effects = new JTextArea();
		effects.setBounds(250, 180, 500, 80);
		effects.setColumns(10);
		effects.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		effects.setText(m.getEffets());

		l_indic = new JLabel("Contre Indications");
		l_indic.setBounds(10, 270, 200, 30);
		l_indic.setForeground(Color.WHITE);
		l_indic.setHorizontalAlignment(JLabel.LEFT);
		l_indic.setFont(new Font("Consolas", Font.BOLD, 18));

		indic = new JTextArea();
		indic.setBounds(250, 270, 500, 80);
		indic.setColumns(10);
		indic.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		indic.setText(m.getContre_indication());

		l_price = new JLabel("Prix Echantillion");
		l_price.setBounds(10, 360, 200, 30);
		l_price.setForeground(Color.WHITE);
		l_price.setHorizontalAlignment(JLabel.LEFT);
		l_price.setFont(new Font("Consolas", Font.BOLD, 18));

		price = new JTextField();
		price.setBounds(250, 360, 50, 20);
		price.setColumns(10);
		price.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		price.setText(m.getPrix_echantillon());

		back = new JButton("Retour");
		back.setBounds(635, 520, 150, 30);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		modify = new JButton("Modifier");
		modify.setBounds(20, 520, 150, 30);
		modify.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent event) {
				DAOMedicament DAOM = new DAOMedicament(sql.getConnection());
				if (DAOM.updateMedicament(name.getText(), familly.getText(), compo.getText(), effects.getText(), indic.getText(), price.getText(), code.getText())) {
					JOptionPane jop = new JOptionPane();
					jop.showMessageDialog(null, "Erreur lors de l'ajout, veuillez réessayer.", "Erreur", JOptionPane.ERROR_MESSAGE);
				} else {
					UIMedicList cr = new UIMedicList(sql);
					cr.setVisible(true);	
					dispose();
					
				}
			}
		});

		getContentPane().setLayout(null);
		getContentPane().add(title);

		getContentPane().add(l_code);
		getContentPane().add(code);

		getContentPane().add(l_name);
		getContentPane().add(name);

		getContentPane().add(l_familly);
		getContentPane().add(familly);

		getContentPane().add(l_compo);
		getContentPane().add(compo);

		getContentPane().add(l_effects);
		getContentPane().add(effects);

		getContentPane().add(l_indic);
		getContentPane().add(indic);

		getContentPane().add(l_price);
		getContentPane().add(price);

		getContentPane().add(back);
		getContentPane().add(modify);
	}
}