import javax.swing.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;

public class ViewEmployee extends JFrame {

    JTable table;
    DefaultTableModel model;

    ViewEmployee() {
        setTitle("Employees");
        setSize(500,350);
        setLayout(null);

        model = new DefaultTableModel();
        table = new JTable(model);

        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Basic Salary");
        model.addColumn("Net Salary");

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20,20,450,150);
        add(sp);

        JButton highest = new JButton("Highest Salary");
        highest.setBounds(50,200,150,30);
        add(highest);

        JButton delete = new JButton("Delete by ID");
        delete.setBounds(250,200,150,30);
        add(delete);

        highest.addActionListener(e -> showHighest());
        delete.addActionListener(e -> deleteEmployee());

        loadData();

        setVisible(true);
    }

    void loadData() {
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM employee");

            while(rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                double salary = rs.getDouble(3);

                double hra = salary * 0.10;
                double bonus = salary * 0.05;
                double net = salary + hra + bonus;

                model.addRow(new Object[]{id, name, salary, net});
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void showHighest() {
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(
                    "SELECT * FROM employee ORDER BY salary DESC LIMIT 1"
            );

            if(rs.next()) {
                JOptionPane.showMessageDialog(null,
                        "Highest Paid:\nName: " + rs.getString("name") +
                                "\nSalary: " + rs.getDouble("salary"));
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void deleteEmployee() {
        String id = JOptionPane.showInputDialog("Enter ID to delete");

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM employee WHERE id=?"
            );

            ps.setInt(1, Integer.parseInt(id));

            int result = ps.executeUpdate();

            if(result > 0) {
                File inputFile = new File("employees.txt");
                File tempFile = new File("temp.txt");

                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

                String line;

                while ((line = reader.readLine()) != null) {
                    if (!line.contains("ID: " + id + ",")) {
                        writer.write(line);
                        writer.newLine();
                    }
                }

                reader.close();
                writer.close();

                inputFile.delete();
                tempFile.renameTo(inputFile);

                JOptionPane.showMessageDialog(null,"Deleted Successfully");
                model.setRowCount(0);
                loadData();
            } else {
                JOptionPane.showMessageDialog(null,"ID not found");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}