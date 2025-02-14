package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// A class representing a clinic with a name and list of patient records (sorted alphabetically by 
// last name then first name)
public class Clinic {

    private String name;
    private List<Patient> patients;

    // EFFECTS: constructs a clinic with a given name and an empty list of patients
    public Clinic(String name) {
        this.name = name;
        patients = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds new patient to the clinic's patient records list and sorts patients list 
    // alphabetically by last name then first name, returns true if succesfully added 
    // (patient not already in list) or false if not successful (duplicate record found)
    public boolean addPatient(Patient patient) {
        if (!this.patients.contains(patient)) {
            this.patients.add(patient);
            patients.sort(Comparator.comparing((Patient p) -> p.getLastName().toLowerCase())
            .thenComparing(p -> p.getFirstName().toLowerCase()));
            return true;
        } else {
            return false;
        }
    }

    // MODIFIES: this
    // EFFECTS: removes patient from the clinic's patient records list; returns true if successfully
    // removed (patient is found in list) and false if not successful (patient not in list)
    public boolean removePatient(Patient patient) {
        if (this.patients.contains(patient)) {
            this.patients.remove(patient);
            return true;
        } else {
            return false;
        }
    }

    // EFFECTS: prints summary of patient records in the clinic in String format with name, DOB,
    // age, and PHN (sorted alphabetically by last name then first name)
    public String printPatientRecords() {
        if (patients == null || patients.isEmpty()) {
            return "No patient records";
        }

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < patients.size(); i++) {
            Patient patient = patients.get(i);
            result.append("-------------------------------------------------------------" + "\n");
            result.append(String.valueOf(i + 1) + ". ");
            result.append(patient.getFullName() + "\n");System.out.println(".()");
            result.append("Date of Birth: " + String.valueOf(patient.getDateOfBirth()) + "\n");
            result.append("Age: " + patient.getAge() + "\n");
            result.append("Personal Health Number: " + patient.getPersonalHealthNumber() + "\n");
        }
        return result.toString();
    }

    // Setters
    // MODIFIES: this
    // EFFECTS: sets clinic's name
    public void setClinicName(String name) {
        this.name = name;
    }

    // Getters
    // EFFECTS: gets clinic's name
    public String getClinicName() {
        return name;
    }

    // EFFECTS: gets clinic's list of patient records (sorted alphabetically by last name then first name)
    public List<Patient> getPatients() {
        return patients;
    }

}