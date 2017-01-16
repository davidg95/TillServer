/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.davidg95.JTill.jtillserver;

import io.github.davidg95.JTill.jtill.*;
import java.awt.Color;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author David
 */
public class CategorysWindow extends javax.swing.JFrame {

    public static CategorysWindow frame;

    private final DataConnectInterface dbConn;
    private Category category;

    private final DefaultTableModel model;
    private List<Category> currentTableContents;

    /**
     * Creates new form CategoryWindow
     */
    public CategorysWindow(DataConnectInterface dc) {
        dbConn = dc;
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        currentTableContents = new ArrayList<>();
        model = (DefaultTableModel) table.getModel();
        showAllCategorys();
    }

    public static void initWindow() {
//        if (frame != null) {
//            frame = new CategorysWindow();
//            frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
//        }
    }

    public static void showCategoryWindow(DataConnectInterface dc) {
        if (frame == null) {
            frame = new CategorysWindow(dc);
            frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        }
        update();
        frame.setCurrentCategory(null);
        frame.setVisible(true);
    }

    public static void update() {
        if (frame != null) {
            frame.showAllCategorys();
        }
    }

    private void updateTable() {
        model.setRowCount(0);

        for (Category c : currentTableContents) {
            Object[] s = new Object[]{c.getID(), c.getName()};
            model.addRow(s);
        }

        table.setModel(model);
        ProductsWindow.update();
    }

    private void showAllCategorys() {
        try {
            currentTableContents = dbConn.getAllCategorys();
            updateTable();
        } catch (IOException | SQLException ex) {
            showError(ex);
        }
    }

    private void setCurrentCategory(Category c) {
        if (c == null) {
            txtName.setText("");
            chkTime.setSelected(false);
            lblTime.setEnabled(false);
            txtStartTime.setEnabled(false);
            lblTimeDash.setEnabled(false);
            txtEndTime.setEnabled(false);
            txtStartTime.setText("");
            txtEndTime.setText("");
            spinAge.setValue(0);
            chkButton.setSelected(false);
            btnColor.setBackground(null);
            btnColor.setEnabled(false);
            chkDefault.setSelected(true);
            chkDefault.setEnabled(false);
            category = null;
        } else {
            category = c;
            txtName.setText(c.getName());
            chkTime.setSelected(c.isTimeRestrict());
            if (c.isTimeRestrict()) {
                chkTime.setSelected(true);
                lblTime.setEnabled(true);
                txtStartTime.setEnabled(true);
                lblTimeDash.setEnabled(true);
                txtEndTime.setEnabled(true);
                txtStartTime.setText(c.getStartSell().toString());
                txtEndTime.setText(c.getEndSell().toString());
            } else {
                chkTime.setSelected(false);
                lblTime.setEnabled(false);
                txtStartTime.setEnabled(false);
                lblTimeDash.setEnabled(false);
                txtEndTime.setEnabled(false);
                txtStartTime.setText("");
                txtEndTime.setText("");
            }
            spinAge.setValue(c.getMinAge());
            chkButton.setSelected(category.isButton());
            if (category.isButton()) {
                chkDefault.setEnabled(true);
                if (category.getColorValue() != 0) {
                    btnColor.setEnabled(true);
                    btnColor.setBackground(new Color(category.getColorValue()));
                    chkDefault.setSelected(false);
                } else {
                    btnColor.setEnabled(false);
                    btnColor.setBackground(null);
                    chkDefault.setSelected(true);
                }
            } else {
                btnColor.setEnabled(false);
                btnColor.setBackground(null);
                chkDefault.setSelected(true);
                chkDefault.setEnabled(false);
            }
        }
    }

