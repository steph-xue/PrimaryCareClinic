package persistence;

import model.Clinic;
import model.ClinicalNote;
import model.Date;
import model.Patient;

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderFileDoesNotExist() {
        JsonReader reader = new JsonReader("./data/noExistingFile.json");
        try {
            Clinic clinic = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyClinic() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyClinic.json");
        try {
            Clinic clinic = reader.read();
            assertEquals("My Clinic", clinic.getClinicName());
            assertEquals(0, clinic.getPatients().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralClinic() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralClinic.json");
        try {
            Clinic clinic = reader.read();
            assertEquals("My Clinic", clinic.getClinicName());
            List<Patient> patients = clinic.getPatients();
            assertEquals(2, patients.size());

            Date dateOfBirth1 = new Date(8, 16, 1965);
            List<String> allergies1 = new ArrayList<>();
            allergies1.add("penicillin");
            allergies1.add("codeine");
            List<String> medications1 = new ArrayList<>();
            medications1.add("metformin");
            medications1.add("ramipril");
            medications1.add("amlodipine");
            List<String> medicalConditions1 = new ArrayList<>();
            medicalConditions1.add("diabetes");
            medicalConditions1.add("hypertension");
            String title1 = "Coughing";
            String body1 = "Patient presenting with severe cough";
            String provider1 = "Dr. Campbell";
            Date visitDate1 = new Date(1, 16, 2025);
            ClinicalNote note1 = new ClinicalNote(title1, body1, provider1, visitDate1);
            String title2 = "Ear infection";
            String body2 = "Patient presenting with infected ear";
            String provider2 = "Dr. Singh";
            Date visitDate2 = new Date(2, 20, 2025);
            ClinicalNote note2 = new ClinicalNote(title2, body2, provider2, visitDate2);
            List<ClinicalNote> notesList1 = new ArrayList<>();
            notesList1.add(note1);
            notesList1.add(note2);

            checkPatientGeneral("Mya", "Cornell", dateOfBirth1, 59, 925245285, patients.get(0));
            checkPatientAllergies(allergies1, patients.get(0));
            checkPatientMedications(medications1, patients.get(0));
            checkPatientMedicalConditions(medicalConditions1, patients.get(0));
            checkPatientClinicalNotes(notesList1, patients.get(0));
            checkClinicalNote(title1, body1, provider1, visitDate1, note1);
            checkClinicalNote(title2, body2, provider2, visitDate2, note2);

            Date dateOfBirth2 = new Date(11, 8, 1971);
            List<String> allergies2 = new ArrayList<>();
            allergies2.add("ciprofloxacin");
            allergies2.add("azithromycin");
            List<String> medications2 = new ArrayList<>();
            medications2.add("rosuvastatin");
            medications2.add("bisoprolol");
            List<String> medicalConditions2 = new ArrayList<>();
            medicalConditions2.add("hyperlipidemia");
            medicalConditions2.add("hypertension");
            String title3 = "Eye infection";
            String body3 = "Patient presenting with eye infection";
            String provider3 = "Dr. Williams";
            Date visitDate3 = new Date(1, 18, 2025);
            ClinicalNote note3 = new ClinicalNote(title3, body3, provider3, visitDate3);
            String title4 = "Skin rash";
            String body4 = "Patient presenting with skin rash";
            String provider4 = "Dr. Jones";
            Date visitDate4 = new Date(2, 12, 2025);
            ClinicalNote note4 = new ClinicalNote(title4, body4, provider4, visitDate4);
            List<ClinicalNote> notesList2 = new ArrayList<>();
            notesList2.add(note3);
            notesList2.add(note4);

            checkPatientGeneral("Amira", "Garcia", dateOfBirth2, 53, 925645120, patients.get(1));
            checkPatientAllergies(allergies2, patients.get(1));
            checkPatientMedications(medications2, patients.get(1));
            checkPatientMedicalConditions(medicalConditions2, patients.get(1));
            checkPatientClinicalNotes(notesList2, patients.get(1));
            checkClinicalNote(title3, body3, provider3, visitDate3, note3);
            checkClinicalNote(title4, body4, provider4, visitDate4, note4);

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
    
}
