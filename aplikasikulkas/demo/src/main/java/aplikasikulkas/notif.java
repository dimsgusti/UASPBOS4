package aplikasikulkas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class notif extends JFrame{
    private JButton click;

    public notif(){
        setTitle("Notifikasi");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(2,2));

        JLabel notif = new JLabel("Terjadi kesalahan silahkan coba lagi!");
        click = new JButton("OK");

        click.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                setVisible(false);
            }
        });

        add(notif);
        add(click);

        setVisible(true);
    }
}
