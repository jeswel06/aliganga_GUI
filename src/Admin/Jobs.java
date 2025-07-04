/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author SCC-COLLEGE
 */
public class Jobs extends javax.swing.JFrame {

    /**
     * Creates new form Jobs
     */
    public Jobs() {
        initComponents();
         loadApprovedJobs();
    }
public void loadApprovedJobs() {
    // SQL to get only approved jobs
    String query = "SELECT job_id, user_id, title, description, location, salary, status, created_at " +
                   "FROM jobs WHERE status = 'approved'";

    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jobappointmen", "root", "");
         PreparedStatement pst = conn.prepareStatement(query);
         ResultSet rs = pst.executeQuery()) {

        // Set column headers for jobs
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[] {
            "Job ID", "User ID", "Title", "Description", "Location",
            "Salary", "Status", "Created At"
        });

        // Populate the table with job data
        while (rs.next()) {
            model.addRow(new Object[] {
                rs.getInt("job_id"),
                rs.getInt("user_id"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getString("location") != null ? rs.getString("location") : "",
                rs.getBigDecimal("salary") != null ? rs.getBigDecimal("salary") : 0.00,
                rs.getString("status") != null ? rs.getString("status") : "",
                rs.getTimestamp("created_at") != null ? rs.getTimestamp("created_at") : ""
            });
        }

        // Display the data in the JTable
        table.setModel(model);

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Failed to load approved jobs.\n" + e.getMessage(),
                "Database Error", JOptionPane.ERROR_MESSAGE);
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Unexpected error occurred.\n" + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
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

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        approved = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(255, 102, 102)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(table);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 13, 660, 260));

        jPanel3.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 680, 280));

        jPanel2.setBackground(new java.awt.Color(255, 102, 51));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 36)); // NOI18N
        jLabel1.setText("JOB APPOINMENT");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel3.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 60));

        jButton1.setText("BACK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 70, 100, 30));

        approved.setText("APPROVED");
        approved.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                approvedActionPerformed(evt);
            }
        });
        jPanel3.add(approved, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 70, 100, 30));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 400));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      AdminDashboard ad = new AdminDashboard();
      ad.setVisible(true);
      this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void approvedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_approvedActionPerformed
                                          
    int row = table.getSelectedRow();

    // Check if a row is selected
    if (row == -1) {
        JOptionPane.showMessageDialog(null, "Please select a job to approve.");
        return;
    }

    // Get job ID from the first column of the selected row
    int jobId = (int) table.getValueAt(row, 0);

    // Update the job status to 'approved'
    String sql = "UPDATE jobs SET status = 'approved' WHERE job_id = ?";

    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jobapppointmen", "root", "");
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setInt(1, jobId);
        int rowsAffected = pstmt.executeUpdate();

        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(null, "Job approved successfully.");
            // Optional: refresh the table
            loadApprovedJobs(); // Make sure you have this method to reload the data
        } else {
            JOptionPane.showMessageDialog(null, "No job was updated. Please try again.");
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error approving job:\n" + e.getMessage(),
                "Database Error", JOptionPane.ERROR_MESSAGE);
    }

    }//GEN-LAST:event_approvedActionPerformed

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
            java.util.logging.Logger.getLogger(Jobs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Jobs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Jobs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Jobs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Jobs().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton approved;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
