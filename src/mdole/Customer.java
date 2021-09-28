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

        if(!isValidEmail(email)){
            throw new IllegalArgumentException("please entre a valid email address ex someone@domain.com");
        }else{
            this.email = email;
        }
    }
    public static boolean isValidEmail(String email){
        return pattern.matcher(email).matches();
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
