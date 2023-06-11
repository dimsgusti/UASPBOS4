package aplikasikulkas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.*;

public class storage extends JFrame{
    private DefaultTableModel tableModel;
    private JTable table;
    private JButton addButton, updateButton, deleteButton, goBack;
    String url = "jdbc:mysql://localhost:3306/aplikasikulkas";
    String dbuser = "root";
    String dbpass = "";
    public storage() throws SQLException {
        
        
        setTitle("CRUD Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(720, 480);
        setLocationRelativeTo(null);
        
        String[] columnNames = {"ID", "Nama", "Jumlah"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);

        addButton = new JButton("Tambah");
        updateButton = new JButton("Ubah");
        deleteButton = new JButton("Hapus");
        goBack = new JButton("Kembali");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addRowToTable();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateRowInTable();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteRowFromTable();
            }
        });

        goBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                App kembali = new App();
                kembali.setVisible(true);
                setVisible(false);
            }
        });

       JPanel buttonPanel = new JPanel();
       buttonPanel.add(addButton);
       buttonPanel.add(updateButton);
       buttonPanel.add(deleteButton);
       buttonPanel.add(goBack);

       fetchMySQLData();

       setLayout(new BorderLayout());
       add(scrollPane, BorderLayout.CENTER);
       add(buttonPanel, BorderLayout.SOUTH);

        
    }
    
    
    private void fetchMySQLData(){
        try{
            final Connection con = DriverManager.getConnection(url, dbuser, dbpass);
            Statement statement = null;
            ResultSet resultSet = null;
            statement = con.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM store");
            tableModel.setRowCount(0);
            while(resultSet.next()){
                String column1 = resultSet.getString("id_store");
                String column2 = resultSet.getString("nama");
                String column3 = resultSet.getString("jumlah");
    
                Object[] row = {column1, column2, column3};
                tableModel.addRow(row);
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    private void addRowToTable() {
        try{
            final Connection con = DriverManager.getConnection(url, dbuser, dbpass);
            String column2 = JOptionPane.showInputDialog("Masukkan nama barang: ");
            String column3 = JOptionPane.showInputDialog("Masukkan jumlah: ");
            String create = "INSERT INTO store (nama, jumlah) VALUES (?,?)";
            PreparedStatement stmt = con.prepareStatement(create);
            stmt.setString(1, column2);
            stmt.setString(2, column3);
            int rowsInserted = stmt.executeUpdate();
            if(rowsInserted > 0){
                System.out.println("Berhasil create");
                Statement statement = null;
                ResultSet resultSet = null;
                statement = con.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM store");
                tableModel.setRowCount(0);
                while(resultSet.next()){
                    String column1 = resultSet.getString("id_store");
                    Object[] row = {column1, column2, column3};
                    tableModel.addRow(row);
                }
            } else{
                System.out.println("Create gagal");
            }
        } catch(SQLException e){
            e.printStackTrace();
        }

    }

    private void updateRowInTable() {
        int selectedRow = table.getSelectedRow();
        String selected = String.valueOf(selectedRow);
        JTextField nama = new JTextField("Nama baru: ");
        String namaBaru = nama.getText();
        JTextField jumlah = new JTextField("Jumlah baru: ");
        String jumlahBaru = jumlah.getText();
        if (selectedRow != -1) {
            String update = "UPDATE store SET nama = ?, jumlah = ? WHERE id_store = ?";
            try(Connection con = DriverManager.getConnection(url, dbuser, dbpass);
            PreparedStatement stmt = con.prepareStatement(update)){
                stmt.setString(1, namaBaru);
                stmt.setString(2, jumlahBaru);
                stmt.setString(3, selected);
                int rowsAffected = stmt.executeUpdate();
                if(rowsAffected > 0){
                    System.out.println("Update berhasil");
                } else{
                    System.out.println("Update gagal");
                }
            } catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    private void deleteRowFromTable() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            try{
                final Connection con = DriverManager.getConnection(url, dbuser, dbpass);
                String delete = "DELETE FROM store WHERE store.id_store = ?";
                PreparedStatement preparedStmt = con.prepareStatement(delete);
                preparedStmt.setInt(1, selectedRow);
                preparedStmt.execute();
                con.close();
                tableModel.removeRow(selectedRow);
            } catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
}