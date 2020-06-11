package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import classes.CompteRendu;
import classes.Praticien;
import db_class.DAOCompteRendu;
import db_class.DAOMedicament;
import db_class.DAOPraticien;
import db_class.SQLConnection;

@SuppressWarnings("serial")
public class UICR extends JFrame {
	private JLabel title;
	private JLabel l_rnumber;
	private JLabel l_practis;
	private JLabel l_rdate;
	private JLabel l_motif;
	private JLabel l_bilan;
	private JLabel l_offer;
	private JScrollPane scroller;

	private JButton back, modify;
	private JTextField rnumber;
	private JComboBox practis;
	private JFormattedTextField rdate;
	private JTextField motif;
	private JTextArea bilan;
	private JTable offer;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UICR frame = new UICR(0, null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public UICR(int cr_id, SQLConnection sql) {
		DAOCompteRendu DAOCR = new DAOCompteRendu(sql.getConnection());
		DAOPraticien DAOP = new DAOPraticien(sql.getConnection());
		CompteRendu leCR = DAOCR.getCompteRendu(cr_id);

		setTitle("PharmaEurope - Comptes-Rendus");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 600);
		getContentPane().setBackground(Color.DARK_GRAY);
		getContentPane().setForeground(Color.DARK_GRAY);

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
		rnumber.setText(String.valueOf(cr_id));
		rnumber.setEditable(false);

		l_practis = new JLabel("Praticien");
		l_practis.setBounds(10, 90, 150, 30);
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
		practis.setBounds(170, 90, 200, 22);
		practis.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		l_rdate = new JLabel("Date Rapport");
		l_rdate.setBounds(10, 120, 150, 30);
		l_rdate.setForeground(Color.WHITE);
		l_rdate.setHorizontalAlignment(JLabel.LEFT);
		l_rdate.setFont(new Font("Consolas", Font.BOLD, 18));

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		rdate = new JFormattedTextField(format);
		rdate.setBounds(170, 120, 96, 20);
		boolean next = true;
		String finalDate = "";
		int j = 0;

		java.util.Date date = null;
		try {
			date = format.parse(leCR.getDate());
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		rdate.setValue(date);
		rdate.setColumns(10);
		rdate.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		l_motif = new JLabel("Motif Visite");
		l_motif.setBounds(10, 150, 150, 30);
		l_motif.setForeground(Color.WHITE);
		l_motif.setHorizontalAlignment(JLabel.LEFT);
		l_motif.setFont(new Font("Consolas", Font.BOLD, 18));

		motif = new JTextField();
		motif.setBounds(170, 150, 200, 20);
		motif.setColumns(10);
		motif.setText(leCR.getMotif());
		motif.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		l_bilan = new JLabel("Bilan");
		l_bilan.setBounds(10, 180, 150, 30);
		l_bilan.setForeground(Color.WHITE);
		l_bilan.setHorizontalAlignment(JLabel.LEFT);
		l_bilan.setFont(new Font("Consolas", Font.BOLD, 18));

		
		bilan = new JTextArea();
		bilan.setBounds(170, 180, 250, 250);
		bilan.setText(leCR.getBilan());
		bilan.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		scroller = new JScrollPane(bilan);
		scroller.setBounds(170, 180, 250, 250);
		scroller.setBackground(Color.DARK_GRAY);
		scroller.setForeground(Color.DARK_GRAY);
		
		l_offer = new JLabel("Offre d'échantillons");
		l_offer.setBounds(500, 180, 200, 30);
		l_offer.setForeground(Color.WHITE);
		l_offer.setHorizontalAlignment(JLabel.LEFT);
		l_offer.setFont(new Font("Consolas", Font.BOLD, 18));

		DAOMedicament DAOM = new DAOMedicament(sql.getConnection());
		HashMap<String, Integer> m = DAOM.getMedicamentsParRapport(cr_id);

		int k = 0;
		
		String[] entetes = {"Médicament", "Quantité"};
		Object[][] rowData = new Object[m.size()][2];
		
		for (String key : m.keySet()) {
			rowData[k][0] = key;
			rowData[k][1] = m.get(key);	
			k++;
		}	
		
		DefaultTableModel model = new DefaultTableModel(rowData, entetes);
		offer = new JTable(model);
		offer.setBounds(500, 210, 200, 170);
		offer.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		

	

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
			public void actionPerformed(ActionEvent event) {
				if (DAOCR.updateCompteRendu(p[practis.getSelectedIndex()].split(" ")[0], (java.util.Date) rdate.getValue(), bilan.getText(), motif.getText(), cr_id)) { 
					JOptionPane jop = new JOptionPane();
					jop.showMessageDialog(null, "Erreur lors de l'ajout, veuillez réessayer.", "Erreur", JOptionPane.ERROR_MESSAGE);
				} else {
					UICRList cr = new UICRList(sql);
					cr.setVisible(true);
					dispose();
				
				}
			}
		});

		getContentPane().setLayout(null);
		getContentPane().add(title);

		getContentPane().add(l_rnumber);
		getContentPane().add(rnumber);

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
		getContentPane().add(modify);
	}
}
