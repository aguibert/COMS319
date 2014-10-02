package iastate.cs319.portfolio1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * @author jdmielke
 */
public class Tab4 extends JPanel {

    private static final long serialVersionUID = 1L;

    public Tab4() {
        setLayout(null);

        JButton btnCreate = new JButton("Create Window");
        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MovementWindow();
            }
        });
        btnCreate.setBounds(137, 89, 121, 54);
        add(btnCreate);

    }
}
