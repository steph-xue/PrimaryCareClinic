# Primary Care Clinic Application 

## An Electronic Health Record (EHR) designed to optimize patient management for clinicians working in primary care clinics

### What will the application do and who will use it? ###
The application serves as an EHR system designed for primary care clinics, enabling efficient management of patients who are currently registered within the clinic. The application will allow for the tracking of patient information, including first and last name, date of birth (DOB), age, personal health number (PHN), allergies, current medications, and medical conditions. Patient data will be presented in a list view, sorted alphabetically by last name then first name. The application will also support the addition of new patients, removal of existing patients, and updates to patient details as required. Additionally, the application will enable users to record and review detailed clinical notes for patient visits, capturing information including the reason for the visit (title), visit details (body), healthcare provider seen, and date of the visit. Users can then view a complete history of all clinical notes for the patient.  

The primary care clinic application is designed to be used by medical professionals and administrative staff, including physicians, clinical pharmacists, nurses, and medical office assistants, to streamline patient management and ensure accurate record-keeping for the improved delivery of patient care.

### Why is this project of interest to you? ###
This project is of interest to me, as I have a background in the healthcare industry as a pharmacist and have worked at a variety of healthcare settings, including community pharmacies, hospitals, and ambulatory care clinics. As a result, I have a multitude of experiences working with different EHR systems, and have firsthand experience with the challenges faced by healthcare professionals in managing patient records. By creating an EHR application, I can apply my technical skills in computer science with my background domain knowledge in healthcare to develop a practical application for clinicians. Moreover, working on this application aligns with my goal to leverage technology to automate processes and enhance operational efficiency in the healthcare industry.

<br>

## User Stories
- As a user, I want to be able to create and add a name for the clinic
- As a user, I want to be able to rename the clinic
- As a user, I want to be able to add new patient records to the clinic and specify each patient's first and last name, DOB, age, PHN, allergies, current medications, and medication conditions
- As a user, I want to be able to view the list of all patient records in the clinic (sorted alphabetically by last name then first name)
- As a user, I want to be able to remove an existing patient record
- As a user, I want to be able to update information in a patient's record
  - Edit the name
  - Edit the DOB and age
  - Edit the PHN
  - Edit (add/remove) an allergy
  - Edit (add/remove) a medication
  - Edit (add/remove) a medical condition
- As a user, I want to be able to add new clinical notes to a patient's record with details such as the reason for visit (title), visit details (body), name of the healthcare provider seen, and the visit date (current date)
- As a user, I want to be able to view the history of all clinical notes for a specific patient (ordered in reverse chronological order)
- As a user, I want to be able to remove a clinical note from a patient's record
- As a user, I want to be able to update a clinical note from a patient's record
  - Edit the title
  - Edit the body
  - Edit the healthcare provider seen
- As a user, when I start the application, I want to be given the option to load my clinic data from file
- As a user, when I select the quit option from the application menu, I want to be reminded to save my clinic data to file and have the option to do so or not

<br>

## Instructions for End User
- You can "add an X to a Y" by clicking on the "add patient" button on the top navigation bar, inputing information in all of the fields (first name, last name, DOB, age, PHN, allergies, medications, medical conditions) and clicking on the "add new patient" button at the bottom of the screen
- You can generate the first required action related to the user story "adding multiple Xs to a Y" by double clicking on a patient in the "view all patients" screen to view all patient specific details. Here there are multiple subactions you can do related directly to the patient:
  - Remove the patient from the clinic by clicking on the red "remove patient" button at the bottom of the profile
  - Click on the grey edit buttons beside any of the patient data fields to update information
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by double clicking on a patient in the "view all patients" screen to view a specific patient profile and clicking on the purple "new clinical note" button to create a new clinical note to add to the patient's profile. Then you can fill out all of the information (title, provider, body) and click the "create new clinical note" button at the bottom to add it to the patient's profile
  - There are also other subactions you can do related to a patient's clinical notes including:
    - Remove the clinical note by clicking on the grey "remove note" button at the bottom of the note in the profile page
    - Click on the grey edit buttons beside any of the clinical note data fields to update information
- You can locate my visual component by opening up the application where the electronic health record (EHR) image and loading gif will be displayed on the loading screen for 10 seconds. There is also a patient profile image located at the top of each of the patient profiles
- You can save the state of my application by clicking on the "save & quit" button at the top right of the navigation bar. This will bring you to a screen where you can either click to "save & quit" or "quit without saving"
- You can reload the state of my application by click on the "load clinic data from file" button on the start screen upon reopening the application (or create a new clinic if you do not want to load from file)

<br>

## Phase 4: Task 2
### Event Log
- Fri Mar 28 01:54:35 PDT 2025 - Clinic name set to Medicare Plus
- Fri Mar 28 01:55:26 PDT 2025 - Patient Mya Cornell added to the clinic
- Fri Mar 28 01:56:04 PDT 2025 - Patient Ashley Davis added to the clinic
- Fri Mar 28 01:56:36 PDT 2025 - Patient Amira Garcia added to the clinic
- Fri Mar 28 01:56:55 PDT 2025 - Set first name of patient Amira Garcia to Freya
- Fri Mar 28 01:57:00 PDT 2025 - Set last name of patient Freya Garcia to Kwon
- Fri Mar 28 01:57:19 PDT 2025 - Set date of birth of patient Freya Kwon to 10/27/1987
- Fri Mar 28 01:57:23 PDT 2025 - Set age of patient Freya Kwon to 54
- Fri Mar 28 01:57:29 PDT 2025 - Set personal health number of patient Freya Kwon to 946846634
- Fri Mar 28 01:58:00 PDT 2025 - Added new clinical note for patient Freya Kwon
- Fri Mar 28 01:58:15 PDT 2025 - Clinical note title set to Heartburn
- Fri Mar 28 01:58:25 PDT 2025 - Clinical note provider set to Dr. Nguyen
- Fri Mar 28 01:58:34 PDT 2025 - Clinical note body set to Patient presents with frequent heartburn and acid regurgitation
- Fri Mar 28 01:58:39 PDT 2025 - Removed clinical note for patient Freya Kwon
- Fri Mar 28 01:58:40 PDT 2025 - Patient Freya Kwon removed from the clinic
- Fri Mar 28 01:59:14 PDT 2025 - Clinic name set to Careplus


## Phase 4: Task 3
### Potential Design Improvements
There are some things I would refactor to improve the design of my project if I had more time:
- I would refactor the PrimaryCareClinicApp class of the UI package that runs the functionality of the command-line user interface by breaking it into smaller, seperate classes to ensure these classes align with the Single Responsibility Principle. This will ensure each class handles one distinct task (e.g. user input, command parsing, output display) to improve cohesion, readability and maintainability.
- For classes in the the model package, I would also try to abstract duplicated code into new methods. For example, I would abstract out code for addAllergy, addMedication, and addMedicalCondition into a separate method to minimize code dupication. Abstracting out duplicated code improves code reusability and enhances maintainbility, as updates to shared logic only need to be made in one place, reducing the risk of inconsistencies.
- I would refactor the NavigationBarUI class of the UI package so that instead of having multiple UI screen classes of the graphical user interface instantiating NavigationBarUI, I would just have it instantiated once for the entire application in the MainUI class. This would allow for a centralized control of navigation and prevent redundant instances, ensuring consistent behaviour across all UI screens.
