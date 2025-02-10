package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDate {

    private Date date1;
    private Date date2;

    @BeforeEach
    public void runBefore() {
        date1 = new Date(8, 28, 1986);
        date2 = new Date(10, 4, 2022);
    }

    @Test
    public void constructorTest() {
        assertEquals(8, date1.getMonth());
        assertEquals(28, date1.getDay());
        assertEquals(1986, date1.getYear());

        assertEquals(10, date2.getMonth());
        assertEquals(4, date2.getDay());
        assertEquals(2022, date2.getYear());
    }

    @Test
    public void printDateTest() {
        assertEquals("08/28/1986", date1.printDate());
        assertEquals("10/04/2022", date2.printDate());
    }

    @Test
    public void addPrefixTest() {
        assertEquals("01", date1.addPrefix(1));
        assertEquals("04", date1.addPrefix(4));
        assertEquals("10", date1.addPrefix(10));
        assertEquals("12", date1.addPrefix(12));
    }

    @Test
    public void setMonthTest() {
        assertEquals(8, date1.getMonth());
        date1.setMonth(12);
        assertEquals(12, date1.getMonth());
    }

    @Test
    public void setDayTest() {
        assertEquals(28, date1.getDay());
        date1.setDay(7);
        assertEquals(7, date1.getDay());
    }

    @Test
    public void setYearTest() {
        assertEquals(1986, date1.getYear());
        date1.setYear(2011);
        assertEquals(2011, date1.getYear());
    }
    
}
