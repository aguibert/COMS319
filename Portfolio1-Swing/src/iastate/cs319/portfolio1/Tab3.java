package iastate.cs319.portfolio1;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

/**
 * @author jdmielke
 */
public class Tab3 extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable table;
    private DefaultTableModel tableModel;

    public Tab3() {
        // Populate Table Tab
        setLayout(null);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 0, 450, 197);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane);
        scrollPane.setPreferredSize(new Dimension(400, 190));
        add(scrollPane);

        table = new JTable();
        table.setModel(createTableModel());
        scrollPane.setViewportView(table);

        JPanel controllerButtonsPanel = createControllerButtons();
        add(controllerButtonsPanel);
    }

    private DefaultTableModel createTableModel() {
        tableModel = new DefaultTableModel(new Object[][] {
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
                        });
        return tableModel;
    }

    private JPanel createControllerButtons() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(0, 198, 450, 33);

        JButton btnAdd = new JButton("Add");
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new Dialog_AddPerson(tableModel, table);
            }
        });

        JButton btnRemove = new JButton("Remove");
        btnRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //Controller finds row by looking at the row currently selected
                //in the view.  This row is then removed from the model.
                //The model will automatically update the view in response.
                tableModel.removeRow(table.getSelectedRow());
            }
        });

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnRemove);

        return buttonPanel;
    }
}