package iastate.cs319.portfolio1;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.JList;

/**
 * @author aguibert
 */
public class DataModel extends AbstractListModel<Job>
{
    private static final long serialVersionUID = -3324475927239713138L;
    private List<Job> dataList = new ArrayList<Job>();
    private static JList<Job> list;
    private static long prevListUpdate = System.currentTimeMillis();

    public void setList(JList<Job> list) {
        DataModel.list = list;
    }

    @Override
    public int getSize() {
        return dataList.size();
    }

    @Override
    public Job getElementAt(int index) {
        return dataList.get(index);
    }

    public void remove(Job toRemove) {
        if (toRemove == null)
            return;
        if (dataList.remove(toRemove))
            updateList();
    }

    public void add(Job toAdd) {
        dataList.add(toAdd);
        updateList();
    }

    public static void updateList() {
        long requestTime = System.currentTimeMillis();
        // synchronize and compare times to prevent update thrashing
        synchronized (list) {
            if (requestTime > prevListUpdate) {
                list.updateUI();
                prevListUpdate = System.currentTimeMillis();
            }
        }
    }
}
