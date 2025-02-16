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
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.personalHealthNumber = personalHealthNumber;
        allergies = new ArrayList<>();
        medications = new ArrayList<>();
        medicalConditions = new ArrayList<>();
        clinicalNotes = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds allergy to patient's current list of allergies, returns true if successfully added (not already
    // in current list to prevent duplicates) and false if not successfully added (duplicate entry)
    public boolean addAllergy(String allergy) {
        if (!this.allergies.contains(allergy)) {
            this.allergies.add(allergy);
            return true;
        } else {
            return false;
        }
    }

    // MODIFIES: this
    // EFFECTS: removes allergy from patient's current list of allergies if present, returns true if successfully 
    // removed and false if not succesfully removed (not found in list)
    public boolean removeAllergy(String allergy) {
        if (this.allergies.contains(allergy)) {
            this.allergies.remove(allergy);
            return true;
        } else {
            return false;
        }
    }

    // MODIFIES: this
    // EFFECTS: if present in current list of allergies, replaces old allergy with new edited allergy, returns true
    // if edit is successful and false if not successful (allergy to edit not found in list) 
    public boolean editAllergy(String oldAllergy, String newAllergy) {
        if (this.allergies.contains(oldAllergy)) {
            int index = this.allergies.indexOf(oldAllergy);
            this.allergies.set(index, newAllergy);
            return true;
        } else {
            return false;
        }
    }

    // MODIFIES: this
    // EFFECTS: adds medication (generic name) to patient's current list of medications, returns true if successfully
    // added (not already in current list to prevent duplicates) and false if not successfully added (duplicate entry)
    public boolean addMedication(String medication) {
        if (!this.medications.contains(medication)) {
            this.medications.add(medication);
            return true;
        } else {
            return false;
        }
    }

    // MODIFIES: this
    // EFFECTS: removes medication (generic name) from patient's current list of medications if present, returns true
    // if successfully removed and false if not succesfully removed (not found in list)
    public boolean removeMedication(String medication) {
        if (this.medications.contains(medication)) {
            this.medications.remove(medication);
            return true;
        } else {
            return false;
        }
    }

    // MODIFIES: this
    // EFFECTS: if present in current list of medications, replaces old with new edited medication (generic name), 
    // returns true if edit is successful and false if not successful (medication to edit not found in list) 
    public boolean editMedication(String oldMedication, String newMedication) {
        if (this.medications.contains(oldMedication)) {
            int index = this.medications.indexOf(oldMedication);
            this.medications.set(index, newMedication);
            return true;
        } else {
            return false;
        }
    }

    // MODIFIES: this
    // EFFECTS: adds medical condition to patient's current list of medical conditions, returns true if successfully
    // added (not already in current list to prevent duplicates) and false if not successfully added (duplicate entry)
    public boolean addMedicalCondition(String medicalCondition) {
        if (!this.medicalConditions.contains(medicalCondition)) {
            this.medicalConditions.add(medicalCondition);
            return true;
        } else {
            return false;
        }
    }

    // MODIFIES: this
    // EFFECTS: removes medical condition from patient's current list of medical conditions if present, returns true
    // if successfully removed and false if not succesfully removed (not found in list)
    public boolean removeMedicalCondition(String medicalCondition) {
        if (this.medicalConditions.contains(medicalCondition)) {
            this.medicalConditions.remove(medicalCondition);
            return true;
        } else {
            return false;
        }
    }

    // MODIFIES: this
    // EFFECTS: if present in current list of medical conditions, replaces old with new edited medical condition,
    // returns true if edit is successful and false if not successful (medical condition to edit not found in list)
    public boolean editMedicalCondition(String oldMedicalCondition, String newMedicalCondition) {
        if (this.medicalConditions.contains(oldMedicalCondition)) {
            int index = this.medicalConditions.indexOf(oldMedicalCondition);
            this.medicalConditions.set(index, newMedicalCondition);
            return true;
        } else {
            return false;
        }
    }

    // MODIFIES: this
    // EFFECTS: adds clinical note to patient's list of clinical notes, returns true if successfully added (not 
    // already in current list to prevent duplicates) and false if not successfully added (duplicate entry)
    public boolean addClinicalNote(ClinicalNote note) {
        if (!this.clinicalNotes.contains(note)) {
            this.clinicalNotes.add(note);
            return true;
        } else {
            return false;
        }
    }

    // MODIFIES: this
    // EFFECTS: removes clinical note from patient's list of clinical notes if present, returns true
    // if successfully removed and false if not succesfully removed (not found in list)
    public boolean removeClinicalNote(ClinicalNote note) {
        if (this.clinicalNotes.contains(note)) {
            this.clinicalNotes.remove(note);
            return true;
        } else {
            return false;
        }
    }

    // EFFECTS: prints patient's current list of allergies in string format
    // separated by commas, or prints no allergies if list is empty
    public String printAllergies() {
        if (allergies.isEmpty()) {
            return "No allergies";
        }

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < allergies.size(); i++) {
            String allergy = allergies.get(i);

            if (i != 0) {
                result.append(", ");
            }

            if (i == 0) {
                allergy = allergy.substring(0, 1).toUpperCase() + allergy.substring(1);
            }
            result.append(allergy);
        }
        return result.toString();
    }

    // EFFECTS: prints patient's current list of medications in string format
    // separated by commas, or prints no medications if list is empty
    public String printMedications() {
        if (medications.isEmpty()) {
            return "No medications";
        }

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < medications.size(); i++) {
            String medication = medications.get(i);

            if (i != 0) {
                result.append(", ");
            }

            if (i == 0) {
                medication = medication.substring(0, 1).toUpperCase() + medication.substring(1);
            }
            result.append(medication);
        }
        return result.toString();
    }

    // EFFECTS: prints patient's current list of medical conditions in string format
    // separated by commas, or prints no medical conditions if list is empty
    public String printMedicalConditions() {
        if (medicalConditions.isEmpty()) {
            return "No medical conditions";
        }

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < medicalConditions.size(); i++) {
            String medicalCondition = medicalConditions.get(i);

            if (i != 0) {
                result.append(", ");
            }

            if (i == 0) {
                medicalCondition = medicalCondition.substring(0, 1).toUpperCase() + medicalCondition.substring(1);
            }
            result.append(medicalCondition);
        }
        return result.toString();
    }

    // EFFECTS: prints details of patient's list of clinical notes 
    public String printClinicalNotes() {
        if (clinicalNotes.isEmpty()) {
            return "No clinical notes";
        }

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < clinicalNotes.size(); i++) {
            ClinicalNote note = clinicalNotes.get(i);
            result.append("---------------------------------------------------------------------------------" + "\n");
            result.append(String.valueOf(i + 1) + ". ");
            result.append(note.getClinicalNoteTitle() + "\n");
            result.append(note.getClinicalNoteBody() + "\n");
            result.append(note.getClinicalNoteProvider() + " ");
            result.append(note.getClinicalNoteDate().printDate() + "\n");
        }
        return result.toString();
    }

    // Setters
    // MODIFIES: this
    // EFFECTS: sets the patient's first name to a new given first name
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // MODIFIES: this
    // EFFECTS: sets the patient's last name to a new given last name
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // REQUIRES: age > 0
    // MODIFIES: this
    // EFFECTS: sets the patient's DOB and age to a new given DOB and age
    public void setDateOfBirthAndAge(Date dateOfBirth, int age) {
        this.dateOfBirth = dateOfBirth;
        this.age = age;
    }

    // REQUIRES: personalHealthNumber > 0
    // MODIFIES: this
    // EFFECTS: sets the patient's PHN to a new given PHN
    public void setPersonalHealthNumber(long personalHealthNumber) {
        this.personalHealthNumber = personalHealthNumber;
    }

    // Getters
    // EFFECTS: gets patient's first name
    public String getFirstName() {
        return firstName;
    }

    // EFFECTS: gets patient's last name
    public String getLastName() {
        return lastName;
    }

    // EFFECTS: gets patient's full name
    public String getFullName() {
        return firstName + " " + lastName;
    }

    // EFFECTS: gets patient's DOB
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    // EFFECTS: gets patient's age
    public int getAge() {
        return age;
    }

    // EFFECTS: gets patient's PHN
    public long getPersonalHealthNumber() {
        return personalHealthNumber;
    }

    // EFFECTS: gets patient's current list of allergies
    public List<String> getAllergies() {
        return allergies;
    }

    // EFFECTS: gets patient's current list of medications
    public List<String> getMedications() {
        return medications;
    }

    // EFFECTS: gets patient's current list of medical conditions
    public List<String> getMedicalConditions() {
        return medicalConditions;
    }

    // EFFECTS: gets patient's list of clinical notes
    public List<ClinicalNote> getClinicalNotes() {
        return clinicalNotes;
    }
}
