package aplikasikulkas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class registerForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public registerForm() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/aplikasikulkas";
        String dbuser = "root";
        String dbpass = "";
        final Connection con = DriverManager.getConnection(url, dbuser, dbpass);

        setTitle("Register Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(7, 1));

        JLabel usernameLabel = new JLabel("Username: ");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password");
        passwordField = new JPasswordField();
        JButton registerButton = new JButton("Register");
        JButton backButton = new JButton("Go back");

        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(registerButton);
        add(backButton);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] passwordChar = passwordField.getPassword();
                String password = new String(passwordChar);

                try {
                    String query = "INSERT INTO user (username, password) VALUES (?,?)";
                    PreparedStatement statement = con.prepareStatement(query);
                    statement.setString(1, username);
                    statement.setString(2, password);
                    int rowsInserted = statement.executeUpdate();
                    if (rowsInserted > 0) {
                        System.out.println("Berhasil registrasi");
                    } else {
                        System.out.println("Gagal melakukan registrasi");
                    }
                } catch (SQLException ev) {
                    ev.printStackTrace();
                }
                try{
                    loginForm login = new loginForm();
                    login.setVisible(true);
                    setVisible(false);
                } catch(SQLException eb){
                    eb.printStackTrace();
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
