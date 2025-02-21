package persistence;

import model.ClinicalNote;
import model.Date;
import model.Patient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonTest {
    protected void checkPatientGeneral(String firstName, String lastName, Date dob, int age, long phn, Patient pt) {
        assertEquals(firstName, pt.getFirstName());
        assertEquals(lastName, pt.getLastName());
        assertEquals(dob, pt.getDateOfBirth());
        assertEquals(age, pt.getAge());
        assertEquals(phn, pt.getPersonalHealthNumber());
    }

    protected void checkPatientAllergies(List<String> allergies, Patient pt) {
        assertEquals(allergies, pt.getAllergies());
    }

    protected void checkPatientMedications(List<String> medications, Patient pt) {
        assertEquals(medications, pt.getMedications());
    }

    protected void checkPatientMedicalConditions(List<String> medicalConditions, Patient pt) {
        assertEquals(medicalConditions, pt.getMedicalConditions());
    }

    protected void checkPatientClinicalNotes(List<ClinicalNote> notes, Patient pt) {
        assertEquals(notes, pt.getClinicalNotes());
    }

    protected void checkClinicalNote(String title, String body, String provider, Date visitDate, ClinicalNote note) {
        assertEquals(title, note.getClinicalNoteTitle());
        assertEquals(body, note.getClinicalNoteBody());
        assertEquals(provider, note.getClinicalNoteProvider());
        assertEquals(visitDate, note.getClinicalNoteDate());
    }
}
