import java.util.Scanner;

public class Register {
    private final Denomination[] denominations;
    private final ChangeCalculator calculator;

    public Register() {
        // Initialize the array of denominations in descending order
        denominations = new Denomination[]{
                new Denomination("One Hundred Dollar Note", 100.0, "bill", "100_dollar_bill.png"),
                new Denomination("Fifty-Dollar Note", 50.0, "bill", "50_dollar_bill.png"),
                new Denomination("Twenty-Dollar Note", 20.0, "bill", "20_dollar_bill.png"),
                new Denomination("Ten-Dollar Note", 10.0, "bill", "10_dollar_bill.png"),
                new Denomination("Five-Dollar Note", 5.0, "bill", "5_dollar_bill.png"),
                new Denomination("One-Dollar Note", 1.0, "bill", "1_dollar_bill.png"),
                new Denomination("Quarter", 0.25, "coin", "25_cent.png"),
                new Denomination("Dime", 0.10, "coin", "10_cent.png"),
                new Denomination("Nickel", 0.05, "coin", "05_cent.png"),
                new Denomination("Penny", 0.01, "coin", "01_cent.png")
        };
        // Initialize with standard calculator
        this.calculator = new StandardChangeCalculator(denominations);
    }

    // Create a purse that makes change for a given amount
    public Purse makeChange(double amt) {
        return calculator.calculateChange(amt);
    }

    // Getter for the denominations array
    public Denomination[] getDenominations() {
        return denominations;
    }

    public static void main(String[] args) {
        // Create the register object
        Register register = new Register();

        // Create the scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Ask the user to input the amount for making change
        System.out.print("Please enter the amount in the purse: ");
        double amount = scanner.nextDouble();

        // Generate and display the purse with the change
        Purse purse = register.makeChange(amount);
        System.out.println(purse);

        // Close the scanner
        scanner.close();
    }
}