    private void showError(Exception e) {
        JOptionPane.showMessageDialog(this, e, "Categorys", JOptionPane.ERROR_MESSAGE);
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
        table = new javax.swing.JTable();
        btnClose = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        chkTime = new javax.swing.JCheckBox();
        txtStartTime = new javax.swing.JTextField();
        txtEndTime = new javax.swing.JTextField();
        lblTime = new javax.swing.JLabel();
        lblTimeDash = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        spinAge = new javax.swing.JSpinner();
        chkButton = new javax.swing.JCheckBox();
        btnColor = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        chkDefault = new javax.swing.JCheckBox();

        setTitle("Categorys");
        setIconImage(TillServer.getIcon());

        table.setModel(new javax.swing.table.DefaultTableModel(
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
                java.lang.String.class, java.lang.String.class
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
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setResizable(false);
            table.getColumnModel().getColumn(1).setResizable(false);
        }

        btnClose.setText("Close");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        jLabel1.setText("Name:");

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnRemove.setText("Remove");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        chkTime.setText("Time Restricted");
        chkTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTimeActionPerformed(evt);
            }
        });

        txtStartTime.setEnabled(false);

        txtEndTime.setEnabled(false);

        lblTime.setText("Time Restriction:");
        lblTime.setEnabled(false);

        lblTimeDash.setText("-");
        lblTimeDash.setEnabled(false);

        jLabel2.setText("Minimum Age:");

        chkButton.setText("Button");
        chkButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkButtonActionPerformed(evt);
            }
        });

        btnColor.setText("Set Button Color");
        btnColor.setEnabled(false);
        btnColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnColorActionPerformed(evt);
            }
        });

        jLabel3.setText("Search:");

        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });

        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        chkDefault.setSelected(true);
        chkDefault.setText("Use Default Colour");
        chkDefault.setEnabled(false);
        chkDefault.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkDefaultActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkTime)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemove))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblTime)
                                .addComponent(jLabel2))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtStartTime)
                                .addComponent(spinAge, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblTimeDash)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtEndTime, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(chkButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnColor)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(chkDefault))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 420, Short.MAX_VALUE)
                        .addComponent(btnClose))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chkTime)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtStartTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTime)
                            .addComponent(lblTimeDash)
                            .addComponent(txtEndTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(spinAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chkButton)
                            .addComponent(btnColor)
                            .addComponent(chkDefault))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAdd)
                            .addComponent(btnSave)
                            .addComponent(btnRemove))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnClose)
                    .addComponent(jLabel3)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if (category == null) {
            Category c;
            try {
                String name = txtName.getText();
                boolean time = chkTime.isSelected();
                Time startSell = null;
                Time endSell = null;
                if (time) {
                    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
                    startSell = new Time(sdf.parse(txtStartTime.getText()).getTime());
                    endSell = new Time(sdf.parse(txtEndTime.getText()).getTime());
                }
                int minAge = (int) spinAge.getValue();
                boolean button = chkButton.isSelected();
                int color;
                if (chkDefault.isSelected()) {
                    color = 0;
                } else {
                    color = btnColor.getBackground().getRGB();
                }
                if (name.equals("")) {
                    JOptionPane.showMessageDialog(this, "Fill out all required fields", "New Category", JOptionPane.ERROR_MESSAGE);
                } else {
                    c = new Category(name, startSell, endSell, time, minAge, button, color);
                    try {
                        dbConn.addCategory(c);
                        showAllCategorys();
                        setCurrentCategory(null);
                    } catch (SQLException | IOException ex) {
                        showError(ex);
                    }
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Fill out all required fields", "New Category", JOptionPane.ERROR_MESSAGE);
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(this, "Invalid time format, user HH:mm:ss", "New Category", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            category = null;
            setCurrentCategory(null);
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        try {
            String name = txtName.getText();
            boolean time = chkTime.isSelected();
            Time startSell = null;
            Time endSell = null;
            if (time) {
                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
                startSell = new Time(sdf.parse(txtStartTime.getText()).getTime());
                endSell = new Time(sdf.parse(txtEndTime.getText()).getTime());
            }
            int minAge = (int) spinAge.getValue();
            boolean button = chkButton.isSelected();
            int color;
            if (chkDefault.isSelected()) {
                color = 0;
            } else {
                color = btnColor.getBackground().getRGB();
            }
            if (name.equals("")) {
                JOptionPane.showMessageDialog(this, "Fill out all required fields", "New Category", JOptionPane.ERROR_MESSAGE);
            } else {
                category.setName(name);
                category.setStartSell(startSell);
                category.setEndSell(endSell);
                category.setTimeRestrict(time);
                category.setMinAge(minAge);
                category.setButton(button);
                category.setColorValue(color);

                try {
                    dbConn.updateCategory(category);
                } catch (SQLException | CategoryNotFoundException | IOException ex) {
                    showError(ex);
                }

                showAllCategorys();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Fill out all required fields", "Category", JOptionPane.ERROR_MESSAGE);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Invalid time format, user HH:mm:ss", "New Category", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        int index = table.getSelectedRow();
        if (index != -1) {
            int opt = JOptionPane.showConfirmDialog(this, "Are you sure you want to remove the following category?\n" + currentTableContents.get(index), "Remove Category", JOptionPane.YES_NO_OPTION);
            if (opt == JOptionPane.YES_OPTION) {
                try {
                    dbConn.removeCategory(currentTableContents.get(index).getID());
                } catch (SQLException | CategoryNotFoundException | IOException ex) {
                    showError(ex);
                }
                showAllCategorys();
                setCurrentCategory(null);
            }
        }
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnCloseActionPerformed

    private void tableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMousePressed
        if (evt.getClickCount() == 1) {
            setCurrentCategory(currentTableContents.get(table.getSelectedRow()));
        }
    }//GEN-LAST:event_tableMousePressed

    private void chkTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTimeActionPerformed
        lblTime.setEnabled(chkTime.isSelected());
        txtStartTime.setEnabled(chkTime.isSelected());
        lblTimeDash.setEnabled(chkTime.isSelected());
        txtEndTime.setEnabled(chkTime.isSelected());
    }//GEN-LAST:event_chkTimeActionPerformed

    private void btnColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnColorActionPerformed
        btnColor.setBackground(JColorChooser.showDialog(this, "Select button color", btnColor.getBackground()));
    }//GEN-LAST:event_btnColorActionPerformed

    private void chkButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkButtonActionPerformed
        chkDefault.setEnabled(chkButton.isSelected());
        if (chkButton.isSelected()) {
            if (chkDefault.isSelected()) {
                btnColor.setEnabled(false);
            } else {
                btnColor.setEnabled(true);
            }
        } else {
            btnColor.setEnabled(false);
            chkDefault.setEnabled(false);
        }
    }//GEN-LAST:event_chkButtonActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        String terms = txtSearch.getText();

        if (terms.isEmpty()) {
            showAllCategorys();
            return;
        }

        List<Category> newList = new ArrayList<>();

        for (Category c : currentTableContents) {
            if (c.getName().toLowerCase().contains(terms.toLowerCase())) {
                newList.add(c);
            }
        }

        if (newList.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No records found", "Search", JOptionPane.PLAIN_MESSAGE);
        } else {
            currentTableContents = newList;
            if (newList.size() == 1) {
                setCurrentCategory(newList.get(0));
            }
        }
        updateTable();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        btnSearch.doClick();
    }//GEN-LAST:event_txtSearchActionPerformed

    private void chkDefaultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkDefaultActionPerformed
        btnColor.setEnabled(!chkDefault.isSelected());
    }//GEN-LAST:event_chkDefaultActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnColor;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearch;
    private javax.swing.JCheckBox chkButton;
    private javax.swing.JCheckBox chkDefault;
    private javax.swing.JCheckBox chkTime;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTime;
    private javax.swing.JLabel lblTimeDash;
    private javax.swing.JSpinner spinAge;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtEndTime;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtStartTime;
    // End of variables declaration//GEN-END:variables
}
