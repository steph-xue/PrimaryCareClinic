package ui.gui;

import javax.swing.*;

import model.Clinic;
import model.Patient;
import model.ClinicalNote;

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
    private JPanel buttonPanel;
    private JScrollPane scrollPane;
    private JButton newNoteButton;
    private JButton deletePatientButton;

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
        addButtonPanel();
        addScrollBar();
        
        add(navBar, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        SwingUtilities.invokeLater(() -> scrollPane.getViewport().setViewPosition(new Point(0, 0)));
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
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        mainContainerPanel.add(contentPanel);
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
        imagePanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        imagePanel.add(patientImageLabel);

        contentPanel.add(imagePanel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    }

    // MODIFIES: this
    // EFFECTS: Create and add panels for patient data to add to the contentPanel
    public void addPatientInfo() {
        JPanel firstNamePanel = createPanel("First Name: ", patient.getFirstName());
        JPanel lastNamePanel = createPanel("Last Name: ", patient.getLastName());
        JPanel dateOfBirthPanel = createPanel("Date of Birth (MM-DD-YYYY): ", patient.getDateOfBirth().printDate());
        JPanel agePanel = createPanel("Age: ", String.valueOf(patient.getAge()));
        JPanel phnPanel = createPanel("Personal Health Number (PHN): ", 
                String.valueOf(patient.getPersonalHealthNumber()));
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
        panel.setBorder(BorderFactory.createEmptyBorder(0, 250, 0, 0));
        return panel;
    }

    // MODIFIES: this
    // EFFECTS: Adds clinical notes in individual boxes to contentPanel or displays no notes if
    // patient does not have any clinical notes
    public void addClinicalNotes() {
        addClinicalNoteTitle();

        if (patient.getClinicalNotes().isEmpty() || patient.getClinicalNotes() == null) {
            JLabel noNotesLabel = createNoNotesLabel();
            contentPanel.add(noNotesLabel);
        } else {
            for (ClinicalNote note: patient.getClinicalNotes()) {
                contentPanel.add(createClinicalNoteBox(note));
                contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Adds clinical note title to contentPanel
    public void addClinicalNoteTitle() {
        contentPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        JLabel notesTitle = new JLabel("Clinical Notes");
        notesTitle.setFont(new Font("Arial", Font.BOLD, 25));
        notesTitle.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        notesTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(notesTitle);
    }

    // EFFECTS: Creates a no clinical notes label to add to contentPanel
    public JLabel createNoNotesLabel() {
        JLabel noNotesLabel = new JLabel("No clinical notes avaliable");
        noNotesLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        noNotesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        noNotesLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 60, 10));
        return noNotesLabel;
    }

    // MODIFIES: this
    // EFFECTS: Creates a clinical note box with all note data to add to contentPanel
    public JPanel createClinicalNoteBox(ClinicalNote note) {
        JPanel notePanel = createNotePanel();

        JLabel titleLabel = new JLabel(note.getClinicalNoteTitle());
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel dateLabel = new JLabel(note.getClinicalNoteDate().printDate());
        dateLabel.setFont(new Font("Arial", Font.ITALIC, 18));
        dateLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel providerLabel = new JLabel(note.getClinicalNoteProvider());
        providerLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        providerLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextArea bodyTextArea = createTextAreaBody(note);

        notePanel.add(titleLabel);
        notePanel.add(dateLabel);
        notePanel.add(providerLabel);
        notePanel.add(bodyTextArea);
        return notePanel;
    }

    // EFFECTS: Creates a clinical note panel with styling for the box
    public JPanel createNotePanel() {
        JPanel notePanel = new JPanel();
        notePanel.setLayout(new BoxLayout(notePanel, BoxLayout.Y_AXIS));
        notePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        notePanel.setBackground(Color.WHITE);
        notePanel.setPreferredSize(new Dimension(1000, 200));
        notePanel.setMinimumSize(new Dimension(1000, 200));
        notePanel.setMaximumSize(new Dimension(1000, 1000));
        notePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return notePanel;
    }

    // EFFECTS: Creates a JTextArea for the clinical note body
    public JTextArea createTextAreaBody(ClinicalNote note) {
        JTextArea bodyTextArea = new JTextArea(note.getClinicalNoteBody());
        bodyTextArea.setFont(new Font("Arial", Font.PLAIN, 18));
        bodyTextArea.setWrapStyleWord(true);
        bodyTextArea.setLineWrap(true);
        bodyTextArea.setEditable(false);
        bodyTextArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        return bodyTextArea;
    }

    // MODIFIES: this
    // EFFECTS: Creates a button panel containing the delete patient and new clinical note buttons
    private void addButtonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.WHITE);

        newNoteButton = new JButton("New Clinical Note");
        styleButtonNewNote(newNoteButton);
        newNoteButton.addActionListener(e -> parent.addClinicalNote(patient));

        deletePatientButton = new JButton("Remove Patient");
        styleButtonDelete(deletePatientButton);
        deletePatientButton.addActionListener(e -> removePatient());

        buttonPanel.add(newNoteButton);
        buttonPanel.add(deletePatientButton);
        contentPanel.add(buttonPanel);
    }

    // MODIFIES: this
    // EFFECTS: Creates and styles a scrolling pane for the user profile
    public void addScrollBar() {
        scrollPane = new JScrollPane(mainContainerPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
    }

    // MODIFIES: this
    // EFFECTS: Removes patient from the clinic and redirects user to the view patients screen
    public void removePatient() {
        clinic.removePatient(patient);
        parent.getViewPatientsScreen().loadPatients();
        parent.showViewPatientsScreen();
    }

    // MODIFIES: button
    // EFFECTS: Add styling to the new clinical note button
    public void styleButtonNewNote(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.setPreferredSize(new Dimension(500, 50));
        button.setMaximumSize(new Dimension(500, 50));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBackground(new Color(70, 10, 70));
        button.setForeground(Color.WHITE);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setFocusable(false);

        addButtonEffectsAdd(button);
    }

    // MODIFIES: button
    // EFFECTS: Add styling to the delete patient button
    public void styleButtonDelete(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.setPreferredSize(new Dimension(500, 50));
        button.setMaximumSize(new Dimension(500, 50));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBackground(new Color(170, 50, 60));
        button.setForeground(Color.WHITE);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setFocusable(false);

        addButtonEffectsDelete(button);
    }

    // MODIFIES: button
    // EFFECTS: Add hover effects to the button
    public void addButtonEffectsAdd(JButton button) {
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(90, 40, 90));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(70, 10, 70));
            }
        });
    }

    // MODIFIES: button
    // EFFECTS: Add hover effects to the button
    public void addButtonEffectsDelete(JButton button) {
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(170, 70, 90));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(170, 50, 60));
            }
        });
    }
    
}
