/**
 *
 */
package iastate.cs319.portfolio1;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 * @author Jon
 * 
 */
@SuppressWarnings("serial")
public class Dialog_AddPerson extends JDialog {

    private final Dialog_AddPerson thisWindow;
    private final DefaultTableModel dtm;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField ageField;
    private JTextField sexField;
    private JTextField vegetarianField;

    /**
     * Create the dialog.
     */
    public Dialog_AddPerson(DefaultTableModel tableModel, JTable table) {
        thisWindow = this;
        this.dtm = tableModel;

        setName("Add Person");
        setBounds(22, 11, 293, 332);

        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        JLabel lblFirstName = new JLabel("First Name:");
        lblFirstName.setBounds(22, 11, 202, 14);
        panel.add(lblFirstName);

        firstNameField = new JTextField();
        firstNameField.setBounds(22, 27, 243, 20);
        panel.add(firstNameField);
        firstNameField.setColumns(10);

        JLabel lblLastName = new JLabel("Last Name:");
        lblLastName.setBounds(22, 58, 134, 14);
        panel.add(lblLastName);

        lastNameField = new JTextField();
        lastNameField.setBounds(22, 76, 243, 20);
        panel.add(lastNameField);
        lastNameField.setColumns(10);

        JLabel lblAge = new JLabel("Age:");
        lblAge.setBounds(22, 107, 46, 14);
        panel.add(lblAge);

        ageField = new JTextField();
        ageField.setBounds(22, 127, 243, 20);
        panel.add(ageField);
        ageField.setColumns(10);

        JLabel lblSex = new JLabel("Sex:");
        lblSex.setBounds(22, 158, 46, 14);
        panel.add(lblSex);

        sexField = new JTextField();
        sexField.setBounds(22, 178, 243, 20);
        panel.add(sexField);
        sexField.setColumns(10);

        JLabel lblVegetarian = new JLabel("Vegetarian:");
        lblVegetarian.setBounds(22, 209, 158, 14);
        panel.add(lblVegetarian);

        vegetarianField = new JTextField();
        vegetarianField.setBounds(22, 229, 243, 20);
        panel.add(vegetarianField);
        vegetarianField.setColumns(10);

        JButton btnOk = new JButton("OK");
        btnOk.setBounds(81, 260, 89, 23);
        panel.add(btnOk);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setBounds(176, 260, 89, 23);
        panel.add(btnCancel);

        btnOk.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Identify the name and other attributes of the person being
                // added to the model.
                dtm.addRow(new Object[] { firstNameField.getText(),
                                         lastNameField.getText(),
                                         ageField.getText(),
                                         sexField.getText(),
                                         vegetarianField.getText()
                });

                thisWindow.dispose();
            }
        });

        btnCancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                thisWindow.dispose();
            }
        });

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setVisible(true);

    }
}
