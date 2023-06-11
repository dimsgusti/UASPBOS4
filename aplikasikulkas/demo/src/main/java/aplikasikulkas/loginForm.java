package aplikasikulkas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class loginForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public loginForm() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/aplikasikulkas";
        String dbuser = "root";
        String dbpass = "";
        final Connection con = DriverManager.getConnection(url, dbuser, dbpass);

        setTitle("Login Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(7, 1));

        JLabel usernameLabel = new JLabel("Username: ");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password");
        passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");
        JButton backButton = new JButton("Go back");

        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(loginButton);
        add(backButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] passwordChar = passwordField.getPassword();
                String password = new String(passwordChar);

                try {
                    String query = "SELECT * FROM user WHERE username = ? AND password = ?";
                    PreparedStatement statement = con.prepareStatement(query);
                    statement.setString(1, username);
                    statement.setString(2, password);
                    ResultSet rs = statement.executeQuery();
                    if(rs.next()){
                        System.out.println("Berhasil masuk!");
                        aplikasi apl = new aplikasi();
                        apl.setVisible(true);
                        setVisible(false);
                    } else{
                        notif Notif = new notif();
                        Notif.setVisible(true);
                        System.out.println("Username atau password salah");
                    }
                } catch (SQLException ev) {
                    ev.printStackTrace();
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                App kembali = new App();
                kembali.setVisible(true);
                setVisible(false);
            }
        });
    }
}
