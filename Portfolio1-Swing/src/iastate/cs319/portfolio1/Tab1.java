package iastate.cs319.portfolio1;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

/**
 * @author put author here
 */
public class Tab1 extends JPanel
{
    private static final long serialVersionUID = 1L;
    private final DataModel dataModel = new DataModel();
    private JTextField jobName;

    public Tab1() {
        setLayout(null);
        // Populate list tab
        final JList<Job> list = new JList<Job>(dataModel);
        list.setCellRenderer(new SelectedCellRenderer());
        dataModel.setList(list);
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBounds(6, 6, 200, 210);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane);
        scrollPane.setPreferredSize(new Dimension(200, 190));
        add(scrollPane);

        JPanel panel = new JPanel();
        panel.setBackground(Color.PINK);
        panel.setBounds(218, 6, 226, 210);
        add(panel);

        JLabel lblEnterAJob = new JLabel("Enter a job name:");
        panel.add(lblEnterAJob);

        jobName = new JTextField();
        panel.add(jobName);
        jobName.setColumns(12);

        JLabel lblWhatIsThe = new JLabel("What is the job duration?");
        panel.add(lblWhatIsThe);

        final JFormattedTextField jobDuration = new JFormattedTextField();
        jobDuration.setColumns(6);
        panel.add(jobDuration);

        JLabel lblSeconds = new JLabel("seconds");
        panel.add(lblSeconds);

        JButton btnCreateJob = new JButton("Create Job");
        panel.add(btnCreateJob);

        JButton btnStartJob = new JButton("Start Selected Jobs");
        btnStartJob.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for (Job j : list.getSelectedValuesList())
                    new Thread(j).start();
            }
        });
        panel.add(btnStartJob);
        btnCreateJob.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String jobn = jobName.getText();
                if (jobn == null || jobn.trim().length() < 1 || jobn.length() > 10) {
                    System.out.println("Job name must not be null and under 10 chars");
                    jobName.setText(""); // clear out the field
                    return;
                }
                int jobdur;
                try {
                    jobdur = Integer.valueOf(jobDuration.getText());
                    if (jobdur < 1 || jobdur > 60)
                        throw new Exception();
                } catch (Exception ex) {
                    System.out.println("Job duration must be between 1 and 60 seconds");
                    jobDuration.setText("");
                    return;
                }

                dataModel.add(new Job(jobn, jobdur));
            }
        });
    }

    @SuppressWarnings("serial")
    private class SelectedCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (isSelected)
                c.setForeground(Color.white);
            else
                c.setForeground(Color.black);
            switch (((Job) value).getState()) {
                case WAITING:
                    c.setBackground(Color.red);
                    break;
                case RUNNING:
                    c.setBackground(Color.yellow);
                    break;
                case COMPLETED:
                    c.setBackground(Color.green);
                    break;
            }
            return c;
        }
    }
}
