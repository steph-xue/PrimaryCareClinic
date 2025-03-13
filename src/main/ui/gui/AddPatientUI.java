package ui.gui;

import javax.swing.*;
import javax.swing.JPanel;

import model.Clinic;
import model.Patient;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddPatientUI extends JPanel {
    private MainUI parent;
    private Clinic clinic;
    private JPanel contentPanel;

    public AddPatientUI(MainUI parent, Clinic clinic) {
        this.parent = parent;
        this.clinic = clinic;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        contentPanel = new JPanel();
        contentPanel.setBackground(Color.WHITE);
    }
    
}
