package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

import classes.*;
import db_class.*;
import utils.*;

public class UINewCR extends JFrame {

	private JLabel title, l_rnumber, l_practis, l_rdate, l_motif, l_bilan, l_offer, l_visiteur;
	private JTextField rnumber, motif, vnumber;
	private JComboBox practis;
	private JButton back, add;
	private JFormattedTextField rdate;
	private JTextArea bilan;
	private JScrollPane scroller;
	private JTable offer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UINewCR frame = new UINewCR(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UINewCR(SQLConnection sql) {
		DAOPraticien DAOP = new DAOPraticien(sql.getConnection());

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(800, 600);
		getContentPane().setBackground(Color.DARK_GRAY);
		getContentPane().setForeground(Color.DARK_GRAY);
		setTitle("PharmaEurope - Nouveau rapport de visite");

		title = new JLabel("Comptes-Rendus :");
		title.setBounds(10, 10, 775, 45);
		title.setForeground(Color.WHITE);
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setFont(new Font("Consolas", Font.BOLD, 24));
		title.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		l_rnumber = new JLabel("Numéro Rapport");
		l_rnumber.setBounds(10, 60, 150, 30);
		l_rnumber.setForeground(Color.GREEN);
		l_rnumber.setHorizontalAlignment(JLabel.LEFT);
		l_rnumber.setFont(new Font("Consolas", Font.BOLD, 18));
		
		rnumber = new JTextField();
		rnumber.setBounds(170, 60, 96, 20);
		rnumber.setColumns(10);
		rnumber.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		l_visiteur = new JLabel("Numéro visiteur");
		l_visiteur.setBounds(10, 90, 150, 30);
		l_visiteur.setForeground(Color.WHITE);
		l_visiteur.setHorizontalAlignment(JLabel.LEFT);
		l_visiteur.setFont(new Font("Consolas", Font.BOLD, 18));
		
		vnumber = new JTextField();
		vnumber.setBounds(170, 90, 96, 20);
		vnumber.setColumns(10);
		vnumber.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		l_practis = new JLabel("Praticien");
		l_practis.setBounds(10, 120, 150, 30);
		l_practis.setForeground(Color.WHITE);
		l_practis.setHorizontalAlignment(JLabel.LEFT);
		l_practis.setFont(new Font("Consolas", Font.BOLD, 18));

		ArrayList<Praticien> lesPraticiens = DAOP.getPraticiens();
		String[] p = new String[lesPraticiens.size()];
		int i = 0;

		for (Praticien praticien : lesPraticiens) {
			p[i] = praticien.getNom() + " " + praticien.getPrenom();
			i++;
		}
		practis = new JComboBox(p);
		practis.setBounds(170, 120, 200, 22);
		practis.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		l_rdate = new JLabel("Date Rapport");
		l_rdate.setBounds(10, 150, 150, 30);
		l_rdate.setForeground(Color.WHITE);
		l_rdate.setHorizontalAlignment(JLabel.LEFT);
		l_rdate.setFont(new Font("Consolas", Font.BOLD, 18));

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		rdate = new JFormattedTextField(format);
		rdate.setBounds(170, 150, 96, 20);
		rdate.setValue(new Date(timestamp.getTime()));
		rdate.setColumns(10);
		rdate.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		l_motif = new JLabel("Motif Visite");
		l_motif.setBounds(10, 180, 150, 30);
		l_motif.setForeground(Color.WHITE);
		l_motif.setHorizontalAlignment(JLabel.LEFT);
		l_motif.setFont(new Font("Consolas", Font.BOLD, 18));

		motif = new JTextField();
		motif.setBounds(170, 180, 200, 20);
		motif.setColumns(10);
		motif.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		l_bilan = new JLabel("Bilan");
		l_bilan.setBounds(10, 210, 150, 30);
		l_bilan.setForeground(Color.WHITE);
		l_bilan.setHorizontalAlignment(JLabel.LEFT);
		l_bilan.setFont(new Font("Consolas", Font.BOLD, 18));

		bilan = new JTextArea();
		bilan.setBounds(170, 210, 250, 250);
		bilan.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		scroller = new JScrollPane(bilan);
		scroller.setBounds(170, 210, 250, 250);
		scroller.setBackground(Color.DARK_GRAY);
		scroller.setForeground(Color.DARK_GRAY);

		l_offer = new JLabel("Offre d'échantillons");
		l_offer.setBounds(500, 210, 200, 30);
		l_offer.setForeground(Color.WHITE);
		l_offer.setHorizontalAlignment(JLabel.LEFT);
		l_offer.setFont(new Font("Consolas", Font.BOLD, 18));

		// String[] columnNames = {"Médicaments", "Nb. Echantillons"};
		// String[] content[] = null;
		offer = new JTable(3, 2);
		offer.setBounds(500, 240, 200, 50);
		offer.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		DAOMedicament DAOM = new DAOMedicament(sql.getConnection());
		ArrayList<Medicament> m = DAOM.getMedicaments();
		String[] tabM = new String[m.size()];
		
		for (int j = 0; j < m.size(); j++)
			tabM[j] = m.get(j).getNom();
	
		JComboBox comboBox = new JComboBox(tabM);
        offer.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(comboBox));

        //Set up tool tips for the sport cells.
        DefaultTableCellRenderer renderer =
                new DefaultTableCellRenderer();
        renderer.setToolTipText("Click for combo box");
        offer.getColumnModel().getColumn(0).setCellRenderer(renderer);

		back = new JButton("Retour");
		back.setBounds(635, 520, 150, 30);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		add = new JButton("Ajouter");
		add.setBounds(20, 520, 150, 30);
		add.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				DAOCompteRendu DAOCR = new DAOCompteRendu(sql.getConnection());
				if (!(rnumber.getText().isEmpty()) /*&&
					DAOCR.setMedicamentsQuantite(vnumber.getText(), rnumber.getText(), tabM[comboBox.getSelectedIndex()],  offer.getModel().getValueAt(0, 1).toString()) */&&
					DAOCR.ajoutCompteRendu(vnumber.getText(), p[practis.getSelectedIndex()].split(" ")[0], (java.util.Date) rdate.getValue(), bilan.getText(), motif.getText(), Integer.parseInt(rnumber.getText()))) {
					
				JOptionPane jop = new JOptionPane();
				jop.showMessageDialog(null, "Erreur lors de l'ajout, veuillez réessayer.", "Erreur", JOptionPane.ERROR_MESSAGE);
				} else {
					dispose();
				}
			}
		});

		getContentPane().setLayout(null);
		getContentPane().add(title);

		getContentPane().add(l_rnumber);
		getContentPane().add(rnumber);
		
		getContentPane().add(l_visiteur);
		getContentPane().add(vnumber);

		getContentPane().add(l_practis);
		getContentPane().add(practis);

		getContentPane().add(l_rdate);
		getContentPane().add(rdate);

		getContentPane().add(l_motif);
		getContentPane().add(motif);

		getContentPane().add(l_bilan);
		getContentPane().add(scroller);

		getContentPane().add(l_offer);
		getContentPane().add(offer);
		
		getContentPane().add(back);
		getContentPane().add(add);

	}

}
