package persistence;

import model.Clinic;
import model.ClinicalNote;
import model.Date;
import model.Patient;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads clinic from JSON data stored in file
// JsonReader code modelled from the JsonSerializationDemo
public class JsonReader {

    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
    }

    // EFFECTS: reads clinic from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Clinic read() throws IOException {
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
    }

    // EFFECTS: parses clinic from JSON object and returns it
    private Clinic parseClinic(JSONObject jsonObject) {
    }

    // MODIFIES: clinic
    // EFFECTS: parses patients list from JSON object and adds them to clinic
    private void addPatients(Clinic clinic, JSONObject jsonObject) {
    }

    // MODIFIES: clinic
    // EFFECTS: parses patient from JSON object and adds it to clinic
    private void addPatient(Clinic clinic, JSONObject jsonObject) {
    }

    // MODIFIES: patient
    // EFFECTS: parses date of birth (DOB) from JSON object and adds them to patient
    private void addDateOfBirth(Patient patient, JSONObject jsonObject) {
    }

    // MODIFIES: patient
    // EFFECTS: parses allergies list from JSON object and adds them to patient
    private void addAllergies(Patient patient, JSONObject jsonObject) {
    }

    // MODIFIES: patient
    // EFFECTS: parses medications list from JSON object and adds them to patient
    private void addMedications(Patient patient, JSONObject jsonObject) {
    }

    // MODIFIES: patient
    // EFFECTS: parses medical conditions list from JSON object and adds them to patient
    private void addMedicalConditions(Patient patient, JSONObject jsonObject) {
    }

    // MODIFIES: patient
    // EFFECTS: parses clinical notes list from JSON object and adds it to patient
    private void addClinicalNotes(Patient patient, JSONObject jsonObject) {
    }

    // MODIFIES: patient
    // EFFECTS: parses clinical note from JSON object and adds it to patient
    private void addClinicalNote(Patient patient, JSONObject jsonObject) {
    }


    // MODIFIES: note
    // EFFECTS: parses visit date from JSON object and adds them to clinical note
    private void addVisitDate(ClinicalNote note, JSONObject jsonObject) {
    }
}
