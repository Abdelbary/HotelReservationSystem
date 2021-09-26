package mdole;

import java.util.regex.Pattern;

public class Customer {
    String firstName;
    String lastName;
    String email;
    static String emailFormat =  "^(.+)@(.+).(.+)";
    static Pattern pattern = Pattern.compile(emailFormat);

    public Customer(String email, String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;

        if(!pattern.matcher(email).matches()){
            throw new IllegalArgumentException("please entre a valid email address ex someone@domain.com");
        }
    }

    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
