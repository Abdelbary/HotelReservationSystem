package mdole;

import java.util.regex.Pattern;

public class Customer {
    private String firstName;
    private String lastName;
    private String email;
    private static final String emailFormat = "^(.+)@(.+).(.+)";
    private static final Pattern pattern = Pattern.compile(emailFormat);

    public Customer(String email, String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;

        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("please entre a valid email address ex someone@domain.com");
        } else {
            this.email = email;
        }
    }

    public static boolean isValidEmail(String email) {
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
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    // Overriding equals() to compare two Complex objects
    @Override
    public boolean equals(Object o) {

        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Customer)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members

        Customer customer = (Customer) o;

        // Compare the data members and return accordingly
        return (this.email.compareTo(customer.email) == 0);
    }

}