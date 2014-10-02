package iastate.cs319.portfolio1;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;

/**
 * @author aguibert
 */
public class Tab2 extends JPanel
{
    private static final long serialVersionUID = 1L;
    final JTextArea textWindow = new JTextArea();
    private JTextField textField;
    private long lastRedrawRequest = System.currentTimeMillis();
    private boolean redrawNeeded = false;
    private JCheckBox chckbxMatchCase = new JCheckBox("Match case");
    private static String initalText = "\"If you know the enemy and know yourself, you need not fear the result of a hundred battles. If you know yourself but not the enemy, for every victory gained you will also suffer a defeat. If you know neither the enemy nor yourself, you will succumb in every battle.\""
                                       + "\n― Sun Tzu, The Art of War";

    public Tab2() {
        setLayout(null);
        textWindow.setText(initalText);
        textWindow.setBounds(6, 6, 399, 126);
        textWindow.setLineWrap(true);
        textWindow.setWrapStyleWord(true);
        textWindow.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                requestRedraw(true);
            }
        });
        add(textWindow);

        textField = new JTextField();
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                requestRedraw(true);
            }
        });
        textField.setBounds(56, 138, 190, 28);
        add(textField);
        textField.setColumns(10);

        JLabel lblSearch = new JLabel("Search:");
        lblSearch.setBounds(6, 144, 61, 16);
        add(lblSearch);
        chckbxMatchCase.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                requestRedraw(false);
            }
        });

        chckbxMatchCase.setBounds(291, 140, 114, 23);
        add(chckbxMatchCase);

        new RedrawDaemon().start();
    }

    private void requestRedraw(boolean deferrable) {
        redrawNeeded = true;
        if (deferrable)
            lastRedrawRequest = System.currentTimeMillis();
    }

    private void highlightText(String toHighlight) {
        redrawNeeded = false;

        textWindow.getHighlighter().removeAllHighlights();

        if (toHighlight == null || toHighlight.trim().length() < 1)
            return;

        Document doc = textWindow.getDocument();
        boolean matchCase = chckbxMatchCase.isSelected();
        try {
            for (int i = 0; i + toHighlight.length() < doc.getLength(); i++) {
                String match = doc.getText(i, toHighlight.length());
                if ((matchCase && toHighlight.equals(match)) ||
                    (!matchCase && toHighlight.equalsIgnoreCase(match))) {
                    DefaultHighlighter.DefaultHighlightPainter highlighter = new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);
                    textWindow.getHighlighter().addHighlight(i, i + toHighlight.length(), highlighter);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class RedrawDaemon extends Thread
    {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(250);
                    if (redrawNeeded && (System.currentTimeMillis() - lastRedrawRequest) > 250)
                        highlightText(textField.getText());
                } catch (Exception e) {
                }
            }
        }
    }
}
