package ui.gui;

import javax.swing.*;

import model.Clinic;
import model.ClinicalNote;
import model.Date;
import model.Patient;

import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.*;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.time.LocalDate;

// Image References
// Health image images/health.jpg retrieved from https://www.freepik.com/premium-vector/basic-healthcare-icon-vector
//-image-can-be-used-home-services_157661598.htm

// Main application UI displays contents of the primary care clinic application
public class MainUI extends JFrame {
    private static final String JSON_STORE = "./data/clinic.json";

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private Clinic clinic;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private LoadingScreenUI loadingScreen;
    private StartScreenUI startScreen;
    private JPanel mainContent;

    // EFFECTS: Constructs the main application UI JFrame
    public MainUI() {
        init();
        setupLayout();
        startApp();
    }

    // EFFECTS: Initializes the primary care clinic application UI with default settings
    public void init() {
        setTitle("Primary Care Clinic Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // EFFECTS: Sets up the main panel, card layout, loading screen, start screen, main content panel
    public void setupLayout() {
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        loadingScreen = new LoadingScreenUI();
        startScreen = new StartScreenUI(this);
        mainContent = createMainContent(); 
        mainPanel.add(loadingScreen, "loading");
        mainPanel.add(startScreen, "start");
        mainPanel.add(mainContent, "main");

        add(mainPanel);
        setVisible(true);
    }
    
    // Start the application and show the loading screen
    public void startApp() {
        showLoadingScreen();
    }

    // EFFECTS: Displays LoadingScreenUI for 10 seconds then switches to display start screen
    public void showLoadingScreen() {
        cardLayout.show(mainPanel, "loading");

        Timer timer = new Timer(10000, e -> showStartScreen());
        timer.setRepeats(false);
        timer.start();
    }

    // EFFECTS: Displays StartScreenUI so user can load from file or create a new clinic
    public void showStartScreen() {
        cardLayout.show(mainPanel, "start");
    }

    // MODIFIES: this
    // EFFECTS: Loads previously saved clinic data to the application; prints out a success message if successful
    // or error message if unable to read from file
    public void loadClinicData() {
        try {
            clinic = jsonReader.read();
            JOptionPane.showMessageDialog(
                    this,
                    "Clinic \"" + clinic.getClinicName() + "\" loaded successfully!",
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE,
                    new ImageIcon("images/health.jpg"));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Unable to read from file!",
                    "Error", 
                    JOptionPane.ERROR_MESSAGE,
                    new ImageIcon("images/health.jpg"));
        }
    }

    // MODIFIES: this
    // EFFECTS: Create a new clinic and allows user to input a new clinic name; prints out a success message
    // if clinic is successfully named or prints an error message if clinic name is empty 
    public void createNewClinic() {
        String clinicName = (String) JOptionPane.showInputDialog(
                this, 
                "Enter new clinic name: ",
                "New Clinic",
                JOptionPane.DEFAULT_OPTION, 
                new ImageIcon("images/health.jpg"), null, null);

        if (clinicName != null && !clinicName.trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "New clinic \"" + clinicName + "\" created successfully!",
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE,
                    new ImageIcon("images/health.jpg"));
            clinic.setClinicName(clinicName);
        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "Clinic name cannot be empty!",
                    "Error", 
                    JOptionPane.ERROR_MESSAGE,
                    new ImageIcon("images/health.jpg"));
        }
    }

    // EFFECTS: Creates the main content panel after the loading screen
    private JPanel createMainContent() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel mainLabel = new JLabel("Main Application Home Screen", SwingConstants.CENTER);
        mainLabel.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(mainLabel, BorderLayout.CENTER);

        return panel;
    }

    // EFFECTS: Shows the main content
    public void showMainContent() {
        cardLayout.show(mainPanel, "main");
    }
    
    // EFFECTS: Runs the main application application 
    public static void main(String[] args) {
        new MainUI();
    }
}
