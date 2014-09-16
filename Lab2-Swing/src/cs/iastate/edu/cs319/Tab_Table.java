package cs.iastate.edu.cs319;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

public class Tab_Table extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable table;

    public Tab_Table() {
        // Populate Table Tab
        setLayout(null);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 0, 450, 300);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane);
        scrollPane.setPreferredSize(new Dimension(400, 190));
        add(scrollPane);

        table = new JTable();
        table.setModel(new DefaultTableModel(
                        new Object[][] {
                                        { "Kathy", "Smith", 25, 'F', false },
                                        { "John", "Doe", 43, 'M', false },
                                        { "Sue", "Black", 61, 'F', true },
                                        { "Jane", "White", 17, 'F', true },
                                        { "Joe", "Brown", 32, 'M', false },
                                        { "Abby", "Dawn", 41, 'F', false },
                                        { "Mila", "Manson", 26, 'F', true },
                                        { "Jack", "Schmitt", 32, 'M', true }
                        },
                        new String[] { "First Name",
                                      "Last Name",
                                      "Age",
                                      "Sex",
                                      "Vegetarian"
                        }
                        ));
        scrollPane.setViewportView(table);
    }
}