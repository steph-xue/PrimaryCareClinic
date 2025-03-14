package ui.gui;

import javax.swing.*;

import model.Clinic;
import model.Patient;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.Flow;

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
        contentPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 40));
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
        imagePanel.setBorder(BorderFactory.createEmptyBorder(0, 35, 0, 0));
        imagePanel.add(patientImageLabel);

        contentPanel.add(imagePanel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    }

    // MODIFIES: this
    // EFFECTS: Create and add panels for patient data to add to the content panel
    public void addPatientInfo() {
        JPanel firstNamePanel = createPanel("First Name: " , patient.getFirstName());
        JPanel lastNamePanel = createPanel("Last Name: ", patient.getLastName());
        JPanel dateOfBirthPanel = createPanel("Date of Birth (MM-DD-YYYY): ", patient.getDateOfBirth().printDate());
        JPanel agePanel = createPanel("Age: ", String.valueOf(patient.getAge()));
        JPanel phnPanel = createPanel("Personal Health Number (PHN): ", String.valueOf(patient.getPersonalHealthNumber()));
        JPanel allergiesPanel = createPanel("Allergies: ", patient.printAllergies());
        JPanel medicationsPanel = createPanel("Medications: ", patient.printMedications());
        JPanel medicalConditionsPanel = createPanel("Medical Conditions: ", patient.printMedicalConditions());

        contentPanel.add(firstNamePanel);
        contentPanel.add(lastNamePanel);
        contentPanel.add(dateOfBirthPanel);
        contentPanel.add(agePanel);
        contentPanel.add(phnPanel);
        contentPanel.add(allergiesPanel);
        contentPanel.add(medicationsPanel);
        contentPanel.add(medicalConditionsPanel);
    }

    // EFFECTS: Create and style a panel for patient data
    public JPanel createPanel(String header, String data) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBackground(Color.WHITE);

        JLabel headerLabel = new JLabel(header);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel dataLabel = new JLabel(data);
        dataLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        panel.add(headerLabel);
        panel.add(dataLabel);
        panel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        return panel;
    }

    public void addClinicalNotes() {
    }
    
}
