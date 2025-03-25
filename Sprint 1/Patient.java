import java.util.*;

public class Patient{
    //Instance vars
    private String firstName;
    private String lastName;
    private int age;
    private String email;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}