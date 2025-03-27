import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.*;

class AdherenceRecordTest {

    @Test
    void adherenceRecordTest() {
        AdherenceRecord record1 = new AdherenceRecord("Tylenol", 1);
        boolean isTaken = record1.isTaken();
        assertEquals(true, isTaken);
        AdherenceRecord record2 = new AdherenceRecord("Ibuprofen", 0);
        boolean istaken = record2.isTaken();
        assertEquals(false, istaken);
        LocalDateTime date = record1.getTimeTaken();
        assertNotEquals(null, date);
    }

    @Test
    void setMedicationNameTest() {
        AdherenceRecord record1 = new AdherenceRecord("Naloxone", 2);
        assertThrows(IllegalArgumentException.class, () -> {
            record1.setMedicationName("Naloxone3");
        });
    }
}