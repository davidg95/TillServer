/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.davidg95.JTill.jtillserver;

import io.github.davidg95.JTill.jtill.*;
import java.awt.Image;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author David
 */
public class DepartmentsWindow extends javax.swing.JFrame {
    
    private static DepartmentsWindow window;

    private final DataConnect dc;

    private final DefaultTableModel model;
    private List<Department> ctc;

    /**
     * Creates new form DepartmentWindow
     */
    public DepartmentsWindow(DataConnect dc, Image icon) {
        this.dc = dc;
        this.setIconImage(icon);
        initComponents();
        model = (DefaultTableModel) tblDep.getModel();
        updateTable();
    }
    
    public static void showWindow(DataConnect dc, Image icon){
        window = new DepartmentsWindow(dc, icon);
        window.setVisible(true);
    }

    private void updateTable() {
        try {
            ctc = dc.getAllDepartments();
            model.setRowCount(0);
            for (Department d : ctc) {
                Object[] row = new Object[]{d.getId(), d.getName()};
                model.addRow(row);
            }
        } catch (SQLException | IOException ex) {
            Logger.getLogger(DepartmentsWindow.class.getName()).log(Level.SEVERE, null, ex);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tblDep = new javax.swing.JTable();
        btnNewDepartment = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Edit Departments");

        tblDep.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "Name"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblDep);
        if (tblDep.getColumnModel().getColumnCount() > 0) {
            tblDep.getColumnModel().getColumn(0).setResizable(false);
            tblDep.getColumnModel().getColumn(1).setResizable(false);
        }

        btnNewDepartment.setText("Create new department");
        btnNewDepartment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewDepartmentActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(102, Short.MAX_VALUE)
                .addComponent(btnNewDepartment)
                .addGap(96, 96, 96)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(168, 168, 168)
                .addComponent(btnNewDepartment)
                .addContainerGap(218, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewDepartmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewDepartmentActionPerformed
        String name = JOptionPane.showInputDialog(this, "Enter name for new department", "New Department", JOptionPane.PLAIN_MESSAGE);

        if(name == null){
            return;
        }
        if (name.equals("")) {
            JOptionPane.showMessageDialog(this, "Cannot have a null value", "New Department", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            dc.addDepartment(new Department(name));
            updateTable();
        } catch (IOException | SQLException ex) {
            JOptionPane.showMessageDialog(this, ex);
        }
    }//GEN-LAST:event_btnNewDepartmentActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNewDepartment;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblDep;
    // End of variables declaration//GEN-END:variables
}
