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
    private HashSet <String> uniqueMedications =new HashSet<String>();



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
    /**
     * Adds an adherence record for the patient. Updates the number of missed
     * doses, the set of unique medications, and the adherence percentage.
     * Prints a message if the patient has missed three or more doses.
     * @param adherenceRecord the adherence record to add
     */
    public void addAdherenceRecord(AdherenceRecord adherenceRecord){
        adherenceRecords.put(adherenceRecord.isTaken(), adherenceRecord);
        uniqueMedications.add(adherenceRecord.getMedicationName());
        if(!adherenceRecord.isTaken()) {
            missedDoses++;
        }
        if(missedDoses>=3){
            System.out.println("DOSE ADHERENCE ALERT");
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
        if(firstName.isEmpty() || !firstName.matches("^[a-zA-Z'-]{1,50}$")) {
            throw new IllegalArgumentException("Invalid first name");
        }
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
        //Ensure last name is valid
        if(lastName.isEmpty() || !lastName.matches("^[a-zA-Z'-]{1,50}$")) {
            throw new IllegalArgumentException("Invalid last name");
        }
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
        //Ensure age is valid or throws exception
        if(age<0){
            throw new IllegalArgumentException("Invalid age");
        }
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
        //Ensure email is valid and has a valid format
        if(email==null|| email.isEmpty()||email.matches("^[^@]+@[^@]+\\.[^@]+$")){
            throw new IllegalArgumentException("Invalid email");
        }
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
        if(missedDoses<0){
            throw new IllegalArgumentException("Invalid missed doses");
        }
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