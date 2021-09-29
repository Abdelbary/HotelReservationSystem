package api;

import Service.CustomerService;
import Service.ReservationService;
import mdole.*;


import java.util.*;

public class HotelResource {

    public static Customer getCustomer(String email){
        return CustomerService.getCustomer(email);
    }


    public static void creatACustomer(String email,String firstName,String lastName){
        CustomerService.addCustomer(email,firstName,lastName);
    }


    public static IRoom getRoom(String roomNumber){
        return ReservationService.getARoom(roomNumber);
    }



    public static Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate){
        Customer customer = HotelResource.getCustomer(customerEmail);
        return ReservationService.reserveRoom(customer,room,checkInDate,checkOutDate);
    }


    public static Collection<Reservation> getCustomerReservation(String customerEmail){
        Customer customer = HotelResource.getCustomer(customerEmail);
        return ReservationService.getCustomerReservation(customer);
    }


    public  static Collection<IRoom> findARoom(Date checkInDate, Date checkOutDate){
        List<IRoom> reservationList = (List<IRoom>) ReservationService.findRooms(checkInDate,checkOutDate);

        return reservationList;
    }

}

