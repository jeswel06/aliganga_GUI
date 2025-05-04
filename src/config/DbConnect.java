package config;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.swing.JOptionPane;

public class DbConnect {
    public Connection connect;

    public DbConnect() {
        try {
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/jobappointmen", "root", "");
            System.out.println("Connected to database successfully!");
        } catch (SQLException ex) {
            System.out.println("Can't connect to database: " + ex.getMessage());
        }
    }

    
    public Connection getConnection() {
        return connect;
    }

    public int insertUser(String fname, String lname, String contactnum, String email,String UserType, String reguser, String regpass) {
        int result = 0; 
        String sql = "INSERT INTO users (fn, ln, cn, em,type, us, ps, status) VALUES (?, ?, ?, ?, ?, ?, ?,?)";

        try (PreparedStatement pst = connect.prepareStatement(sql)) {
            pst.setString(1, fname);
            pst.setString(2, lname);
            pst.setString(3, contactnum);
            pst.setString(4, email);
            pst.setString(5, UserType);
            pst.setString(6, reguser);
            pst.setString(7, regpass);
            pst.setString(8, "Pending");

            result = pst.executeUpdate();
            System.out.println("User inserted successfully with 'Pending' status!");
        } catch (SQLException ex) {
            System.out.println("Insert Error: " + ex.getMessage());
        }
        return result;
    }
    
public void updateData(String sql, String fname, String lname, String email, String userType, String reguser, String number, String pass, String destination, String userId) {
    try (PreparedStatement pst = connect.prepareStatement(sql)) {
        // Set parameters directly
        pst.setString(1, fname);
        pst.setString(2, lname);
        pst.setString(3, email);
        pst.setString(4, userType);
        pst.setString(5, reguser);
        pst.setString(6, number);
        pst.setString(7, pass);
        pst.setString(8, destination);
        pst.setString(9, userId);

        int rowsUpdated = pst.executeUpdate();
        if (rowsUpdated > 0) {
            JOptionPane.showMessageDialog(null, "Data Updated Successfully!");
        } else {
            System.out.println("Data Update Failed!");
        }
    } catch (SQLException ex) {
        System.out.println("Connection Error: " + ex.getMessage());
    }
}
 public ResultSet getData(String sql) throws SQLException{
            Statement stmt = connect.createStatement();
            ResultSet rst = stmt.executeQuery(sql);
            return rst;
        }
 public void updateData(String sql){
            try{
                PreparedStatement pst = connect.prepareStatement(sql);
                    int rowsUpdated = pst.executeUpdate();
                        if(rowsUpdated > 0){
                            JOptionPane.showMessageDialog(null, "Data Updated Successfully!");
                        }else{
                            System.out.println("Data Update Failed!");
                        }
                        pst.close();
            }catch(SQLException ex){
                System.out.println("Connection Error: "+ex);
            }
 }
  public int insertData(String sql){
            int result;
            try{
                PreparedStatement pst = connect.prepareStatement(sql);
                pst.executeUpdate();
                System.out.println("Inserted Successfully!");
                pst.close();
                result =1;
            }catch(SQLException ex){
                System.out.println("Connection Error: "+ex);
                result =0;
            }
            return result;
        }
}