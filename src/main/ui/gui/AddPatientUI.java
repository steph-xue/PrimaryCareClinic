package ui.gui;

import javax.swing.*;

import model.Clinic;
import model.Patient;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// Image References
// JOptionPane Health Logo images/health.jpg retrieved from https://www.freepik.com/premium-vector/basic-healthcare-icon-vector
//-image-can-be-used-home-services_157661598.html

// AddPatientUI displays the page to add a new patient to the clinic
public class AddPatientUI extends JPanel {
    private MainUI parent;
    private Clinic clinic;
    private JPanel contentPanel;
    private JPanel mainContainerPanel;
    private NavigationBarUI navBar;
    private JLabel currentAllergiesLabel;
    private JLabel currentMedicationsLabel;
    private JLabel currentMedicalConditionsLabel;
    private List<String> currentAllergiesList;
    private List<String> currentMedicationsList;
    private List<String> currentMedicalConditionsList;
    private JButton addButton;

    // Constructs an add patient UI JPanel to add a new patient to the clinic with a navigation bar
    public AddPatientUI(MainUI parent, Clinic clinic) {
        this.parent = parent;
        this.clinic = clinic;
        currentAllergiesList = new ArrayList<>();
        currentMedicationsList = new ArrayList<>();
        currentMedicalConditionsList = new ArrayList<>();

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        navBar = new NavigationBarUI(parent, clinic);
        createMainContainerPanel();
        createContentPanel();
        addFormFields();
        addListFields();
        createAddButton();
        contentPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        contentPanel.add(addButton);

        mainContainerPanel.add(contentPanel);
        add(navBar, BorderLayout.NORTH);
        add(mainContainerPanel, BorderLayout.CENTER);
    }

    // EFFECTS: Create main container panel for centered layout
    public void createMainContainerPanel() {
        mainContainerPanel = new JPanel();
        mainContainerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        mainContainerPanel.setBackground(Color.WHITE);
    }

    // EFFECTS: Create content panel for vertical box layout of form elements
    public void createContentPanel() {
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
    }

    // EFFECTS: Add form fields for user input
    public void addFormFields() {
        JTextField firstNameField = createStyledTextField();
        addFormField("First Name: ", firstNameField);
        JTextField lastNameField = createStyledTextField();
        addFormField("Last Name: ", lastNameField);
        JTextField dateOfBirthField = createStyledTextField();
        addFormField("Date of Birth (MM-DD-YYYY): ", dateOfBirthField);
        JTextField ageField = createStyledTextField();
        addFormField("Age: ", ageField);
        JTextField phnField = createStyledTextField();
        addFormField("Personal Health Number (PHN): ", phnField);
    }

    // EFFECTS: Create styled text input field
    public JTextField createStyledTextField() {
        JTextField textfield = new JTextField(20);
        textfield.setFont(new Font("Arial", Font.PLAIN, 18));
        textfield.setPreferredSize(new Dimension(300, 35));
        return textfield;
    }

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

    // EFFECTS: Add list fields for user input 
    public void addListFields() {
        currentAllergiesLabel = createStyledLabel();
        addListField("Allergies: ", currentAllergiesLabel, currentAllergiesList);

        currentMedicationsLabel = createStyledLabel();
        addListField("Medications: ", currentMedicationsLabel, currentMedicationsList);

        currentMedicalConditionsLabel = createStyledLabel();
        addListField("Medical conditions: ", currentMedicalConditionsLabel, currentMedicalConditionsList);
    }

    // EFFECTS: Create styled label for displaying list items
    public JLabel createStyledLabel() {
        JLabel label = new JLabel("None");
        label.setFont(new Font("Arial", Font.PLAIN, 18));
        return label;
    }

    // EFFECTS: Add the title, current list text, and button to add more of the item to list in each list field
    public void addListField(String title, JLabel currentTextLabel, List<String> currentList) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        currentTextLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton addButton = new JButton("Add another");
        addButton.setPreferredSize(new Dimension(120, 35));
        addButton.addActionListener(e -> addToList(currentList, currentTextLabel));

        panel.add(titleLabel);
        panel.add(currentTextLabel);
        panel.add(addButton);
        contentPanel.add(panel);
    }

    // EFFECTS: Adds user input to list and updates the text label list
    public void addToList(List<String> currentList, JLabel currentTextLabel) {
        String input = (String) JOptionPane.showInputDialog(
                this, 
                "Add new: ",
                "Add",
                JOptionPane.DEFAULT_OPTION, 
                new ImageIcon("images/health.jpg"), null, null);
        
        if (input != null && !input.trim().isEmpty()) {
            String formattedInput = Character.toUpperCase(input.charAt(0)) + input.substring(1).toLowerCase();
            currentList.add(formattedInput);
            currentTextLabel.setText(String.join(", ", currentList));
        }
    }

    // EFFECTS: Add create button to add a new patient to the clinic
    public void createAddButton() {
        addButton = new JButton("Add new patient");
        styleButton(addButton);
        addButton.addActionListener(e -> parent.addNewPatient());
    }

    // EFFECTS: Add styling to the buttons
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

    // EFFECTS: Add hover effects to button
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
