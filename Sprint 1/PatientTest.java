import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PatientTest {

    @Test
    void addAdherenceRecordTest() {
        Patient newPatient = new Patient("Allison", "Dolores",23, "doallison25@gmail.com");
        newPatient.addMedication("Tylenol", "weekly", 1);
        AdherenceRecord record1 = new AdherenceRecord("Tylenol", 0);
        newPatient.addAdherenceRecord(record1);
        AdherenceRecord record2 = new AdherenceRecord("Tylenol", 0);
        newPatient.addAdherenceRecord(record2);
        AdherenceRecord record3 = new AdherenceRecord("Tylenol", 0);
        newPatient.addAdherenceRecord(record3);
        int numOfNotis = newPatient.getMissedDoseNotis();
        assertEquals(1,numOfNotis);
        assertEquals(3, newPatient.getMissedDoses());
    }

    @Test
    void getMissedDosesTest() {
        Patient newPatient = new Patient("Tom", "Ford",27, "tommyford39@gmail.com");
        newPatient.addMedication("Ibuprofen", "weekly", 1);
        AdherenceRecord record1 = new AdherenceRecord("Ibuprofen", 0);
        newPatient.addAdherenceRecord(record1);
        AdherenceRecord record2 = new AdherenceRecord("Ibuprofen", 0);
        newPatient.addAdherenceRecord(record2);
        int missedDoses = newPatient.getMissedDoses();
        assertEquals(2, missedDoses);
    }

    @Test
    void getAdherencePercentageTest() {
        Patient newPatient = new Patient("Mary", "Smith",21, "bloodymary89@gmail.com");
        newPatient.addMedication("Naloxone", "daily", 2);
        AdherenceRecord record1 = new AdherenceRecord("Naloxone", 2);
        newPatient.addAdherenceRecord(record1);
        double maryAdherencePercentage = newPatient.getAdherencePercentage();
        assertEquals(100.00, maryAdherencePercentage);

        Patient newPatient2 = new Patient("Larry", "Jones",35, "ljones@gmail.com");
        newPatient2.addMedication("Clonazepam", "daily", 3);
        AdherenceRecord record2 = new AdherenceRecord("Clonazepam", 1);
        newPatient2.addAdherenceRecord(record2);
        double larryAdherencePercentage = newPatient2.getAdherencePercentage();
        assertEquals(100.00, larryAdherencePercentage);
    }
}