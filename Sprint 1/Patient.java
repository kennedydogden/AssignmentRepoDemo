//Imports
import com.sun.xml.internal.ws.util.StringUtils;
import javafx.util.Pair;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.sql.Connection;

public class Patient{
    //Instance vars
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    //Record of patient's adherence
    private ArrayList<AdherenceRecord> adherenceRecords= new ArrayList<>();
    private int missedDoses=0;
    private double adherencePercentage;
    //How many times the patient has been notified of too many missed doses (3 or more)
    private int missedDoseNotis;
    private MedicationSchedule schedule;
    //Connection to mysql database
    private Connection db = initializeDatabase();



    //Constructors
    public Patient(String firstName, String lastName, int age){
        this.firstName=firstName;
        this.lastName=lastName;
        this.age=age;
        schedule=new MedicationSchedule();
    }
    public Patient(String firstName,String lastName, int age, String email){
        this.firstName=firstName;
        this.lastName=lastName;
        this.age=age;
        this.email=email;
        schedule = new MedicationSchedule();

    }

    /**
     * Initializes the database connection.
     * Must have a file called application.properties in .idea (or change filepath)
     * Establishes a connection to the database, and executes a USE statement to select the adherencedb database.
     * @return the database connection
     * @throws RuntimeException if there is a problem with the application properties file/doesn't exist, or if there is a problem connecting to the database
     */
    public static Connection initializeDatabase(){
        Properties properties = new Properties();
        try{
            properties.load(new FileInputStream(".idea/application.properties"));
        } catch (IOException e) {
            throw new RuntimeException("Problem with application properties file/doesn't exist");
        }
        try{
            Connection connection = DriverManager.getConnection(properties.getProperty("url"),properties.getProperty("user"), properties.getProperty("sqlpwd"));
            Statement statement = connection.createStatement();
            statement.execute("USE adherencedb");
            return connection;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Adds an adherence record for the patient. Updates the number of missed
     * doses and the adherence percentage.
     * Prints a message if the patient has missed three or more doses.
     * @param adherenceRecord the adherence record to add
     */
    public void addAdherenceRecord(AdherenceRecord adherenceRecord){
        adherenceRecords.add(adherenceRecord);
        if(!adherenceRecord.isTaken()) {
            missedDoses++;
        }
        if(missedDoses>=3){
            System.out.println("DOSE ADHERENCE ALERT");
            //Counts how many times a patient has been notified
            missedDoseNotis++;
        }
        //Calculate adherence percentage and format (2 decimal places)
        adherencePercentage=(double)(adherenceRecords.size()-missedDoses)/adherenceRecords.size()*100;
        adherencePercentage=Double.parseDouble(String.format("%.2f", adherencePercentage));

    }
    /**
     * Adds a medication to the patient's schedule.
     * @param medicationName the name of the medication
     * @param timing the timing at which the medication should be taken
     * @param dosage the dosage of the medication
     */
    public void addMedication(String medicationName, String timing, int dosage){
        schedule.addMedication(medicationName,timing,dosage);
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
        //Ensures first name is valid and follows and valid format (only letters and hyphens)
        if(firstName.isEmpty()|| !firstName.matches("^[a-zA-Z'-]{1,50}$")) {
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
        //Ensure last name is valid and follows valid format (only letters and hyphens)
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
        //Ensure email is valid and has a simple valid format
        // (one char[isn't @] then a @ then at least one char[isn't @] then a . then at least one char[isn't @])
        if(email==null|| email.isEmpty()||!email.matches("^[^@]+@[^@]+\\.[^@]+$")){
            throw new IllegalArgumentException("Invalid email");
        }
        this.email = email;
    }

    /**
     * Gets the adherence records of the patient
     * @return the adherence records
     */
    public ArrayList<AdherenceRecord> getAdherenceRecords() {
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

    /**
     * Gets the number of missed dose notifications that have been sent to the patient
     * @return the number of missed dose notifications
     */
    public int getMissedDoseNotis() {
        return missedDoseNotis;
    }

    /**
     * Retrieves the medication schedule for the patient.
     * @return Patient's medication schedule
     */
    public MedicationSchedule getSchedule() {
        return schedule;
    }
}

class MedicationSchedule{

    //Map to hold medication name with timing and dosage in a Pair
    private HashMap<String,Pair<Frequency,Integer>> medications= new HashMap<>();

    //Initializes Medicine Schedule with one medication
    public MedicationSchedule(String medicationName, String timing, int dosage){
        this.addMedication(medicationName,timing,dosage);
    }
    //Initializes Medicine Schedule with no medications
    public MedicationSchedule(){
        this.medications= new HashMap<>();
    }

    /**
     * Adds a medication to the medication schedule. The medication name must
     * consist only of letters, and the timing must be either "daily" or "weekly". The dosage
     * must be a positive integer.
     * @param medicationName the name of the medication
     * @param timing how often the medicine is to be taken (daily or weekly)
     * @param dosage the dosage of the medication
     * @throws IllegalArgumentException if the medication name, timing, or dosage is invalid
     */
    public void addMedication(String medicationName, String timing, int dosage) {
        if (medicationName == null || medicationName.isEmpty() || !medicationName.matches("[a-zA-Z\\s]+")) {
            throw new IllegalArgumentException("Invalid medication name");
        }
        Frequency frequency = null;
        if (timing.equalsIgnoreCase("daily") || timing.equalsIgnoreCase("weekly")) {
            frequency = Frequency.valueOf(timing.toUpperCase());
        }
        if (timing.isEmpty() || !timing.matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("Invalid timing format or is empty");
        }
        if (dosage < 0) {
            throw new IllegalArgumentException("Invalid dosage");
        }
        //Add medication using params
        medications.put(medicationName, new Pair<>(frequency, dosage));
    }

}
enum Frequency{
    DAILY,WEEKLY;
    /**
     * Returns the name of the frequency with the first letter capitalized.
     * @return the capitalized name of the frequency
     */
    public String toString(){
        return StringUtils.capitalize(this.name());
    }
}