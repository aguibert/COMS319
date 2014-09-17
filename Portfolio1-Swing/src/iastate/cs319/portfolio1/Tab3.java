package iastate.cs319.portfolio1;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

/**
 * 
 * @author put author here
 * 
 */
public class Tab3 extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable table;

    public Tab3() {
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