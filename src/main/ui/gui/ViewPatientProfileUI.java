package ui.gui;

import javax.swing.*;

import model.Clinic;
import model.Patient;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// Image References
// Patient image images/patient.jpg retrieved from https://www.istockphoto.com/vector/patient-icon-vector-of-
// male-person-profile-avatar-symbol-for-medical-treatment-in-gm1147248235-309382448

// ViewPatientProfileUI displays details of a specific patient profile
public class ViewPatientProfileUI extends JPanel {
    private MainUI parent;
    private Clinic clinic;
    private Patient patient;
    private NavigationBarUI navBar;
    private JPanel mainContainerPanel;
    private JPanel contentPanel;
    private JLabel patientImageLabel;

    // EFFECTS: Constructs a view patient profile UI JPanel displaying details of a specific patient
    public ViewPatientProfileUI(MainUI parent, Clinic clinic, Patient patient) {
        this.parent = parent;
        this.clinic = clinic;
        this.patient = patient;

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        navBar = new NavigationBarUI(parent, clinic);

        createMainContainerPanel();
        createContentPanel();
        addPatientImage();
        addPatientInfo();
        addClinicalNotes();

        mainContainerPanel.add(contentPanel);
        add(navBar, BorderLayout.NORTH);
        add(mainContainerPanel, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: Create main container panel for centered layout
    public void createMainContainerPanel() {
        mainContainerPanel = new JPanel();
        mainContainerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        mainContainerPanel.setBackground(Color.WHITE);
    }

    // MODIFIES: this
    // EFFECTS: Create content panel for vertical box layout of patient data
    public void createContentPanel() {
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
    }

    // MODIFIES: this
    // EFFECTS: Create and add image for patient profile
    public void addPatientImage() {
        ImageIcon patientImage = new ImageIcon("images/patient.jpg");
        patientImage = new ImageIcon(patientImage.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));  

        patientImageLabel = new JLabel();
        patientImageLabel.setIcon(patientImage);

        JPanel imagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        imagePanel.setBackground(Color.WHITE);
        imagePanel.add(patientImageLabel);

        contentPanel.add(imagePanel);
    }

    // EFFECTS: Create and add labels for patient data to the content panel
    public void addPatientInfo() {
        JLabel firstNameLabel = createLabel("First Name: " + patient.getFirstName());
        JLabel lastNameLabel = createLabel("Last Name: " + patient.getLastName());
        JLabel dateOfBirthLabel = createLabel("Date of Birth (MM-DD-YYYY): " + patient.getDateOfBirth().printDate());
        JLabel ageLabel = createLabel("Age: " + patient.getAge());
        JLabel phnLabel = createLabel("Personal Health Number (PHN): " + patient.getPersonalHealthNumber());
        JLabel allergiesLabel = createLabel("Allergies: " + patient.printAllergies());
        JLabel medicationsLabel = createLabel("Medications: " + patient.printMedications());
        JLabel medicalConditionsLabel = createLabel("Medical Conditions: " + patient.printMedicalConditions());

        contentPanel.add(firstNameLabel);
        contentPanel.add(lastNameLabel);
        contentPanel.add(dateOfBirthLabel);
        contentPanel.add(ageLabel);
        contentPanel.add(phnLabel);
        contentPanel.add(allergiesLabel);
        contentPanel.add(medicationsLabel);
        contentPanel.add(medicalConditionsLabel);
    }

    // EFFECTS: Create and style a label for patient data
    public JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        return label;
    }

    public void addClinicalNotes() {
    }
    
}
