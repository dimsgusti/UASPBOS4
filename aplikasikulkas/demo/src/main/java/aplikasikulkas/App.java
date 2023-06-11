package aplikasikulkas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App extends JFrame{
    public App(){
        setTitle("Aplikasi Kulkas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300,200);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());
        
        JLabel judulLabel = new JLabel("Pilih menu:");
        JButton openFile1Button = new JButton("Login");
        JButton openFile2Button = new JButton("Register");

        openFile1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                try{
                    loginForm login = new loginForm();
                    login.setVisible(true);
                    setVisible(false);
                } catch(SQLException ec){
                    ec.printStackTrace();
                }
            }
        });

        openFile2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                try{
                    registerForm register = new registerForm();
                    register.setVisible(true);
                    setVisible(false);

                } catch(SQLException ex){
                    ex.printStackTrace();
                }
            }
        });

        add(judulLabel);
        add(openFile1Button);
        add(openFile2Button);

        setVisible(true);
    }

    static void connection() throws SQLException{
        String url = "jdbc:mysql://localhost:3306/aplikasikulkas";
        String dbuser = "root";
        String dbpass = "";
        try(Connection con = DriverManager.getConnection(url, dbuser, dbpass);
        Statement stmt = con.createStatement()){
            if(con.isValid(5)){
                System.out.println("Berhasil terhubung ke database!");
            } else{
                System.out.println("Gagal terhubung ke database!");
            }
        }
    }

    public static void main(String[] args) throws SQLException{
        connection();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run(){
                new App();
            }
        });
    }
}
