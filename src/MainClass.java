import Service.CustomerServiceClass;
import Service.ReservationService;
import api.AdminResource;
import api.HotelResource;
import mdole.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MainClass {
    private static String[] MainMenuOptions = {
            "---------------------------------------------------",
            "1. Find and reserve a room",
            "2. See my Reservations",
            "3. Create an Account",
            "4. Admin.",
            "5. Exist",
            "---------------------------------------------------"
    };

    private static String[] AdminMenuOptions = {
            "---------------------------------------------------",
            "1. see all customers",
            "2. See all rooms",
            "3. see all reservations",
            "4. add room",
            "5. back to main menu",
            "---------------------------------------------------"
    };

    static AdminResource adminResource = new AdminResource();
    static HotelResource hotelResource = new HotelResource();

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        boolean run = true;
        while(run){
            for(String option : MainMenuOptions){
                System.out.println(option);
            }
            System.out.println("Please Select a number for the menu option");
            try{
                String userInput = scan.nextLine();
                switch (userInput){
                    case "1":
                        findAndReserveRotuine();
                        break;
                    case "2":
                        getCustomerReservationRotuine();
                        break;
                    case "3":
                        createNewCustomer(scan);
                        break;
                    case "4":
                        adminFlow(scan);
                        break;
                    case "5":
                        run = false;
                        break;
                    default:
                        System.out.println("Please enter a valid number between 1 and 5");
                        continue;
                }
            }
            catch (Exception ex){

            }
        }

    }

    private static void getCustomerReservationRotuine() {
        Scanner scan = new Scanner(System.in);
        String email;
        while(true){
            System.out.println("Please Entre your email.");
            email = scan.nextLine();
            if(Customer.isValidEmail(email)){
                break;
            }else{
                System.out.print("Please entre a valid email formate..someone@domain.com");
            }
        }
        Customer customer = CustomerServiceClass.getCustomer(email);
        List<Reservation> reservations = (List<Reservation>) ReservationService.getCustomerReservation(customer);

        for(Reservation reservation : reservations){
            System.out.println(reservation);
        }

    }

    private static void findAndReserveRotuine() {
        Scanner scan = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
        sdf.setLenient(false);

        Date checkInDate, checkOutDate;
        boolean run = true;
        while(run){
            try{
                System.out.println("Entre check in Date example 01/02/2020");
                String input1 = scan.nextLine();
                String input2 = scan.nextLine();
                sdf.parse(input1);
                sdf.parse(input2);
                checkInDate = new SimpleDateFormat("dd/MM/yyyy").parse(input1);
                checkOutDate = new SimpleDateFormat("dd/mm/yyyy").parse(input2);
                List<IRoom> rooms = (List<IRoom>) ReservationService.findRooms(checkInDate,checkOutDate);

                for(IRoom room : rooms){
                    System.out.println(room);
                }
                break;
            }
            catch(Exception e)
            {
                System.out.println("Please entre a valid CheckIn and Checkout Date");
            }

        }


    }

    private static void adminFlow(Scanner scan) {
        while(true){
            for(String option : AdminMenuOptions){
                System.out.println(option);
            }
            System.out.println("Please Select a number for the menu option");
            try{
                String userInput = scan.nextLine();
                switch (userInput){
                    case "1":
                         List<Customer> customers = (List<Customer>) adminResource.getAllCustomers();
                         for(Customer customer : customers){
                             System.out.println(customer);
                         }
                        break;
                    case "2":
                        List<IRoom> rooms = (List<IRoom>) adminResource.getAllRooms();
                        for(IRoom room : rooms){
                            System.out.println(room);
                        }
                        break;
                    case "3":
                        System.out.println("Please entre your email");
                        while(true){
                            String email = scan.nextLine();
                            if(!Customer.isValidEmail(email)){
                                System.out.println("please entre a valid email address ex someone@domain.com");
                            }
                            else{
                                List<Reservation> reservations = (List<Reservation>) hotelResource.getCustomerReservation(email);
                                for(Reservation reservation : reservations){
                                    System.out.println(reservation);
                                }
                                break;
                            }
                        }
                        break;
                    case "4":
                        while(true){
                            roomCreationRoutine();
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
                        break;
                    case "5":
                        return;
                    default:
                        System.out.println("Please enter a valid number between 1 and 5");
                        continue;
                }
            }
            catch (Exception ex){

            }
        }
    }

    private static void roomCreationRoutine() {
        Scanner scan = new Scanner(System.in);
        String roomNumber;
        Double price;
        RoomType roomType;
        while(true){
            System.out.println("please add room number");
            roomNumber = scan.nextLine();
            IRoom room = ReservationService.getARoom(roomNumber);

            if(room != null){
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
        ReservationService.addRoom(room);
    }

    private static void createNewCustomer(Scanner scan){
        while(true){
            System.out.println("Entre email formate someone@domain.com");
            String email = scan.nextLine();
            System.out.println(email);
            System.out.println("Entre first Name");
            String firstName = scan.nextLine();
            System.out.println("Entre last Name");
            String lastName = scan.nextLine();
            try{
                CustomerServiceClass.addCustomer(email,firstName,lastName);
                System.out.println("account created successfully!!");
                break;
            }
            catch (Exception ex){
                System.out.println("Please entre a valid email formate");
            }
        }
    }
}
