package iastate.cs319.portfolio1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class Portfolio1_Main extends JFrame {

    /**
     * 
     * 
     * 
     * 
     * DO NOT CHANGE ANYTHING IN THIS CLASS
     * 
     * 
     * 
     * 
     * 
     * - We are going to keep this class unchanged while the 3 of us work on the portfolio in order to avoid having to merge anything.
     * 
     * - Pick a tab to work on and modify the stuff inside that class (not the name of the class).
     * 
     * - When we get to a good merging spot, we can rename classes so they make more sense after everything is pushed into 1 copy.
     * 
     */

    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Portfolio1_Main frame = new Portfolio1_Main();
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
    public Portfolio1_Main() {

        JPanel tab1 = new Tab1();
        JPanel tab2 = new Tab2();
        JPanel tab3 = new Tab3();
        JPanel tab4 = new Tab4();
        JPanel tab5 = new Tab5();
        JPanel tab6 = new Tab6();
        JPanel tab7 = new Tab7();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);
        tabbedPane.setBounds(0, 0, 434, 262);
        contentPane.add(tabbedPane);
        tabbedPane.addTab("Tab1", tab1);
        tabbedPane.addTab("Tab2", tab2);
        tabbedPane.addTab("Tab3", tab3);
        tabbedPane.addTab("Tab4", tab4);
        tabbedPane.addTab("Tab5", tab5);
        tabbedPane.addTab("Tab6", tab6);
        tabbedPane.addTab("Tab7", tab7);

        tabbedPane.setSelectedIndex(0);
    }
}
