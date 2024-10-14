import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class Order extends DefaultTableModel {

    private ArrayList<String[]> values;
    private String[] columns;

    public Order(ArrayList<String[]> values, String[] columns) {
        this.values = values;
        this.columns = columns;
    }

    @Override
    public int getRowCount() {
        if (values == null) return 0;
        return values.size();
    }

    @Override
    public int getColumnCount() {
        if (columns == null) return 0;
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public Object getValueAt(int row, int column) {
        return values.get(row)[column];
    }
}
