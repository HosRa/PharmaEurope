package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import classes.CompteRendu;
import classes.Medicament;
import db_class.DAOCompteRendu;
import db_class.DAOMedicament;
import db_class.SQLConnection;
import utils.ButtonColumn;

@SuppressWarnings("serial")
public class UIMedicList extends JFrame {
	private JTable cr_list;
	private JScrollPane scroller;
	private JLabel title;
	private JButton back;
	private JButton new_medic;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIMedicList frame = new UIMedicList(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public UIMedicList(SQLConnection sql) {
		setTitle("PharmaEurope - Médicaments");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 600);
		getContentPane().setBackground(Color.DARK_GRAY);
		getContentPane().setForeground(Color.DARK_GRAY);
		
		title = new JLabel("Médicaments :");
		title.setBounds(10, 10, 775, 45);
		title.setForeground(Color.WHITE);
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setFont(new Font("Consolas", Font.BOLD, 24));
		title.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		//DAOCompteRendu cr = new DAOCompteRendu(sql.getConnection());
        //ArrayList<CompteRendu> c = cr.getAllComptesRendus();
		
		DAOMedicament DAOm = new DAOMedicament(sql.getConnection());
        ArrayList<Medicament> m = DAOm.getMedicaments();
		
		String[] entetes = { "Code médicament", "Nom", "Composition", "Actions"};
		Object[][] rowData = new Object[m.size()][4];
		
		for (int i = 0; i < m.size(); i++) {
			rowData[i][0] = m.get(i).getDepotlegal();
			rowData[i][1] = m.get(i).getNom();
			rowData[i][2] = m.get(i).getComposition();
			rowData[i][3] = "Modifier";
		}
		Action modify = new AbstractAction()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		        JTable table = (JTable) e.getSource();
		        int modelRow = Integer.valueOf(e.getActionCommand());
		     
		    	UIMedic UIm = new UIMedic(sql, (String) table.getValueAt(modelRow, 0));
		    	setVisible(false);
                UIm.setVisible(true);
                
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		    }
		};
		
		DefaultTableModel model = new DefaultTableModel(rowData, entetes);
		cr_list = new JTable(model);
		cr_list.setBackground(Color.DARK_GRAY);
		cr_list.setFont(new Font("Consolas", Font.BOLD, 15));
		cr_list.setForeground(Color.WHITE);
		cr_list.setRowHeight(35);
		cr_list.getTableHeader().setFont(new Font("Consolas", Font.BOLD, 15));

		ButtonColumn buttonColumn = new ButtonColumn(cr_list, modify, 3);
	
		
		scroller = new JScrollPane(cr_list);
		scroller.setBounds(10, 65, 775, 425);
		scroller.setBackground(Color.DARK_GRAY);
		scroller.setForeground(Color.DARK_GRAY);
		

		
		back = new JButton("Retour");
		back.setBounds(635, 520, 150, 30);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { dispose(); }
		});
		
		getContentPane().setLayout(null);
		getContentPane().add(title);
		getContentPane().add(scroller);
		getContentPane().add(back);
	}

}
