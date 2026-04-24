import javax.swing.*;
import java.sql.*;
import java.io.*;

public class AddEmployee extends JFrame {

    JTextField id, name, salary;

    AddEmployee() {
        setTitle("Add Employee");
        setSize(300,300);
        setLayout(null);

        JLabel l1 = new JLabel("ID:");
        l1.setBounds(20,30,100,25);
        add(l1);

        id = new JTextField();
        id.setBounds(120,30,120,25);
        add(id);

        JLabel l2 = new JLabel("Name:");
        l2.setBounds(20,70,100,25);
        add(l2);

        name = new JTextField();
        name.setBounds(120,70,120,25);
        add(name);

        JLabel l3 = new JLabel("Salary:");
        l3.setBounds(20,110,100,25);
        add(l3);

        salary = new JTextField();
        salary.setBounds(120,110,120,25);
        add(salary);

        JButton save = new JButton("Save");
        save.setBounds(90,160,100,30);
        add(save);

        JButton clear = new JButton("Clear");
        clear.setBounds(90,200,100,30);
        add(clear);

        save.addActionListener(e -> saveEmployee());

        clear.addActionListener(e -> {
            id.setText("");
            name.setText("");
            salary.setText("");
        });

        setVisible(true);
    }

    void saveEmployee() {
        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO employee VALUES(?,?,?)"
            );

            int empId = Integer.parseInt(id.getText());
            String empName = name.getText();
            double empSalary = Double.parseDouble(salary.getText());

            ps.setInt(1, empId);
            ps.setString(2, empName);
            ps.setDouble(3, empSalary);

            ps.executeUpdate();

            // 📄 FILE HANDLING (VISIBLE LOCATION)
            FileWriter fw = new FileWriter("employees.txt", true);
            fw.write("ID: " + empId + ", Name: " + empName + ", Salary: " + empSalary + "\n");
            fw.close();

            JOptionPane.showMessageDialog(null,"Saved Successfully");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error: " + e);
        }
    }
}