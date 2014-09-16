package cs.iastate.edu.cs319;

import java.awt.EventQueue;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
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
		
		JPanel panel_list = new ListTab();
		JPanel panel_tree = new JPanel();
		JPanel panel_table = new TableTab();
		
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
		tabbedPane.addTab("Tree", panel_tree);
		tabbedPane.addTab("Table", panel_table);
		
		tabbedPane.setSelectedIndex(0);
	}
}
