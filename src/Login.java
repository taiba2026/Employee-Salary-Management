import javax.swing.*;

public class Login extends JFrame {
    JTextField user;
    JPasswordField pass;

    Login() {
        setTitle("Login");
        setSize(300,200);
        setLayout(null);

        JLabel l1 = new JLabel("Username:");
        l1.setBounds(20,30,80,25);
        add(l1);

        user = new JTextField();
        user.setBounds(100,30,150,25);
        add(user);

        JLabel l2 = new JLabel("Password:");
        l2.setBounds(20,70,80,25);
        add(l2);

        pass = new JPasswordField();
        pass.setBounds(100,70,150,25);
        add(pass);

        JButton login = new JButton("Login");
        login.setBounds(100,110,100,30);
        add(login);

        login.addActionListener(e -> {
            if(user.getText().equals("admin") &&
                    String.valueOf(pass.getPassword()).equals("1234")) {
                new Dashboard();
                dispose();
            } else {
                JOptionPane.showMessageDialog(null,"Invalid Login");
            }
        });

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Login();
    }
}