/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import Admin.AdminDashboard;
import Admin.CurrentUsers;
import Admin.Login;
import static Admin.Login.hashPassword;
import config.DbConnect;
import config.Session;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author SCC24
 */
public class CreateUserForm extends javax.swing.JFrame {
private Connection connect;
    /**
     * Creates new form createUserForm
     */
    public CreateUserForm() {
        initComponents();
        this.setResizable(false);
         this.setLocationRelativeTo(null);
    }
    
     private String userId; // Declare userId at the class level

    public void setUserId(String id) {
        this.userId = id; // Store the user ID for later use
    }
    public String destination;
   File selectedFile;
   public String oldpath;
   public String path;
   
    



public int FileExistenceChecker(String path){
        File file = new File(path);
        String fileName = file.getName();
        
        Path filePath = Paths.get("src/Images", fileName);
        boolean fileExists = Files.exists(filePath);
        
        if (fileExists) {
            return 1;
        } else {
            return 0;
        }
    
    }
public void imageUpdater(String existingFilePath, String newFilePath){
        File existingFile = new File(existingFilePath);
        if (existingFile.exists()) {
            String parentDirectory = existingFile.getParent();
            File newFile = new File(newFilePath);
            String newFileName = newFile.getName();
            File updatedFile = new File(parentDirectory, newFileName);
            existingFile.delete();
            try {
                Files.copy(newFile.toPath(), updatedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Image updated successfully.");
            } catch (IOException e) {
                System.out.println("Error occurred while updating the image: "+e);
            }
        } else {
            try{
                Files.copy(selectedFile.toPath(), new File(destination).toPath(), StandardCopyOption.REPLACE_EXISTING);
            }catch(IOException e){
                System.out.println("Error on update!");
            }
        }
   }
public ImageIcon ResizeImage(String ImagePath, byte[] pic, JLabel label) {
    ImageIcon MyImage = (ImagePath != null) ? new ImageIcon(ImagePath) : new ImageIcon(pic);

    int labelWidth = label.getWidth();
    if (labelWidth == 0) labelWidth = 150; // fallback width if not initialized

    int newHeight = getHeightFromWidth(ImagePath, labelWidth);
    if (newHeight <= 0) newHeight = 150; // fallback height

    Image img = MyImage.getImage();
    Image newImg = img.getScaledInstance(labelWidth, newHeight, Image.SCALE_SMOOTH);
    return new ImageIcon(newImg);
}

public static int getHeightFromWidth(String imagePath, int desiredWidth) {
        try {
            
            File imageFile = new File(imagePath);
            BufferedImage image = ImageIO.read(imageFile);
            
           
            int originalWidth = image.getWidth();
            int originalHeight = image.getHeight();
            
            
            int newHeight = (int) ((double) desiredWidth / originalWidth * originalHeight);
            
            return newHeight;
        } catch (IOException ex) {
            System.out.println("No image found!"+ex);
        }
        
        return -1;
    }

 private void logActivity(int userId, String action) {
    String logSql = "INSERT INTO logs (user_id, activity, log_time) VALUES (?, ?, NOW())";

    try (Connection connect = new DbConnect().getConnection(); 
         PreparedStatement pst = connect.prepareStatement(logSql)) {

        pst.setInt(1, userId);  // Set the user ID from the session
        pst.setString(2, action);  // Set the action (e.g., "Admin logged in")

        int rowsInserted = pst.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Log successfully recorded.");
        }

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Log Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        id = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        Fname = new javax.swing.JTextField();
        Passreg = new javax.swing.JPasswordField();
        userReg = new javax.swing.JTextField();
        Email = new javax.swing.JTextField();
        Number = new javax.swing.JTextField();
        Lname = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        image = new javax.swing.JLabel();
        select = new javax.swing.JButton();
        remove = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 51, 51));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Arial Black", 1, 15)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("EXIT");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 32));

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jLabel12.setText("WELCOME TO");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 210, 32));

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jLabel11.setText("JOB APPLICATION");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, 270, 32));

        id.setEditable(false);
        jPanel2.add(id, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 10, 60, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 910, 40));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(255, 51, 51)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel3.setText("Type:");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 310, 90, 40));

        Fname.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 51, 51)));
        Fname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FnameActionPerformed(evt);
            }
        });
        jPanel3.add(Fname, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 160, 140, 30));

        Passreg.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 51, 51)));
        Passreg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PassregActionPerformed(evt);
            }
        });
        jPanel3.add(Passreg, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 400, 140, 30));

        userReg.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 51, 51)));
        userReg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userRegActionPerformed(evt);
            }
        });
        jPanel3.add(userReg, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 360, 140, 30));

        Email.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 51, 51)));
        Email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmailActionPerformed(evt);
            }
        });
        jPanel3.add(Email, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 280, 140, 30));

        Number.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 51, 51)));
        Number.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NumberActionPerformed(evt);
            }
        });
        jPanel3.add(Number, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 240, 140, 30));

        Lname.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 51, 51)));
        Lname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LnameActionPerformed(evt);
            }
        });
        jPanel3.add(Lname, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 200, 140, 30));

        jLabel5.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel5.setText("First Name:");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 150, 90, 40));

        jLabel6.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel6.setText("Last Name:");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 190, 90, 40));

        jLabel7.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel7.setText("Number:");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 230, 90, 40));

        jButton1.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 51, 51));
        jButton1.setText("REGISTER");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 480, 130, 40));

        jButton2.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 51, 51));
        jButton2.setText("BACK");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 480, 90, 40));

        jComboBox1.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Client", "Applicator", "Supervisor", "Admin" }));
        jComboBox1.setBorder(null);
        jComboBox1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel3.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 320, 140, 30));

        jLabel9.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel9.setText("Email:");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 270, 90, 40));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(255, 51, 51)));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        jLabel1.setText("Complete your info Here!");
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, -1, -1));

        jLabel2.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel2.setText("Password:");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 90, 40));

        jLabel4.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel4.setText("Username:");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 90, 40));

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel10.setText("USER FORM");
        jPanel4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 130, 32));

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 320, 380));

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel5.add(image, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 290, 360));

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 80, 290, 360));

        select.setText("Select");
        select.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectActionPerformed(evt);
            }
        });
        jPanel3.add(select, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 450, -1, -1));

        remove.setText("Remove");
        remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeActionPerformed(evt);
            }
        });
        jPanel3.add(remove, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 450, -1, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 910, 530));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 530));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel8MouseClicked

    private void FnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FnameActionPerformed

    private void PassregActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PassregActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PassregActionPerformed

    private void userRegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userRegActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userRegActionPerformed

    private void EmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EmailActionPerformed

    private void NumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NumberActionPerformed

    private void LnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LnameActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       String fname = Fname.getText().trim();
    String lname = Lname.getText().trim();
    String username = userReg.getText().trim();
    String email = Email.getText().trim();
    String password = Passreg.getText().trim();
    String cn = Number.getText().trim();
    String type = jComboBox1.getSelectedItem().toString();
    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    // Validation checks
    if (fname.isEmpty() || lname.isEmpty() || cn.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty()) {
        JOptionPane.showMessageDialog(this, "All fields are required. Please fill out the form.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    if (!fname.matches("[a-zA-Z ]+") || !lname.matches("[a-zA-Z ]+")) {
        JOptionPane.showMessageDialog(this, "Only letters are allowed for First and Last Name.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    if (!cn.matches("\\d+")) {
        JOptionPane.showMessageDialog(this, "Invalid contact number! Only numbers are allowed.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    if (!email.matches(emailRegex)) {
        JOptionPane.showMessageDialog(this, "Invalid Email! Please enter a valid email address.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    if (!username.matches("[a-zA-Z0-9_]{5,}")) {
        JOptionPane.showMessageDialog(this, "Invalid Username! Must be at least 5 characters and contain only letters, numbers, and underscores.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    if (!password.matches("^(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?])(?=.*\\d).{8,}$")) {
        JOptionPane.showMessageDialog(this, "Invalid Password! Must be at least 8 characters long, contain one uppercase letter, one special character, and one number.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    if (selectedFile == null) {
        JOptionPane.showMessageDialog(this, "Please upload a profile image.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try (Connection connect = new DbConnect().getConnection()) {
        // Check if username or email already exists
        String checkQuery = "SELECT COUNT(*) FROM users WHERE us = ? OR em = ?";
        try (PreparedStatement checkStmt = connect.prepareStatement(checkQuery)) {
            checkStmt.setString(1, username);
            checkStmt.setString(2, email);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(this, "Username or Email already exists. Please choose another.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        // Move uploaded profile image
        try {
            Files.copy(selectedFile.toPath(), new File(destination).toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error uploading image: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Hash the password
        String hashedPassword = hashPassword(password);

        // Insert new user into the users table
        String insertQuery = "INSERT INTO users (fn, ln, cn, em, us, ps, type, status, image) VALUES (?, ?, ?, ?, ?, ?, ?, 'Pending', ?)";
        try (PreparedStatement insertStmt = connect.prepareStatement(insertQuery)) {
            insertStmt.setString(1, fname);
            insertStmt.setString(2, lname);
            insertStmt.setString(3, cn);
            insertStmt.setString(4, email);
            insertStmt.setString(5, username);
            insertStmt.setString(6, hashedPassword);
            insertStmt.setString(7, type);
            insertStmt.setString(8, destination);

            int inserted = insertStmt.executeUpdate();

            if (inserted > 0) {
                // Logging action
                Session sess = Session.getInstance();
                int adminId = sess.getuid(); // Ensure session is loaded
                String action = "Admin Registered a user";
                
                // Log the action in the logs table
                String logSql = "INSERT INTO logs (user_id, activity, log_time) VALUES (?, ?, NOW())"; // Updated to use user_id
                try (PreparedStatement logStmt = connect.prepareStatement(logSql)) {
                    logStmt.setInt(1, adminId);  // Use admin ID (from session)
                    logStmt.setString(2, action); // Action message
                    logStmt.executeUpdate();
                }

                JOptionPane.showMessageDialog(this, "Registration Successful! Your account is pending approval.", "Success", JOptionPane.INFORMATION_MESSAGE);
                new CurrentUsers().setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Registration failed. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        AdminDashboard ad = new AdminDashboard();
        ad.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void selectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectActionPerformed
       JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    try {
                        selectedFile = fileChooser.getSelectedFile();
                        destination = "src/Images/" + selectedFile.getName();
                        path  = selectedFile.getAbsolutePath();
                        
                        
                        if(FileExistenceChecker(path) == 1){
                          JOptionPane.showMessageDialog(null, "File Already Exist, Rename or Choose another!");
                            destination = "";
                            path="";
                        }else{
                            image.setIcon(ResizeImage(path, null, image));
                            select.setEnabled(false);
                            remove.setEnabled(true);
                            
                        }
                    } catch (Exception ex) {
                        System.out.println("File Error!"+ex);
                    }
                }
    }//GEN-LAST:event_selectActionPerformed

    private void removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeActionPerformed
       remove.setEnabled(false);
       select.setEnabled(true);
       image.setIcon(null);
       destination = "";
       path = "";
    }//GEN-LAST:event_removeActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        Session sess = Session.getInstance();
    if (sess.getuid() == 0) {
        JOptionPane.showMessageDialog(this, "No account found, Log in First!", "Login Required", JOptionPane.WARNING_MESSAGE);
        Login lf = new Login();
        lf.setVisible(true);
        this.dispose();
        return; 
    }
   
    id.setText(String.valueOf(sess.getuid()));
    }//GEN-LAST:event_formWindowActivated

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CreateUserForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreateUserForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreateUserForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreateUserForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CreateUserForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextField Email;
    public javax.swing.JTextField Fname;
    public javax.swing.JTextField Lname;
    public javax.swing.JTextField Number;
    public javax.swing.JPasswordField Passreg;
    private javax.swing.JTextField id;
    public javax.swing.JLabel image;
    public javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    public javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    public javax.swing.JButton remove;
    public javax.swing.JButton select;
    public javax.swing.JTextField userReg;
    // End of variables declaration//GEN-END:variables
}
