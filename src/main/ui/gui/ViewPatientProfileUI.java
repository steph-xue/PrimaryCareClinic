package ui.gui;

import javax.swing.*;

import model.Clinic;
import model.Patient;
import model.ClinicalNote;
import model.Date;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Collections;

// Image References
// Patient image images/patient.jpg retrieved from https://www.istockphoto.com/vector/patient-icon-vector-of-
// male-person-profile-avatar-symbol-for-medical-treatment-in-gm1147248235-309382448
// Edit image images/edit.png retrieved from https://www.veryicon.com/icons/miscellaneous/linear-small-icon/
// edit-246.html

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
        JPanel firstNamePanel = createPanel("First Name: ", patient.getFirstName(), "firstName");
        JPanel lastNamePanel = createPanel("Last Name: ", patient.getLastName(), "lastName");
        JPanel dateOfBirthPanel = createPanel("Date of Birth (MM-DD-YYYY): ", patient.getDateOfBirth().printDate(),
                "dob");
        JPanel agePanel = createPanel("Age: ", String.valueOf(patient.getAge()), "age");
        JPanel phnPanel = createPanel("Personal Health Number (PHN): ", 
                String.valueOf(patient.getPersonalHealthNumber()), "phn");
        JPanel allergiesPanel = createListPanel("Allergies: ", patient.printAllergies(), "allergies");
        JPanel medicationsPanel = createListPanel("Medications: ", patient.printMedications(), "medications");
        JPanel medicalConditionsPanel = createListPanel("Medical Conditions: ", patient.printMedicalConditions(), 
                "medicalConditions");

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
    public JPanel createPanel(String header, String data, String fieldName) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBackground(Color.WHITE);

        JLabel headerLabel = new JLabel(header);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel dataLabel = new JLabel(data);
        dataLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        JButton editButton = createEditButton(header, fieldName, dataLabel);

        panel.add(headerLabel);
        panel.add(dataLabel);
        panel.add(editButton);
        panel.setBorder(BorderFactory.createEmptyBorder(0, 250, 0, 0));
        return panel;
    }

    // EFFECTS: Create and style editing button for each of the user fields that allows the user
    // to edit the selected field
    public JButton createEditButton(String header, String fieldName, JLabel dataLabel) {
        JButton editButton = new JButton();
        ImageIcon editIcon = new ImageIcon("images/edit.png");
        Image scaledImage = editIcon.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        editButton.setIcon(new ImageIcon(scaledImage));
        editButton.setPreferredSize(new Dimension(15, 15));
        editButton.setBorderPainted(false);
        editButton.setContentAreaFilled(false);
        editButton.setFocusPainted(false);

        addEditActionListener(editButton, header, fieldName, dataLabel);

        return editButton;
    }

    // EFFECTS: Add action listener to edit button to allow the user to edit the selected field
    public void addEditActionListener(JButton editButton, String header, String fieldName, JLabel dataLabel) {
        editButton.addActionListener(e -> {
            String newValue = (String) JOptionPane.showInputDialog(
                    this, 
                    "Enter new " + header.substring(0, header.length() - 2) + ": ",
                    "Edit",
                    JOptionPane.DEFAULT_OPTION, 
                    new ImageIcon("images/health.jpg"), null, null
            );

            if (newValue != null && !newValue.trim().isEmpty()) {
                updatePatientField(fieldName, newValue.trim(), dataLabel);
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: update patient information with user inputed edits based on the field selected
    public void updatePatientField(String key, String value,  JLabel dataLabel) {
        switch (key) {
            case "firstName":
                patient.setFirstName(value);
                dataLabel.setText(patient.getFirstName());
                break;
            case "lastName":
                patient.setLastName(value);
                dataLabel.setText(patient.getLastName());
                break;
            case "dob":
                parseAndSetDateOfBirth(value);
                dataLabel.setText(patient.getDateOfBirth().printDate());
                break;
            case "age":
                parseAndSetAge(value);
                dataLabel.setText(String.valueOf(patient.getAge()));
                break;
            case "phn":
                parseAndSetPersonalHealthNumber(value);
                dataLabel.setText(String.valueOf(patient.getPersonalHealthNumber()));
                break;
        }   
    }

    // MODIFIES: this
    // EFFECTS: Parses date of birth in (MM-DD-YYYY) string format to date object to set for the selected patient;
    // shows error message if string date is not in the right format
    public void parseAndSetDateOfBirth(String dateOfBirth) {
        try {
            String[] parts = dateOfBirth.split("-");
            if (parts.length != 3) {
                JOptionPane.showMessageDialog(this, "Invalid date format!", "Error", 
                            JOptionPane.ERROR_MESSAGE);
                return;
            }

            int month = Integer.parseInt(parts[0]);
            int day = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);
            Date date = new Date(month, day, year);

            patient.setDateOfBirth(date);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid date format!", "Error", 
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    // MODIFIES: this
    // EFFECTS: Parses age from string to integer to set for the selected patient; shows error message 
    // if age is not in the right format
    public void parseAndSetAge(String age) {
        int parsedAge;

        try {
            parsedAge = Integer.parseInt(age);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid age format!", "Error", 
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        patient.setAge(parsedAge);
    }

    // MODIFIES: this
    // EFFECTS: Parses phn from string to long to set for the selected patient; shows error message
    // if phn is not in the right format or if it is not 9-digits long
    public void parseAndSetPersonalHealthNumber(String phn) {
        long parsedPersonalHealthNumber;

        try {
            if (phn.length() != 9) {
                JOptionPane.showMessageDialog(this, "Personal health number (PHN) must be 9-digits!", "Error", 
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            parsedPersonalHealthNumber = Long.parseLong(phn);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid personal health number (PHN) format!", "Error", 
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        patient.setPersonalHealthNumber(parsedPersonalHealthNumber);
    }

    // EFFECTS: Create and style a panel for patient list data
    public JPanel createListPanel(String header, String data, String fieldName) {
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
            List<ClinicalNote> notes = patient.getClinicalNotes();
            Collections.reverse(notes);
            for (ClinicalNote note: notes) {
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
    // with editing buttons (title, provider, body) and a delete clinical note button
    public JPanel createClinicalNoteBox(ClinicalNote note) {
        JPanel notePanel = createNotePanel();

        JPanel titlePanel = createNoteTitlePanel(note);
        JPanel datePanel = createNoteDatePanel(note);
        JPanel providerPanel = createNoteProviderPanel(note);
        JPanel bodyPanel = createNoteBodyPanel(note);

        notePanel.add(titlePanel);
        notePanel.add(datePanel);
        notePanel.add(providerPanel);
        notePanel.add(bodyPanel);
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

    // EFFECTS: Creates a clinical note title panel with styling and an editing button 
    public JPanel createNoteTitlePanel(ClinicalNote note) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel(note.getClinicalNoteTitle());
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton editButton = createEditNoteButton(note, "title", titleLabel);

        panel.add(titleLabel);
        panel.add(editButton);
        return panel;
    }

    // EFFECTS: Creates a clinical note date panel with styling and an editing button 
    public JPanel createNoteDatePanel(ClinicalNote note) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.setBackground(Color.WHITE);

        JLabel dateLabel = new JLabel(note.getClinicalNoteDate().printDate());
        dateLabel.setFont(new Font("Arial", Font.ITALIC, 18));
        dateLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(dateLabel);
        return panel;
    }

    // EFFECTS: Creates a clinical note provider panel with styling and an editing button 
    public JPanel createNoteProviderPanel(ClinicalNote note) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.setBackground(Color.WHITE);

        JLabel providerLabel = new JLabel(note.getClinicalNoteProvider());
        providerLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        providerLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton editButton = createEditNoteButton(note, "provider", providerLabel);

        panel.add(providerLabel);
        panel.add(editButton);
        return panel;
    }

    // EFFECTS: Creates a clinical note body panel with styling and an editing button 
    public JPanel createNoteBodyPanel(ClinicalNote note) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE);

        JTextArea bodyTextArea = createTextAreaBody(note);
        JButton editButton = createEditNoteButton(note, "body", bodyTextArea);

        panel.add(bodyTextArea, BorderLayout.CENTER);
        panel.add(editButton, BorderLayout.EAST);
        return panel;
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

    // EFFECTS: Create and style editing button for each of the clinical note fields that allows the user
    // to edit the selected field
    public JButton createEditNoteButton(ClinicalNote note, String fieldName, JComponent dataComponent) {
        JButton editButton = new JButton();
        ImageIcon editIcon = new ImageIcon("images/edit.png");
        Image scaledImage = editIcon.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        editButton.setIcon(new ImageIcon(scaledImage));
        editButton.setPreferredSize(new Dimension(15, 15));
        editButton.setBorderPainted(false);
        editButton.setContentAreaFilled(false);
        editButton.setFocusPainted(false);

        addEditNoteActionListener(note, editButton, fieldName, dataComponent);

        return editButton;
    }

    // EFFECTS: Add action listener to edit button to allow the user to edit the selected field
    public void addEditNoteActionListener(ClinicalNote note, JButton editButton, String fieldName, 
            JComponent dataComponent) {
        editButton.addActionListener(e -> {
            String newValue = (String) JOptionPane.showInputDialog(
                    this, 
                    "Enter new " + fieldName + ": ",
                    "Edit",
                    JOptionPane.DEFAULT_OPTION, 
                    new ImageIcon("images/health.jpg"), null, null
            );

            if (newValue != null && !newValue.trim().isEmpty()) {
                updateNoteField(note, fieldName, newValue.trim(), dataComponent);
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: update patient information with user inputed edits based on the field selected
    public void updateNoteField(ClinicalNote note, String key, String value, JComponent dataComponent) {
        switch (key) {
            case "title":
                note.setClinicalNoteTitle(value);
                ((JLabel) dataComponent).setText(note.getClinicalNoteTitle());
                break;
            case "provider":
                note.setClinicalNoteProvider(value);
                ((JLabel) dataComponent).setText(note.getClinicalNoteProvider());
                break;
            case "body":
                note.setClinicalNoteBody(value);
                ((JTextArea) dataComponent).setText(note.getClinicalNoteBody());
                break;
        }   
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
