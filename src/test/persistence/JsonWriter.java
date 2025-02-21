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

class JsonWriterTest extends JsonTest {

    @Test
    void testWriterFileDoesNotExist() {
        try {
            Clinic clinic = new Clinic("My Clinic");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            Clinic clinic = new Clinic("My Clinic");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyClinic.json");
            writer.open();
            writer.write(clinic);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyClinic.json");
            clinic = reader.read();
            assertEquals("My Clinic", clinic.getClinicName());
            assertEquals(0, clinic.getPatients().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralClinic() {
        try {
            Clinic clinic = new Clinic("My Clinic");

            Date dateOfBirth1 = new Date(9, 11, 1968);
            Patient patient1 = new Patient("Ashley", "Smith", dateOfBirth1, 56, 920215344);
            patient1.addAllergy("amoxicillin");
            patient1.addAllergy("ibuprofen");
            patient1.addMedication("ramipril");
            patient1.addMedication("amlodipine");
            patient1.addMedication("escitalopram");
            patient1.addMedicalCondition("hypertension");
            patient1.addMedicalCondition("anxiety");
            String title1 = "Constipation";
            String body1 = "Patient presenting with severe constipation";
            String provider1 = "Dr. Johnson";
            Date visitDate1 = new Date(1, 10, 2025);
            ClinicalNote note1 = new ClinicalNote(title1, body1, provider1, visitDate1);
            String title2 = "Urinary tract infection";
            String body2 = "Patient presenting with signs of a urinary tract infection";
            String provider2 = "Dr. Kaur";
            Date visitDate2 = new Date(2, 15, 2025);
            ClinicalNote note2 = new ClinicalNote(title2, body2, provider2, visitDate2);
            patient1.addClinicalNote(note1);
            patient1.addClinicalNote(note2);
            clinic.addPatient(patient1);

            Date dateOfBirth2 = new Date(1, 6, 1971);
            Patient patient2 = new Patient("Thomas", "Davis", dateOfBirth2, 54, 905845652);
            patient2.addAllergy("tetracycline");
            patient2.addAllergy("aspirin");
            patient2.addMedication("metformin");
            patient2.addMedication("valproic acid");
            patient2.addMedicalCondition("diabetes");
            patient2.addMedicalCondition("epilepsy");
            String title3 = "Allergic rhinitis";
            String body3 = "Patient presenting with sneezing and nasal congestion";
            String provider3 = "Dr. Rodriguez";
            Date visitDate3 = new Date(1, 18, 2025);
            ClinicalNote note3 = new ClinicalNote(title3, body3, provider3, visitDate3);
            String title4 = "Cold sore";
            String body4 = "Patient presenting with new cold sore";
            String provider4 = "Dr. Young";
            Date visitDate4 = new Date(2, 12, 2025);
            ClinicalNote note4 = new ClinicalNote(title4, body4, provider4, visitDate4);
            patient2.addClinicalNote(note3);
            patient2.addClinicalNote(note4);
            clinic.addPatient(patient2);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralClinic.json");
            writer.open();
            writer.write(clinic);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralClinic.json");
            clinic = reader.read();
            assertEquals("My Clinic", clinic.getClinicName());
            List<Patient> patients = clinic.getPatients();
            assertEquals(2, patients.size());

            List<String> allergies1 = new ArrayList<>();
            allergies1.add("amoxicillin");
            allergies1.add("ibuprofen");
            List<String> medications1 = new ArrayList<>();
            medications1.add("ramipril");
            medications1.add("amlodipine");
            medications1.add("escitalopram");
            List<String> medicalConditions1 = new ArrayList<>();
            medicalConditions1.add("hypertension");
            medicalConditions1.add("anxiety");
            List<ClinicalNote> notesList1 = new ArrayList<>();
            notesList1.add(note1);
            notesList1.add(note2);

            checkPatientGeneral("Ashley", "Smith", dateOfBirth1, 56, 920215344, patients.get(0));
            checkPatientAllergies(allergies1, patients.get(0));
            checkPatientMedications(medications1, patients.get(0));
            checkPatientMedicalConditions(medicalConditions1, patients.get(0));
            checkPatientClinicalNotes(notesList1, patients.get(0));

            List<String> allergies2 = new ArrayList<>();
            allergies2.add("tetracycline");
            allergies2.add("aspirin");
            List<String> medications2 = new ArrayList<>();
            medications2.add("metformin");
            medications2.add("valproic acid");
            List<String> medicalConditions2 = new ArrayList<>();
            medicalConditions2.add("diabetes");
            medicalConditions2.add("epilepsy");
            List<ClinicalNote> notesList2 = new ArrayList<>();
            notesList2.add(note3);
            notesList2.add(note4);

            checkPatientGeneral("Thomas", "Davis", dateOfBirth2, 54, 905845652, patients.get(1));
            checkPatientAllergies(allergies2, patients.get(1));
            checkPatientMedications(medications2, patients.get(1));
            checkPatientMedicalConditions(medicalConditions2, patients.get(1));
            checkPatientClinicalNotes(notesList2, patients.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}