package ui.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import model.Clinic;
import model.Patient;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// ViewPatientsUI displays the homepage with a list of all patients in the clinic
public class ViewPatientsUI extends JPanel {
    private MainUI parent;
    private Clinic clinic;
    private JPanel contentPanel;
    private JTable patientTable;
    private DefaultTableModel tableModel;
    private NavigationBarUI navBar;

    // EFFECTS: Constructs a ViewPatientsUI screen displaying all patients in a table with a navigation bar
    public ViewPatientsUI(MainUI parent, Clinic clinic) {
        this.parent = parent;
        this.clinic = clinic;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);
        navBar = new NavigationBarUI(parent, clinic);
        contentPanel.add(navBar, BorderLayout.NORTH);
        createTable();
        add(contentPanel, BorderLayout.CENTER);

        adjustTableHeader();
        setColumnWidths();
        addDoubleClickListener();
        loadPatients();
    }

    // EFFECTS: Creates table with formatting
    public void createTable() {
        String[] columnNames = {"First Name", "Last Name", "Date of Birth (DOB)", "Age", "Personal Health Number (PHN)"};

        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };

        patientTable = new JTable(tableModel);
        patientTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        patientTable.setRowHeight(30);
        patientTable.setFont(new Font("Arial", Font.PLAIN, 16));

        JScrollPane scrollPane = new JScrollPane(patientTable);
        scrollPane.setPreferredSize(new Dimension(1000, 500));
        contentPanel.add(scrollPane);
    }

    // EFFECTS: Adjusts patient table header formatting
    public void adjustTableHeader() {
        JTableHeader tableHeader = patientTable.getTableHeader();
        tableHeader.setFont(new Font("Arial", Font.BOLD, 18));
        add(tableHeader, BorderLayout.NORTH);
    }

    // EFFECTS: Sets patient table column widths
    public void setColumnWidths() {
        patientTable.getColumnModel().getColumn(0).setPreferredWidth(150);
        patientTable.getColumnModel().getColumn(1).setPreferredWidth(150);
        patientTable.getColumnModel().getColumn(2).setPreferredWidth(150);
        patientTable.getColumnModel().getColumn(3).setPreferredWidth(100);
        patientTable.getColumnModel().getColumn(4).setPreferredWidth(250);
    }

    // EFFECTS: Adds double click event listener to the table rows to view a specific patient profile
    public void addDoubleClickListener() {
        patientTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = patientTable.getSelectedRow();
                    if (row >= 0) {
                        Patient selectedPatient = clinic.getPatients().get(row);
                        parent.viewPatientProfileScreen(selectedPatient);
                    }
                }
            }
        });
    }

    // EFFECTS: Loads patient information into the table
    public void loadPatients() {
        tableModel.setRowCount(0);
        for (Patient p: clinic.getPatients()) {;
            Object[] rowData = {p.getFirstName(), p.getLastName(), p.getDateOfBirth().printDate(), 
                p.getAge(), p.getPersonalHealthNumber()};
            tableModel.addRow(rowData);
        }
    }
}
