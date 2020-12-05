/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;
import java.sql.*;

/**
 *
 * @author ameri
 */
public class ConnectUti {
        Connection conn = null;
        public static Connection ConectDb() {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/keeptoo", "root", "");
                JOptionPane.showMessageDialog(null, "Connected");
                return conn;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
                return null;
            }

        }
              
        public static ObservableList<users> getDatausers() {
           
            Connection conn = ConnectDb();
            ObservableList<users> list = FXCollections.observableArrayList();
            try {
                PreparedStatment ps = conn.preparedStatement("select * from users");
                ResultSet rs = ps.executeQuery();
                
                while (rs.next()) {   
                list.add(new users(Integer.parseInt(rs.getString("user_id")), rs.getString("username"), rs.getString("password"), rs.getString("email"), rs.getString("type")));
                }
            }
            catch (Exception e) {
                
            }
            return list;
        }
        
}
