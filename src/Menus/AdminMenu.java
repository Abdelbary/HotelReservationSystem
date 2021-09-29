package Menus;

import api.AdminResource;
import mdole.Customer;
import mdole.IRoom;
import mdole.Room;
import mdole.RoomType;

import java.util.List;
import java.util.Scanner;

public class AdminMenu {
    private static AdminResource adminResource = new AdminResource();
    public static void roomCreation() {
        Scanner scan = new Scanner(System.in);
        String roomNumber;
        Double price;
        RoomType roomType;
        while(true){
            System.out.println("please add room number");
            roomNumber = scan.nextLine();


            if(adminResource.IsRoomReserved(roomNumber)){
                System.out.println("Room Number already taken");
                continue;
            }else{
                break;
            }
        }
        while(true){
            System.out.println("Please entre price per night");
            String input = scan.next();
            try {
                price = Double.parseDouble(input);
                break;
            }catch (Exception ex){
                System.out.println("Please entre a valid number");
            }
        }

        while(true){
            System.out.println("Entre room type 1 for single 2 for double");
            String input = scan.next();
            try {
                Integer roomNum = Integer.parseInt(input);
                roomType = (roomNum == 1)? RoomType.SINGLE : RoomType.DOUBLE;
                if(roomNum != 1 && roomNum != 2){
                    throw new IllegalArgumentException("");
                }
                break;
            }catch (Exception ex){
                System.out.println("Please entre a valid number");
            }
        }
        IRoom room = new Room(roomNumber,price,roomType);
        adminResource.addRoom(room);
    }

    public static void printAllCustomers() {
        List<Customer> customers = (List<Customer>) adminResource.getAllCustomers();
        for(Customer customer : customers){
            System.out.println(customer);
        }
    }

    public static void printAllRooms() {
        List<IRoom> rooms = (List<IRoom>) adminResource.getAllRooms();
        for(IRoom room : rooms){
            System.out.println(room);
        }
    }

    public static void displayAllReservations() {
        adminResource.displayAllReservations();
    }

    public static void roomCreationRoutine() {
        Scanner scan = new Scanner(System.in);
        while(true){
            AdminMenu.roomCreation();
            System.out.println("Would you like to add another room? y/n");
            String option = scan.nextLine();
            if(option.compareTo("y") != 0 && option.compareTo("n") != 0){
                System.out.println("please entre y/n");
                continue;
            }
            else{
                if(option.compareTo("y") == 0){
                    continue;
                }else{
                    break;
                }
            }
        }
    }
}
