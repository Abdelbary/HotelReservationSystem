import Menus.AdminMenu;
import Menus.MainMenu;

import java.util.Scanner;

public class HotelApp {
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
                        MainMenu.findAndReserveRotuine();
                        break;
                    case "2":
                        MainMenu.getCustomerReservationRotuine();
                        break;
                    case "3":
                        MainMenu.createNewCustomer();
                        break;
                    case "4":
                        adminFlow();
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





    private static void adminFlow() {
        Scanner scan = new Scanner(System.in);
        while(true){
            for(String option : AdminMenuOptions){
                System.out.println(option);
            }
            System.out.println("Please Select a number for the menu option");
            try{
                String userInput = scan.nextLine();
                switch (userInput){
                    case "1":
                        AdminMenu.printAllCustomers();
                        break;
                    case "2":
                        AdminMenu.printAllRooms();
                        break;
                    case "3":
                        AdminMenu.displayAllReservations();
                        break;
                    case "4":
                       AdminMenu.roomCreationRoutine();
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



}
