import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Represents a single instance of taking a medication (or if not taken)
 */
public class AdherenceRecord {
    /**
     * medication name: name of medication
     * timeTaken: time medication was taken or time when it was not taken
     * dosage: amount of medication in mg. If zero, medication was not taken
     * taken: true if medication was taken, false otherwise (based on dosage)
     */
    private String medicationName;
    private LocalDateTime timeTaken;
    private int dosage;
    private boolean taken;

    /**
     * Create a new AdherenceRecord. Records the current time as the time medication was taken
     * @param medicationName name of medication
     * @param dosage amount of medication; if zero, medication was not taken
     */
    public AdherenceRecord(String medicationName, int dosage) {
        //Ensure dosage is non-negative
        if(dosage<0) {
            throw new IllegalArgumentException("Invalid dosage");
        }

        this.setMedicationName(medicationName);
        //Record current time without seconds
        this.setTimeTaken(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        this.setDosage(dosage);
        setTaken(dosage!=0);
    }

    /**
     * Get the name of the medication
     * @return name of medication
     */
    public String getMedicationName() {
        return medicationName;
    }

    /**
     * Sets the name of the medication
     * @param medicationName the name of the medication to set
     */
    public void setMedicationName(String medicationName) {
        if(!medicationName.matches("[a-zA-Z]+") || medicationName==null||medicationName.isEmpty()) {
            throw new IllegalArgumentException("Invalid medication name (only letters and spaces). Data not saved");
        }
        this.medicationName = medicationName;

    }

    /**
     * Gets the time at which the medication was taken
     * @return the time at which the medication was taken
     */
    public LocalDateTime getTimeTaken() {
        return timeTaken;
    }

    /**
     * Sets the time at which the medication was taken
     * @param timeTaken the time at which the medication was taken
     */
    public void setTimeTaken(LocalDateTime timeTaken) {
        //Removes seconds from timeTaken
        this.timeTaken = timeTaken.truncatedTo(ChronoUnit.SECONDS);
    }

    /**
     * Gets the dosage of the medication.
     * @return the amount of medication in mg
     *
     */
    public int getDosage() {
        return dosage;
    }

    /**
     * Sets the dosage of the medication.
     * @param dosage the amount of medication in mg
     */
    public void setDosage(int dosage) {
        this.dosage = dosage;
        if(dosage<0) {
            throw new IllegalArgumentException("Invalid dosage. Dosage not saved");
        }
        taken=dosage!=0;
    }

    /**
     * Determines whether the medication has been taken.
     * @return true if the medication has been taken; false otherwise
     */
    public boolean isTaken() {
        return taken;
    }

    /**
     * Sets whether the medication has been taken.
     * @param taken true if the medication was taken, false otherwise
     */
    public void setTaken(boolean taken) {
        this.taken = taken;
    }
}
