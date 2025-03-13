package ui.gui;

import javax.swing.JPanel;

import model.Clinic;
import model.Patient;

public class ViewPatientProfileUI extends JPanel {
    private MainUI parent;
    private Clinic clinic;
    private JPanel contentPanel;

    // EFFECTS: Constructs a ViewPatientsUI screen displaying all patients in a table format
    public ViewPatientProfileUI(MainUI parent, Clinic clinic, Patient p) {
        this.parent = parent;
        this.clinic = clinic;
    }
    
}
