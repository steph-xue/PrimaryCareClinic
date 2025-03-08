package ui.gui;

import javax.swing.*;
import java.awt.*;

// Main application UI displays contents of the primary care clinic application
public class MainUI extends JFrame {
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
        setSize(1100, 700);
        setLocationRelativeTo(null);
    }

    // EFFECTS: Sets up the main panel, card layout, loading screen, start screen, main content panel
    public void setupLayout() {
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        loadingScreen = new LoadingScreenUI();
        startScreen = new StartScreenUI();
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

        Timer timer = new Timer(1000, e -> showStartScreen());
        timer.setRepeats(false);
        timer.start();
    }

    // EFFECTS: Displays StartScreenUI so user can load from file or create a new clinic
    public void showStartScreen() {
        cardLayout.show(mainPanel, "start");
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
