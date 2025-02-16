package ui;

import model.Clinic;
import model.ClinicalNote;
import model.Date;
import model.Patient;

import java.util.Scanner;

public class PrimaryCareClinicApp {
    private Clinic clinic;
    private Scanner scanner;
    private boolean isProgramRunning;
    private boolean isClinicRunning;

    // EFFECTS: creates an instance of the PrimaryCareClinic console ui application
    public PrimaryCareClinicApp() {
        init();

        printDivider();
        System.out.println("Welcome to the Primary Care Clinic app!");
        printDivider();

        while (this.isProgramRunning) {
            if (!isClinicRunning) {
                handleStartMenu();
            } else {
                handleMainMenu();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes the application with the starting values where the program is running 
    // but the clinic is not yet running
    public void init() {
        this.scanner = new Scanner(System.in);
        this.isProgramRunning = true;
        this.isClinicRunning = false;
    }

    // EFFECTS: displays and processes inputs for the start menu
    public void handleStartMenu() {
        displayStartMenu();
        String input = this.scanner.nextLine();
        processStartMenuCommands(input);
    }

    // EFFECTS: displays a list of commands that can be used in the start menu when no clinic has
    // yet been created 
    public void displayStartMenu() {
        System.out.println("Please select an option:");
        System.out.println("c: Create a new clinic");
        System.out.println("q: Exit the application");
        printDivider();
    }

    // EFFECTS: processes the user's input in the start menu when no clinic has yet been created
    public void processStartMenuCommands(String input) {
        switch (input) {
            case "c":
                createNewClinic();
                break;
            case "q":
                quitApplication();
                break;
            default:
                printDivider();
                System.out.println("Invalid option inputted. Please try again.");
        }
        printDivider();
    }

    // MODIFIES: this
    // EFFECTS: creates new clinic with name from the user and sets the clinic as running;
    // prints out a success message
    public void createNewClinic() {
        printDivider();
        System.out.println("Please enter a name for the clinic:");
        String input = this.scanner.nextLine();
        clinic = new Clinic(input);
        isClinicRunning = true;

        printDivider();
        System.out.print("New clinic \"");
        System.out.print(clinic.getClinicName());
        System.out.print("\" successfully created!\n");
        printDivider();
        System.out.print("*** Welcome to ");
        System.out.print(clinic.getClinicName());
        System.out.print("! *** \n");
    }

    // EFFECTS: displays and processes inputs for the main menu
    public void handleMainMenu() {
        displayMainMenu();
        String input = this.scanner.nextLine();
        processMainMenuCommands(input);
    }

    // EFFECTS: displays a list of commands that can be used in the main menu; the user can add
    // a new patient, view all patients, rename the clinic, or exit the application
    public void displayMainMenu() {
        System.out.println("Please select an option:");
        System.out.println("a: Add a new patient record");
        System.out.println("v: View all patient records");
        System.out.println("r: Rename the clinic");
        System.out.println("q: Exit the application");
        printDivider();
    }

    // EFFECTS: processes the user's input in the main menu
    public void processMainMenuCommands(String input) {
        switch (input) {
            case "a":
                addNewPatientRecord();
                break;
            case "v":
                viewAllPatientRecords();
                break;
            case "r":
                renameClinic();
                break;
            case "q":
                quitApplication();
                break;
            default:
                printDivider();
                System.out.println("Invalid option inputted. Please try again.");
        }
        printDivider();
    }

    // EFFECTS: add new patient to patient record list using input from the user for first name,
    // last name, date of birth (DOB), age, personal health number (PHN) and additional information
    // like allergies, medications, and medical conditions; prints confirmation message for adding patient
    public void addNewPatientRecord() {
        printDivider();
        System.out.println("Adding new patient");
        System.out.println("Please enter the first name: ");
        String firstName = this.scanner.nextLine();
        System.out.println("Please enter the last name: ");
        String lastName = this.scanner.nextLine();
        System.out.println("Please enter the date of birth (month) (e.g. 3 for March): ");
        int monthofBirth = this.scanner.nextInt();
        System.out.println("Please enter the date of birth (day): ");
        int dayofBirth = this.scanner.nextInt();
        System.out.println("Please enter the date of birth (year): ");
        int yearofBirth = this.scanner.nextInt();
        System.out.println("Please enter the age: ");
        int age = this.scanner.nextInt();
        System.out.println("Please enter the 9-digit personal health number (PHN): ");
        long personalHealthNumber = this.scanner.nextLong();
        scanner.nextLine(); 
        Date dateOfBirth = new Date(monthofBirth, dayofBirth, yearofBirth);
        Patient newPatient = new Patient(firstName, lastName, dateOfBirth, age, personalHealthNumber);
        clinic.addPatient(newPatient);
        newPatientConfirmationMessage(newPatient);
        addAdditionalInformation(newPatient);
    }

    // EFFECTS: prints out a new patient added confirmation message
    public void newPatientConfirmationMessage(Patient newPatient) {
        printDivider();
        System.out.print("New patient: ");
        System.out.print(newPatient.getFullName() + "\n");
        System.out.print("Date of birth (DOB): ");
        System.out.print(newPatient.getDateOfBirth().printDate() + "\n");
        System.out.print("Age: ");
        System.out.print(newPatient.getAge() + "\n");
        System.out.print("Personal health number (PHN): ");
        System.out.print(newPatient.getPersonalHealthNumber() + "\n");
        System.out.print("New patient has been successfully created!\n");
    }

    // EFFECTS: add additional information for the new the patient including allergies, medications,
    // and medical conditions 
    public void addAdditionalInformation(Patient newPatient) {
        printDivider();
        System.out.println("Adding allergy, medication, and medical condition information for new patient");

        addAllergies(newPatient);
        addMedications(newPatient);
        addMedicalConditions(newPatient);

        printDivider();
        System.out.print("Successfully added the following for new patient \"");
        System.out.print(newPatient.getFullName() + "\": " + "\n");
        System.out.print("Allergies: ");
        System.out.print(newPatient.printAllergies() + "\n");
        System.out.print("Medications: ");
        System.out.print(newPatient.printMedications() + "\n");
        System.out.print("Medical conditions: ");
        System.out.print(newPatient.printMedicalConditions() + "\n");
    }

    // EFFECTS: displays menu for adding additional allergies for new patient
    public void addAllergies(Patient newPatient) {
        boolean allergiesInputed = false;

        while (!allergiesInputed) {
            printDivider();
            System.out.println("Please select an option to input allergies:");
            System.out.println("a: Add an allergy");
            System.out.println("n: No more known allergies");

            printDivider();
            String input = this.scanner.nextLine();
            allergiesInputed = handleAddAllergies(input, newPatient);
        }
    }

    // MODIFIES: this
    // EFFECTS: handles adding of allergies for new patient into list of allergies based on user input;
    // returns false if user is not done adding allergies and returns true if no more allergies to add
    public boolean handleAddAllergies(String input, Patient newPatient) {
        switch (input) {
            case "a":
                printDivider();
                System.out.println("Add allergy: ");
                String allergy = this.scanner.nextLine();
                newPatient.addAllergy(allergy);
                return false;
            case "n":
                return true;
            default:
                printDivider();
                System.out.println("Invalid option inputted. Please try again.");
                return false;
        }
    }

    // EFFECTS: displays menu for adding additional medications for new patient
    public void addMedications(Patient newPatient) {
        boolean medicationsInputed = false;

        while (!medicationsInputed) {
            printDivider();
            System.out.println("Please select an option to input medications:");
            System.out.println("a: Add a medication");
            System.out.println("n: No more known medications");
            printDivider();

            String input = this.scanner.nextLine();
            medicationsInputed = handleAddMedications(input, newPatient);
        }
    }

    // MODIFIES: this
    // EFFECTS: handles adding of medications for new patient into list of medications based on user input;
    // returns false if user is not done adding medications and returns true if no more medications to add
    public boolean handleAddMedications(String input, Patient newPatient) {
        switch (input) {
            case "a":
                printDivider();
                System.out.println("Add medication: ");
                String medication = this.scanner.nextLine();
                newPatient.addMedication(medication);
                return false;
            case "n":
                return true;
            default:
                printDivider();
                System.out.println("Invalid option inputted. Please try again.");
                return false;
        }
    }

    // EFFECTS: displays menu for adding additional medical conditions for new patient
    public void addMedicalConditions(Patient newPatient) {
        boolean medicalConditionsInputed = false;

        while (!medicalConditionsInputed) {
            printDivider();
            System.out.println("Please select an option to input medical conditions:");
            System.out.println("a: Add an medical condition");
            System.out.println("n: No more known medical conditions");
            printDivider();

            String input = this.scanner.nextLine();
            medicalConditionsInputed = handleAddMedicalConditions(input, newPatient);
        }
    }

    // MODIFIES: this
    // EFFECTS: handles adding of medical conditions for new patient into list of medical conditions based 
    // on user input; returns false if user is not done adding medical conditions and returns true if
    // no more medical conditions to add
    public boolean handleAddMedicalConditions(String input, Patient newPatient) {
        switch (input) {
            case "a":
                printDivider();
                System.out.println("Add medical condition: ");
                String medicalCondition = this.scanner.nextLine();
                newPatient.addMedicalCondition(medicalCondition);
                return false;
            case "n":
                return true;
            default:
                printDivider();
                System.out.println("Invalid option inputted. Please try again.");
                return false;
        }
    }

    // EFFECTS: view all current patient records and handles the patient specific menu to view/edit
    // patient specific information
    public void viewAllPatientRecords() {
        printDivider();
        System.out.println("Viewing all patient records");
        System.out.println(clinic.printPatientRecords());
        if (!clinic.getPatients().isEmpty()) {
            handleViewAllPatientsMenu();
        }
    }

    // EFFECTS: displays and processes inputs for the view all patients menu
    public void handleViewAllPatientsMenu() {
        boolean viewAllPatientsMenu = true;

        while (viewAllPatientsMenu) {
            displayViewAllPatientsMenu();
            String input = this.scanner.nextLine();
            viewAllPatientsMenu = processViewPatientMenuCommands(input);
        }
    }

    // EFFECTS: displays a list of commands that can be used in the view all patients menu; the user can
    // type in a number to select to view/edit a specific patient record or go back to the main menu
    public void displayViewAllPatientsMenu() {
        printDivider();
        System.out.println("Please select an option:");
        System.out.println("# (Index number): View/edit a patient record at this index");
        System.out.println("b: Go back to main menu");
        printDivider();
    }

    // EFFECTS: processes the user's input in the view all patients menu; returns true if
    // user is to continue viewing the view all patients menu and false if going back to 
    // the main menu
    public Boolean processViewPatientMenuCommands(String input) {
        // Check if input is an index number that can be retrieved from the list of patients
        if (input.matches("\\d+") && (Integer.parseInt(input) <= clinic.getPatients().size())) { 
            int index = Integer.parseInt(input);
            Patient patient = clinic.getPatients().get(index - 1);
            printRetrievedPatientRecord(patient);
            handlePatientMenu(patient);
            return true;
        } else {
            switch (input) {
                case "b":
                    return false;
                default:
                    printDivider();
                    System.out.println("Invalid option inputted. Please try again.");
                    return true;
            }
        }
    }

    // EFFECTS: prints out details of the selected patient record 
    public void printRetrievedPatientRecord(Patient patient) {
        printDivider();
        System.out.println("Viewing patient record");
        printDivider();
        System.out.print("Patient name: ");
        System.out.print(patient.getFullName() + "\n");
        System.out.print("Date of birth (DOB): ");
        System.out.print(patient.getDateOfBirth().printDate() + "\n");
        System.out.print("Age: ");
        System.out.print(patient.getAge() + "\n");
        System.out.print("Personal health number (PHN): ");
        System.out.print(patient.getPersonalHealthNumber() + "\n");
        System.out.print("Allergies: ");
        System.out.print(patient.printAllergies() + "\n");
        System.out.print("Medications: ");
        System.out.print(patient.printMedications() + "\n");
        System.out.print("Medical conditions: ");
        System.out.print(patient.printMedicalConditions() + "\n");
        System.out.print("Number of clinical notes: ");
        System.out.print(patient.getClinicalNotes().size() + "\n");
        printDivider();
    }

    // EFFECTS: displays and processes inputs for the patient menu
    public void handlePatientMenu(Patient patient) {
        boolean viewPatientMenu = true;

        while (viewPatientMenu) {
            displayPatientMenu();
            String input = this.scanner.nextLine();
            viewPatientMenu = processPatientMenuCommands(input, patient);
        }
    }

    // EFFECTS: displays a list of commands that can be used in the patient menu; the user can
    // edit patient information, add a clinical note, view/edit previous clinical notes, and 
    // go back to the view all patients menu
    public void displayPatientMenu() {
        System.out.println("Please select an option:");
        System.out.println("e: Edit patient information");
        System.out.println("a: Add new clinical note");
        System.out.println("v: View/edit clinical notes");
        System.out.println("b: Go back to view all patients menu");
        printDivider();
    }

    // EFFECTS: processes the user's input in the patient menu; returns true if user is
    // to continue viewing the patient specific menu and false if going back to the 
    // view all patients menu
    public Boolean processPatientMenuCommands(String input, Patient patient) {
        switch (input) {
            case "e":
                editPatientInformation(patient);
                return true;
            case "a":
                addNewClinicalNote(patient);
                return true;
            case "v":
                viewEditClinicalNotes(patient);
                return true;
            case "b":
                return false;
            default:
                printDivider();
                System.out.println("Invalid option inputted. Please try again.");
                return true;
        }
    }

    // EFFECTS: displays and processes inputs for the editing patient information
    public void editPatientInformation(Patient patient) {
        boolean editPatientMenu = true;

        while (editPatientMenu) {
            displayEditPatientMenu();
            String input = this.scanner.nextLine();
            editPatientMenu = processEditPatientMenuCommands(input, patient);
        }
    }

    // EFFECTS: displays a list of commands that can be used to edit the patient's record;
    // user can remove the patient record, edit personal information, edit medical information,
    // or go back to the patient menu
    public void displayEditPatientMenu() {
        System.out.println("Please select an option:");
        System.out.println("r: Remove patient record");
        System.out.println("p: Edit personal information");
        System.out.println("o: Edit allergies/medications/medical conditions");
        System.out.println("b: Go back to view patient menu");
        printDivider();
    }

    // EFFECTS: processes the user's input in the patient menu; returns true if user is
    // to continue viewing the patient editing menu and false if going back to the 
    // view patient specific menu
    public Boolean processEditPatientMenuCommands(String input, Patient patient) {
        switch (input) {
            case "r":
                removePatient(patient);
                return false;
            case "p":
                editPersonalInformation(patient);
                return true;
            case "o":
                editMedicalInformation(patient);
                return true;
            case "b":
                return false;
            default:
                printDivider();
                System.out.println("Invalid option inputted. Please try again.");
                return true;
        }
    }

    // MODIFIES: this
    // EFFECTS: removes patient from clinic's list of patient records
    public void removePatient(Patient patient) {
        if (clinic.removePatient(patient)) {
            System.out.println("Patient successfully removed!");
        } else {
            System.out.println("Patient not found in records.");
        }
    }

    // EFFECTS: displays and processes inputs for editing the patient's personal information
    public void editPersonalInformation(Patient patient) {
        boolean editPersonalMenu = true;

        while (editPersonalMenu) {
            displayEditPersonalMenu();
            String input = this.scanner.nextLine();
            editPersonalMenu = processEditPersonalMenuCommands(input, patient);
        }
    }

    // EFFECTS: displays a list of commands that can be used to edit the patient's personal information;
    // user can edit the patient's first name, last name, date of birth (DOB) and age, personal health
    // number (PHN), or go back to the patient editing menu
    public void displayEditPersonalMenu() {
        System.out.println("Please select an option:");
        System.out.println("f: Edit first name");
        System.out.println("l: Edit last name");
        System.out.println("d: Edit date of birth (DOB) and age");
        System.out.println("p: Edit personal health number (PHN)");
        System.out.println("b: Go back to view patient editing menu");
        printDivider();
    }

    // EFFECTS: processes the user's input in patient's personal information menu; returns true if user
    // is to continue viewing patient's personal information menu and false if going back to view the 
    // patient editing menu
    public Boolean processEditPersonalMenuCommands(String input, Patient patient) {
        switch (input) {
            case "f":
                editFirstName(patient);
                return true;
            case "l":
                editLastName(patient);
                return true;
            case "d":
                editDateOfBirthAge(patient);
                return true;
            case "p":
                editPersonalHealthNumber(patient);
                return true;
            case "b":
                return false;
            default:
                printDivider();
                System.out.println("Invalid option inputted. Please try again.");
                return true;
        }
    }

    // MODIFIES: this
    // EFFECTS: allows user to edit the patient's first name
    public void editFirstName(Patient patient) {
        printDivider();
        System.out.println("Enter in a new first name: ");
        String input = this.scanner.nextLine();
        patient.setFirstName(input);

        printDivider();
        System.out.print("Patient's first name successfully changed to \"");
        System.out.print(patient.getFirstName());
        System.out.print("! \n");
    }

    // MODIFIES: this
    // EFFECTS: allows user to edit the patient's last name
    public void editLastName(Patient patient) {
        printDivider();
        System.out.println("Enter in a new last name: ");
        String input = this.scanner.nextLine();
        patient.setLastName(input);

        printDivider();
        System.out.print("Patient's last name successfully changed to \"");
        System.out.print(patient.getLastName());
        System.out.print("\"! \n");
    }

    // MODIFIES: this
    // EFFECTS: allows user to edit the patient's date of birth and age
    public void editDateOfBirthAge(Patient patient) {
        printDivider();
        System.out.println("Enter in a new date of birth (month) (e.g. 3 for March): ");
        int monthofBirth = this.scanner.nextInt();
        System.out.println("Enter in a new date of birth (day): ");
        int dayofBirth = this.scanner.nextInt();
        System.out.println("Enter in a new date of birth (year): ");
        int yearofBirth = this.scanner.nextInt();
        System.out.println("Enter in a new age: ");
        int age = this.scanner.nextInt();
        Date dateOfBirth = new Date(monthofBirth, dayofBirth, yearofBirth);
        patient.setDateOfBirthAndAge(dateOfBirth, age);
        
        printDivider();

        System.out.print("Patient's date of birth (DOB) and age successfully changed to \"");
        System.out.print(patient.getDateOfBirth().printDate() + " and ");
        System.out.print(patient.getAge());
        System.out.print("\"! \n");
    }

    // MODIFIES: this
    // EFFECTS: allows user to edit the patient's personal health number (PHN)
    public void editPersonalHealthNumber(Patient patient) {
        printDivider();
        System.out.println("Enter in a new 9-digit personal health number (PHN): ");
        long input = this.scanner.nextLong();
        printDivider();

        patient.setPersonalHealthNumber(input);
        System.out.print("Patient's personal health number (PHN) successfully changed to \"");
        System.out.print(patient.getPersonalHealthNumber());
        System.out.print("\"! \n");
    }

    // EFFECTS: displays and processes inputs for editing the patient's medical information
    // including allergies, medications, and medical conditions
    public void editMedicalInformation(Patient patient) {
        boolean editMedicalMenu = true;

        while (editMedicalMenu) {
            displayEditMedicalMenu();
            String input = this.scanner.nextLine();
            editMedicalMenu = processEditMedicalMenuCommands(input, patient);
        }
    }

    // EFFECTS: displays a list of commands that can be used to edit the patient's medical information;
    // user can edit the patient's allergies, medications, and medical conditions or go back to the 
    // patient editing menu
    public void displayEditMedicalMenu() {
        System.out.println("Please select an option:");
        System.out.println("a: Edit allergies");
        System.out.println("m: Edit medications");
        System.out.println("c: Edit medical conditions");
        System.out.println("b: Go back to view patient editing menu");
        printDivider();
    }

    // EFFECTS: processes the user's input in patient's medical information menu; returns true if user
    // is to continue viewing patient's medical information menu and false if going back to view the 
    // patient editing menu
    public Boolean processEditMedicalMenuCommands(String input, Patient patient) {
        switch (input) {
            case "a":
                editAllergyInformation(patient);
                return true;
            case "m":
                editMedicationInformation(patient);
                return true;
            case "c":
                editMedicalConditionInformation(patient);
                return true;
            case "b":
                return false;
            default:
                printDivider();
                System.out.println("Invalid option inputted. Please try again.");
                return true;
        }
    }

    // EFFECTS: displays and processes inputs for editing the patient's allergy information
    public void editAllergyInformation(Patient patient) {
        boolean editAllergyMenu = true;

        while (editAllergyMenu) {
            displayEditAllergyMenu();
            String input = this.scanner.nextLine();
            editAllergyMenu = processEditAllergyMenuCommands(input, patient);
        }
    }

    // EFFECTS: displays a list of commands that can be used to edit the patient's allergy information;
    // user can add, remove, or edit (replace) an existing allergy
    public void displayEditAllergyMenu() {
        System.out.println("Please select an option:");
        System.out.println("a: Add allergy");
        System.out.println("r: Remove allergy");
        System.out.println("e: Edit (replace) existing allergy");
        System.out.println("b: Go back to view patient medical information menu");
        printDivider();
    }

    // EFFECTS: processes the user's input in patient's allergy information menu; returns true if user
    // is to continue viewing patient's allergy information menu and false if going back to view the 
    // patient medical information menu
    public Boolean processEditAllergyMenuCommands(String input, Patient patient) {
        switch (input) {
            case "a":
                addAllergy(patient);
                return true;
            case "r":
                removeAllergy(patient);
                return true;
            case "e":
                replaceAllergy(patient);
                return true;
            case "b":
                return false;
            default:
                printDivider();
                System.out.println("Invalid option inputted. Please try again.");
                return true;
        }
    }

    // MODIFIES: this
    // EFFECTS: allows user to add a new allergy
    public void addAllergy(Patient patient) {
        printDivider();
        System.out.println("Add a new allergy: ");
        String input = this.scanner.nextLine();
        patient.addAllergy(input);

        printDivider();
        System.out.print("New allergy \"");
        System.out.print(input);
        System.out.print("\" added! \n");
    }

    // MODIFIES: this
    // EFFECTS: allows user to remove an allergy if in list of allergies
    public void removeAllergy(Patient patient) {
        printDivider();
        System.out.print("Current allergies: ");
        System.out.print(patient.printAllergies() + "\n");

        printDivider();
        System.out.println("Remove an allergy: ");
        String input = this.scanner.nextLine();
        printDivider();

        if (patient.removeAllergy(input)) {
            System.out.println("Allergy removed! \"");
        } else {
            System.out.println("Allergy not found in list! \"");
        }
    }

    // MODIFIES: this
    // EFFECTS: allows user to replace an allergy with a new allergy if in list of allergies
    public void replaceAllergy(Patient patient) {
        printDivider();
        System.out.print("Current allergies: ");
        System.out.print(patient.printAllergies() + "\n");

        printDivider();
        System.out.println("Replace this allergy: ");
        String oldAllergy = this.scanner.nextLine();
        System.out.println("New allergy: ");
        String newAllergy = this.scanner.nextLine();
        printDivider();

        if (patient.editAllergy(oldAllergy, newAllergy)) {
            System.out.println("Allergy replaced! \"");
        } else {
            System.out.println("Allergy not found in list! \"");
        }
    }

    // EFFECTS: displays and processes inputs for editing the patient's medication information
    public void editMedicationInformation(Patient patient) {
        boolean editMedicationMenu = true;

        while (editMedicationMenu) {
            displayEditMedicationMenu();
            String input = this.scanner.nextLine();
            editMedicationMenu = processEditMedicationMenuCommands(input, patient);
        }
    }

    // EFFECTS: displays a list of commands that can be used to edit the patient's medication information;
    // user can add, remove, or edit (replace) an existing medication
    public void displayEditMedicationMenu() {
        System.out.println("Please select an option:");
        System.out.println("a: Add medication");
        System.out.println("r: Remove medication");
        System.out.println("e: Edit (replace) existing medication");
        System.out.println("b: Go back to view patient medical information menu");
        printDivider();
    }

    // EFFECTS: processes the user's input in patient's medication information menu; returns true if user
    // is to continue viewing patient's medication information menu and false if going back to view the 
    // patient medical information menu
    public Boolean processEditMedicationMenuCommands(String input, Patient patient) {
        switch (input) {
            case "a":
                addMedication(patient);
                return true;
            case "r":
                removeMedication(patient);
                return true;
            case "e":
                replaceMedication(patient);
                return true;
            case "b":
                return false;
            default:
                printDivider();
                System.out.println("Invalid option inputted. Please try again.");
                return true;
        }
    }

    // MODIFIES: this
    // EFFECTS: allows user to add a new medication
    public void addMedication(Patient patient) {
        printDivider();
        System.out.println("Add a new medication: ");
        String input = this.scanner.nextLine();
        patient.addMedication(input);

        printDivider();
        System.out.print("New medication \"");
        System.out.print(input);
        System.out.print("\" added! \n");
    }

    // MODIFIES: this
    // EFFECTS: allows user to remove an allergy if in list of medications
    public void removeMedication(Patient patient) {
        printDivider();
        System.out.print("Current medications: ");
        System.out.print(patient.printMedications() + "\n");

        printDivider();
        System.out.println("Remove a medication: ");
        String input = this.scanner.nextLine();
        printDivider();

        if (patient.removeMedication(input)) {
            System.out.println("Medication removed! \"");
        } else {
            System.out.println("Medication not found in list! \"");
        }
    }

    // MODIFIES: this
    // EFFECTS: allows user to replace an medication with a new medication if in list of medications
    public void replaceMedication(Patient patient) {
        printDivider();
        System.out.print("Current medications: ");
        System.out.print(patient.printMedications() + "\n");

        printDivider();
        System.out.println("Replace this medication: ");
        String oldMedication = this.scanner.nextLine();
        System.out.println("New medication : ");
        String newMedication = this.scanner.nextLine();
        printDivider();

        if (patient.editAllergy(oldMedication, newMedication)) {
            System.out.println("Medication replaced! \"");
        } else {
            System.out.println("Medication not found in list! \"");
        }
    }

    // EFFECTS: displays and processes inputs for editing the patient's medical condition information
    public void editMedicalConditionInformation(Patient patient) {
        boolean editMedicalConditionMenu = true;

        while (editMedicalConditionMenu) {
            displayEditMedicalConditionMenu();
            String input = this.scanner.nextLine();
            editMedicalConditionMenu = processEditMedicalConditionMenuCommands(input, patient);
        }
    }

    // EFFECTS: displays a list of commands that can be used to edit the patient's medical condition information;
    // user can add, remove, or edit (replace) an existing medical condition
    public void displayEditMedicalConditionMenu() {
        System.out.println("Please select an option:");
        System.out.println("a: Add medical condition");
        System.out.println("r: Remove medical condition");
        System.out.println("e: Edit (replace) existing medical condition");
        System.out.println("b: Go back to view patient medical information menu");
        printDivider();
    }

    // EFFECTS: processes the user's input in patient's medical condition information menu; returns true if user
    // is to continue viewing patient's medical condition information menu and false if going back to view the 
    // patient medical information menu
    public Boolean processEditMedicalConditionMenuCommands(String input, Patient patient) {
        switch (input) {
            case "a":
                addMedicalCondition(patient);
                return true;
            case "r":
                removeMedicalCondition(patient);
                return true;
            case "e":
                replaceMedicalCondition(patient);
                return true;
            case "b":
                return false;
            default:
                printDivider();
                System.out.println("Invalid option inputted. Please try again.");
                return true;
        }
    }

    // MODIFIES: this
    // EFFECTS: allows user to add a new medical condition
    public void addMedicalCondition(Patient patient) {
        printDivider();
        System.out.println("Add a new medical condition: ");
        String input = this.scanner.nextLine();
        patient.addMedicalCondition(input);

        printDivider();
        System.out.print("New medical condition \"");
        System.out.print(input);
        System.out.print("\" added! \n");
    }

    // MODIFIES: this
    // EFFECTS: allows user to remove an medical condition if in list of medical conditions
    public void removeMedicalCondition(Patient patient) {
        printDivider();
        System.out.print("Current medical conditions: ");
        System.out.print(patient.printMedicalConditions() + "\n");

        printDivider();
        System.out.println("Remove an medical condition: ");
        String input = this.scanner.nextLine();
        printDivider();

        if (patient.removeMedicalCondition(input)) {
            System.out.println("Medical condition removed! \"");
        } else {
            System.out.println("Medical condition not found in list! \"");
        }
    }

    // MODIFIES: this
    // EFFECTS: allows user to replace an medical condition with a new medical condition if in 
    // list of medical conditions
    public void replaceMedicalCondition(Patient patient) {
        printDivider();
        System.out.print("Current medical conditions: ");
        System.out.print(patient.printMedicalConditions() + "\n");

        printDivider();
        System.out.println("Replace this medical condition: ");
        String oldMedicalCondition = this.scanner.nextLine();
        System.out.println("New medical condition: ");
        String newMedicalCondition = this.scanner.nextLine();
        printDivider();

        if (patient.editMedicalCondition(oldMedicalCondition, newMedicalCondition)) {
            System.out.println("Medical condition replaced! \"");
        } else {
            System.out.println("Medical condition not found in list! \"");
        }
    }

    // EFFECTS: processes the user's input in the patient menu
    public void addNewClinicalNote(Patient patient) {
        System.out.println("Add new clinical note");
    }

    // EFFECTS: processes the user's input in the patient menu
    public void viewEditClinicalNotes(Patient patient) {
        System.out.println("View/edit clinical notes");
    }

    // MODIFIES: this
    // EFFECTS: rename the clinic using input from the user and print out a success message
    public void renameClinic() {
        printDivider();
        System.out.println("Please enter a new name for the clinic:");
        String input = this.scanner.nextLine();
        clinic.setClinicName(input);

        printDivider();
        System.out.print("Clinic renamed to \"");
        System.out.print(clinic.getClinicName());
        System.out.print("\" successfully!\n");
        printDivider();
        System.out.print("*** Welcome to ");
        System.out.print(clinic.getClinicName());
        System.out.print("! *** \n");
    }

    // MODIFIES: this
    // EFFECTS: prints a closing message and marks the program and clinic as not running
    public void quitApplication() {
        printDivider();
        System.out.println("Thanks for using the Primary Care Clinic app!");
        this.isProgramRunning = false;
        this.isClinicRunning = false;
    }

    // EFFECTS: prints out a line of dashes to act as a divider
    private void printDivider() {
        System.out.println("---------------------------------------------------------------------------------");
    }
    
}
