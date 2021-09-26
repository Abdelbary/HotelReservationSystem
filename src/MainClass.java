import Service.CustomerServiceClass;

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
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);

        while(true){
            for(String option : MainMenuOptions){
                System.out.println(option);
            }
            System.out.println("Please Select a number for the menu option");
            try{
                String userInput = scan.nextLine();
                switch (userInput){
                    case "1":
                        break;
                    case "2":
                        break;
                    case "3":
                        createNewCustomer(scan);
                        break;
                    case "4":
                        break;
                    case "5":
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
                break;
            }
            catch (Exception ex){
                System.out.println("Please entre a valid email formate");
            }
        }
    }
}
