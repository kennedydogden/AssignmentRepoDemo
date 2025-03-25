import java.text.DecimalFormat;
import java.util.*;

public class Patient{
    //Instance vars
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private Map<Boolean,AdherenceRecord> adherenceRecords=new HashMap<Boolean,AdherenceRecord>();
    private int missedDoses=0;
    private double adherencePercentage;


    //Constructors
    public Patient(String firstName, String lastName, int age){
        this.firstName=firstName;
        this.lastName=lastName;
        this.age=age;

    }
    public Patient(String firstName,String lastName, int age, String email){
        this.firstName=firstName;
        this.lastName=lastName;
        this.age=age;
        this.email=email;
    }
    public void addAdherenceRecord(AdherenceRecord adherenceRecord){
        adherenceRecords.put(adherenceRecord.isTaken(), adherenceRecord);
        if(!adherenceRecord.isTaken()) {
            missedDoses++;
        }
        adherencePercentage=(double)(adherenceRecords.size()-missedDoses)/adherenceRecords.size();
        DecimalFormat df = new DecimalFormat("##.##");
        adherencePercentage=Double.parseDouble(df.format(adherencePercentage));
    }

    /**
     * Gets the first name of the patient
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the patient
     * @param firstName the new first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the patient
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the patient
     * @param lastName the new last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the age of the patient
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age of the patient
     * @param age the new age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Gets the email of the patient
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the patient
     * @param email the new email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the adherence records of the patient
     * @return the adherence records
     */
    public Map<Boolean, AdherenceRecord> getAdherenceRecords() {
        return adherenceRecords;
    }


    /**
     * Gets the number of missed doses for the patient
     * @return the number of missed doses
     */
    public int getMissedDoses() {
        return missedDoses;
    }

    /**
     * Sets the number of missed doses for the patient
     * @param missedDoses the new number of missed doses
     */
    public void setMissedDoses(int missedDoses) {
        this.missedDoses = missedDoses;
    }

    /**
     * Gets the adherence percentage of the patient
     * @return the adherence percentage
     */
    public double getAdherencePercentage() {
        return adherencePercentage;
    }
}