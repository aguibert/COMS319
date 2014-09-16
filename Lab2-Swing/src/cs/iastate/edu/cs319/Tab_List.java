package cs.iastate.edu.cs319;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

public class Tab_List extends JPanel
{
	private static final long serialVersionUID = 1L;
	private final DataModel dataModel = new DataModel();

	public Tab_List(){
		// Populate list tab
		final JList<String> list = new JList<String>(dataModel);
		list.setCellRenderer(new SelectedCellRenderer());
		dataModel.setList(list);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollPane);
		scrollPane.setPreferredSize(new Dimension(400, 190));
		add(scrollPane);
		
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JButton btnAdd = new JButton("Add");
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Dialog_AddCompany(dataModel);
			}
		});
		add(btnAdd);
		JButton btnRemove = new JButton("Remove");
		btnRemove.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String value = list.getSelectedValue();
				dataModel.remove(value);
			}
		});
		add(btnRemove);
	}
	
	@SuppressWarnings("serial")
	private class SelectedCellRenderer extends DefaultListCellRenderer{
		@Override
		public Component getListCellRendererComponent(@SuppressWarnings("rawtypes") JList list, Object value, int index, boolean isSelected, boolean cellHasFocus){
			Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus); 
			if(isSelected){
				c.setBackground(Color.yellow);
				c.setForeground(Color.black);
			}
			return c;
		}
	}
}
