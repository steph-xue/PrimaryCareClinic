package model;

import java.util.ArrayList;
import java.util.List;

// A class representing a clinic with a name and list of patient records
public class Clinic {

    private String name;
    private List<Patient> patients;

    // EFFECTS: constructs a clinic with a given name and an empty list of patients
    public Clinic(String name) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: adds new patient to the clinic's patient records list; returns true if succesfully added
    // (patient not already in list) or false if not successful (duplicate record found)
    public boolean addPatient(Patient patient) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: removes patient from the clinic's patient records list; returns true if successfully
    // removed (patient is found in list) and false if not successful (patient not in list)
    public boolean removePatient(Patient patient) {
        // stub
    }

    // EFFECTS: prints summary of patient records in the clinic in String format (sorted alphabetically
    // by last name)
    public String printPatientRecords() {
        // stub
    }

    // Setters
    // MODIFIES: this
    // EFFECTS: sets clinic's name
    public String setClinicName(String name) {
        // stub
    }

    // Getters
    // EFFECTS: gets clinic's name
    public String getClinicName() {
        // stub
    }

    // EFFECTS: gets clinic's list of patient records (sorted alphabetically by last name)
    public List<Patient> getPatients() {
        // stub
    }

}