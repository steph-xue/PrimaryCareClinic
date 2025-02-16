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
        this.title = title;
        this.body = body;
        this.healthCareProvider = name;
        this.visitDate = date;
    }

    // EFFECTS: prints summary of the clinical note in String format
    public String printClinicaNote() {
        StringBuilder result = new StringBuilder();

        result.append("---------------------------------------------------------------------------------" + "\n");
        result.append(getClinicalNoteTitle() + "\n");
        result.append(getClinicalNoteBody() + "\n");
        result.append(getClinicalNoteProvider() + " ");
        result.append(getClinicalNoteDate().printDate() + "\n");

        return result.toString();
    }

    // Setters
    // MODIFIES: this
    // EFFECTS: sets clinical note's title with given title
    public void setClinicalNoteTitle(String title) {
        this.title = title;
    }

    // MODIFIES: this
    // EFFECTS: sets clinical note's body with given body
    public void setClinicalNoteBody(String body) {
        this.body = body;
    }

    // MODIFIES: this
    // EFFECTS: sets clinical note's healthcare provider's name with given name
    public void setClinicalNoteProvider(String name) {
        this.healthCareProvider = name;
    }

    // MODIFIES: this
    // EFFECTS: sets clinical note's date of visit with given date
    public void setClinicalNoteDate(Date date) {
        this.visitDate = date;
    }

    // Getters
    // EFFECTS: gets clinical note's title
    public String getClinicalNoteTitle() {
        return title;
    }

    // EFFECTS: gets clinical note's body
    public String getClinicalNoteBody() {
        return body;
    }

    // EFFECTS: gets clinical note's healthcare provider's name
    public String getClinicalNoteProvider() {
        return healthCareProvider;
    }

    // EFFECTS: gets clinical note's date of visit
    public Date getClinicalNoteDate() {
        return visitDate;
    }

}