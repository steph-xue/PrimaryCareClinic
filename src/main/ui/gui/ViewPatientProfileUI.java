package ui.gui;

import javax.swing.JPanel;

import model.Clinic;
import model.Patient;

// ViewPatientProfileUI displays details of a specific patient profile
public class ViewPatientProfileUI extends JPanel {
    private MainUI parent;
    private Clinic clinic;
    private JPanel contentPanel;

    // EFFECTS: Constructs a view patient profile UI JPanel displaying details of a specific patient
    public ViewPatientProfileUI(MainUI parent, Clinic clinic, Patient p) {
        this.parent = parent;
        this.clinic = clinic;
    }
    
}
