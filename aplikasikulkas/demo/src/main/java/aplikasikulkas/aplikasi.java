package aplikasikulkas;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class aplikasi extends JFrame {
    private JLabel modeNow;
    private JLabel mode;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JButton selMode;
    private JLabel tempNow;
    private JLabel temp;
    private JButton tempButton;
    private JButton onOff;
    private JButton backButton;
    private JButton storeSettings;


    public aplikasi() {
        setTitle("Register Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(720, 480);
        setLocationRelativeTo(null);
        // setLayout(new GridLayout(7, 4));
        setLayout(new FlowLayout());

        modeNow = new JLabel("Mode saat ini:");
        mode = new JLabel("Normal");
        radioButton1 = new JRadioButton("Normal");
        radioButton2 = new JRadioButton("Hemat daya");
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButton1);
        buttonGroup.add(radioButton2);
        selMode = new JButton("Pilih");
        selMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(radioButton1.isSelected()){
                    mode.setText("Normal");
                } else if(radioButton2.isSelected()){
                    mode.setText("Hemat daya");
                } else{
                    mode.setText("Normal");
                }
            }
        });
        tempNow = new JLabel("Temperatur saat ini:");
        temp = new JLabel("0 Celcius");
        final JSlider slider = new JSlider(JSlider.HORIZONTAL, -20, 20 ,-5);
        slider.setMajorTickSpacing(5);
        slider.setMinorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        tempButton = new JButton("Ubah suhu");

        tempButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                int suhu = slider.getValue();
                String suhu1 = String.valueOf(suhu);
                temp.setText(suhu1 + " Celcius");
            }
        });

        onOff = new JButton("Matikan");

        onOff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                temp.setText("Mati");
                mode.setText("Mati");
            }
        });
        
        storeSettings = new JButton("Daftar penyimpanan");

        storeSettings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                try{
                    storage str = new storage();
                    str.setVisible(true);
                    setVisible(false);

                } catch(SQLException ex){
                    ex.printStackTrace();
                }
            }
        });

        backButton = new JButton("Kembali");

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                App app = new App();
                app.setVisible(true);
                setVisible(false);
            }
        });

        add(modeNow);
        add(mode);
        add(radioButton1);
        add(radioButton2);
        add(selMode);
        add(tempNow);
        add(temp);
        add(slider);
        add(tempButton);
        add(onOff);
        add(storeSettings);
        add(backButton);
        
        setVisible(true);
    }
}
