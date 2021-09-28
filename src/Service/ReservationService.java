package Service;

import mdole.Customer;
import mdole.IRoom;
import mdole.Reservation;
import mdole.Room;

import java.util.*;

public class ReservationService {
    static List<IRoom> rooms = new ArrayList<IRoom>();
    static Map<String,IRoom> mapOfRooms = new HashMap<String,IRoom>();

    static List<Reservation> reservations = new ArrayList<Reservation>();


    public static void addRoom(IRoom room){
        rooms.add(room);
        mapOfRooms.put(room.getRoomNumber(),room);
    }

    public static IRoom getARoom(String roomId){
        return mapOfRooms.get(roomId);
    }

    static public Reservation reserveRoom(Customer customer, IRoom room, Date checkInDate , Date CheckOutDate){
        room.updateRoomState(false);
        Reservation reservation = new Reservation(customer,room,checkInDate,CheckOutDate);
        return reservation;
    }


    static public Collection<IRoom> getAllRooms(){
        return rooms;
    }

    public static Collection<IRoom> findRooms(Date checkInDate,Date checkOutDate){
        List<IRoom> availableRooms = new ArrayList<IRoom>();
        for(Reservation reservation : reservations){
            if(reservation.getCheckInDate().compareTo(checkInDate) <= 0  && reservation.getCheckOutDate().compareTo(checkOutDate) >= 0){
                availableRooms.add(reservation.getRoom());
            }
        }
        for(IRoom room : rooms){
            if(room.isFree()){
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }


    static public Collection<Reservation> getCustomerReservation(Customer customer){
        List<Reservation> customerReservations = new ArrayList<Reservation>();

        for(Reservation reservation : reservations) {
            if (reservation.getCustomer() == customer) {
                customerReservations.add(reservation);
            }
        }

        return customerReservations;
    }

    static public void PrintAllReservations(){
        for(Reservation reservation : reservations){
            System.out.println(reservation);
        }
    }
}
