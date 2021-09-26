package Service;

import mdole.Customer;

import java.util.*;

public class CustomerServiceClass {
    static List<Customer> customers = new ArrayList<Customer>();
    static Map<String,Customer> mapOfCustomers = new HashMap<String,Customer>();

    public static void addCustomer(String email, String firstName, String lastName){

        try{
            Customer customer = new Customer(email,firstName,lastName);
            customers.add(customer);
            mapOfCustomers.put(email,customer);
        }
        catch (Exception ex){
            throw new IllegalArgumentException("Please Provide vaild email");
        }

    }
    static public Customer getCustomer(String customerEmail){
        return mapOfCustomers.get(customerEmail);
    }

    static public Collection<Customer> getAllCustomers(){
        return customers;
    }
}
