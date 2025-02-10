package model;

// A class representing a clinical note for a patient's visit at the clinic with a title, body, name of 
// healthcare provider seen, and date of visit
public class ClinicalNote {

    private String title;
    private String body;
    private String healthCareProvider;
    private Date visitDate;

    // EFFECTS: constructs a clinical note with a given title, body, name of healthcare provider seen, and current date
    public ClinicalNote(String title, String body, String name, Date date) {
        // stub
    }

    // EFFECTS: prints summary of the clinical note in String format
    public String printClinicaNote() {
        // stub
    }

    // Setters
    // MODIFIES: this
    // EFFECTS: sets clinical note's title with given title
    public void setClinicalNoteTitle(String title) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: sets clinical note's body with given body
    public void setClinicalNoteBody(String body) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: sets clinical note's healthcare provider's name with given name
    public void setClinicalNoteProvider(String name) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: sets clinical note's date of visit with given date
    public void setClinicalNoteDate(Date date) {
        // stub
    }

    // Getters
    // EFFECTS: gets clinical note's title
    public String getClinicalNoteTitle() {
        // stub
    }

    // EFFECTS: gets clinical note's body
    public String getClinicalNoteBody() {
        // stub
    }

    // EFFECTS: gets clinical note's healthcare provider's name
    public String getClinicalNoteProvider() {
        // stub
    }

    // EFFECTS: gets clinical note's date of visit
    public Date getClinicalNoteDate() {
        // stub
    }

}