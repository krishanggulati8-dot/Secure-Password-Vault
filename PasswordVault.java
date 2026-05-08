import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PasswordVault {
    // UI Components
    private JFrame window;
    private JTextField siteIn, userIn;
    private JPasswordField passIn;
    private DefaultTableModel model;
    private JTable table;
    
    private ArrayList<Entry> entryList = new ArrayList<>();
    private final String DATA_FILE = "passwords.txt";

   
    static class Entry {
        String site, user, pass;
        Entry(String s, String u, String p) {
            this.site = s; this.user = u; this.pass = p;
        }
    }

    public static void main(String[] args) {
        new PasswordVault().init();
    }

    private void init() {
        window = new JFrame("My Password Vault");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(600, 400);
        window.setLayout(new BorderLayout(5, 5));

        // Top Panel for Inputs
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        inputPanel.add(new JLabel("Website:"));
        siteIn = new JTextField();
        inputPanel.add(siteIn);

        inputPanel.add(new JLabel("Username:"));
        userIn = new JTextField();
        inputPanel.add(userIn);

        inputPanel.add(new JLabel("Password:"));
        passIn = new JPasswordField();
        inputPanel.add(passIn);

        // Button Panel
        JPanel btnPanel = new JPanel(new FlowLayout());
        JButton addBtn = new JButton("Add");
        JButton delBtn = new JButton("Delete");
        JButton saveBtn = new JButton("Save");

        addBtn.addActionListener(e -> addEntry());
        delBtn.addActionListener(e -> deleteEntry());
        saveBtn.addActionListener(e -> saveFile());

        btnPanel.add(addBtn);
        btnPanel.add(delBtn);
        btnPanel.add(saveBtn);

        // Combine inputs and buttons at the top
        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.add(inputPanel, BorderLayout.CENTER);
        northPanel.add(btnPanel, BorderLayout.SOUTH);

        // Table setup
        String[] cols = {"Website", "User", "Password"};
        model = new DefaultTableModel(cols, 0);
        table = new JTable(model);
        
        window.add(northPanel, BorderLayout.NORTH);
        window.add(new JScrollPane(table), BorderLayout.CENTER);

        loadFile(); // Load existing data on start
        window.setVisible(true);
    }

    private void addEntry() {
        String s = siteIn.getText();
        String u = userIn.getText();
        String p = new String(passIn.getPassword());

        if (s.isEmpty() || u.isEmpty() || p.isEmpty()) {
            JOptionPane.showMessageDialog(window, "Fill in all fields!");
            return;
        }

        entryList.add(new Entry(s, u, p));
        model.addRow(new Object[]{s, u, "********"}); // Mask password in table
        
        // Clear fields
        siteIn.setText("");
        userIn.setText("");
        passIn.setText("");
    }

    private void deleteEntry() {
        int row = table.getSelectedRow();
        if (row != -1) {
            entryList.remove(row);
            model.removeRow(row);
        } else {
            JOptionPane.showMessageDialog(window, "Select a row first");
        }
    }

    private void saveFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(DATA_FILE))) {
            for (Entry en : entryList) {
                pw.println(en.site + "," + en.user + "," + en.pass);
            }
            JOptionPane.showMessageDialog(window, "Saved to " + DATA_FILE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadFile() {
        File f = new File(DATA_FILE);
        if (!f.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    entryList.add(new Entry(parts[0], parts[1], parts[2]));
                    model.addRow(new Object[]{parts[0], parts[1], "********"});
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading file");
        }
    }
}