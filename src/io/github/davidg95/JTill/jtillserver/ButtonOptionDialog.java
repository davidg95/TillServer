/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.davidg95.JTill.jtillserver;

import io.github.davidg95.JTill.jtill.*;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.MouseInfo;
import java.awt.Window;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * ButtonOptionsDialog for editing a till button.
 *
 * @author David
 */
public final class ButtonOptionDialog extends javax.swing.JDialog {

    private static JDialog dialog;
    private static TillButton button;

    private final DataConnect dc;

    private final int maxWidth;
    private final int maxHeight;

    /**
     * Creates new form ButtonOptionDialog.
     *
     * @param parent the parent component.
     * @param dc the data connection.
     * @param maxWidth the maximum width of the button.
     * @param maxHeight the maximum height of the button.
     */
    public ButtonOptionDialog(Window parent, DataConnect dc, int maxWidth, int maxHeight) {
        super(parent);
        this.dc = dc;
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
        initComponents();
        int x = (int) MouseInfo.getPointerInfo().getLocation().getX();
        int y = (int) MouseInfo.getPointerInfo().getLocation().getY();
        this.setLocation(x - getWidth() / 2, y - getHeight() / 2);
        setModal(true);
        setTitle(button.getName());
        if (button.getName().equals("[SPACE]")) {
            btnRemove.setEnabled(false);
        }
        txtWidth.setText(button.getWidth() + "");
        txtHeight.setText(button.getHeight() + "");
        txtItem.setText(button.getName());
        switch (button.getColorValue()) {
            case TillButton.BLUE:
                radBlue.setSelected(true);
                break;
            case TillButton.RED:
                radRed.setSelected(true);
                break;
            case TillButton.GREEN:
                radGreen.setSelected(true);
                break;
            case TillButton.YELLOW:
                radYellow.setSelected(true);
                break;
            case TillButton.ORANGE:
                radOrange.setSelected(true);
                break;
            case TillButton.PURPLE:
                radPurple.setSelected(true);
                break;
            case TillButton.WHITE:
                radWhite.setSelected(true);
                break;
            case TillButton.BLACK:
                radBlack.setSelected(true);
                break;
            default:
                break;
        }
    }

