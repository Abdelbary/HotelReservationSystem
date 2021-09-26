package api;

import Service.CustomerServiceClass;
import Service.ReservationService;
import mdole.Customer;
import mdole.IRoom;
import mdole.Reservation;

import java.util.Collection;
import java.util.Date;

public class HotelResource {
    public Customer getCustomer(String email){
        return CustomerServiceClass.getCustomer(email);
    }

    public void creatACustomer(String email,String firstName,String lastName){
        CustomerServiceClass.addCustomer(email,firstName,lastName);
    }

    public IRoom getRoom(String roomNumber){
        return ReservationService.getARoom(roomNumber);
    }

    /*
    public Reservation bookARoom(String CustomerEmail, IRoom room, Date checkInDate, Date checkOutDate){

    }
*/
    public Collection<Reservation> getCustomerReservation(String customerEmail){
        Customer customer = CustomerServiceClass.getCustomer(customerEmail);
        return ReservationService.getCustomerReservation(customer);
    }
/*
    public Collection<IRoom> findARoom(Date checkInDate,Date checkOutDate){

    }
*/
}

