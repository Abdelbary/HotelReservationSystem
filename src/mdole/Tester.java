package mdole;

public class Tester {
    public static void main(String[] args) {
    Customer customer = new Customer("first","last","fist.last@gmial.com");
    System.out.println(customer);

    Customer customer2 = new Customer("first","last","gmial.com");
    System.out.println(customer2);

    }
}
