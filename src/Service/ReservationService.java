package Service;

import mdole.Customer;
import mdole.IRoom;
import mdole.Reservation;
import mdole.Room;

import java.util.*;

public final class ReservationService {
    private static ReservationService INSTANCE;

    private ReservationService() {
    }

    public static ReservationService getInstance(){
        if(INSTANCE == null){
            INSTANCE = new ReservationService();
        }

        return INSTANCE;
    }

    private  static List<IRoom> rooms = new ArrayList<IRoom>();
    private static Map<String,IRoom> mapOfRooms = new HashMap<String,IRoom>();

    private static List<Reservation> reservations = new ArrayList<Reservation>();


    public static void addRoom(IRoom room){
        rooms.add(room);
        mapOfRooms.put(room.getRoomNumber(),room);
    }

    public static IRoom getARoom(String roomId){
        return mapOfRooms.get(roomId);
    }

    public  static  Reservation reserveRoom(Customer customer, IRoom room, Date checkInDate , Date CheckOutDate){
        room.updateRoomState(false);
        Reservation reservation = new Reservation(customer,room,checkInDate,CheckOutDate);
        reservations.add(reservation);
        return reservation;
    }


    public static  Collection<IRoom> getAllRooms(){
        return rooms;
    }

    public static Collection<IRoom>  findRooms(Date checkInDate,Date checkOutDate){
        List<IRoom> availableRooms = new ArrayList<IRoom>();
        for(Reservation reservation : reservations){
            if(reservation.getCheckInDate().after(checkOutDate)  || reservation.getCheckOutDate().before(checkInDate) ){
                availableRooms.add(reservation.getRoom());
            }
        }
        int counter =0 ;
        while(counter < rooms.size()){
            if(rooms.get(counter).isFree()){
                availableRooms.add(rooms.get(counter));
            }
            counter++;
        }

        return availableRooms;
    }


    public static  Collection<Reservation> getCustomerReservation(Customer customer){
        List<Reservation> customerReservations = new ArrayList<Reservation>();
        for(Reservation reservation : reservations) {
            if (reservation.getCustomer().equals(customer)) {
                customerReservations.add(reservation);
            }
        }

        return customerReservations;
    }

    public static  void printAllReservations(){
        for(Reservation reservation : reservations){
            System.out.println(reservation);
        }
    }
}
