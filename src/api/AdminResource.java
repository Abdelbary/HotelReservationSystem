package api;

import Service.CustomerServiceClass;
import Service.ReservationService;
import mdole.Customer;
import mdole.IRoom;

import java.util.Collection;

public class AdminResource {
    public Customer getCustomer(String email){
        return CustomerServiceClass.getCustomer(email);
    }

    public void addRoom(IRoom room){
        ReservationService.addRoom(room);
    }

    public Collection<IRoom> getAllRooms(){
        return ReservationService.getAllRooms();
    }

    public Collection<Customer> getAllCustomers(){
        return CustomerServiceClass.getAllCustomers();
    }

    public void displayAllReservations(){
        ReservationService.PrintAllReservations();
    }
}
