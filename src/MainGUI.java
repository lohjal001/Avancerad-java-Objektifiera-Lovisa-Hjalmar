import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class MainGUI extends JFrame {

    private JPanel panel;
    private JButton openCSVButton;
    private JButton openJSONButton;
    private JTable table1;
    private JScrollPane scrollPane;

    private String csvFilePath = "C:\\Users\\lovis\\HT23\\Objektivering-java-23\\src\\sample.csv";
    private String jsonFilePath = "C:\\Users\\lovis\\HT23\\Objektivering-java-23\\src\\sample.json";


    // Constructor
    public MainGUI() {
        // Initialize components
        panel = new JPanel(new BorderLayout());
        openCSVButton = new JButton("Open CSV");
        openJSONButton = new JButton("Open JSON");
        table1 = new JTable();
        scrollPane = new JScrollPane(table1);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(openCSVButton);
        buttonPanel.add(openJSONButton);

        panel.add(buttonPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panel);
        setSize(800, 800);
        setLocationRelativeTo(null);
        setVisible(true);

        buttonActionCSV();
        buttonActionJSON();
    }

    // for CSV Button
    public void buttonActionCSV() {
        openCSVButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = new File(csvFilePath);
                if (file.exists()) {
                    try {
                        ArrayList<String[]> all = CSVHandler.readCSV(file);
                        updateTable(all);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(MainGUI.this, "Error reading CSV file: " + ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(MainGUI.this, "CSV file not found at " + csvFilePath);
                }
            }
        });
    }

    // for JSON Button
    public void buttonActionJSON() {
        openJSONButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = new File(jsonFilePath);
                if (file.exists()) {
                    try {
                        ArrayList<String[]> all = JSONHandler.readJSON(file);
                        updateTable(all);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(MainGUI.this, "Error reading JSON file: " + ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(MainGUI.this, "JSON file not found at " + jsonFilePath);
                }
            }
        });
    }

    private void updateTable(ArrayList<String[]> data) {
        if (data != null && !data.isEmpty()) {
            String[] columns = data.get(0);
            Order order = new Order(data, columns);
            table1.setModel(order);

            TableRowSorter<TableModel> sorter = new TableRowSorter<>(order);
            table1.setRowSorter(sorter);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainGUI());
    }
}