package model;

// A class representing a date with a month, day, and year
public class Date {

    private int month;
    private int day;
    private int year;

    // REQUIRES: 0 < year, 0 < month < 13, 0 < day < 32
    // EFFECTS: constructs a date with given month, day, and year
    public Date(int month, int day, int year) {
        // stub
    }

    // EFFECTS: returns date in the String format "MM/DD/YYYY"
    public String printDate() {
        // stub
    }

    // EFFECTS: adds prefix zero to single digit numbers, returning it in a String format
    public String addPrefix(int number) {
        // stub
    }

    // REQUIRES: 0 < month < 13
    // MODIFIES: this
    // EFFECTS: sets the month to the given month
    public int setMonth(int month) {
        // stub
    }

    // REQUIRES: 0 < day < 32
    // MODIFIES: this
    // EFFECTS: sets the day to the given day
    public int setDay(int day) {
        // stub
    }

    // Setters
    // REQUIRES: 0 < year
    // MODIFIES: this
    // EFFECTS: sets the year to the given year
    public int setYear(int year) {
        // stub
    }

    // Getters
    // EFFECTS: gets the month of the date
    public int getMonth() {
        // stub
    }

    // EFFECTS: gets the day of the date
    public int getDay() {
        // stub
    }

    // EFFECTS: gets the year of the date
    public int getYear() {
        // stub
    }
}
