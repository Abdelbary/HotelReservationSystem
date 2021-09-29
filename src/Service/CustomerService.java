package Service;

import mdole.Customer;

import java.util.*;

public class CustomerService {
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
    public static  Customer getCustomer(String customerEmail){
        return mapOfCustomers.get(customerEmail);
    }

    public static  Collection<Customer> getAllCustomers(){
        return customers;
    }
}
