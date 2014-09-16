package cs.iastate.edu.cs319;

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
public class Dialog_AddAnimal extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private final JTextField newAnimal = new JTextField();
    private final Dialog_AddAnimal thisWindow;
    private final DefaultTreeModel tModel;
    private final JTree tree;

    public Dialog_AddAnimal(DefaultTreeModel tmod, JTree t) {
        thisWindow = this;
        this.tModel = tmod;
        this.tree = t;
        setName("Enter new animal name");
        setBounds(100, 100, 297, 125);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setLayout(new FlowLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        JLabel lblNewLabel = new JLabel("What is the new animal?");
        contentPanel.add(lblNewLabel);
        contentPanel.add(newAnimal);
        newAnimal.setColumns(20);
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        JButton okButton = new JButton("OK");
        okButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Identify the node that has been selected
                DefaultMutableTreeNode selected = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                if (selected == null)
                    return;
                if (!selected.getAllowsChildren())
                    selected = (DefaultMutableTreeNode) selected.getParent();

                // add a new node as the last child of the selected node
                tModel.insertNodeInto(new DefaultMutableTreeNode(newAnimal.getText()), selected, selected.getChildCount());

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
        okButton.setActionCommand("OK");
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                thisWindow.dispose();
            }
        });
        cancelButton.setActionCommand("Cancel");
        buttonPane.add(cancelButton);

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
