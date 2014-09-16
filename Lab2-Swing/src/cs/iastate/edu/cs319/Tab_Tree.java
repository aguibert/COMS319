package cs.iastate.edu.cs319;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class Tab_Tree extends JPanel
{
    private static final long serialVersionUID = 1L;
    private JTree tree;
    private DefaultTreeModel tModel;

    public Tab_Tree() {
        // Layout Management of the Panel
        setLayout(new BorderLayout());

        // CREATE THE TREEMODEL
        tModel = createTreeModel();

        // CREATE THE JTREE (the VIEW) and SET ITS PROPERTIES
        tree = new JTree(tModel);
        tree.setShowsRootHandles(true);
        tree.setRootVisible(true);
        tree.setEditable(false);

        // ADD THE JTREE to the main panel (in a scroll pane)
        JScrollPane scroll = new JScrollPane(tree);
        add(scroll, BorderLayout.CENTER);

        // CREATE THE CONTROLLER BUTTONS TO MODIFY THE TREE MODEL
        JPanel controllerButtonsPanel = createControllerButtonsPanel();

        // ADD THE CONTROLLER BUTTONS to the main panel.
        add(controllerButtonsPanel, BorderLayout.SOUTH);

    }

    private DefaultTreeModel createTreeModel() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Animals");
        HashMap<String, String[]> treeMap = new HashMap<String, String[]>();
        treeMap.put("Mammals", new String[] { "Human", "Kangaroo", "Elephant", "Goat" });
        treeMap.put("Reptiles", new String[] { "Lizard", "Boa", "Iguana" });
        treeMap.put("Birds", new String[] { "Duck", "Pigeon", "Turkey", "Goose" });
        treeMap.put("Insects", new String[] { "Termite", "Ladybug", "Fly", "Ant" });
        treeMap.put("Aquatic", new String[] { "Sword Fish", "Shark", "Whale" });
        for (String animal : treeMap.keySet()) {
            DefaultMutableTreeNode curNode = new DefaultMutableTreeNode(animal, true);
            root.add(curNode);
            for (String type : treeMap.get(animal))
                curNode.add(new DefaultMutableTreeNode(type, false));
        }
        DefaultTreeModel treeModel = new DefaultTreeModel(root);
        return treeModel;
    }

    private JPanel createControllerButtonsPanel() {
        JPanel buttonPanel = new JPanel();
        JButton addChld = new JButton("Add");
        addChld.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new Dialog_AddAnimal(tModel, tree);
            }
        });
        JButton remNode = new JButton("Remove");
        buttonPanel.add(addChld);
        buttonPanel.add(remNode);

        // remove some selected node: you cannot remove the root node
        remNode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                // Identify the node that has been selected
                DefaultMutableTreeNode selected = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                if (selected == null)
                    return;

                // Identify the parent of the selected node; we are not allowing
                // the root node to be removed
                if (selected.getParent() == null)
                    return;

                // User the models remove method to remove the selected node
                tModel.removeNodeFromParent(selected);
            }
        });

        return buttonPanel;

    }
}
