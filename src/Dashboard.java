import javax.swing.*;

public class Dashboard extends JFrame {

    Dashboard() {
        setTitle("Dashboard");
        setSize(300,250);
        setLayout(null);

        JButton add = new JButton("Add Employee");
        add.setBounds(70,30,150,30);
        add(add);

        JButton view = new JButton("View Employees");
        view.setBounds(70,80,150,30);
        add(view);

        JButton exit = new JButton("Exit");
        exit.setBounds(70,130,150,30);
        add(exit);

        add.addActionListener(e -> new AddEmployee());
        view.addActionListener(e -> new ViewEmployee());
        exit.addActionListener(e -> System.exit(0));

        setVisible(true);
    }
}