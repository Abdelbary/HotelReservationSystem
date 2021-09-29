package api;

import Service.CustomerService;
import Service.ReservationService;
import mdole.Customer;
import mdole.IRoom;

import java.util.Collection;

public class AdminResource {
    public Customer getCustomer(String email){
        return CustomerService.getCustomer(email);
    }

    public void addRoom(IRoom room){
        ReservationService.addRoom(room);
    }

    public Collection<IRoom> getAllRooms(){
        return ReservationService.getAllRooms();
    }

    public Collection<Customer> getAllCustomers(){
        return CustomerService.getAllCustomers();
    }

    public void displayAllReservations(){
        ReservationService.printAllReservations();
    }

    public boolean IsRoomReserved(String roomId){
        IRoom room = ReservationService.getARoom(roomId);
        if(room !=null){
            return true;
        }
        else{
            return false;
        }
    }
}
