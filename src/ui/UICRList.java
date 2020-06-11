package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import classes.CompteRendu;
import db_class.DAOCompteRendu;
import db_class.SQLConnection;
import utils.*;

public class UICRList extends JFrame {
	private JTable cr_list;
	private JScrollPane scroller;
	private JLabel title;
	private JButton back;
	private JButton new_cr;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UICRList frame = new UICRList(null);
					frame.setVisible(true);
				} catch (Exception e) { e.printStackTrace(); }
			}
		});
	}

	public UICRList(SQLConnection sql) {
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
		
		DAOCompteRendu cr = new DAOCompteRendu(sql.getConnection());
        ArrayList<CompteRendu> c = cr.getAllComptesRendus();
		
		String[] entetes = { "Numéro rapport", "Date", "Motif", "Actions"};
		Object[][] rowData = new Object[c.size()][4];
		
		for (int i = 0; i < c.size(); i++) {
			rowData[i][0] = c.get(i).getNumCompteRendu();
			rowData[i][1] = c.get(i).getDate().toString();
			rowData[i][2] = c.get(i).getMotif();
			rowData[i][3] = "Modifier";
			
		}
		Action modify = new AbstractAction()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		        JTable table = (JTable) e.getSource();
		        int modelRow = Integer.valueOf(e.getActionCommand());
		     
		    	UICR cr = new UICR((int) table.getValueAt(modelRow, 0), sql);
		    	setVisible(false);
                cr.setVisible(true);
                
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
		
		new_cr = new JButton("Nouveau");
		new_cr.setBounds(10, 520, 150, 30);
		new_cr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				dispose();
				UINewCR uiNewCR = new UINewCR(sql);
				setVisible(true);
				uiNewCR.setVisible(true);
				}
		});
		
		back = new JButton("Retour");
		back.setBounds(635, 520, 150, 30);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { dispose(); }
		});
		
		getContentPane().setLayout(null);
		getContentPane().add(title);
		getContentPane().add(scroller);
		getContentPane().add(new_cr);
		getContentPane().add(back);
	}

}
