/**
 *
 */
package iastate.cs319.portfolio1;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * @author jdmielke
 */
@SuppressWarnings("serial")
public class MovementWindow extends JDialog {
    private JTextField locationFieldX;
    private JTextField locationFieldY;

    public MovementWindow() {

        getContentPane().setBackground(Color.GRAY);
        setUndecorated(true);
        getContentPane().setLayout(null);
        setBounds(22, 11, 230, 183);

        JLabel label = new JLabel("X:");
        label.setBounds(22, 22, 20, 14);
        getContentPane().add(label);

        locationFieldX = new JTextField();
        locationFieldX.setEditable(false);
        locationFieldX.setColumns(10);
        locationFieldX.setBounds(32, 19, 56, 20);
        getContentPane().add(locationFieldX);
        locationFieldX.setText(String.valueOf(getLocation().getX()));

        JLabel label_1 = new JLabel("Y:");
        label_1.setBounds(121, 22, 20, 14);
        getContentPane().add(label_1);

        locationFieldY = new JTextField();
        locationFieldY.setEditable(false);
        locationFieldY.setColumns(10);
        locationFieldY.setBounds(131, 19, 71, 20);
        getContentPane().add(locationFieldY);
        locationFieldY.setText(String.valueOf(getLocation().getY()));

        JLabel label_2 = new JLabel("UP:  W");
        label_2.setBounds(86, 50, 46, 14);
        getContentPane().add(label_2);

        JLabel label_3 = new JLabel("RIGHT:  D");
        label_3.setBounds(142, 72, 60, 14);
        getContentPane().add(label_3);

        JLabel label_4 = new JLabel("LEFT:  A");
        label_4.setBounds(32, 72, 46, 14);
        getContentPane().add(label_4);

        JLabel label_5 = new JLabel("DOWN:  S");
        label_5.setBounds(86, 97, 57, 14);
        getContentPane().add(label_5);

        JButton button = new JButton("Dismiss");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        button.setBounds(71, 133, 89, 23);
        getContentPane().add(button);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent key) {
                //Move up when 'W' is pressed
                if (String.valueOf(key.getKeyChar()).equalsIgnoreCase("w")) {
                    setLocation(getLocation().x, getLocation().y - 5);
                    locationFieldY.setText(String.valueOf(getLocation().getY()));
                }
                //Move left when 'A' is pressed
                if (String.valueOf(key.getKeyChar()).equalsIgnoreCase("a")) {
                    setLocation(getLocation().x - 5, getLocation().y);
                    locationFieldX.setText(String.valueOf(getLocation().getX()));
                }
                //Move down when 'S' is pressed
                if (String.valueOf(key.getKeyChar()).equalsIgnoreCase("s")) {
                    setLocation(getLocation().x, getLocation().y + 5);
                    locationFieldY.setText(String.valueOf(getLocation().getY()));
                }
                //Move right when 'D' is pressed
                if (String.valueOf(key.getKeyChar()).equalsIgnoreCase("d")) {
                    setLocation(getLocation().x + 5, getLocation().y);
                    locationFieldX.setText(String.valueOf(getLocation().getX()));
                }
            }
        });

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        //JDialog must be focusable and in focus for any key pressed
        //listener to take affect
        setFocusable(true);

    }

}
