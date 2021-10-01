package Menus;

import api.HotelResource;
import mdole.Customer;
import mdole.IRoom;
import mdole.Reservation;

import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.ResolverStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MainMenu {
    private static HotelResource hotelResource = new HotelResource();
    private static final DateTimeFormatter dateFormatter = new DateTimeFormatterBuilder()
            .parseStrict()
            .appendPattern("MM/dd/uuuu")
            .toFormatter()
            .withResolverStyle(ResolverStyle.STRICT);

    public static boolean isValidDate(String date){
        try {
            LocalDate.parse(date,dateFormatter);
            return true;
        }
        catch (Exception ex){
            System.out.print(ex.getMessage()+"\n");
            return false;
        }
    }
    public static void findAndReserveRotuine() {
        Scanner scan = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/uuuu");
        sdf.setLenient(false);
        Date checkInDate, checkOutDate;
        IRoom reservationRoom = null;
        String email = null;
        Customer customer = null;
        boolean run = true;
        while(run){
            try{
                System.out.println("Entre check in Date example 01/02/2020");
                String input1 = scan.nextLine();
                String input2 = scan.nextLine();
                if(!isValidDate(input1) || !isValidDate(input2)){

                    continue;
                }
//                try{
//                    sdf.parse(input1);
//                    sdf.parse(input2);
//                }catch (Exception ex){
//                    System.out.println("Please entre a valid dates");
//                    continue;
//                }

                checkInDate = new SimpleDateFormat("dd/mm/yyyy").parse(input1);
                checkOutDate = new SimpleDateFormat("dd/mm/yyyy").parse(input2);

                if(checkInDate.after(checkOutDate)){
                    System.out.println("CheckIn Date should be before checkout date!!");
                    continue;
                }
                List<IRoom> rooms = (List<IRoom>)   hotelResource.findARoom(checkInDate,checkOutDate);

                for(IRoom room : rooms){
                    System.out.println(room);
                }
                if(rooms.isEmpty()){
                    System.out.println("No Avialbale Room at the given dates");
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(checkInDate);
                    cal.add(Calendar.DAY_OF_WEEK, 7);
                    checkInDate = cal.getTime();
                    cal.setTime(checkOutDate);
                    cal.add(Calendar.DAY_OF_WEEK, 7);
                    checkOutDate = cal.getTime();
                    rooms = (List<IRoom>)   hotelResource.findARoom(checkInDate,checkOutDate);

                    if(!rooms.isEmpty()){
                        System.out.println("other Rooms available starting from" +checkInDate);

                        for(IRoom room : rooms){
                            System.out.println(room);
                        }
                    }else{
                        break;
                    }


                }
                System.out.println("Please choose a room number");
                run = true;
                while(run){
                    try {
                        String roomNum = scan.nextLine();

                        for(IRoom room : rooms){
                            if(roomNum.compareTo(room.getRoomNumber()) == 0){
                                reservationRoom = room;
                                run =false;
                                break;
                            }
                        }
                        if(run != false){
                            throw new IllegalArgumentException();
                        }
                    }catch (Exception ex){
                        System.out.println("Please entre a valid room number");
                    }
                }
                while(true){
                    System.out.println("Do you have account wiht us? y/n");
                    String option = scan.nextLine();
                    if(option.compareTo("y") != 0 && option.compareTo("n") != 0){
                        System.out.println("please entre y/n");
                        continue;
                    }
                    else{
                        if(option.compareTo("y") == 0){
                            while(true) {
                                System.out.println("Please Entre your email.");
                                email = scan.nextLine();
                                if (Customer.isValidEmail(email)) {
                                    break;
                                } else {
                                    System.out.print("Please entre a valid email formate..someone@domain.com");
                                }
                            }
                        }else{
                            email = createNewCustomer();
                        }
                        hotelResource.bookARoom(email,reservationRoom,checkInDate,checkOutDate);
                        System.out.println("Your Reservation added Successfully!!");
                        break;
                    }
                }

                break;
            }
            catch(Exception e)
            {
                System.out.println("Please entre a valid CheckIn and Checkout Date");
            }

        }


    }

    public static void getCustomerReservationRotuine() {
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

        List<Reservation> reservations = (List<Reservation>) hotelResource.getCustomerReservation(email);

        for(Reservation reservation : reservations){
            System.out.println(reservation);
        }

    }

    public static String createNewCustomer(){
        Scanner scan = new Scanner(System.in);
        String email = null;
        while(true){
            System.out.println("Entre email formate someone@domain.com");
            email = scan.nextLine();
            if(hotelResource.getCustomer(email) != null){
                System.out.println("This email is already taken please try another one!!");
                continue;
            }
            System.out.println("Entre first Name");
            String firstName = scan.nextLine();
            System.out.println("Entre last Name");
            String lastName = scan.nextLine();

            try{
                hotelResource.creatACustomer(email,firstName,lastName);
                System.out.println("account created successfully!!");
                break;
            }
            catch (Exception ex){
                System.out.println("Please entre a valid email formate");
            }
        }
        return email;
    }

}
