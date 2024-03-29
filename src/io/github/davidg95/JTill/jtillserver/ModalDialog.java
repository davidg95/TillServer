/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.davidg95.JTill.jtillserver;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.print.PrinterJob;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Class which shows a modal dialog that can be used for showing progress.
 *
 * @author David
 */
public class ModalDialog {

    private JDialog dialog; //The dialog.
    private JPanel panel; //The panel for the components.
    private JLabel label; //The message label.

    private final String title; //The title of the window.
    private String text; //The text on the message label.
    private PrinterJob job; //The printer job (if any) associated with this message.
    private boolean hidden;

    /**
     * Constructor which creates the dialog.
     *
     * @param parent the parent component.
     * @param title the title.
     * @param text the message.
     */
    public ModalDialog(Component parent, String title, String text) {
        this.title = title;
        this.text = text;
        this.hidden = false;
        init();
        dialog.setLocationRelativeTo(parent);
    }

    /**
     * Constructor which created the dialog. This constructor assigns a
     * <code>PrinterJob</code> to the dialog which can be cancelled by clicking
     * the cancel button.
     *
     * @param parent the parent component.
     * @param title the title.
     * @param text the message.
     * @param job the PrinterJob to assign.
     */
    public ModalDialog(Component parent, String title, String text, PrinterJob job) {
        this(parent, title, text);
        this.job = job;
        this.hidden = false;
        JButton button = new JButton("Cancel");
        button.addActionListener((ActionEvent e) -> {
            this.job.cancel();
        });
        panel.add(button);
        dialog.setMinimumSize(new Dimension(100, 100));
    }

    private void init() {
        panel = new JPanel();
        label = new JLabel(text);
        panel.add(label);
        dialog = new JDialog();
        dialog.setLocationRelativeTo(null);
        dialog.setResizable(false);
        dialog.setTitle(title);
        dialog.add(panel);
        dialog.setModal(true);
        dialog.setMinimumSize(new Dimension(100, 70));
    }

    /**
     * Changes the text on the dialog. This method makes a call to
     * <code>SwingUtilities.invokeLater()</code>.
     *
     * @param text the new text.
     */
    public void setText(String text) {
        this.text = text;
        SwingUtilities.invokeLater(() -> {
            label.setText(text);
        });
    }

    /**
     * Method to show the dialog.
     */
    public void show() {
        if (!hidden) {
            dialog.setVisible(true);
        }
    }

    /**
     * Method to hide the dialog.
     */
    public void hide() {
        this.hidden = true;
        dialog.setVisible(false);
    }

}
