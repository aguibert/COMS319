package iastate.cs319.portfolio1;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

@SuppressWarnings("serial")
public class Dialog_BackgroundJob extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private final JTextField jobDuration = new JTextField();
    private final Dialog_BackgroundJob thisWindow;
    private final DefaultTreeModel tModel;
    private final JTree tree;
    private JTextField jobName;

    public Dialog_BackgroundJob(DefaultTreeModel tmod, JTree t) {
        thisWindow = this;
        this.tModel = tmod;
        this.tree = t;
        setName("Enter new animal name");
        setBounds(100, 100, 350, 150);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setLayout(new FlowLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        JLabel lblJobName = new JLabel("Enter a job name:");
        contentPanel.add(lblJobName);

        jobName = new JTextField();
        contentPanel.add(jobName);
        jobName.setColumns(16);
        JLabel lblJobDuration = new JLabel("How long will the job take?");
        contentPanel.add(lblJobDuration);
        contentPanel.add(jobDuration);
        jobDuration.setColumns(5);

        JLabel lblJobDurationUnits = new JLabel("ms");
        contentPanel.add(lblJobDurationUnits);
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        JButton btnStartJob = new JButton("Start Job");
        btnStartJob.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Identify the node that has been selected
                DefaultMutableTreeNode selected = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                if (selected == null)
                    return;
                if (!selected.getAllowsChildren())
                    selected = (DefaultMutableTreeNode) selected.getParent();

                // add a new node as the last child of the selected node
                tModel.insertNodeInto(new DefaultMutableTreeNode(jobDuration.getText()), selected, selected.getChildCount());

                // Lets also expand the tree to show the new node
                // Find the array of nodes that make up the path from the root
                // to the newly added node
                TreeNode[] nodes = tModel.getPathToRoot(selected.getChildAt(selected.getChildCount() - 1));

                // Create a tree path with these nodes
                TreePath path = new TreePath(nodes);
                // Make the entire path visible and make the scroller to move to
                // the last path component
                tree.scrollPathToVisible(path);
                thisWindow.dispose();
            }
        });
        btnStartJob.setActionCommand("OK");
        buttonPane.add(btnStartJob);
        getRootPane().setDefaultButton(btnStartJob);
        JButton btnCancel = new JButton("Cancel");
        btnCancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                thisWindow.dispose();
            }
        });
        btnCancel.setActionCommand("Cancel");
        buttonPane.add(btnCancel);

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
