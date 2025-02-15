package ui;

import model.Clinic;
import model.ClinicalNote;
import model.Date;
import model.Patient;

import java.util.ArrayList;
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
    // EFFECTS: initializes the application with the starting values
    // where the program is running but the clinic is not yet running
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

    // EFFECTS: displays a list of commands that can be used in the start menu
    // when no clinic has been created yet
    public void displayStartMenu() {
        System.out.println("Please select an option:");
        System.out.println("a: Create a new clinic");
        System.out.println("q: Exit the application");
        printDivider();
    }


    // EFFECTS: processes the user's input in the start menu when no clinic
    // clinic has been created yet
    public void processStartMenuCommands(String input) {
        switch (input) {
            case "a":
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
    // EFFECTS: creates new clinic with name from the user and sets the clinic
    // as running; prints out a success message
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

    // EFFECTS: displays a list of commands that can be used in the main menu
    public void displayMainMenu() {
        System.out.println("Please select an option:");
        System.out.println("v: View all patient records");
        System.out.println("a: Add a new patient record");
        System.out.println("r: Rename the clinic");
        System.out.println("q: Exit the application");
        printDivider();
    }

    // EFFECTS: processes the user's input in the main menu
    public void processMainMenuCommands(String input) {
        switch (input) {
            case "v":
                viewAllPatientRecords();
                break;
            case "a":
                addNewPatientRecord();
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

    // EFFECTS: view all patient records and patient specific menu
    public void viewAllPatientRecords() {
        System.out.println("Viewing records");
        printDivider();
    }

    // EFFECTS: add new patient to patient record list using input from the user
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
        Date dateOfBirth = new Date(monthofBirth, dayofBirth, yearofBirth);
        Patient newPatient = new Patient(firstName, lastName, dateOfBirth, age, personalHealthNumber);
        clinic.addPatient(newPatient);
        newPatientConfirmationMessage(newPatient);
        addAdditionalInformation(newPatient);
    }

    // EFFECTS: prints out a new patient confirmation success message
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

    // EFFECTS: add additional information from the patient including allergies,
    // medications, and medical conditions 
    public void addAdditionalInformation(Patient newPatient) {
        printDivider();
        System.out.println("Adding allergy, medication, and medical condition information for new patient");

        addAllergies(newPatient);
        addMedications(newPatient);
        addMedicalConditions(newPatient);

        printDivider();
        System.out.println("Successfully added the following for the new patient: ");
        System.out.print("Allergies: ");
        System.out.print(newPatient.printAllergies() + "\n");
        System.out.print("Medications: ");
        System.out.print(newPatient.printMedications() + "\n");
        System.out.print("Medical conditions: ");
        System.out.print(newPatient.printMedicalConditions() + "\n");
    }

    // EFFECTS: add allergies for patient using user input
    public void addAllergies(Patient newPatient) {
        boolean allergiesInputed = false;

        while (!allergiesInputed) {
            printDivider();
            System.out.println("Please select an option to input allergies:");
            System.out.println("a: Add an allergy");
            System.out.println("n: No more known allergies");
            String input = this.scanner.nextLine();

            switch (input) {
                case "a":
                    printDivider();
                    System.out.println("Add allergy: ");
                    String allergy = this.scanner.nextLine();
                    newPatient.addAllergy(allergy);
                    break;
                case "n":
                    allergiesInputed = true;
                    break;
                default:
                    printDivider();
                    System.out.println("Invalid option inputted. Please try again.");
            }
        }
    }

    // EFFECTS: add medications for patient using user input
    public void addMedications(Patient newPatient) {
        boolean medicationsInputed = false;

        while (!medicationsInputed) {
            printDivider();
            System.out.println("Please select an option to input medications:");
            System.out.println("a: Add a medication");
            System.out.println("n: No more known medications");
            String input = this.scanner.nextLine();

            switch (input) {
                case "a":   
                    printDivider();
                    System.out.println("Add medication: ");
                    String medication = this.scanner.nextLine();
                    newPatient.addMedication(medication);
                    break;
                case "n":
                    medicationsInputed = true;
                    break;
                default:
                    printDivider();
                    System.out.println("Invalid option inputted. Please try again.");
            }
        }
    }

    // EFFECTS: add allergies for patient using user input
    public void addMedicalConditions(Patient newPatient) {
        boolean medicalConditionsInputed = false;

        while (!medicalConditionsInputed) {
            printDivider();
            System.out.println("Please select an option to input medical conditions:");
            System.out.println("a: Add a medical condition");
            System.out.println("n: No more known medical conditions");
            String input = this.scanner.nextLine();

            switch (input) {
                case "a":
                    printDivider();
                    System.out.println("Add medical condition: ");
                    String medicalCondition = this.scanner.nextLine();
                    newPatient.addMedicalCondition(medicalCondition);
                    break;
                case "n":
                    medicalConditionsInputed = true;
                    break;
                default:
                    printDivider();
                    System.out.println("Invalid option inputted. Please try again.");
            }
        }
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
        System.out.println("-------------------------------------------------------------------------");
    }
    
}
