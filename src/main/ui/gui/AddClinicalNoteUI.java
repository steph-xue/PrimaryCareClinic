package ui.gui;

import javax.swing.*;

import model.Clinic;
import model.ClinicalNote;
import model.Patient;
import model.Date;

import java.awt.*;
import java.time.LocalDate;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// Image References
// JOptionPane Health Logo images/health.jpg retrieved from https://www.freepik.com/premium-vector/basic-healthcare-icon-vector
//-image-can-be-used-home-services_157661598.html

// AddClinicalNoteUI displays the page to add a new clinical note for a specific patient
public class AddClinicalNoteUI extends JPanel {
    private MainUI parent;
    private Clinic clinic;
    private Patient patient;
    private NavigationBarUI navBar;
    private JPanel mainContainerPanel;
    private JPanel contentPanel;
    private JTextField titleField;
    private JTextField providerField;
    private JTextField bodyField;
    private JButton addButton;

    // Constructs an add clinical note UI JPanel with a navigation bar to add a new clinical note to the clinic 
    public AddClinicalNoteUI(MainUI parent, Clinic clinic, Patient patient) {
        this.parent = parent;
        this.clinic = clinic;
        this.patient = patient;

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        navBar = new NavigationBarUI(parent, clinic);
        createMainContainerPanel();
        createContentPanel();
        addFormFields();
        createAddButton();
        contentPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        contentPanel.add(addButton);

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
    // EFFECTS: Create content panel for vertical box layout of form elements
    public void createContentPanel() {
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
    }

    // MODIFIES: this
    // EFFECTS: Add form fields for user input
    public void addFormFields() {
        titleField = createStyledTextField();
        addFormField("Title: ", titleField);
        providerField = createStyledTextField();
        addFormField("Healthcare Provider: ", providerField);
        bodyField = createStyledTextFieldLarge();
        addFormField("Note Details: ", bodyField);
    }

    // EFFECTS: Create styled text input field
    public JTextField createStyledTextField() {
        JTextField textfield = new JTextField(20);
        textfield.setFont(new Font("Arial", Font.PLAIN, 18));
        textfield.setPreferredSize(new Dimension(300, 35));
        return textfield;
    }

    // EFFECTS: Create large styled text input field
    public JTextField createStyledTextFieldLarge() {
        JTextField textfield = new JTextField(20);
        textfield.setFont(new Font("Arial", Font.PLAIN, 18));
        textfield.setPreferredSize(new Dimension(300, 100));
        return textfield;
    }

    // MODIFIES: this
    // EFFECTS: Add a label and text field for each form field
    public void addFormField(String text, JTextField textField) {
        JPanel panel = new JPanel(); 
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 18));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(label);
        panel.add(textField);
        contentPanel.add(panel);
    }

    // MODIFIES: this
    // EFFECTS: Add create button to add a new clinical note for the patient
    public void createAddButton() {
        addButton = new JButton("Create new clinical note");
        styleButton(addButton);
        addButton.addActionListener(e -> createNewClinicalNote());
    }

    // EFFECTS: Create new clinical note object to add to the patient
    public void createNewClinicalNote() {
        String title = titleField.getText().trim();
        String provider = providerField.getText().trim();
        String body = bodyField.getText().trim();

        if (title.isEmpty() || provider.isEmpty() || body.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Fields cannot be empty!", "Error", 
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        String dateString = LocalDate.now().toString();

        String[] parts = dateString.split("-");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);
        Date date = new Date(month, day, year);
        
        addNote(title, provider, body, date);
    }

    // MODIFIES: this
    // EFFECTS: Adds new clinical note for the specific patient with user inputed information and
    // clears all fields once clinical note is successfully added with a success message
    public void addNote(String title, String provider, String body, Date date) {

        title = title.substring(0, 1).toUpperCase() + title.substring(1).toLowerCase();
        provider = provider.substring(0, 1).toUpperCase() + provider.substring(1).toLowerCase();
        body = body.substring(0, 1).toUpperCase() + body.substring(1).toLowerCase();

        ClinicalNote note = new ClinicalNote(title, body, provider, date);
        this.patient.addClinicalNote(note);
        clearFields();

        JOptionPane.showMessageDialog(
                    this,
                    "New clinical note \"" + title + "\" added successfully!",
                    "Success", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/health.jpg"));
        System.out.println(clinic.getPatients());

        parent.getViewPatientsScreen().loadPatients();
        parent.showViewPatientsScreen();
    }

    // MODIFIES: this
    // EFFECTS: Clears all user input fields 
    public void clearFields() {
        titleField.setText("");
        providerField.setText("");
        bodyField.setText("");
    }

    // MODIFIES: button
    // EFFECTS: Add styling to the button
    public void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.setPreferredSize(new Dimension(500, 50));
        button.setMaximumSize(new Dimension(500, 50));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBackground(new Color(70, 10, 70));
        button.setForeground(Color.WHITE);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setFocusable(false);

        addButtonEffects(button);
    }

    // MODIFIES: button
    // EFFECTS: Add hover effects to the button
    public void addButtonEffects(JButton button) {
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
}
