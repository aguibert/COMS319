package cs.iastate.edu.cs319;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class Lab2Swing extends JFrame {

	private JPanel contentPane;
	private static final Rectangle FULL_SIZE = new Rectangle(0, 0, 450, 275);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Lab2Swing frame = new Lab2Swing();
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
	public Lab2Swing() {
		
		JPanel panel_list = new JPanel();
		
		JPanel panel_tree = new JPanel();
		
		JPanel panel_table = new JPanel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);
		tabbedPane.setBounds(FULL_SIZE);
		contentPane.add(tabbedPane);
		tabbedPane.addTab("List", panel_list);
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		DataModel dataModel = new DataModel();
		for(String company : dataModel.getAllCompanies())
			listModel.addElement(company);
		
		// Populate list tab
		JList<String> list = new JList<String>();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setModel(listModel);
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel_list.add(scrollPane);
		scrollPane.setPreferredSize(new Dimension(400, 225));
		panel_list.add(scrollPane);
		
		tabbedPane.addTab("Tree", panel_tree);
		tabbedPane.addTab("Table", panel_table);
		tabbedPane.setSelectedIndex(0);
	}
	
	private void createListTab(JPanel panel_list)
	{
		
	}
}
