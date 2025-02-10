package model;

import java.util.ArrayList;
import java.util.List;

// A class representing a patient with a first name, last name, date of birth (DOB), age, personal health number (PHN),
// list of current allergies, medications (generic names), medical conditions, and clinical notes
public class Patient {

    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private int age;
    private long personalHealthNumber;
    private List<String> allergies;
    private List<String> medications;
    private List<String> medicalConditions;
    private List<ClinicalNote> clinicalNotes;

    // REQUIRES: age > 0, personalHealthNumber > 0
    // EFFECTS: constructs a new record for a patient with their first name, last name, DOB, age, PHN, and an empty
    // list of current allergies, medications, medical conditions, and clinical notes
    public Patient(String firstName, String lastName, Date dateOfBirth, int age, long personalHealthNumber) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: adds allergy to patient's current list of allergies, returns true if successfully added (not already
    // in current list to prevent duplicates) and false if not successfully added (duplicate entry)
    public boolean addAllergy(String allergy) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: removes allergy from patient's current list of allergies if present, returns true if successfully 
    // removed and false if not succesfully removed (not found in list)
    public boolean removeAllergy(String allergy) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: if present in current list of allergies, replaces old allergy with new edited allergy, returns true
    // if edit is successful and false if not successful (allergy to edit not found in list) 
    public boolean editAllergy(String oldAllergy, String newAllergy) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: adds medication (generic name) to patient's current list of medications, returns true if successfully
    // added (not already in current list to prevent duplicates) and false if not successfully added (duplicate entry)
    public boolean addMedication(String medication) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: removes medication (generic name) from patient's current list of medications if present, returns true
    // if successfully removed and false if not succesfully removed (not found in list)
    public boolean removeMedication(String medication) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: if present in current list of medications, replaces old with new edited medication (generic name), 
    // returns true if edit is successful and false if not successful (medication to edit not found in list) 
    public boolean editMedication(String oldMedication, String newMedication) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: adds medical condition to patient's current list of medical conditions, returns true if successfully
    // added (not already in current list to prevent duplicates) and false if not successfully added (duplicate entry)
    public boolean addMedicalCondition(String medicalCondition) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: removes medical condition from patient's current list of medical conditions if present, returns true
    // if successfully removed and false if not succesfully removed (not found in list)
    public boolean removeMedicalCondition(String medicalCondition) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: if present in current list of medical conditions, replaces old with new edited medical condition,
    // returns true if edit is successful and false if not successful (medical condition to edit not found in list)
    public boolean editMedicalCondition(String oldMedicalCondition, String newMedicalCondition) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: adds clinical note to patient's list of clinical notes, returns true if successfully added (not 
    // already in current list to prevent duplicates) and false if not successfully added (duplicate entry)
    public boolean addClinicalNote(ClinicalNote note) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: removes clinical note from patient's list of clinical notes if present, returns true
    // if successfully removed and false if not succesfully removed (not found in list)
    public boolean removeClinicalNote(ClinicalNote note) {
        // stub
    }

    // EFFECTS: prints patient's current list of allergies in string format
    public String printAllergies() {
        // stub
    }

    // EFFECTS: prints patient's current list of medications in string format
    public String printMedications() {
        // stub
    }

    // EFFECTS: prints patient's current list of medical conditions in string format
    public String printMedicalConditions() {
        // stub
    }

    // EFFECTS: prints details of patient's list of clinical notes
    public String printClinicalNotes() {
        // stub
    }

    // Setters
    // MODIFIES: this
    // EFFECTS: sets the patient's first name to a new given first name
    public void setFirstName(String firstName) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: sets the patient's last name to a new given last name
    public void setLastName(String lastName) {
        // stub
    }

    // REQUIRES: age > 0
    // MODIFIES: this
    // EFFECTS: sets the patient's DOB and age to a new given DOB and age
    public void setDateOfBirthAndAge(Date dateOfBirth, int age) {
        // stub
    }

    // REQUIRES: personalHealthNumber > 0
    // MODIFIES: this
    // EFFECTS: sets the patient's PHN to a new given PHN
    public void setPersonalHealthNumber(long personalHealthNumber) {
        // stub
    }

    // Getters
    // EFFECTS: gets patient's first name
    public String getFirstName() {
        // stub
    }

    // EFFECTS: gets patient's last name
    public String getLastName() {
        // stub
    }

    // EFFECTS: gets patient's full name
    public String getFullName() {
        // stub
    }

    // EFFECTS: gets patient's DOB
    public Date getDateOfBirth() {
        // stub
    }

    // EFFECTS: gets patient's age
    public Date getAge() {
        // stub
    }

    // EFFECTS: gets patient's PHN
    public long getPersonalHealthNumber() {
        // stub
    }

    // EFFECTS: gets patient's current list of allergies
    public List<String> getAllergies() {
        // stub
    }

    // EFFECTS: gets patient's current list of medications
    public List<String> getMedications() {
        // stub
    }

    // EFFECTS: gets patient's current list of medical conditions
    public List<String> getMedicalConditions() {
        // stub
    }

    // EFFECTS: gets patient's list of clinical notes
    public List<ClinicalNote> getClinicalNotes() {
        // stub
    }
}