    /**
     * Shows the ButtonOptionsDialog. Returns null if remove button was
     * selected, otherwise it will return an updated button object.
     *
     * @param parent the parent window.
     * @param b the button object.
     * @param dc the data connection.
     * @param maxWidth the maximum width of the button.
     * @param maxHeight the maximum height of the button.
     * @return the button with any changed applied.
     */
    public static TillButton showDialog(Component parent, TillButton b, DataConnect dc, int maxWidth, int maxHeight) {
        Window window = null;
        if (parent instanceof Dialog || parent instanceof Frame) {
            window = (Window) parent;
        }
        button = b;
        dialog = new ButtonOptionDialog(window, dc, maxWidth, maxHeight);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
        return button;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        colorGroup = new javax.swing.ButtonGroup();
        btnRemove = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();
        btnChangeButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtWidth = new javax.swing.JTextField();
        txtHeight = new javax.swing.JTextField();
        txtItem = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        radBlue = new javax.swing.JRadioButton();
        radRed = new javax.swing.JRadioButton();
        radGreen = new javax.swing.JRadioButton();
        radYellow = new javax.swing.JRadioButton();
        radOrange = new javax.swing.JRadioButton();
        radPurple = new javax.swing.JRadioButton();
        radWhite = new javax.swing.JRadioButton();
        radBlack = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        btnRemove.setText("Remove Button");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        btnClose.setText("Close");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        btnChangeButton.setText("Change Button");
        btnChangeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangeButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Width:");

        jLabel2.setText("Height:");

        txtWidth.setText("1");
        txtWidth.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtWidthKeyReleased(evt);
            }
        });

        txtHeight.setText("1");
        txtHeight.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtHeightKeyReleased(evt);
            }
        });

        jLabel3.setText("Current:");

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Color"));

        colorGroup.add(radBlue);
        radBlue.setSelected(true);
        radBlue.setText("Blue");
        radBlue.setActionCommand("1");

        colorGroup.add(radRed);
        radRed.setText("Red");
        radRed.setActionCommand("2");

        colorGroup.add(radGreen);
        radGreen.setText("Green");
        radGreen.setActionCommand("3");

        colorGroup.add(radYellow);
        radYellow.setText("Yellow");
        radYellow.setActionCommand("4");

        colorGroup.add(radOrange);
        radOrange.setText("Orange");
        radOrange.setActionCommand("5");

        colorGroup.add(radPurple);
        radPurple.setText("Purple");
        radPurple.setActionCommand("6");

        colorGroup.add(radWhite);
        radWhite.setText("White");
        radWhite.setActionCommand("7");

        colorGroup.add(radBlack);
        radBlack.setText("Black");
        radBlack.setActionCommand("8");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(radGreen)
                    .addComponent(radYellow)
                    .addComponent(radRed)
                    .addComponent(radBlue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(radBlack)
                    .addComponent(radWhite)
                    .addComponent(radOrange)
                    .addComponent(radPurple)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radBlue)
                    .addComponent(radBlack))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radRed)
                    .addComponent(radWhite))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radGreen)
                    .addComponent(radPurple))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radYellow))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(radOrange))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtWidth)
                    .addComponent(txtItem)
                    .addComponent(txtHeight, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                    .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnChangeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnRemove)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnChangeButton)
                        .addGap(40, 40, 40)
                        .addComponent(btnClose))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtWidth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtHeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnSave))
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        button.setName("[SPACE]");
        txtItem.setName("[SPACE]");
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void btnChangeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangeButtonActionPerformed
        Item i = ItemSelectDialog.showDialog(this, dc);
        if (i != null) {
            button.setItem(i.getId());
            button.setName(i.getName());
            txtItem.setText(i.getName());
        }
    }//GEN-LAST:event_btnChangeButtonActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        String w = txtWidth.getText();
        String h = txtHeight.getText();
        if(w.length() == 0 || h.length() == 0){
            JOptionPane.showMessageDialog(this, "Must enter a value for width and height", "Button Options", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(!Utilities.isNumber(w) || !Utilities.isNumber(h)){
            JOptionPane.showMessageDialog(this, "A number must be entered", "Button Options", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int width = Integer.parseInt(w);
        int height = Integer.parseInt(h);
        if(width <= 0 || height <= 0){
            JOptionPane.showMessageDialog(this, "Width and height must be greater than zero", "Button Options", JOptionPane.ERROR_MESSAGE);
            return;
        }
        button.setWidth(width);
        button.setHeight(height);
        int col = 0;
        if (radBlue.isSelected()) {
            col = TillButton.BLUE;
        } else if (radRed.isSelected()) {
            col = TillButton.RED;
        } else if (radGreen.isSelected()) {
            col = TillButton.GREEN;
        } else if (radYellow.isSelected()) {
            col = TillButton.YELLOW;
        } else if (radOrange.isSelected()) {
            col = TillButton.ORANGE;
        } else if (radPurple.isSelected()) {
            col = TillButton.PURPLE;
        } else if (radWhite.isSelected()) {
            col = TillButton.WHITE;
        } else if (radBlack.isSelected()) {
            col = TillButton.BLACK;
        }
        button.setColorValue(col);
        JOptionPane.showMessageDialog(this, "Size and color saved", "Button Options", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnSaveActionPerformed

    private void txtWidthKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtWidthKeyReleased
        if (Utilities.isNumber(txtWidth.getText())) {
            int val = Integer.parseInt(txtWidth.getText());
            if (val < 1 || val > maxWidth) {
                JOptionPane.showMessageDialog(this, "This must be greater than 0 and less than " + maxWidth + ", which is the maximum width for this item", "Button", JOptionPane.ERROR_MESSAGE);
                txtWidth.setSelectionStart(0);
                txtWidth.setSelectionEnd(txtWidth.getText().length());
            }
        } else {
            JOptionPane.showMessageDialog(this, "You must enter a number", "Button", JOptionPane.ERROR_MESSAGE);
            txtWidth.setSelectionStart(0);
            txtWidth.setSelectionEnd(txtWidth.getText().length());
        }
    }//GEN-LAST:event_txtWidthKeyReleased

    private void txtHeightKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHeightKeyReleased
        if (Utilities.isNumber(txtHeight.getText())) {
            int val = Integer.parseInt(txtHeight.getText());
            if (val < 1 || val > maxHeight) {
                JOptionPane.showMessageDialog(this, "This must be greater than 0 and less than " + maxHeight + ", which is the maximum width for this item", "Button", JOptionPane.ERROR_MESSAGE);
                txtHeight.setSelectionStart(0);
                txtHeight.setSelectionEnd(txtHeight.getText().length());
            }
        } else {
            JOptionPane.showMessageDialog(this, "You must enter a number", "Button", JOptionPane.ERROR_MESSAGE);
            txtHeight.setSelectionStart(0);
            txtHeight.setSelectionEnd(txtHeight.getText().length());
        }
    }//GEN-LAST:event_txtHeightKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChangeButton;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnSave;
    private javax.swing.ButtonGroup colorGroup;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton radBlack;
    private javax.swing.JRadioButton radBlue;
    private javax.swing.JRadioButton radGreen;
    private javax.swing.JRadioButton radOrange;
    private javax.swing.JRadioButton radPurple;
    private javax.swing.JRadioButton radRed;
    private javax.swing.JRadioButton radWhite;
    private javax.swing.JRadioButton radYellow;
    private javax.swing.JTextField txtHeight;
    private javax.swing.JTextField txtItem;
    private javax.swing.JTextField txtWidth;
    // End of variables declaration//GEN-END:variables
}
